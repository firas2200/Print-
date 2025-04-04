
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
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.nio.file.Path;

@RestController
public class Hangers {
    @GetMapping("/Hangers/{suffix}/{startNumber}/{endNumber}")
    public ResponseEntity<byte[]> generateStickersPDF(
            @PathVariable int startNumber,
            @PathVariable int endNumber,
            @PathVariable String suffix) {
        try {
            // Create PDF in memory
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            float topMargin = 1.2f * 27.35f;     // ~42.5 pt
            //  float bottomMargin = 0.53f * 28.35f;   // ~15 pt
            //   float leftMargin = 0.2f * 28.35f;      // ~5.7 pt
            //   float rightMargin = 0.5f * 28.35f;     // ~14.2 pt
            document.setMargins(topMargin,12,10,19);
            int columns = 5; // Five stickers per row
            int rows = 13; // 13 rows per page (Total 65 stickers per page)
            int totalTickets = columns * rows;
            // Create table
            Table table = new Table(UnitValue.createPercentArray(columns))
                    .useAllAvailableWidth()  // Ensure it fits A4 width
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            int currentNumber = startNumber;
            for (int i = 0; i < totalTickets && currentNumber <= endNumber; i++) {
                String ticketCode = suffix + String.format("%03d", currentNumber) ; // Add suffix at the start
                currentNumber++;
                // Create text below barcode
                Paragraph text = new Paragraph(ticketCode)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setFontSize(26)
                        .setMarginRight(18)
                        .setMarginTop(-10);
                // Generate barcode image
                float barcodeWidth = 125; // 8 cm (80mm)
                float barcodeHeight = 16;
                Image barcodeImage = generateBarcode(ticketCode);
                barcodeImage.setWidth(barcodeWidth);
                barcodeImage.setHeight(barcodeHeight);
                barcodeImage.setTextAlignment(TextAlignment.CENTER);
                barcodeImage.setMarginRight(6) ;
                barcodeImage.setMarginLeft(-10);
                // Adjust barcode size
                // Create cell with barcode and text
                Cell cell = new Cell()
                        .setPadding(6)
                        .setMarginLeft(4)
                        .setPaddingBottom(10)
                        .setPaddingRight(-1)
                        .setPaddingLeft(-4)
                        .add(barcodeImage)
                        .add(text)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER);
                // Add the cell to the table
                table.addCell(cell);
            }
            // Add table to document
            document.add(table);
            document.close();
            // Convert PDF to byte array and return as HTTP response
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=stickers.pdf")
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
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
