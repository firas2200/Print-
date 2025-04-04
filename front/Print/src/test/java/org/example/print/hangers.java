package org.example.print;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
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
import java.nio.file.Path;

@RestController
public class hangers {

    @GetMapping({"/Hangers/{prefix}/{startNumber}/{endNumber}"})
    public ResponseEntity<byte[]> generateStickersPDF(
            @PathVariable(required = false) String prefix,
            @PathVariable int startNumber,
            @PathVariable(required = false) Integer endNumber) {
        try {
            // Create PDF in memory
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, pdf.getDefaultPageSize());

            // Set document margins (conversion from cm to points)
            float topMargin = 1.5f * 28.35f;
            float bottomMargin = 0.53f * 28.35f;
            float leftMargin = 0.2f * 28.35f;
            float rightMargin = 0.5f * 28.35f;
            document.setMargins(topMargin, rightMargin, bottomMargin, leftMargin);

            // Define cell dimensions and spacing (in points)
            float cellWidth = 155;     // cell width
            float cellHeight = 69;     // cell height
            float spaceRight = 12;     // space to the right
            float spaceDown = 30;      // space at the bottom

            int columns = 5; // 5 cells per row
            int rows = 13;   // 13 rows per page (65 stickers per page)
            int totalTickets = columns * rows;

            if (endNumber == null) {
                endNumber = startNumber + totalTickets - 1;
            }

            // Create table with fixed column widths
            Table table = new Table(new float[]{cellWidth, cellWidth, cellWidth, cellWidth, cellWidth});
            table.setFixedLayout();
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

            int currentNumber = startNumber;
            for (int i = 0; i < totalTickets && currentNumber <= endNumber; i++) {
                String ticketCode = (prefix != null ? prefix : "") + String.format("%03d", currentNumber);
                currentNumber++;

                // Generate barcode image
                Image barcodeImage = generateBarcode(ticketCode);
                // Optionally adjust barcode size relative to cell size
                barcodeImage.setWidth(cellWidth * 0.9f);
                barcodeImage.setHeight(cellHeight * 0.5f);

                // Create text element
                Paragraph text = new Paragraph(ticketCode)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(21);

                // Use a Div container to add spacing inside the cell
                Div container = new Div();
                container.add(barcodeImage);
                container.add(text);
                container.setMarginRight(spaceRight);
                container.setMarginBottom(spaceDown);

                // Create cell with fixed dimensions and no extra padding/margins
                Cell cell = new Cell()
                        .add(container)
                        .setWidth(cellWidth)
                        .setHeight(cellHeight)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setPadding(0)
                        .setBorder(null);

                table.addCell(cell);
            }

            document.add(table);
            document.close();

            // Return the PDF as an HTTP response
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





















/*package org.example.print;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
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
import java.nio.file.Path;

@RestController
public class hangers {

    @GetMapping({  "/Hangers/{prefix}/{startNumber}/{endNumber}"})
    public ResponseEntity<byte[]> generateStickersPDF(
            @PathVariable(required = false) String prefix,
            @PathVariable int startNumber,
            @PathVariable(required = false) Integer endNumber) {
        try {
            // Create PDF in memory
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, pdf.getDefaultPageSize());            // Convert cm to points
            float topMargin = 1.5f * 28.35f;     // ~42.5 pt
            float bottomMargin = 0.53f * 28.35f;   // ~15 pt
            float leftMargin = 0.2f * 28.35f;      // ~5.7 pt
            float rightMargin = 0.5f * 28.35f;     // ~14.2 pt
            document.setMargins(topMargin, rightMargin, bottomMargin, leftMargin);
// Add a header event to reserve a header area of 1.47 cm (~41.7 pt)
            float headerHeight = 1.47f * 25.35f;   // ~41.7 pt
            pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new Container.HeaderEventHandler(headerHeight));

            int columns = 5; // Five stickers per row
            int rows = 13; // 13 rows per page (Total 65 stickers per page)
            int totalTickets = columns * rows;

            // If no end number is provided, generate only 65 stickers from startNumber
            if (endNumber == null) {
                endNumber = startNumber + totalTickets - 1;
            }

            // Create table
            Table table = new Table(columns)
                    .useAllAvailableWidth()  // Ensure it fits A4 width
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);

            int currentNumber = startNumber;
            for (int i = 0; i < totalTickets && currentNumber <= endNumber; i++) {
                String ticketCode = (prefix != null ? prefix : "") + String.format("%03d", currentNumber);
                currentNumber++;
                float barcodeWidth = 155; // 8 cm (80mm)
                float barcodeHeight = 15;
                // Generate barcode image
                Image barcodeImage = generateBarcode(ticketCode);
                // Adjust barcode size
                barcodeImage.setWidth(barcodeWidth);
                barcodeImage.setHeight(barcodeHeight);
                barcodeImage.setMarginLeft(8);



                // Create text below barcode
                Paragraph text = new Paragraph(ticketCode)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(-4)
                        .setMarginLeft(7)
                        .setFontSize(21);


                // Create cell with barcode and text
                Cell cell = new Cell()
                        .add(barcodeImage)
                        .add(text)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setPadding(8)
                        .setMarginTop(2)
                        .setMarginLeft(2)
                        .setBorder(null);

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
}*/
