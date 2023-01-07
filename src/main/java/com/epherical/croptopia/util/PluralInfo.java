package com.epherical.croptopia.util;

import java.util.Objects;

public interface PluralInfo {

    /**
     * If the object has a plural word form that is <i>different</i> from its base form
     * @return
     */
    boolean hasPlural();

    /**
     *
     * @param word The word to form the plural from, not <code>null</code>.
     * @param hasPlural {@see #hasPlural}
     * @return the plural form of the supplied word
     * @throws NullPointerException If <code>word</code> refers to <code>null</code>.
     */
    static String plural(String word, boolean hasPlural) {
        Objects.requireNonNull(word);
        if (!hasPlural) {
            return word;
        }
        if (word.endsWith("y")) {
            return word.substring(0, word.length()-1) + "ies";
        }
        if (word.endsWith("leaf")) {
            return word.substring(0, word.length()-1) + "ves";
        }
        if (word.endsWith("knife")) {
            return word.substring(0, word.length()-2) + "ves";
        }
        if (word.endsWith("sh") || word.endsWith("tomato") || word.endsWith("ch")) {
            return word + "es";
        }
        return word + "s";
    }

}
