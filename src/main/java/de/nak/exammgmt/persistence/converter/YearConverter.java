/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.Year;

/**
 * Convert a {@link java.time.Year} into database column representation as {@link java.sql.Date} and back again.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Date> {

    @Override
    public Date convertToDatabaseColumn(Year value) {
        return value == null ? null : Date.valueOf(value.toString()); // FIXME
    }

    @Override
    public Year convertToEntityAttribute(Date value) {
        return value == null ? null : Year.from(value.toLocalDate());
    }

}