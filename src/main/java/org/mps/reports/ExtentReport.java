package org.mps.reports;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.mps.constants.FrameworkConstants;
import org.mps.enums.CategoryType;
import org.mps.enums.ConfigProperties;
import org.mps.enums.Priority;
import org.mps.enums.Severity;
import org.mps.exceptions.FrameworkException;
import org.mps.exceptions.InvalidPathForSparkXMLConfigFileException;
import org.mps.utils.PropertyUtils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

    private ExtentReport() {
    }

    private static ExtentReports extentReport;

    public static void initReports() {

        if (Objects.isNull(extentReport)) {
            extentReport = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
            extentReport.attachReporter(sparkReporter);
            ExtentSparkReporterConfig sparkReporterConfig = sparkReporter.config();
            sparkReporterConfig.setCss(".badge-primary{background-color:#1f5573; color:#FFFFFF} .badge-success{background-color:#00203FFF; color:#FFFFFF} .badge-danger{background-color:#ADEFD1FF; color:#3283ac} .customfontsize{font-size:120%}.log.badge-danger{background-color:#fd3232}");
            sparkReporterConfig.setJs("document.getElementsByClassName('logo')[0].style ='background-image:url(https://images.crunchbase.com/image/upload/c_lpad,h_32,w_32,f_auto,q_auto:eco,dpr_1/v1505384964/jo6ankxfhm34gazbwrwf.jpg)';");

            try {
                sparkReporter.loadXMLConfig(FrameworkConstants.getSparkConfigXmlFilePath());
            } catch (FileNotFoundException e) {
                throw new InvalidPathForSparkXMLConfigFileException("Spark Config XML file path is incorrect. Please Check it again");
            } catch (IOException e) {
                throw new FrameworkException("Some Exception occured while reading Excel File");
            }

            extentReport.setSystemInfo("OS", System.getProperty("os.name"));
            extentReport.setSystemInfo("Browser", PropertyUtils.get(ConfigProperties.BROWSER));
            extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReport.setSystemInfo("Environment", PropertyUtils.get(ConfigProperties.ENVIRONMENT));
            extentReport.setSystemInfo("App URL", "<a href='" + PropertyUtils.get(ConfigProperties.URL) + "'>https://mcstaging.marinepartssource.com/</a>");
        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extentReport)) {
            extentReport.flush();
        }
        ExtentManager.unload();
        try {
            Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTest(String testcaseName, String testcaseDescription) {
        ExtentManager.setExtentTest(extentReport.createTest(testcaseName, testcaseDescription));
    }

    public static void addAuthors(String[] authors) {
        for (String author : authors) {
            ExtentManager.getExtentTest().assignAuthor(author);
        }
    }

    public static void addCategories(CategoryType[] categories) {
        for (CategoryType category : categories) {
            ExtentManager.getExtentTest().assignCategory(category.toString());
        }
    }

    public static void addSeverity(Severity[] severityTypes) {
        for (Severity severity : severityTypes) {
            ExtentManager.getExtentTest().assignCategory(severity.toString());
        }
    }

    public static void addPriority(Priority[] priorityTypes) {
        for (Priority priority : priorityTypes) {
            ExtentManager.getExtentTest().assignCategory(priority.toString());
        }
    }
}
