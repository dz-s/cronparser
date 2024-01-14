package org.example.models;

import java.util.List;

/**
 * Represents a schedule parsed from a cron string.
 */
public class Schedule {

    private final List<Integer> minutes;
    private final List<Integer> hours;
    private final List<Integer> daysOfMonth;
    private final List<Integer> months;
    private final List<Integer> daysOfWeek;
    private final String command;

    /**
     * Creates a new Schedule instance.
     *
     * @param minutes    List of minutes when the schedule should run.
     * @param hours     List of hours when the schedule should run.
     * @param daysOfMonth List of days in the month when the schedule should run.
     * @param months     List of months when the schedule should run.
     * @param daysOfWeek List of days in the week when the schedule should run.
     * @param command   The command associated with the schedule.
     */
    public Schedule(List<Integer> minutes, List<Integer> hours, List<Integer> daysOfMonth,
                    List<Integer> months, List<Integer> daysOfWeek, String command) {
        this.minutes = minutes;
        this.hours = hours;
        this.daysOfMonth = daysOfMonth;
        this.months = months;
        this.daysOfWeek = daysOfWeek;
        this.command = command;
    }

    public List<Integer> getMinutes() {
        return minutes;
    }

    public List<Integer> getHours() {
        return hours;
    }

    public List<Integer> getDaysOfMonth() {
        return daysOfMonth;
    }

    public List<Integer> getMonths() {
        return months;
    }

    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return formatField("minute", getMinutes()) +
                formatField("hour", getHours()) +
                formatField("day of month", getDaysOfMonth()) +
                formatField("month", getMonths()) +
                formatField("day of week", getDaysOfWeek()) +
                formatCommandField(getCommand());
    }

    private String formatField(String fieldName, List<?> values) {
        StringBuilder fieldOutput = new StringBuilder(fieldName + " ".repeat(Math.max(0, 14 - fieldName.length())));
        for (var value : values) {
            fieldOutput.append(value).append(" ");
        }
        fieldOutput.append("\n");
        return fieldOutput.toString();
    }

    private String formatCommandField(String command) {
        return "command" + " ".repeat(Math.max(0, 14 - "command".length())) + command + "\n";
    }


}
