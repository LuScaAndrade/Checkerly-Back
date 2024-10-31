package com.Checkerly.BackEnd.services;

import com.Checkerly.BackEnd.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailDTO emailDTO, String name, String eventName, String date) {
        String directoryPath = "certificates/event-1"; // Caminho relativo
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Cria os diretórios, se não existirem
        }

        String certificatePath = directoryPath + "/certificado.pdf"; // Caminho do PDF
        try {
            createPdf(certificatePath); // Gera o PDF apenas com a imagem
            sendEmailWithAttachment(emailDTO.email(), "Certificado de Comparecimento", certificatePath, name, eventName, date);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    private void createPdf(String path) throws IOException {
        // Criação do documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        // Carregar a imagem de fundo
        PDImageXObject backgroundImage = PDImageXObject.createFromFile("C://Users/Lucas/Downloads/Certificado.png", document);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Desenha a imagem de fundo
            contentStream.drawImage(backgroundImage, 0, 0, page.getMediaBox().getWidth(), page.getMediaBox().getHeight());
        }

        // Salvar o documento
        document.save(path);
        document.close();
    }

    private void sendEmailWithAttachment(String to, String subject, String pathToAttachment, String name, String eventName, String date) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indica que tem anexo

        helper.setFrom("noreply@email.com");
        helper.setSubject(subject);
        helper.setTo(to);

        // Carregar o template do email
        String template = loadEmailTemplate();
        // Substituir os placeholders pelo conteúdo real
        template = template.replace("#nome", name)
                .replace("#evento", eventName)
                .replace("#data", date);

        helper.setText(template, true);

        // Adiciona o anexo
        File file = new File(pathToAttachment);
        helper.addAttachment("certificado.pdf", file);

        // Envia o e-mail
        javaMailSender.send(message);
    }

    public String loadEmailTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource("template-email.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
