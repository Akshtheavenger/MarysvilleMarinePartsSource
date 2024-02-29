package org.mps.listeners;

import org.mps.annotations.FrameworkAnnotation;
import org.mps.enums.ConfigProperties;
import org.mps.reports.ExtentLogger;
import org.mps.reports.ExtentManager;
import org.mps.reports.ExtentReport;
import org.mps.utils.EmailUtils;
import org.mps.utils.PropertyUtils;
import org.testng.*;

import java.util.Arrays;

public class ListenerClass implements ITestListener, ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();
        if (PropertyUtils.get(ConfigProperties.SENDEXECUTIONSUMMARY).equalsIgnoreCase("yes")) {
            EmailUtils.TestExecutionSummary();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author());
        ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category());
        ExtentReport.addSeverity(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).severity());
        ExtentReport.addPriority(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).priority());

        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);
        ExtentManager.getExtentTest().assignCategory(className);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName() + " is passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentLogger.fail(result.getMethod().getMethodName() + " is failed");
        ExtentLogger.fail(result.getMethod().getMethodName(), true);
        ExtentLogger.fail(result.getThrowable().toString());
        ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getMethodName(), true);
        ExtentLogger.skip(result.getThrowable().toString());
        ExtentLogger.skip(Arrays.toString(result.getThrowable().getStackTrace()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        /*
            Not being Used Currently

         */
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
         /*
            Not being Used Currently

         */
    }

    @Override
    public void onStart(ITestContext context) {
        /*
            Not being Used Currently

         */
    }

    @Override
    public void onFinish(ITestContext context) {
        /*
            Not being Used Currently

         */
    }
}
