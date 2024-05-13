package com.consol.api.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();

        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        return fieldNames;
    }

    public static List<Class> getFieldTypes(Field[] fields) {
        List<Class> fieldNames = new ArrayList<>();

        for (Field field : fields) {
            fieldNames.add(field.getType());
        }

        return fieldNames;
    }
}
