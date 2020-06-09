package com.ndsl.bun133.logger;

import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String ERROR_COLOR=ANSI_RED;
    public static final String WARN_COLOR=ANSI_YELLOW;

    public PrintStream OutStream;

    public boolean isDebugShow=false;
    public boolean isLow_Level_Debug_Show=false;

    public Logger(boolean isDebugShow){
        OutStream= System.out;
        setDebug(isDebugShow);
    }

    public Logger(boolean isDebugShow,boolean isLow_Level_Debug_Show){
        OutStream= System.out;
        setDebug(isDebugShow);
        setLow_Level_Debug_Show(isLow_Level_Debug_Show);
    }

    public void setDebug(boolean isDebugShow) {
        this.isDebugShow=isDebugShow;
    }

    public void setLow_Level_Debug_Show(boolean isShow) {
        this.isLow_Level_Debug_Show=isShow;
    }

    public Logger(PrintStream CustomStream){
        OutStream=CustomStream;
    }

    public void println(@NotNull String text){
        if(text.isEmpty()) return;
        OutStream.println(text);
    }

    public void print(String text){
        OutStream.print(text);
    }

    public void println(String color, String text){
        OutStream.println(withColor(color,text));
    }

    public void print(String color, String text){
        OutStream.print(withColor(color,text));
    }

    public void error(String text){
        println(ERROR_COLOR,text);
    }

    public void warn(String text){
        println(WARN_COLOR,text);
    }

    public String withColor(String color, String text){
        return color+text+ANSI_RESET;
    }

    public void debug(String s) {
        if(isDebugShow){
            println(s);
        }
    }

    public void low_level_debug(String s){
        if(isLow_Level_Debug_Show){
            println(s);
        }
    }
}
