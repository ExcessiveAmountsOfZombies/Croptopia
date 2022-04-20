package com.epherical.croptopia.dependencies;

import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class ModMenu extends Dependency implements ModMenuApi {

    public ModMenu() {
        super("modmenu");
    }


}
