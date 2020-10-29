package me.thonk.croptopia.mixin;

import me.thonk.croptopia.generator.GeneratorRegistry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DiskMixin {

    @Inject(method = "addDefaultDisks", at = @At("TAIL"), cancellable = true)
    private static void addDefaultDisks(GenerationSettings.Builder builder, CallbackInfo info) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES,
                GeneratorRegistry.DISK_SALT_CONFIGURED);
    }
}
