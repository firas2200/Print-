package org.example.print.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.print.Entity.PartIDData;
import org.example.print.Entity.ListingData;
import org.example.print.Entity.VehicleData;
import org.example.print.Service.PartIDDataServiceInterface;
import org.example.print.Service.ListingDataServiceInterface;
import org.example.print.Service.VehicleDataServiceInterface;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("/api/partiddata")
public class PartIDDataController {

    private final PartIDDataServiceInterface partService;
    private final ListingDataServiceInterface listingService;
    private final VehicleDataServiceInterface vehicleService;

    public PartIDDataController(PartIDDataServiceInterface partService,
                                ListingDataServiceInterface listingService,
                                VehicleDataServiceInterface vehicleService) {
        this.partService = partService;
        this.listingService = listingService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/sticker")
    public ResponseEntity<?> getSticker(@RequestParam String id) {
        // Step 1: Fetch PartIDData using the provided id to get the title and the actual partId.
        Optional<PartIDData> partOpt = partService.getPartById(id);
        if (partOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No part found for id: " + id);
        }
        PartIDData part = partOpt.get();
        // 'actualPartId' is the unique part identifier stored in the PartIDData entity.
        String actualPartId = part.getPartId();

        // Step 2: Use the actual partId to fetch ListingData and obtain the vehicle ID.
        Optional<ListingData> listingOpt = listingService.findByPartId(actualPartId);
        if (listingOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No listing found for part id: " + actualPartId);
        }
        ListingData listing = listingOpt.get();
        Short vehicleId = listing.getVehicleId();

        // Step 3: Fetch VehicleData using the vehicle ID to get marque and model range descriptions.
        String marqueDescription = "";
        String modelRangeDescription = "";
        if (vehicleId != null) {
            Optional<VehicleData> vehicleOpt = vehicleService.findByVehicleId(vehicleId);
            if (vehicleOpt.isPresent()) {
                VehicleData vehicle = vehicleOpt.get();
                marqueDescription = vehicle.getMarqueDescription() != null ? vehicle.getMarqueDescription() : "";
                modelRangeDescription = vehicle.getModelRangeDescription() != null ? vehicle.getModelRangeDescription() : "";
            }
        }
        // Combine the vehicle descriptions into a display string.
        String displayValue = marqueDescription + (!modelRangeDescription.isEmpty() ? "\n" + modelRangeDescription : "");

        // Build the combined part and vehicle string for the sticker's left top cell.
        String partVehicleStr = actualPartId;
        if (vehicleId != null) {
            partVehicleStr += "-" + vehicleId;
        }

        try {
            // Step 4: Generate the sticker PDF using the part data, vehicle descriptions, and barcode image.
            byte[] pdfBytes = generateStickerPDF(part, displayValue, partVehicleStr);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=sticker.pdf")
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error generating PDF.");
        }
    }

    private byte[] generateStickerPDF(PartIDData part, String displayValue, String partVehicleStr) throws Exception {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Create a table with two columns for layout.
            Table table = new Table(new float[]{120F, 50F});
            table.setFixedLayout();
            table.setWidth(170 * 72f / 96f );
            table.setHeight(72 * 72f / 96f );
            table.setMarginLeft(16f);
            table.setMarginTop(9f);
            table.setBorder(new SolidBorder(1));
            // Left top cell: displays the combined part and vehicle ID.
            PdfFont arialBlack = PdfFontFactory.createFont();
            Cell leftTop = new Cell()
                    .add(new Paragraph(partVehicleStr)
                            .setFont(arialBlack)
                            .setBold()
                            .setFontSize(15)
                            .setMarginLeft(18)
                            .setMarginTop(-5))
                    .setBorderTop(Border.NO_BORDER)
                    .setBorderBottom(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER)
                    .setBorder(Border.NO_BORDER);
            table.addCell(leftTop);

            // Right cell: displays the part title and vehicle details.
            String titleText = part.getTitle() != null ? part.getTitle() : "";
            Paragraph titleParagraph = new Paragraph(titleText)
                    .setFontSize(9)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph displayParagraph = new Paragraph(displayValue)
                    .setFontSize(9)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMultipliedLeading(1.2f);;

            Cell rightCell = new Cell(2, 1)
                    .add(titleParagraph)
                    .add(displayParagraph)
                    .setBorderLeft(new SolidBorder(1))  // This ensures a vertical line on the left of the right cell.
                    .setBorderTop(Border.NO_BORDER)
                    .setBorderBottom(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER);
            table.addCell(rightCell);

            // Generate a barcode image based on the part id.
            Image barcodeImage = generateBarcodeImage(part.getPartId());
            barcodeImage.setWidth(98);
            barcodeImage.setHeight(17);
            barcodeImage.setMarginTop(2);

            // Left bottom cell: displays the barcode image.
            Cell leftBottom = new Cell()
                    .add(barcodeImage)
                    .setMarginTop(-5)
                    .setBorderTop(Border.NO_BORDER)
                    .setBorderBottom(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER)
                    .setBorderRight(new SolidBorder(1));
            table.addCell(leftBottom);

            document.add(table);
            document.close();
            return baos.toByteArray();
        }
    }

    private Image generateBarcodeImage(String text) throws Exception {
        // Create a barcode image using the CODE 128 format.
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, 300, 100);
        Path tempFile = Files.createTempFile("barcode-", ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempFile);
        return new Image(ImageDataFactory.create(tempFile.toUri().toURL()));
    }
}