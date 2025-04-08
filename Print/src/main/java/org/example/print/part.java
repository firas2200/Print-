package org.example.print;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageData;
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
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Personaliser")
public class part {

    /**
     * Example requests:
     *
     * 1. GET /part/print?code=ABC&code=DEF&times=3
     *    -> "ABC" repeated 3 times and "DEF" repeated 3 times.
     *
     * 2. GET /part/print?code=ABC&times=2&code=DEF&times=3
     *    -> "ABC" repeated 2 times and "DEF" repeated 3 times.
     *
     * This endpoint generates a PDF with a single column of barcodes.
     */
    @GetMapping("/print")
    public ResponseEntity<byte[]> generateStickersPDF(
            @RequestParam(name = "code") List<String> codes,
            @RequestParam(name = "times") List<Integer> times
    ) {
        try {
            // If only one "times" is provided, apply it to every code.
            if (times.size() == 1 && codes.size() > 1) {
                int singleTime = times.get(0);
                List<Integer> newTimes = new ArrayList<>();
                for (int i = 0; i < codes.size(); i++) {
                    newTimes.add(singleTime);
                }
                times = newTimes;
            }
            // Ensure we have the same number of codes and times.
            if (codes.size() != times.size()) {
                return ResponseEntity.badRequest()
                        .body("Mismatch: you must provide the same number of 'code' and 'times' parameters."
                                .getBytes());
            }

            // Build a list of codes to print, repeating each code as specified.
            List<String> allCodes = new ArrayList<>();
            for (int i = 0; i < codes.size(); i++) {
                String c = codes.get(i).trim();
                int repeatCount = times.get(i);

                if (c.isEmpty() || repeatCount <= 0) {
                    return ResponseEntity.badRequest()
                            .body(("Invalid input: code='" + c + "', times=" + repeatCount).getBytes());
                }

                for (int j = 0; j < repeatCount; j++) {
                    allCodes.add(c);
                }
            }

            // Prepare an in-memory PDF.
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Create a single-column table (100% width).
            Table table = new Table(UnitValue.createPercentArray(1)) ;
            table.setWidth(170 * 72f / 96f );




            // For each code, generate a barcode and add it to the table.
            for (String code : allCodes) {
                Image barcodeImage = generateBarcode(code);
                barcodeImage.setWidth(139); // adjust as needed
                barcodeImage.setHeight(16); // adjust as needed
                barcodeImage.setMarginTop(3);
                //barcodeImage.setMarginLeft (-20) ;
                PdfFont arialBlack = PdfFontFactory.createFont();
                Paragraph text = new Paragraph(code)
                        .setBold()
                        .setFont(arialBlack)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(20);

                Cell cell = new Cell()
                        .add(barcodeImage)
                        .add(text)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setPadding(4)
                        .setBorder(new SolidBorder(1));

                table.addCell(cell);

            }

            document.add(table);
            document.close();

            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=part.pdf")
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(("Error generating PDF: " + e.getMessage()).getBytes());
        }
    }

    /**
     * Helper method to generate a CODE_128 barcode from text.
     */
    private Image generateBarcode(String text) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(text, BarcodeFormat.CODE_128, 150, 50);
        Path tempImagePath = Files.createTempFile("barcode", ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempImagePath);
        ImageData imageData = ImageDataFactory.create(tempImagePath.toUri().toURL());
        return new Image(imageData);
    }
}
