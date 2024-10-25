package com.Checkerly.BackEnd.services;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CertificateService {

    public byte[] generateCertificate(Map<String, String> participantInfo) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        // Abre o documento
        document.open();

        // Adiciona conteúdo ao certificado
        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

        document.add(new Paragraph("Certificado de Comparecimento", titleFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Certificamos que:", normalFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(participantInfo.get("name"), titleFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Participou com sucesso no evento", normalFont));
        document.add(new Paragraph(participantInfo.get("event"), normalFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Data: " + LocalDate.now().toString(), normalFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Signature", normalFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Copyrights ©  2024 Checkerly®. All rights reserved ", normalFont));

        // Fecha o documento
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

    public void sendCertificateByEmail(String toEmail, String participantName, byte[] certificatePdf) throws MessagingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Seu Certificado de Participação");
        helper.setText("Olá " + participantName + ",\n\nObrigado por participar do evento. Em anexo, você encontrará seu certificado de participação.");

        // Anexando o PDF do certificado
        helper.addAttachment("certificado.pdf", new ByteArrayResource(certificatePdf));

        mailSender.send(message);
    }
}
