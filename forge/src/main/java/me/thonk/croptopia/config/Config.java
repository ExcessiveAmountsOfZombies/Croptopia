package me.thonk.croptopia.config;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = "croptopia", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
    public ForgeConfigSpec config;

    public static ForgeConfigSpec.ConfigValue<Boolean> rightClickHarvest;
    public static boolean canRightClickHarvest;

    public Config() {
        rightClickHarvest = CONFIG_BUILDER.comment("allows the user to right click harvest crops")
                .translation("croptopia.config.rightclickharvest")
                .define("rightClickHarvest", true);


        this.config = CONFIG_BUILDER.build();
    }

    @SubscribeEvent
    public void initConfig(ModConfig.ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() == config) {
            canRightClickHarvest = rightClickHarvest.get();
        }
    }
}
