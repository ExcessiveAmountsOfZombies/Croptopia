package me.thonk.croptopia.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static me.thonk.croptopia.data.CroptopiaDataGenerator.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataEvent {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new ItemGenerator(generator, "croptopia", event.getExistingFileHelper()));
        generator.addProvider(new LanguageGenerator(generator, "croptopia", "en_us"));
        BlockTagGenerator blockTagGen = new BlockTagGenerator(generator, "croptopia", event.getExistingFileHelper());
        generator.addProvider(blockTagGen);
        generator.addProvider(new ItemTagGenerator(generator, blockTagGen, "croptopia", event.getExistingFileHelper()));
    }
}
