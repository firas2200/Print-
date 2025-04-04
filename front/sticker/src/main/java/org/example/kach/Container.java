package org.example.kach;

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
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.nio.file.Path;

@RestController
public class Container {

    @GetMapping("/container/{prefix}/{suffix}")
    public ResponseEntity<byte[]> generateBarcodePDF(
            @PathVariable String prefix,
            @PathVariable String suffix) {
        try {
            // Create PDF in memory
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            float topMargin = 1.2f * 27.35f;     // ~42.5
            document.setMargins(topMargin,12,10,19);

            int columns = 5; // 5 columns per row
            int rows = 13; // 13 rows per page (Total 35 barcodes per page)
            Table table = new Table(columns).useAllAvailableWidth().setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Generate barcode values (AA00, AB00, AC00, etc.)
            char firstLetter = Character.toUpperCase(prefix.charAt(0));
            char secondLetter = 'A';  // Start from 'A'

            for (int i = 0; i < columns * rows; i++) {
                var  barcodeValue = "" + firstLetter + secondLetter + suffix.toUpperCase();

                // Move to next letter (A → B → C ...)
                if ((i + 1) % 5 == 0) {
                    if (secondLetter < 'Z') {
                        secondLetter++;
                    } else {
                        secondLetter = 'A';
                        firstLetter++;
                    }
                }

                // Create text below barcode
                Paragraph text = new Paragraph(barcodeValue)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(-6)
                        .setMarginRight(5)
                        .setFontSize(26);
                // Generate barcode image
                float barcodeWidth = 122        ;
                float barcodeHeight = 16;

                Image barcodeImage = generateBarcode(barcodeValue);
                barcodeImage.setWidth(barcodeWidth);
                barcodeImage.setHeight(barcodeHeight);
                barcodeImage.setTextAlignment(TextAlignment.CENTER);
                barcodeImage.setMarginLeft(-6);



                // Create cell with barcode and text
                Cell cell = new Cell()
                        .setPaddingBottom(10)
                        .setPaddingRight(-2)
                        .setPaddingLeft(-4)
                        .add(barcodeImage)
                        .add(text)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER);

                table.addCell(cell);
            }

            // Add table to document
            document.add(table);
            document.close();

            // Convert PDF to byte array and return as HTTP response
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=barcode_grid.pdf")
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(("Error generating PDF: " + e.getMessage()).getBytes());
        }
    }
    private static Image generateBarcode(String text) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, 150, 50);
        Path tempImagePath = java.nio.file.Files.createTempFile("barcode", ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempImagePath);
        ImageData imageData = ImageDataFactory.create(tempImagePath.toUri().toURL());
        return new Image(imageData);
    }
}
