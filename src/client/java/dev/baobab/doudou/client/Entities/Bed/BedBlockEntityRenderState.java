package dev.baobab.doudou.client.Entities.Bed;

import dev.baobab.doudou.Entities.Bed.BedType;
import dev.baobab.doudou.client.DoudouClient;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class BedBlockEntityRenderState extends BlockEntityRenderState {
	
	public final BlockModelRenderState blockModelRenderState = new BlockModelRenderState();
	
	private Direction direction = Direction.NORTH;
	private Vec3 pos = DoudouClient.CONFIG.doudouOffset;
	private Vec3 posStrawBed = DoudouClient.CONFIG.doudouOffsetStrawBed;
	private float angle = DoudouClient.CONFIG.doudouYRotation;
	private BedType bedType = BedType.BED;

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	public void setBedType(BedType bedType)
	{
		this.bedType = bedType;
	}

	public Vec3 getPos()
	{
		switch (this.bedType) {
			case BED:
				return pos;
			case STRAW_BED:
				return posStrawBed;
			default:
				break;
		}
		return pos;
	}

	public float getAngle()
	{
		return this.angle;
	}
}
