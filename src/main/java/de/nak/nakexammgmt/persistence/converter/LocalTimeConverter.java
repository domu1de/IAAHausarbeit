/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

/**
 * Convert a {@link LocalTime} into database column representation as {@link Time} and back again.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime value) {
        return value == null ? null : Time.valueOf(value);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time value) {
        return value == null ? null : value.toLocalTime();
    }
}