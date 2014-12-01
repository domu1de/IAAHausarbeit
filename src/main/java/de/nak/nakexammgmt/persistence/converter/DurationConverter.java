/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

/**
 * Convert a {@link Duration} into database column representation as {@link String} and back again.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, String> {

    @Override
    public String convertToDatabaseColumn(Duration value) {
        return value == null ? null : value.toString();
    }

    @Override
    public Duration convertToEntityAttribute(String value) {
        return value == null ? null : Duration.parse(value);
    }
}