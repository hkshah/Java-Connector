package com.rapidvalue.master.dao.util;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.rapidvalue.master.dao.annotations.Column;
import com.rapidvalue.master.dao.exception.DaoException;

public class DomainDataProcessorUtil {

    private static final Logger LOG = Logger.getLogger(DomainDataProcessorUtil.class);

    private DomainDataProcessorUtil() {

    }

    public static void setValues(Object target, String columnName, Object value) throws DaoException {
        Class<? extends Object> klass = target.getClass();
        boolean isValueSet = false;
        while (klass != null && !isValueSet) {
            isValueSet = setValues(target, columnName, value, klass.getDeclaredFields());
            klass = klass.getSuperclass();
        }
    }

    private static boolean setValues(Object target, String columnName, Object value, Field[] fields)
            throws DaoException {
        boolean isValueSet = false;
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (columnName.equals(column.name())) {
                        field.setAccessible(true);
                        field.set(target, value);
                        isValueSet = true;
                        break;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            LOG.error("Failed to set value to object of type " + target.getClass().getName(), e);
            throw new DaoException(500, "Failed to execute procedure.", e);
        }
        return isValueSet;
    }

    public static Object getValue(String columnName, Object source) throws DaoException {
        Object ret = null;
        Class<? extends Object> klass = source.getClass();
        while (klass != null && ret == null) {
            ret = getValue(columnName, source, klass.getDeclaredFields());
            klass = klass.getSuperclass();
        }
        return ret;
    }

    private static Object getValue(String columnName, Object source, Field[] fields) throws DaoException {
        Object ret = null;
        try {
            if (source != null) {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column column = field.getAnnotation(Column.class);
                        if (columnName.equals(column.name())) {
                            field.setAccessible(true);
                            ret = field.get(source);
                            break;
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
            LOG.error("Failed to get value from object of type " + source.getClass().getName(), e);
            throw new DaoException(500, "Failed to execute procedure.", e);
        }
        return ret;
    }

}