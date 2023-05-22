package core;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class FunctionsDateUtils {

	public static Instant currentInstantDateTime() {
		return Instant.now();
	}
	
	public static Instant currentMonthStartDay() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfMonth = currentDate.withDayOfMonth(1);

        return startOfMonth.atStartOfDay(ZoneId.of("UTC")).toInstant();
	}

	public static Instant currentMonthFinalDay() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

        return endOfMonth.atTime(23, 59, 59).atZone(ZoneId.of("UTC")).toInstant();
	}

	public static Instant increaseMonthToDate(Instant dateToIncrease, Integer quantityToIncrease) {
        YearMonth yearMonth = YearMonth.from(dateToIncrease.atZone(ZoneOffset.UTC));
        YearMonth newYearMonth = yearMonth.plusMonths(quantityToIncrease);

        Integer maxDayOfMonth = newYearMonth.lengthOfMonth();
        Integer inputDayOfMonth = dateToIncrease.atZone(ZoneOffset.UTC).getDayOfMonth();
        Integer dayOfMonth = Math.min(maxDayOfMonth, inputDayOfMonth);

        return newYearMonth.atDay(dayOfMonth).atStartOfDay().toInstant(ZoneOffset.UTC);

	}
}
