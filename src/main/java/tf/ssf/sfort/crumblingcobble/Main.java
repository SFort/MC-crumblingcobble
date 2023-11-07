package tf.ssf.sfort.crumblingcobble;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;


public class Main implements ModInitializer {
	public static Block STONE;
	public static Block COBBLESTONE;

	public static class CrumblingBlock extends Block {
		public Block sup;

		public CrumblingBlock(Settings settings, Block sup) {
			super(settings);
			this.sup = sup;
		}
		public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
			return new ItemStack(sup);
		}
		public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
			world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS);
		}
		public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
			float f = state.getHardness(world, pos);
			if (f == -1.0F) {
				return 0.0F;
			} else {
				int i = player.getMainHandStack().isSuitableFor(sup.getDefaultState()) ? 30 : 100;
				return player.getBlockBreakingSpeed(state) / f / (float)i;
			}
		}
	}

	@Override
	public void onInitialize() {
		COBBLESTONE = Registry.register(Registries.BLOCK, id("cobblestone"), new CrumblingBlock(
				AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).strength(2.0F, 6.0F),
				Blocks.COBBLESTONE
				));
		STONE = Registry.register(Registries.BLOCK, id("stone"), new CrumblingBlock(
				AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).strength(1.5F, 6.0F),
				Blocks.STONE
		));
	}
	public static Identifier id(String name){
		return new Identifier("crumblingcobble", name);
	}
}
