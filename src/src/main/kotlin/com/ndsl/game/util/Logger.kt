package com.ndsl.game.util

import java.io.PrintStream

class Logger(var out: PrintStream) {
    val ERROR_COLOR = ANSI_RED
    val WARN_COLOR = ANSI_YELLOW

    var showDebug: Boolean = false
    var showInfo: Boolean = true
    var showWarn: Boolean = true
    var showError: Boolean = true

    fun debug(s: String){if(showDebug) out.println("$ANSI_WHITE$s$ANSI_RESET")}
    fun info(s: String){if(showInfo) out.println("$ANSI_BLUE$s$ANSI_RESET")}
    fun warn(s: String){if(showWarn) out.println("$WARN_COLOR$s$ANSI_RESET")}
    fun error(s: String){if(showError) out.println("$ERROR_COLOR$s$ANSI_RESET")}
}




val ANSI_RESET = "\u001B[0m"
val ANSI_BLACK = "\u001B[30m"
val ANSI_RED = "\u001B[31m"
val ANSI_GREEN = "\u001B[32m"
val ANSI_YELLOW = "\u001B[33m"
val ANSI_BLUE = "\u001B[34m"
val ANSI_PURPLE = "\u001B[35m"
val ANSI_CYAN = "\u001B[36m"
val ANSI_WHITE = "\u001B[37m"

val ANSI_BLACK_BACKGROUND = "\u001B[40m"
val ANSI_RED_BACKGROUND = "\u001B[41m"
val ANSI_GREEN_BACKGROUND = "\u001B[42m"
val ANSI_YELLOW_BACKGROUND = "\u001B[43m"
val ANSI_BLUE_BACKGROUND = "\u001B[44m"
val ANSI_PURPLE_BACKGROUND = "\u001B[45m"
val ANSI_CYAN_BACKGROUND = "\u001B[46m"
val ANSI_WHITE_BACKGROUND = "\u001B[47m"