package org.example.print;




import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class ItemCodes {

    @GetMapping("/Item/barcode/{startNumber}")
    public ResponseEntity<byte[]> generateStickersPDF(@PathVariable int startNumber) {
        try {
            // Create PDF in memory with A4 size
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, pdf.getDefaultPageSize());

            // Set margins: top = 1.2 cm, right = 0.3 cm, bottom = 30pt (default), left = 0.9 cm
            float topMargin = 1f * 28.35f;   // ~34.02 points
            float leftMargin = 0.9f * 28.35f;   // ~25.5 points
            float rightMargin = 0.3f * 28.35f;  // ~8.5 points

            document.setMargins(topMargin, rightMargin, 3, leftMargin);


            int columns = 5; // 5 stickers per row
            int rows = 13; // 13 rows per page (Total 65 stickers per page)

            Table table = new Table(columns).useAllAvailableWidth()
                    .setHorizontalAlignment(HorizontalAlignment.CENTER)
                    .setWidth(UnitValue.createPercentValue(100)); // Use full width of A4

            for (int i = 0; i < rows * columns; i++) {
                int ticketNumber = startNumber + i;
                String ticketCode = String.format("%06d", ticketNumber); // Format as six-digit code
                float barcodeWidth = 96;
                float barcodeHeight = 16;
                Image barcodeImage = generateBarcode(ticketCode);
                barcodeImage.setWidth(barcodeWidth);
                barcodeImage.setHeight(barcodeHeight);
                barcodeImage.setTextAlignment(TextAlignment.CENTER);
               // barcodeImage.getPaddingLeft();
                barcodeImage.setMarginLeft(5);
                // Create text below barcode

                Paragraph text = new Paragraph(ticketCode)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setFontSize(25)
                        .setMarginRight(5)
                        .setMarginLeft(-8)
                        .setMarginTop(-6);


                // Create a cell with barcode and text
                Cell cell = new Cell()
                        .setMarginTop(25)
                        .setPaddingBottom(9)
                        .setPaddingRight(-4)
                        .setPaddingTop(5)
                        .setMarginLeft(55)
                        .add(barcodeImage)
                        .add(text)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER);
                // Add the cell to the table
                table.addCell(cell);
            }

            // Add table to the document
            document.add(table);
            document.close();

            // Convert PDF to byte array and return as HTTP response
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=stickers.pdf")
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(("Error generating PDF: " + e.getMessage()).getBytes());
        }
    }

    private static Image generateBarcode(String text) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, 800, 100);
        Path tempImagePath = Files.createTempFile("barcode", ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempImagePath);
        ImageData imageData = ImageDataFactory.create(tempImagePath.toUri().toURL());
        return new Image(imageData);
    }
}