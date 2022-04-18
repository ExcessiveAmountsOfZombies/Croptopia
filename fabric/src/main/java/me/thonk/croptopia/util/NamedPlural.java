package me.thonk.croptopia.util;

public interface NamedPlural extends NamedLikeEnum, PluralInfo {

    default String getPlural() {
        return PluralInfo.plural(getLowercaseName(), hasPlural());
    }

}
