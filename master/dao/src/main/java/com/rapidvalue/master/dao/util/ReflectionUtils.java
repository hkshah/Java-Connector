package com.rapidvalue.master.dao.util;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.rapidvalue.master.dao.exception.DaoException;

public class ReflectionUtils {

    private static final Logger LOG = Logger.getLogger(ReflectionUtils.class);

    private ReflectionUtils() {

    }

    public static Object getField(Object source, String property) throws DaoException {

        Object getField = null;
        try {
            Field field = source.getClass().getDeclaredField(property);
            field.setAccessible(true);
            getField = field.get(source);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            LOG.error("Failed to get fields::\n" + e);
            throw new DaoException(500, "Failed to get fields.", e);
        }
        return getField;
    }

    public static void setField(Object target, String property, Object value) throws DaoException {
        try {
            Field field = target.getClass().getDeclaredField(property);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            LOG.error("Failed to set fields::\n" + e);
            throw new DaoException(500, "Failed to map fields.", e);
        }
    }

}