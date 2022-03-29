package com.samin.dosan.domain.training.training_target;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMap {
    public static Map<TargetKey, Integer> getDataToMap(Object object) {
        Map<TargetKey, Integer> result = new HashMap<>();

        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String name = getFiled(field.getName());
                TargetKey targetKey = TargetKey.valueOf(name);
                Integer value = (Integer) field.get(object);

                result.put(targetKey, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException();
            }
        }

        return result;
    }

    private static String getFiled(String filed) {
        String result = "";
        char[] chars = filed.toCharArray();

        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                result += "_" + c;
            } else {
                result += Character.toUpperCase(c);
            }
        }

        return result;
    }
}
