package org.mps.constants;

import org.mps.enums.ConfigProperties;
import org.mps.pages.FunctionLibraryPage;
import org.mps.utils.PropertyUtils;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
    private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";

    private static final String JSONCONFIGFILEPATH = RESOURCESPATH + "/config/config.json";

    private static final String SPARKCONFIGXMLFILEPATH = System.getProperty("user.dir") + "/spark-config.xml";

    public static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/Extent-test-output/";

    private static String extentReportFilePath = "";


    private static final int EXPLICITWAITDURATION = 15;

    public static String getExtentReportFilePath() {

        if(extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    public static String createReportPath(){
        if(PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("Yes")){
            return EXTENTREPORTFOLDERPATH + FunctionLibraryPage.getDateAndTimeAndDay() + "/MMPS_TestExecutionReport.html";
        }
        return EXTENTREPORTFOLDERPATH + "/MMPS_TestExecutionReport.html";
    }

    public static String getConfigFilePath() {
        return CONFIGFILEPATH;
    }

    public static String getSparkConfigXmlFilePath() {
        return SPARKCONFIGXMLFILEPATH;
    }

    public static String getJsonconfigfilepath() {
        return JSONCONFIGFILEPATH;
    }

    public static int getEXPLICITWAITDURATION(){
        return EXPLICITWAITDURATION;
    }


}
