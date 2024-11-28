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

    public void sendEmail(EmailDTO emailDTO, String name, String eventName) {
        String directoryPath = "certificates/event-1"; // Caminho relativo
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Cria os diretórios, se não existirem
        }

        String certificatePath = directoryPath + "/certificado.pdf"; // Caminho do PDF
        try {
            createPdf(certificatePath); // Gera o PDF apenas com a imagem
            sendEmailWithAttachment(emailDTO.email(), "Certificado de Comparecimento", certificatePath, name, eventName);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    private void createPdf(String path) throws IOException {
        // Criação do documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth())); // Página em paisagem
        document.addPage(page);

        // Carregar a imagem de fundo
        PDImageXObject backgroundImage = PDImageXObject.createFromFile("C://Users/Lucas/Downloads/Certificado.jpg", document);
//
////        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
////            // Ajusta a rotação da página para horizontal
////            float pageWidth = page.getMediaBox().getHeight(); // Largura para orientação horizontal
////            float pageHeight = page.getMediaBox().getWidth(); // Altura para orientação horizontal
////            contentStream.saveGraphicsState();
////            contentStream.transform(new Matrix(0, 1, -1, 0, pageWidth, 0)); // Rotaciona a página para desenhar corretamente
////            // Desenha a imagem de fundo ajustada à página rotacionada
////            contentStream.drawImage(backgroundImage, 0, 0, pageWidth, pageHeight);
////            contentStream.restoreGraphicsState();
////        }
//
//        // Dimensões da página e da imagem
//        float pageWidth = page.getMediaBox().getWidth();
//        float pageHeight = page.getMediaBox().getHeight();
//        float imageWidth = backgroundImage.getWidth();
//        float imageHeight = backgroundImage.getHeight();
//
//        // Calcular as coordenadas para centralizar a imagem
//        float x = (pageWidth - imageWidth) / 4;
//        float y = (pageHeight - imageHeight) / 4;
//
//        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//            // Desenha a imagem centralizada
//            contentStream.drawImage(backgroundImage, x, y, imageWidth, imageHeight);
//        }
//
//        // Salvar o documento
//        document.save(path);
//        document.close();

            // Dimensões da página e da imagem
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            float imageWidth = backgroundImage.getWidth();
            float imageHeight = backgroundImage.getHeight();

            // Calcular a escala para ajustar a imagem ao tamanho da página, mantendo as proporções
            float scaleWidth = pageWidth / imageWidth;
            float scaleHeight = pageHeight / imageHeight;
            float scale = Math.min(scaleWidth, scaleHeight); // Usa a menor escala para evitar cortes

            // Dimensões escaladas da imagem
            float scaledWidth = imageWidth * scale;
            float scaledHeight = imageHeight * scale;

            // Calcular as coordenadas para centralizar a imagem na página
            float x = (pageWidth - scaledWidth) / 2;
            float y = (pageHeight - scaledHeight) / 2;

            // Desenhar a imagem na página centralizada e escalada
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.drawImage(backgroundImage, x, y, scaledWidth, scaledHeight);
            }

            // Salvar o documento
            document.save(path);
            document.close();
        }

    private void sendEmailWithAttachment(String to, String subject, String pathToAttachment, String name, String eventName) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indica que tem anexo

        helper.setFrom("noreply@email.com");
        helper.setSubject(subject);
        helper.setTo(to);

        // Carregar o template do email
        String template = loadEmailTemplate();

        // Garantir que name e eventName não sejam nulos
        String participantName = (name != null) ? name : "Participante";
        String event = (eventName != null) ? eventName : "Evento";

        template = template.replace("#nome", participantName)
                .replace("#evento", event);

        template = template.replace("#nome", name != null ? name : "Participante")
                .replace("#evento", eventName != null ? eventName : "Evento");

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
