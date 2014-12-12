/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.struts2.util.StrutsTypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.Map;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class JSR310Converter extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values != null && values.length > 0 && values[0] != null && values[0].length() > 0) {
            try {
                if (LocalDateTime.class == toClass) {
                    return LocalDateTime.parse(values[0]);
                } else if (LocalDate.class == toClass) {
                    return LocalDate.parse(values[0]);
                } else if (LocalTime.class == toClass) {
                    return LocalTime.parse(values[0]);
                } else if (Year.class == toClass) {
                    return Year.parse(values[0]);
                }
            } catch (DateTimeParseException e) {
                throw new TypeConversionException(e);
            }
        }
        return null;
    }

    @Override
    public String convertToString(Map context, Object o) {
        return o.toString();
    }

}
