package org.example.models;

public enum CronFieldType {
    MINUTES(0, 59),
    HOURS(0, 23),
    DAYS_OF_MONTH(1, 31),
    MONTHS(1, 12),
    DAYS_OF_WEEK(1, 7);

    private final int max;
    private final int min;

    CronFieldType(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

}
