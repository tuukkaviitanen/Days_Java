package org.days;

import com.beust.jcommander.IStringConverter;

import java.time.LocalDate;

public class LocalDateConverter implements IStringConverter<LocalDate> {


    @Override
    public LocalDate convert(String string) {
        try {
            return LocalDate.parse(string);
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException("Illegal Date value: " + string);
        }
    }
}
