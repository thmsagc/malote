package com.es.maloteapi.entity.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringUtils {
    public static String localDateToStringDdMmYyyy(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return data.format(formatter);
    }

    public static LocalDate stringToLocalDateDdMmYyyy(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(string, formatter);
    }
}
