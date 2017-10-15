package com.nexxera.bakerybudget.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtil {
	public static final ZoneId BRAZIL = ZoneId.of("America/Sao_Paulo");

	public static ZonedDateTime newZonedDateTime() {
		return LocalDateTime.now().atZone(BRAZIL);
	}

	public static Date newDateFrom(ZonedDateTime zonedDateTime) {
		return Date.from(zonedDateTime.toInstant());
	}
}