package org.mps.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

    private ExtentManager() {
    }

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    static void setExtentTest(ExtentTest driverReference) {
        extentTest.set(driverReference);
    }

    static void unload() {
        extentTest.remove();
    }
}
