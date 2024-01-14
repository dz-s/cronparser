public class TestConstants {
    public static final String VALID_CRON_STRING = "*/15 2-10 1,15 2,3,4 1,3,5 /usr/bin/find";
    public static final String INVALID_HOUR_CRON_STRING = "*/15 100 1,15 * 1-5 /usr/bin/find";
    public static final String INVALID_DAY_OF_MONTH_CRON_STRING = "*/15 0 99,12 * 1-5 /usr/bin/find";
    public static final String INVALID_MONTH_CRON_STRING = "*/15 0 1,15 88 1-5 /usr/bin/find";
    public static final String INVALID_DAY_OF_WEEK_CRON_STRING = "*/15 0 1,15 * 1-99 /usr/bin/find";
}
