package org.example.print;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/Personaliser")
public class perso {

    /**
     * Endpoint 1 (Single code): /Personaliser/{code}/{times}
     * e.g. GET /Personaliser/ABC123/5
     */
    @GetMapping("/{code}/{times}")
    public ResponseEntity<byte[]> generateSinglePersonaliserPDF(
            @PathVariable String code,
            @PathVariable int times) {
        try {
            // Generate PDF with the single code repeated 'times'
            byte[] pdfBytes = createPdfForCodes(List.of(code), List.of(times));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=personaliser.pdf")
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Endpoint 2 (Multi-code): /Personaliser/print?code=abc&times=3&code=xyz&times=4
     * e.g. GET /Personaliser/print?code=ABC123&times=2&code=DEF456&times=3
     *
     * This will handle multiple pairs of (code, times).
     */
    @GetMapping("/print")
    public ResponseEntity<byte[]> generateMultiPersonaliserPDF(
            @RequestParam("code") List<String> codes,
            @RequestParam("times") List<Integer> times) {
        try {
            // Generate PDF with each code repeated according to each times
            byte[] pdfBytes = createPdfForCodes(codes, times);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=personaliser.pdf")
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Common method to create a PDF for a list of codes + parallel list of times.
     */
    private byte[] createPdfForCodes(List<String> codes, List<Integer> times) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Margins
        float topMargin = 36;        // 0.5 inch
        float bottomMargin = 36;     // 0.5 inch
        float leftMargin = 36;       // 0.5 inch
        float rightMargin = 300;
        document.setMargins(topMargin, rightMargin, bottomMargin, leftMargin);

        // Table layout
        int columns = 1;  // 5 stickers per row
        int rows = 13;    // 13 rows per page
        int totalSlots = columns * rows;

        Table table = new Table(UnitValue.createPercentArray(columns))
                .useAllAvailableWidth()
                .setHorizontalAlignment(HorizontalAlignment.CENTER);

        // We'll fill the table cell by cell, cycling through codes.
        int cellCount = 0;

        // Loop over each code & times in parallel
        for (int i = 0; i < codes.size(); i++) {
            String codeVal = codes.get(i);
            int repeatCount = times.get(i);

            // For each code, repeat 'repeatCount' times
            for (int r = 0; r < repeatCount; r++) {
                // If table is full, add it to doc, then start a new page
                if (cellCount >= totalSlots) {
                    document.add(table);
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

                    // create a fresh table
                    table = new Table(UnitValue.createPercentArray(columns))
                            .useAllAvailableWidth()
                            .setHorizontalAlignment(HorizontalAlignment.CENTER);
                    cellCount = 0;
                }

                // Make the text
                Paragraph text = new Paragraph(codeVal)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setFontSize(26)
                        .setMarginRight(160)
                        .setMarginTop(-10);

                // Generate barcode
                float barcodeWidth = 129;
                float barcodeHeight = 17;
                Image barcodeImage = generateBarcode(codeVal);
                barcodeImage.setWidth(barcodeWidth);
                barcodeImage.setHeight(barcodeHeight);
                barcodeImage.setTextAlignment(TextAlignment.CENTER);
                barcodeImage.setMarginLeft(-20);

                // Create cell
                Cell cell = new Cell()
                        .setMarginTop(30)
                        .setWidth(barcodeWidth)
                        .setMarginLeft(-6)
                        .setPaddingBottom(11)
                        .add(barcodeImage)
                        .add(text)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER)
                        .setPadding(6);

                table.addCell(cell);
                cellCount++;
            }
        }

        // Add remaining cells if any
        if (cellCount > 0) {
            document.add(table);
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Helper method to generate a CODE_128 barcode from text
     */
    private static Image generateBarcode(String text) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, 150, 50);
        Path tempImagePath = java.nio.file.Files.createTempFile("barcode", ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempImagePath);
        ImageData imageData = ImageDataFactory.create(tempImagePath.toUri().toURL());
        return new Image(imageData);
    }
}
