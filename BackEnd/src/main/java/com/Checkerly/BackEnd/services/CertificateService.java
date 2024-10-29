package com.Checkerly.BackEnd.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Service
public class CertificateService {

    public byte[] generateCertificate(Map<String, String> participantInfo) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Certificado de Comparecimento")
                .setFontSize(18).setBold());
        document.add(new Paragraph("Certificamos que:"));
        document.add(new Paragraph(participantInfo.get("name"))
                .setFontSize(18).setBold());
        document.add(new Paragraph("Participou com sucesso no evento: " + participantInfo.get("event")));
        document.add(new Paragraph("Data: " + LocalDate.now().toString()));
        document.add(new Paragraph("Signature"));
        document.add(new Paragraph("Copyrights © 2024 Checkerly®. Todos direitos reservados"));

        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    public void saveCertificateToFile(byte[] pdfBytes, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(pdfBytes);
        fos.close();
    }

    @Autowired
    private JavaMailSender mailSender;

    public void sendCertificateByEmail(String toEmail, String participantName, byte[] certificatePdf) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Seu Certificado de Participação");
        helper.setText("Olá " + participantName + ",\n\nObrigado por participar do evento. Em anexo, você encontrará seu certificado de participação.");

        helper.addAttachment("certificado.pdf", new ByteArrayResource(certificatePdf));

        mailSender.send(message);
    }
}
