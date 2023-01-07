package com.epherical.croptopia.register;

/**
 * Enum for all commonly used crop categories; always in plural form, if existent.
 */
public enum TagCategory {

    NONE,
    CROPS,
    FRUITS,
    GRAIN,
    NUTS,
    VEGETABLES;

    private final String lowerCaseName;

    TagCategory() {
        lowerCaseName = name().toLowerCase();
    }

    public String getLowerCaseName() {
        return lowerCaseName;
    }
}
