
import org.example.CronFieldParser;
import org.example.exceptions.InvalidInputException;
import org.example.models.Schedule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronFieldParserTest {

    @Test
    public void parse_ValidCronFields_ReturnsSchedule() {
        CronFieldParser cronFieldParser = new CronFieldParser();

        String[] cronFields = TestConstants.VALID_CRON_STRING.split("\\s+");

        Schedule schedule = cronFieldParser.parse(cronFields);

        assertEquals(schedule.getMinutes(), List.of(0, 15, 30, 45));
        assertEquals(schedule.getHours(), List.of(2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertEquals(schedule.getDaysOfMonth(), List.of(1, 15));
        assertEquals(schedule.getMonths(), List.of(2, 3, 4));
        assertEquals(schedule.getDaysOfWeek(), List.of(1, 3, 5));
        assertEquals(schedule.getCommand(), "/usr/bin/find");
    }

    @Test
    public void parse_InvalidCronFields_ThrowsInvalidInputException() {
        CronFieldParser cronFieldParser = new CronFieldParser();
        String[] invalidCronFields = TestConstants.INVALID_HOUR_CRON_STRING.split("\\s+");

        assertThrows(InvalidInputException.class, () -> cronFieldParser.parse(invalidCronFields));
    }
}
