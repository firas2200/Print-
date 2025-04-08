package org.example.print;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;

@RestController
public class Arrows {
    private static final String DOWN_ARROW_PATH = "C:\\Users\\TN-100\\Downloads\\down-arrow.png";  // Update with actual path
    private static final String SIDE_ARROW_PATH = "C:\\Users\\TN-100\\Downloads\\next.png";  // Update with actual path
    @GetMapping("/arrows/down")
    public ResponseEntity<byte[]> generateArrowDownPDF() {
        return generateArrowPDF(DOWN_ARROW_PATH, "arrow_down.pdf");
    }
    @GetMapping("arrows/side")
    public ResponseEntity<byte[]> generateArrowSidePDF() {
        return generateArrowPDF(SIDE_ARROW_PATH, "arrow_side.pdf");
    }
    private ResponseEntity<byte[]> generateArrowPDF(String imagePath, String fileName) {
        try {
            // Create PDF in memory
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            int columns = 5; // 5 columns per row
            int rows = 13; // 7 rows per page
            Table table = new Table(columns).useAllAvailableWidth();

            // Check if the image file exists
            File file = new File(imagePath);
            if (!file.exists()) {
                return ResponseEntity.badRequest().body(("Image not found: " + imagePath).getBytes());
            }

            ImageData imageData = ImageDataFactory.create(imagePath);

            for (int i = 0; i < columns * rows; i++) {
                Image arrowImage = new Image(imageData);
                arrowImage.scaleToFit(51, 51);  // Adjust size as needed

                Cell cell = new Cell()
                        .add(arrowImage)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setPaddingLeft(25)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER);

                table.addCell(cell);
            }

            document.add(table);
            document.close();

            // Convert PDF to byte array and return as HTTP response
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(("Error generating PDF: " + e.getMessage()).getBytes());
        }
    }
}
