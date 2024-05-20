package EmailDelivery;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class for sending email reports.
 */
public class emailReport {

    /**
     * Refreshes the folder content and sends an email with the contents.
     */
    public static void refreshAndSendEmail() throws InterruptedException {
        // Email credentials
        final String username = "ali.hassan@gmail.com"; // Your email address
        final String password = "password"; // Your email password
        String folderPath = "C:\\Users\\T490\\OneDrive\\Desktop\\Automation\\test-output"; // Folder path to be sent

        // Refresh the folder content (delete or update files)
        refreshFolderContent(folderPath);

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // Outlook's SMTP server
        props.put("mail.smtp.port", "587"); // Port for TLS

        // Email session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ali.hassan@gmail.com")); // Set sender's email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mercurials2024@gmail.com")); // Set recipient's email
            message.setSubject("TestNG Automation Report (TAMASHA APP)"); // Email subject

            message.setText("Please find the folder contents attached.");


            // Compress folder contents into a ZIP file
            File zipFile = compressFolder(folderPath);

            // Attach ZIP file to the email
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(zipFile);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            // Send email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refreshes the content of the specified folder.
     *
     * @param folderPath The path of the folder to refresh.
     */
    private static void refreshFolderContent(String folderPath) throws InterruptedException {
        // Implement logic to refresh folder content (e.g., delete/update files)
        // For example:
        // Delete all files in the folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    /**
     * Compresses the contents of the specified folder into a ZIP file.
     *
     * @param folderPath The path of the folder to compress.
     * @return The compressed ZIP file.
     * @throws IOException If an I/O error occurs.
     */

    static File compressFolder(String folderPath) throws IOException {
        Path sourceFolderPath = Paths.get(folderPath);
        File zipFile = new File("test-report.zip");

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            Files.walk(sourceFolderPath)
                    .filter(path -> Files.isRegularFile(path))
                    .forEach(filePath -> {
                        String fileName = filePath.getFileName().toString().toLowerCase();
                        if (fileName.startsWith("index") || fileName.startsWith(".emailable") ||
                                fileName.endsWith(".html") || fileName.endsWith(".xml") || fileName.endsWith(".png"))

                        {
                            try {
                                Path relativePath = sourceFolderPath.relativize(filePath);
                                zos.putNextEntry(new ZipEntry(relativePath.toString()));
                                Files.copy(filePath, zos);
                                zos.closeEntry();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        return zipFile;
    }



    /**
     * Adds a file or directory to the ZIP output stream.
     *
     * @param rootPath The root path of the file or directory.
     * @param file     The file or directory to add to the ZIP output stream.
     * @param zos      The ZIP output stream.
     * @throws IOException If an I/O error occurs.
     */
    private static void addToZip(File rootPath, File file, ZipOutputStream zos) throws IOException {
        if (file.isDirectory()) {
            for (File innerFile : file.listFiles()) {
                addToZip(rootPath, innerFile, zos);
            }
        } else {
            String relativePath = rootPath.toURI().relativize(file.toURI()).getPath();
            ZipEntry zipEntry = new ZipEntry(relativePath);
            zos.putNextEntry(zipEntry);
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
            zos.closeEntry();
        }
    }




    /**
     * Sends an email with the specified report and subject.
     *
     * @param pslReport The report to send.
     * @param s         The subject of the email.
     */
    public static void sendEmail(String pslReport, String s) {
        // Method implementation
    }
}



//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

//
//public class emailReport {
//    public static void main(String[] args) {
//
//        // Recipient's email ID needs to be mentioned.
//        String to = "abcd@gmail.com";
//        // Sender's email ID needs to be mentioned
//        String from = "web@gmail.com";
//        // Office 365 SMTP server settings
//        String host = "smtp.office365.com";
//        String username = "your_office365_email@domain.com";
//        String password = "your_office365_password";
//
//        // Get system properties
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.port", "587"); // Port for TLS/SSL
//
//        // Get the default Session object.
//        Session session = Session.getInstance(properties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));
//
//            // Set Subject: header field
//            message.setSubject("This is the Subject Line!");
//
//            // Create the message part
//            BodyPart messageBodyPart = new MimeBodyPart();
//
//            // Fill the message
//            messageBodyPart.setText("This is message body");
//
//            // Create a multipart message
//            Multipart multipart = new MimeMultipart();
//
//            // Set text message part
//            multipart.addBodyPart(messageBodyPart);
//
//            // Part two is attachment
//            messageBodyPart = new MimeBodyPart();
//            String filename = "<Enter File Path of Emailable Report>";
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filename);
//            multipart.addBodyPart(messageBodyPart);
//
//            // Send the complete message parts
//            message.setContent(multipart);
//
//            // Send message
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//















//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////
////
////
////package EmailDelivery;
////
////import com.lowagie.text.Document;
////import com.lowagie.text.DocumentException;
////import com.lowagie.text.PageSize;
////import com.lowagie.text.html.simpleparser.HTMLWorker;
////import com.lowagie.text.pdf.PdfWriter;
////import io.appium.java_client.AppiumDriver;
////import io.appium.java_client.MobileElement;
////import javax.mail.*;
////import javax.mail.internet.InternetAddress;
////import javax.mail.internet.MimeBodyPart;
////import javax.mail.internet.MimeMessage;
////import javax.mail.internet.MimeMultipart;
////import java.io.*;
////import java.nio.file.*;
////import java.util.Properties;
////
////public class emailReport {
////
////    public static void main(String[] args) throws InterruptedException {
////        AppiumDriver<MobileElement> driver = null; // Initialize your AppiumDriver instance here if needed
////        Thread.sleep(10000);
////        refreshAndSendEmail(driver);
////    }
////
////    public static void refreshAndSendEmail(AppiumDriver<MobileElement> driver) throws InterruptedException {
////        // Email credentials
////        final String username = "ali.hassan@mercurialminds.com"; // Your email address
////        final String password = "Basti@000"; // Your email password
////        String folderPath = "C:\\Users\\T490\\OneDrive\\Desktop\\Automation\\test-output"; // Folder path to be sent
////
////        // Refresh the folder content
////        refreshFolderContent(folderPath);
////        // Convert HTML report to PDF
////        String pdfFilePath = convertHtmlToPdf(folderPath);
////
////        // Send email with PDF attachment
////        sendEmail(username, password, pdfFilePath);
////    }
////
////    private static void refreshFolderContent(String folderPath) {
////        File folder = new File(folderPath);
////        File[] files = folder.listFiles();
////        if (files != null) {
////            for (File file : files) {
////                if (file.isFile() && !file.getName().equals("emailable-report.html")) {
////                    file.delete();
////                }
////            }
////        } else {
////            System.out.println("No files found in the folder: " + folderPath);
////        }
////    }
////
////    private static String convertHtmlToPdf(String folderPath) {
////        String htmlFilePath = folderPath + File.separator + "emailable-report.html";
////        String pdfFilePath = folderPath + File.separator + "emailable-report.pdf";
////
////        try (InputStream inputStream = new FileInputStream(htmlFilePath);
////             OutputStream outputStream = new FileOutputStream(pdfFilePath)) {
////
////            Document document = new Document(PageSize.A4);
////            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
////            document.open();
////
////            HTMLWorker htmlWorker = new HTMLWorker(document);
////            htmlWorker.parse(new BufferedReader(new InputStreamReader(inputStream)));
////
////            document.close();
////
////        } catch (IOException | DocumentException e) {
////            e.printStackTrace();
////        }
////
////        return pdfFilePath;
////    }
////
////    private static void sendEmail(String username, String password, String attachmentFilePath) {
////        Properties props = new Properties();
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.smtp.host", "smtp.office365.com"); // Outlook's SMTP server
////        props.put("mail.smtp.port", "587"); // Port for TLS
////
////        Session session = Session.getInstance(props,
////                new javax.mail.Authenticator() {
////                    protected PasswordAuthentication getPasswordAuthentication() {
////                        return new PasswordAuthentication(username, password);
////                    }
////                });
////
////        try {
////            Message message = new MimeMessage(session);
////            message.setFrom(new InternetAddress(username));
////            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("alibasti2021@gmail.com"));
////            message.setSubject("TestNG Automation Report (TAMASHA APP)");
////
////            BodyPart messageBodyPart = new MimeBodyPart();
////            messageBodyPart.setText("Please find the attached PDF report.");
////
////            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
////            attachmentBodyPart.attachFile(new File(attachmentFilePath));
////
////            Multipart multipart = new MimeMultipart();
////            multipart.addBodyPart(messageBodyPart);
////            multipart.addBodyPart(attachmentBodyPart);
////
////            message.setContent(multipart);
////
////            Transport.send(message);
////
////            System.out.println("Email sent successfully!");
////
////        } catch (MessagingException | IOException e) {
////            e.printStackTrace();
////        }
////    }
////}
////
