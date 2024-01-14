package org.example;

import org.example.exceptions.InvalidInputException;
import org.example.models.Schedule;

/**
 * Command-line application for parsing and displaying cron schedules.
 */
public class CronExpressionParser {
    private final CronFieldParser cronFieldParser;


    public CronExpressionParser(CronFieldParser cronFieldParser) {
        this.cronFieldParser = cronFieldParser;
    }

    public void processCronString(String[] args) {

        if (args.length != 1) {
            throw new InvalidInputException("Usage: java CronParser 'cron_string'");
        }

        String[] cronFields = args[0].split("\\s+");
        if (cronFields.length != 6) {
            throw new InvalidInputException("Invalid number of fields in the cron string.");
        }

        Schedule schedule = cronFieldParser.parse(cronFields);
        System.out.println(schedule);
    }


    public static void main(String[] args) {
        CronFieldParser cronParser = new CronFieldParser();
        CronExpressionParser cronParserApp = new CronExpressionParser(cronParser);
        cronParserApp.processCronString(args);
    }
}