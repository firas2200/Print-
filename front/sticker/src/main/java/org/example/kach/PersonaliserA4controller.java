package org.example.kach;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/PersonaliserA4")
public class PersonaliserA4controller {

    /**
     * Endpoint to generate a Personaliser PDF in A4 layout.
     * Expects multiple code and times pairs as query parameters.
     * Example:
     *   GET /PersonaliserA4/print?code=ABC&times=2&code=XYZ&times=3
     */
    @GetMapping("/print")
    public ResponseEntity<byte[]> generatePersonaliserA4PDF(
            @RequestParam("code") List<String> codes,
            @RequestParam("times") List<Integer> times) {
        try {
            byte[] pdfBytes = createPdfForCodes(codes, times);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=personaliserA4.pdf")
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Creates a PDF with a grid layout of 5 columns and 13 rows (65 cells per page).
     * Each cell shows the barcode and text for a given code.
     */
    private byte[] createPdfForCodes(List<String> codes, List<Integer> times) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Set margins for an A4 page (adjust as needed)
        document.setMargins(10, 12, 10, 19); // top, right, bottom, left

        // Define the grid layout: 5 columns and 13 rows (65 cells per page)
        int columns = 5;
        int rows = 13;
        int totalSlots = columns * rows;

        Table table = new Table(UnitValue.createPercentArray(columns))
                .useAllAvailableWidth()
                .setHorizontalAlignment(HorizontalAlignment.CENTER);

        int cellCount = 0;

        // Loop over each code/time pair and repeat the code the specified number of times
        for (int i = 0; i < codes.size(); i++) {
            String codeVal = codes.get(i);
            int repeatCount = times.get(i);

            for (int r = 0; r < repeatCount; r++) {
                // When the current page is full, add the table and start a new page
                if (cellCount >= totalSlots) {
                    document.add(table);
                    document.add(new AreaBreak());
                    table = new Table(UnitValue.createPercentArray(columns))
                            .useAllAvailableWidth()
                            .setHorizontalAlignment(HorizontalAlignment.CENTER);
                    cellCount = 0;
                }

                // Generate barcode image for the code
                Image barcodeImage = generateBarcode(codeVal);
                barcodeImage.setWidth(125)  ;
                barcodeImage.setHeight(16) ;
                barcodeImage.setTextAlignment(TextAlignment.CENTER);
                barcodeImage.setMarginRight(6) ;
                barcodeImage.setMarginLeft(-8) ;
                // Create a paragraph for the code text
                Paragraph codeText = new Paragraph(codeVal)
                        .setBold()
                        .setFontSize(26)
                        .setMarginTop(-7)
                        .setMarginLeft(-18)
                        .setTextAlignment(TextAlignment.CENTER);

                // Create a cell that holds both the barcode image and the text
                Cell cell = new Cell()
                        .setBorder(Border.NO_BORDER)
                        .setPadding(-2)
                        .setPaddingTop(17)
                        .add(barcodeImage)
                        .add(codeText)
                        .setTextAlignment(TextAlignment.CENTER);

                table.addCell(cell);
                cellCount++;
            }
        }

        // Optionally, fill any remaining cells to complete the grid
        while (cellCount < totalSlots) {
            table.addCell(new Cell().setBorder(Border.NO_BORDER));
            cellCount++;
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }

    /**
     * Helper method to generate a CODE_128 barcode from text.
     */
    private static Image generateBarcode(String text) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, 150, 50);
        Path tempFile = java.nio.file.Files.createTempFile("barcode", ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempFile);
        ImageData imageData = ImageDataFactory.create(tempFile.toUri().toURL());
        return new Image(imageData);
    }
}
