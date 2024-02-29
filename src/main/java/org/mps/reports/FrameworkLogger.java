package org.mps.reports;

import org.mps.enums.LogType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class FrameworkLogger {

    private FrameworkLogger(){}

    private static final Consumer<String> PASS = message -> ExtentManager.getExtentTest().pass(message);
    private static final Consumer<String> FAIL = message -> ExtentManager.getExtentTest().fail(message);
    private static final Consumer<String> SKIP = message -> ExtentManager.getExtentTest().skip(message);
    private static final Consumer<String> INFO = message -> ExtentManager.getExtentTest().info(message);

    private static final Consumer<String> CONSOLEINFO = message -> System.out.println("Info : " +message);

    private static final Consumer<String> CONSOLEPASS = message -> System.out.println("Pass : " +message);

    private static final Consumer<String> EXTENTANDCONSOLEINFO = INFO.andThen(CONSOLEINFO);

    private static final Consumer<String> EXTENTANDCONSOLEPASS = PASS.andThen(CONSOLEPASS);

    private static Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);

    static{
        MAP.put(LogType.PASS,PASS);
        MAP.put(LogType.FAIL,FAIL);
        MAP.put(LogType.SKIP,SKIP);
        MAP.put(LogType.INFO,INFO);
        MAP.put(LogType.CONSOLEINFO,CONSOLEINFO);
        MAP.put(LogType.CONSOLEPASS,CONSOLEPASS);
        MAP.put(LogType.EXTENTANDCONSOLEINFO,EXTENTANDCONSOLEINFO);
        MAP.put(LogType.EXTENTANDCONSOLEPASS,EXTENTANDCONSOLEPASS);

    }

    public static void log(LogType status, String message){
        MAP.get(status).accept(message);
    }


}
