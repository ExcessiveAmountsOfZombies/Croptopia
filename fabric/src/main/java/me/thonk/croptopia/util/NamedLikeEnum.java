package me.thonk.croptopia.util;

public interface NamedLikeEnum {

    String name();

    default String getLowercaseName() {
        return name().toLowerCase();
    }

}
