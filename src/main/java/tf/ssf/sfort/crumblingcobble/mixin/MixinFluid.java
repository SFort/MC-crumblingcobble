package tf.ssf.sfort.crumblingcobble.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tf.ssf.sfort.crumblingcobble.Main;

@Mixin(FluidBlock.class)
public class MixinFluid {
	@ModifyArg(at=@At(value="INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"), index = 1,
			method = "receiveNeighborFluids(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z")
	private BlockState crumble(BlockState par2) {
		if (par2.isOf(Blocks.COBBLESTONE)) return Main.COBBLESTONE.getDefaultState();
		return par2;
	}
}
