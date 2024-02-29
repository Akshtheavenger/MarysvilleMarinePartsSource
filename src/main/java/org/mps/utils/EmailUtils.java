package org.mps.utils;

import org.mps.constants.FrameworkConstants;
import org.mps.enums.ConfigProperties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailUtils {

    public static void TestExecutionSummary() {

        String reportFilePath = FrameworkConstants.getExtentReportFilePath();
        String toEmail = PropertyUtils.get(ConfigProperties.TOEMAIL);
        String fromEmail = PropertyUtils.get(ConfigProperties.FROMEMAIL);
        String cc = PropertyUtils.get(ConfigProperties.CCEMAIL);
        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587"; //Earlier used 465
        final String username = PropertyUtils.get(ConfigProperties.EMAILUSERNAME);
        final String password = PropertyUtils.get(ConfigProperties.EMAILPASSWORD);

        // Create a session with SMTP settings
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        // Authenticate and create a session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            message.setSubject(PropertyUtils.get(ConfigProperties.ENVIRONMENT) + " Environment Automation Suite Execution Summary");
            // Create the email body part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached test report.");
            String browser = PropertyUtils.get(ConfigProperties.BROWSER);
            String environment = PropertyUtils.get(ConfigProperties.ENVIRONMENT);

            String emailBody = "Hi Team, \r\n" + "\r\n"
                    + "Please find the Execution Summary of " + environment + " Automation Suite.\r\n" + "\r\n"
                    + "Total Test Cases: " + TestCaseStatisticsUtils.getTotalCountOfAllTestCases() + "\r\n" + "Passed: " + TestCaseStatisticsUtils.getPassedTestCasesCount() + "\r\n"
                    + "Failed: " + TestCaseStatisticsUtils.getFailedTestCasesCount() + "\r\n" + "Skipped: " + TestCaseStatisticsUtils.getSkippedTestCasesCount() + "\r\n" + "\r\n"
                    + "Overall Pass Percentage: " + TestCaseStatisticsUtils.getOverAllPassPercentage() + "%\r\n" + "Environment: " + environment + "\r\n"
                    + "Browser: " + browser + "\r\n" + "\r\n" + "Please find the Execution report attached aswell";
            messageBodyPart.setText(emailBody);
            String signature = "\r\n" + "\r\n" + "Thanks and Regards,\r\n" + "Marysville Automation Team\r\n" + "";
            messageBodyPart.setText(emailBody + signature);
            // Create the attachment part
            BodyPart attachmentBodyPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(reportFilePath);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("Automation Test Results of " + environment + ".html");

            // Combine parts into a multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);
            // Send the email
            Transport.send(message);
            System.out.println("Email with Extent Spark Report is attached and sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email Not Sent");
        }
    }
}
