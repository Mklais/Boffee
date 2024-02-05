package com.klaisapp.bookclub.logger;

public class LogMessageFormatter {
    /**
     * Generates a formatted log message.
     *
     * @param category   The category of the log message.
     * @param subsection An optional subsection within the log (e.g., "Registration"). Pass null or empty string if not applicable.
     * @param operation  The operation description.
     * @param details    Detailed information about the operation.
     * @return A formatted log message string.
     */
    public static String format(LogCategory category, LogSubsection subsection, String operation, String details) {
        // Checks if subsection is NONE, if so, don't include it in the log message
        String subsectionPart = !subsection.equals(LogSubsection.NONE)
                ? "\n---|" + subsection + "|---"
                : "";
        return String.format("""
                \n---|%s|---%s
                * Operation: %s
                * Details: %s
                ---***---
                """, category.name(), subsectionPart, operation, details);
    }
}
