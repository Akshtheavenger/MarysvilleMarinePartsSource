package org.mps.utils;

import com.google.common.base.Utf8;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mps.constants.FrameworkConstants;
import org.mps.enums.ConfigProperties;

import java.io.File;
import java.io.IOException;

public final class TestCaseStatisticsUtils {

    private TestCaseStatisticsUtils(){}

    public static Document document;

    public static String getPassedTestCasesCount() {
        try {
            document = Jsoup.parse(new File(FrameworkConstants.getExtentReportFilePath()), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Incorrect Xpath. Please check it again");
        }
        return document.selectXpath(PropertyUtils.get(ConfigProperties.PASSEDTESTCASESCOUNT)).text();
    }

    public static String getFailedTestCasesCount(){
        try {
            document = Jsoup.parse(new File(FrameworkConstants.getExtentReportFilePath()), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Incorrect Xpath. Please check it again");
        }
        return document.selectXpath(PropertyUtils.get(ConfigProperties.FAILEDTESTCASESCOUNT)).text();

    }

    public static String getSkippedTestCasesCount(){
        try {
            document = Jsoup.parse(new File(FrameworkConstants.getExtentReportFilePath()), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Incorrect Xpath. Please check it again");
        }
        return document.selectXpath(PropertyUtils.get(ConfigProperties.SKIPPEDTESTCASESCOUNT)).text();
    }

    public static String getTotalCountOfAllTestCases() {
        return Integer.toString(Integer.parseInt(getPassedTestCasesCount()) + Integer.parseInt(getFailedTestCasesCount())
                + Integer.parseInt(getSkippedTestCasesCount()));
    }

    public static String getOverAllPassPercentage() {
        return Integer.toString(Math
                .round((Float.parseFloat(getPassedTestCasesCount()) / Integer.parseInt(getTotalCountOfAllTestCases())) * 100));
    }
}
