package com.epherical.croptopia;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.server.commands.SetBlockCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class SetupCommand {


    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.setblock.failed"));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
        dispatcher.register((Commands.literal("croptest").requires((serverCommandSource) -> {
            return serverCommandSource.hasPermission(2);
        })).then(Commands.argument("pos", BlockPosArgument.blockPos()).then((((Commands.argument("block", BlockStateArgument.block(commandBuildContext)).executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgument.getLoadedBlockPos(commandContext, "pos"), BlockStateArgument.getBlock(commandContext, "block"), SetBlockCommand.Mode.REPLACE, null);
        })).then(Commands.literal("destroy").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgument.getLoadedBlockPos(commandContext, "pos"), BlockStateArgument.getBlock(commandContext, "block"), SetBlockCommand.Mode.DESTROY, null);
        }))).then(Commands.literal("keep").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgument.getLoadedBlockPos(commandContext, "pos"), BlockStateArgument.getBlock(commandContext, "block"), SetBlockCommand.Mode.REPLACE, (cachedBlockPosition) -> cachedBlockPosition.getLevel().isEmptyBlock(cachedBlockPosition.getPos()));
        }))).then(Commands.literal("replace").executes((commandContext) -> {
            return execute(commandContext.getSource(), BlockPosArgument.getLoadedBlockPos(commandContext, "pos"), BlockStateArgument.getBlock(commandContext, "block"), SetBlockCommand.Mode.REPLACE, null);
        })))));
    }

    private static int execute(CommandSourceStack source, BlockPos pos, BlockInput block, SetBlockCommand.Mode mode, @Nullable Predicate<BlockInWorld> condition) throws CommandSyntaxException {
        ServerLevel serverWorld = source.getLevel();
        if (condition != null && !condition.test(new BlockInWorld(serverWorld, pos, true))) {
            throw FAILED_EXCEPTION.create();
        } else {

            ServerLevel world = source.getLevel();
            world.setBlock(pos, block.getState(), 2);
            world.setBlock(pos.offset(1, 0, 0), block.getState().setValue(BlockStateProperties.AGE_7, 1), 2);
            world.setBlock(pos.offset(2, 0, 0), block.getState().setValue(BlockStateProperties.AGE_7, 5), 2);
            world.setBlock(pos.offset(3, 0, 0), block.getState().setValue(BlockStateProperties.AGE_7, 7), 2);

            serverWorld.blockUpdated(pos, block.getState().getBlock());
            source.sendSuccess(Component.translatable("commands.setblock.success", pos.getX(), pos.getY(), pos.getZ()), true);
            return 1;
        }
    }
}
