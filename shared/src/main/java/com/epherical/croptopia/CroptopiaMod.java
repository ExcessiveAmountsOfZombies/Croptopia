package com.epherical.croptopia;

import com.epherical.croptopia.common.PlatformAdapter;

public record CroptopiaMod(PlatformAdapter<?> platform) {

    private static CroptopiaMod mod;

    public CroptopiaMod(PlatformAdapter<?> platform) {
        this.platform = platform;
        mod = this;
    }

    public static CroptopiaMod getInstance() {
        return mod;
    }
}
