/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import org.thymeleaf.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.*;
import java.time.chrono.ChronoZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Used to serialize javascript.
 *
 * COPYRIGHT: Based on {@link org.thymeleaf.util.JavaScriptUtils}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class AdvancedJavaScriptUtils {

    private AdvancedJavaScriptUtils() {}

    public static String escapeString(final String str) {
        return StringUtils.escapeJavaScript(str);
    }

    public static String print(final Object object) {
        final StringBuilder output = new StringBuilder();
        print(output, object);
        return output.toString();
    }

    public static String printString(final String str) {
        final StringBuilder output = new StringBuilder();
        if (str == null) {
            printNull(output);
        } else {
            printString(output, str);
        }
        return output.toString();
    }

    public static String printNumber(final Number number) {
        final StringBuilder output = new StringBuilder();
        if (number == null) {
            printNull(output);
        } else {
            printNumber(output, number);
        }
        return output.toString();
    }

    public static String printBoolean(final Boolean bool) {
        final StringBuilder output = new StringBuilder();
        if (bool == null) {
            printNull(output);
        } else {
            printBoolean(output, bool);
        }
        return output.toString();
    }

    public static String printCollection(final Collection<?> collection) {
        final StringBuilder output = new StringBuilder();
        if (collection == null) {
            printNull(output);
        } else {
            printCollection(output, collection);
        }
        return output.toString();
    }

    public static String printArray(final Object array) {
        final StringBuilder output = new StringBuilder();
        if (array == null) {
            printNull(output);
        } else {
            printArray(output, array);
        }
        return output.toString();
    }


    public static String printMap(final Map<?,?> map) {
        final StringBuilder output = new StringBuilder();
        if (map == null) {
            printNull(output);
        } else {
            printMap(output, map);
        }
        return output.toString();
    }


    public static String printObject(final Object object) {
        final StringBuilder output = new StringBuilder();
        if (object == null) {
            printNull(output);
        } else {
            printObject(output, object);
        }
        return output.toString();
    }

    private static void print(final StringBuilder output, final Object object) {
        if (object == null) {
            printNull(output);
            return;
        }
        if (object instanceof String) {
            printString(output, (String) object);
            return;
        }
        if (object instanceof Character) {
            printString(output, object.toString());
            return;
        }
        if (object instanceof Number) {
            printNumber(output, (Number) object);
            return;
        }
        if (object instanceof Boolean) {
            printBoolean(output, (Boolean) object);
            return;
        }
        if (object instanceof Date) {
            printDate(output, (Date) object);
            return;
        }
        if (object instanceof TemporalAccessor) {
            printTemporalAccessor(output, (TemporalAccessor) object);
            return;
        }
        if (object instanceof TemporalAmount) {
            printTemporalAmount(output, (TemporalAmount) object);
            return;
        }
        if (object.getClass().isArray()) {
            printArray(output, object);
            return;
        }
        if (object instanceof Collection<?>) {
            printCollection(output, (Collection<?>) object);
            return;
        }
        if (object instanceof Map<?,?>) {
            printMap(output, (Map<?, ?>) object);
            return;
        }
        if (object.getClass().isEnum()) {
            printEnum(output, object);
            return;
        }
        printObject(output, object);
    }

    private static void printNull(final StringBuilder output) {
        output.append("null");
    }

    private static void printString(final StringBuilder output, final String str) {
        output.append('\'');
        output.append(StringUtils.escapeJavaScript(str));
        output.append('\'');
    }

    private static void printNumber(final StringBuilder output, final Number number) {
        output.append(number.toString());
    }

    private static void printBoolean(final StringBuilder output, final Boolean bool) {
        output.append(bool.toString());
    }

    private static void printDate(final StringBuilder output, final Date date) {
        output.append('\'');
        output.append((date instanceof java.sql.Date ? new Date(date.getTime()) : date).toString());
        output.append('\'');
    }

    private static void printTemporalAccessor(final StringBuilder output, final TemporalAccessor temporalAccessor) {
        output.append('\'');
        output.append(zonedTime(temporalAccessor).toString());
        output.append('\'');
    }

    private static void printTemporalAmount(final StringBuilder output, final TemporalAmount temporalAmount) {
        output.append('\'');
        output.append(temporalAmount.toString());
        output.append('\'');
    }

    private static void printArray(final StringBuilder output, final Object arrayObj) {
        output.append('[');
        if (arrayObj instanceof Object[]) {
            final Object[] array = (Object[]) arrayObj;
            boolean first = true;
            for (final Object element: array) {
                if (first) {
                    first = false;
                } else {
                    output.append(',');
                }
                print(output, element);
            }
        } else if (arrayObj instanceof boolean[]) {
            final boolean[] array = (boolean[]) arrayObj;
            boolean first = true;
            for (final boolean element: array) {
                if (first) {
                    first = false;
                } else {
                    output.append(',');
                }
                print(output, element);
            }
        } else if (arrayObj instanceof byte[]) {
            final byte[] array = (byte[]) arrayObj;
            boolean first = true;
            for (final byte element: array) {
                if (first) {
                    first = false;
                } else {
                    output.append(',');
                }
                print(output, element);
            }
        } else if (arrayObj instanceof short[]) {
            final short[] array = (short[]) arrayObj;
            boolean first = true;
            for (final short element: array) {
                if (first) {
                    first = false;
                } else {
                    output.append(',');
                }
                print(output, element);
            }
        } else if (arrayObj instanceof int[]) {
            final int[] array = (int[]) arrayObj;
            boolean first = true;
            for (final int element: array) {
                if (first) {
                    first = false;
                } else {
                    output.append(',');
                }
                print(output, element);
            }
        } else if (arrayObj instanceof long[]) {
            final long[] array = (long[]) arrayObj;
            boolean first = true;
            for (final long element: array) {
                if (first) {
                    first = false;
                } else {
                    output.append(',');
                }
                print(output, element);
            }
        } else if (arrayObj instanceof float[]) {
            final float[] array = (float[]) arrayObj;
            boolean first = true;
            for (final float element: array) {
                if (first) {
                    first = false;
                } else {
                    output.append(',');
                }
                print(output, element);
            }
        } else if (arrayObj instanceof double[]) {
            final double[] array = (double[]) arrayObj;
            boolean first = true;
            for (final double element: array) {
                if (first) {
                    first = false;
                } else {
                    output.append(',');
                }
                print(output, element);
            }
        } else {
            throw new IllegalArgumentException("Cannot print \"" + arrayObj + "\" of class " + arrayObj.getClass().getName() + " as an array");
        }
        output.append(']');
    }

    private static void printCollection(final StringBuilder output, final Collection<?> collection) {
        output.append('[');
        boolean first = true;
        for (final Object element: collection) {
            if (first) {
                first = false;
            } else {
                output.append(',');
            }
            print(output, element);
        }
        output.append(']');
    }

    private static void printMap(final StringBuilder output, final Map<?,?> map) {
        output.append('{');
        boolean first = true;
        for (final Map.Entry<?,?> entry: map.entrySet()) {
            if (first) {
                first = false;
            } else {
                output.append(',');
            }
            printKeyValue(output, entry.getKey(), entry.getValue());
        }
        output.append('}');
    }

    private static void printKeyValue(final StringBuilder output, final Object key, final Object value) {
        print(output, key);
        output.append(':');
        print(output, value);
    }

    private static void printObject(final StringBuilder output, final Object object) {
        try {
            final PropertyDescriptor[] descriptors =
                    Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors();
            final Map<String,Object> properties = new LinkedHashMap<String, Object>(descriptors.length + 1, 1.0f);
            for (final PropertyDescriptor descriptor : descriptors) {
                final Method readMethod =  descriptor.getReadMethod();
                if (readMethod != null) {
                    final String name = descriptor.getName();
                    if (!"class".equals(name.toLowerCase())) {
                        final Object value = readMethod.invoke(object);
                        properties.put(name, value);
                    }
                }
            }
            printMap(output, properties);
        } catch (final IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            throw new IllegalArgumentException("Could not perform introspection on object of class " + object.getClass().getName(), e);
        }
    }

    private static void printEnum(final StringBuilder output, final Object object) {
        final Enum<?> enumObject = (Enum<?>) object;
        final Class<?> enumClass = object.getClass();

        final Map<String,Object> properties = new LinkedHashMap<String, Object>(3, 1.0f);
        properties.put("$type", enumClass.getSimpleName());
        properties.put("$name", enumObject.name());

        printMap(output, properties);
    }

    private static TemporalAccessor zonedTime(TemporalAccessor target) {
        if(target instanceof ChronoZonedDateTime) {
            return target;
        } else if(target instanceof LocalDateTime) {
            return ZonedDateTime.of((LocalDateTime) target, ZonedDateTime.now().getOffset());
        } else if(target instanceof LocalDate) {
            return ZonedDateTime.of((LocalDate)target, LocalTime.MIDNIGHT, ZonedDateTime.now().getOffset());
        } else if(target instanceof Instant) {
            return ZonedDateTime.ofInstant((Instant)target, ZonedDateTime.now().getOffset());
        } else {
            return target;
        }
    }

}
