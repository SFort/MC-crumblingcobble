package tf.ssf.sfort.crumblingcobble.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tf.ssf.sfort.crumblingcobble.Main;

@Mixin(LavaFluid.class)
public class MixinLavaFluid {
	@ModifyArg(at=@At(value="INVOKE", target = "Lnet/minecraft/world/WorldAccess;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), index = 1,
			method = "flow(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;Lnet/minecraft/fluid/FluidState;)V")
	private BlockState crumble(BlockState par2) {
		if (par2.isOf(Blocks.STONE)) return Main.STONE.getDefaultState();
		return par2;
	}
}
