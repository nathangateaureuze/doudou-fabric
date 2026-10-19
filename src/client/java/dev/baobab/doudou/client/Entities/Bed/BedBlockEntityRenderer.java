package dev.baobab.doudou.client.Entities.Bed;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import dev.baobab.doudou.Blocks.Doudou.DoudouBlock;
import dev.baobab.doudou.Entities.Bed.BedBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class BedBlockEntityRenderer implements BlockEntityRenderer<BedBlockEntity, BedBlockEntityRenderState>{
    
	private final BlockModelResolver blockModelResolver;
	private final BlockDisplayContext blockDisplayContext;

    public BedBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
		blockModelResolver = context.blockModelResolver();
		blockDisplayContext = BlockDisplayContext.create();
    }

    @Override
    public BedBlockEntityRenderState createRenderState() {
        return new BedBlockEntityRenderState();
    }

	@Override
	public void extractRenderState(BedBlockEntity blockEntity, BedBlockEntityRenderState state, float tickProgress, Vec3 cameraPos, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        
        if (!blockEntity.isEmpty()) {
            if (blockEntity.getDoudou() != null) {
                state.setDirection(blockEntity.getBlockState().getValue(DoudouBlock.FACING));
                state.setBedType(blockEntity.getBedType());
		        blockModelResolver.update(state.blockModelRenderState, blockEntity.getDoudou(), blockDisplayContext);
            }
        }
    }

    @Override
    public void submit(BedBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        float angle = 180;
        switch (state.getDirection()) {
            case Direction.SOUTH:
                angle += 180;
                break;
            case Direction.WEST:
                angle += 270;
                break;
            case Direction.EAST:
                angle += 90;
                break;
            default:
                break;
        }
		poseStack.translate(new Vec3(0.5,0.5,0.5));
        poseStack.rotate(Axis.YN.rotationDegrees(angle - state.getAngle()));
		poseStack.translate(new Vec3(-0.5,-0.5,-0.5));
		poseStack.translate(state.getPos());
		state.blockModelRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

		poseStack.popPose();
    }
}
