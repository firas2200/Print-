package org.example.print;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class PDFCreator {
    public static void main(String[] args) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("GeneratedPDF.pdf"));
            document.open();
            document.add(new Paragraph("Hello, Bruv! This is your first PDF generated using Java."));
            document.close();
            System.out.println("PDF created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
