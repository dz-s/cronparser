package org.example;

import org.example.exceptions.InvalidInputException;
import org.example.models.CronFieldType;
import org.example.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class CronFieldParser {
    public Schedule parse(String[] cronFields){

        try {
            return new Schedule(
                    getExpandedField(cronFields[0], CronFieldType.MINUTES),
                    getExpandedField(cronFields[1], CronFieldType.HOURS),
                    getExpandedField(cronFields[2], CronFieldType.DAYS_OF_MONTH),
                    getExpandedField(cronFields[3], CronFieldType.MONTHS),
                    getExpandedField(cronFields[4], CronFieldType.DAYS_OF_WEEK),
                    cronFields[5]
            );
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid value in the cron string found.");
        }
    }


    private List<Integer> getExpandedField(String cronField, CronFieldType type){
        List<Integer> values = new ArrayList<>();
        processIntervals(cronField, type, values);
        processRangeOfValues(cronField, type, values);
        processFixedValues(cronField, type, values);
        return values;
    }

    private void processIntervals(String cronField, CronFieldType type, List<Integer> values)
            throws InvalidInputException {
        if (cronField.startsWith("*")) {
            String[] steps = cronField.split("/");
            final int len = steps.length;

            if (len > 2) {
                throw new InvalidInputException("Too many values for that interval:" + cronField);
            }

            int step = 1;

            if(len == 2) {
                step = Integer.parseInt(steps[1]);
            }
            validateValue(step, type);
            expandField(type.getMin(), type.getMax(), step, values);
        }
    }

    private void processRangeOfValues(String cronField, CronFieldType type, List<Integer> values)
            throws InvalidInputException {
        if(!cronField.contains("-")){
            return;
        }
        String[] range = cronField.split("-");
        if (range.length != 2) {
            throw new InvalidInputException("Wrong range format for that field: " + cronField);
        }
        int begin = Integer.parseInt(range[0]);
        int end = Integer.parseInt(range[1]);
        validateRange(begin, end, type);
        expandField(begin, end, 1, values);
    }

    private void processFixedValues(String cronField, CronFieldType type, List<Integer> values)
            throws InvalidInputException {
        if(!(cronField.contains(",") || cronField.matches("\\d+"))){
            return;
        }
        String[] fixedDates = cronField.split(",");
        for (String sValue : fixedDates) {
            Integer value = parseNumber(sValue);
            validateValue(parseNumber(sValue), type);
            values.add(value);
        }
    }

    private void expandField(int start, int end, int step, List<Integer> values){
        for (int i = start; i <= end; i += step) {
            values.add(i);
        }
    }

    private void validateRange(int start, int end, CronFieldType type) throws InvalidInputException {
        if (start < type.getMin() || end > type.getMax()) {
            throw new InvalidInputException("Value is out of range");
        }
    }
    private void validateValue(int value, CronFieldType type) throws InvalidInputException {
        if (value < type.getMin() || value > type.getMax()) {
            throw new InvalidInputException("Value is out of range");
        }
    }

    private Integer parseNumber(String sNum) throws InvalidInputException {
        try {
            return Integer.parseInt(sNum);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Not a number: " + sNum);
        }
    }
}
