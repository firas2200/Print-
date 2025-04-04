package org.example.print;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class BarcodePDFGenerator {
    private static final int TOTAL_CELLS = 65; // Number of barcodes per A4 page

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the starting number for the barcodes: ");
        int startNumber = scanner.nextInt();
        scanner.close();

        String pdfFilePath = "Generated Barcodes.pdf";

        try {
            PdfWriter writer = new PdfWriter(pdfFilePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (int i = 0; i < TOTAL_CELLS; i++) {
                int barcodeNumber = startNumber + i;
                String barcodeValue = String.valueOf(barcodeNumber);
                Image barcodeImage = generateBarcode(barcodeValue);
                document.add(barcodeImage);
            }

            document.close();
            System.out.println("PDF with barcodes generated: " + pdfFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Image generateBarcode(String text) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, 150, 50);
        Path tempImagePath = Files.createTempFile("barcode", ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempImagePath);
        ImageData imageData = ImageDataFactory.create(tempImagePath.toUri().toURL());
        return new Image(imageData);
    }
}
