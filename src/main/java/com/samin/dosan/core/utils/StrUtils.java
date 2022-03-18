package com.samin.dosan.core.utils;

public class StrUtils {

    public static String urlToEnumName(String name) {
        return name.toUpperCase()
                .replaceAll("-", "_");
    }
}
