package org.example.kach.controller.ParID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;


import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
//import jakarta.persistence.Table;
import org.example.kach.Entity.PartId.PartIDData;
import org.example.kach.Service.PartID.PartIDDataServiceInterface;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/partiddata")
public class PartIDDataController {

    private final PartIDDataServiceInterface service;

    public PartIDDataController(PartIDDataServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<PartIDData> getAllParts() {
        return service.getAllParts();
    }

    @GetMapping("/title/{id}")
    public ResponseEntity<String> getTitleByPartId(@PathVariable String id) {
        Optional<PartIDData> part = service.getPartById(id);
        return part.map(value -> ResponseEntity.ok(value.getTitle()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/title/sticker/{id}")
    public ResponseEntity<byte[]> getTitleSticker(@PathVariable String id) {
        Optional<PartIDData> partOpt = service.getPartById(id);
        if (partOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PartIDData part = partOpt.get();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // 1) Create PDF Document in memory
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
// Create a table with two columns: left and right (each 50% width)
            Table table = new Table(new float[]{50F, 50F})
                    .useAllAvailableWidth();

            // --- Top-left cell: Part ID
            Cell leftTop = new Cell()
                    .add(new Paragraph(part.getPartId() != null ? part.getPartId() : "")
                            .setBold()
                            .setFontSize(18)
                            .setMarginLeft(25)
                    )
                    .setBorder(Border.NO_BORDER);
            table.addCell(leftTop);

            // --- Top-right cell: Title
            Cell rightCell  = new Cell(2,1)
                    .add(new Paragraph(part.getTitle() != null ? part.getTitle() : "")

                            .setFontSize(10)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setMarginRight(398)
                            .setMaxWidth(55)
                    )
                    .setBorder(Border.NO_BORDER);
            table.addCell(rightCell );

            // --- Bottom-left cell: Barcode
            Image barcodeImage = generateBarcodeImage(part.getPartId());
            // Adjust these as needed to fit your layout
            barcodeImage.setWidth(110);
            barcodeImage.setHeight(17);
            barcodeImage.setMarginTop(-10);


            Cell leftBottom  = new Cell()
                    .add(barcodeImage)
                    .setMarginTop(-5)
                    .setBorder(Border.NO_BORDER);

            table.addCell(leftBottom );




            // 3) Add the table to the document
            document.add(table);


            // 4) Close document
            document.close();

            // 5) Return PDF as response
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=title-sticker.pdf")
                    .body(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Helper method to generate barcode image using ZXing.
     */
    private Image generateBarcodeImage(String text) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, 300, 100);
        Path tempFile = Files.createTempFile("barcode-", ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempFile);
        return new Image(ImageDataFactory.create(tempFile.toUri().toURL()));
    }






















/*
    @GetMapping("/{id}")
    public ResponseEntity<PartIDData> getPartById(@PathVariable String id) {
        Optional<PartIDData> part = service.getPartById(id);
        return part.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PartIDData createPart(@RequestBody PartIDData part) {
        return service.savePart(part);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable String id) {
        service.deletePart(id);
        return ResponseEntity.noContent().build();
    }*/
}
