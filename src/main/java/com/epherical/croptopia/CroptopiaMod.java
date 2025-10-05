package com.epherical.croptopia;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.common.MiscNames;
import com.epherical.croptopia.common.PlatformAdapter;
import com.epherical.croptopia.config.CroptopiaConfig;
import com.epherical.croptopia.config.IdentifierSerializer;
import com.epherical.croptopia.config.TreeConfiguration;
import com.epherical.croptopia.register.Composter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.effects.SpawnParticlesEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public record CroptopiaMod(PlatformAdapter<?> platform, CroptopiaConfig config) {
    public static ArrayList<Item> cropItems = new ArrayList<>();
    public static ArrayList<Block> cropBlocks = new ArrayList<>();
    public static ArrayList<Block> leafBlocks = new ArrayList<>();
    public static ArrayList<Item> seeds = new ArrayList<>();

    private static CroptopiaMod mod;


    public CroptopiaMod(PlatformAdapter<?> platform, CroptopiaConfig config) {
        this.platform = platform;
        this.config = config;
        config.addSerializer(TreeConfiguration.class, TreeConfiguration.Serializer.INSTANCE);
        config.addSerializer(ResourceLocation.class, IdentifierSerializer.INSTANCE);
        config.loadConfig(MiscNames.MOD_ID);
        mod = this;
    }

    public void registerCompost() {
        Composter composter = new Composter();
        composter.init();
    }

    public static CroptopiaMod getInstance() {
        return mod;
    }

    public static Item.Properties createGroup(ResourceLocation id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, requireNonNull(id)));
    }

    public static ResourceLocation createIdentifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(MiscNames.MOD_ID, name);
    }

    public static BlockBehaviour.Properties createCropSettings(ResourceLocation id) {
        return BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, requireNonNull(id))).
                mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP);
    }

    public static LeafCropBlock createLeavesBlock(ResourceLocation id) {
        return new LeafCropBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, requireNonNull(id))).
                mapColor(MapColor.PLANT).strength(0.2F).ignitedByLava().randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(CroptopiaMod::canSpawnOnLeaves).isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false));
    }

    public static LeavesBlock createRegularLeavesBlock(ResourceLocation id) {
        return new UntintedParticleLeavesBlock(
                0.1f, // REVIEW 1.21.5
                ParticleTypes.PALE_OAK_LEAVES, // REVIEW 1.21.5
                BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, requireNonNull(id))).
                mapColor(MapColor.PLANT).strength(0.2F).ignitedByLava().randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(CroptopiaMod::canSpawnOnLeaves).isSuffocating(CroptopiaMod::never).isViewBlocking(CroptopiaMod::never));
    }

    public static BlockBehaviour.Properties createSaplingSettings(ResourceLocation id) {
        return BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, requireNonNull(id))).
                mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS);
    }

    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    public static boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
