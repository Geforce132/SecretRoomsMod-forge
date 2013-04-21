package mods.SecretRoomsMod.blocks;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.EnumMobType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCamoPlateWeighted extends BlockCamoPlate
{
	private int	maxWeight;

	public BlockCamoPlateWeighted(int par1, int weight)
	{
		super(par1, false);
		maxWeight = weight;
	}
	
	@Override
	public Icon getIcon(int i, int meta)
	{
		if (i == 1)
			return Block.planks.getBlockTextureFromSide(i);

		if (maxWeight > 100 && i == 3)
			return Block.blockIron.getBlockTextureFromSide(i);
		else if (i == 3)
			return Block.blockGold.getBlockTextureFromSide(i);

		return this.blockIcon;
	}

	protected int getCurrentWeight(World world, int x, int y, int z)
	{
		int l = 0;
		List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, this.getSensetiveAABB(x, y, z));

		if (list == null)
			return 0;

		Iterator<EntityItem> it = list.iterator();

		while (it.hasNext())
		{
			EntityItem entityitem = it.next();
			l += entityitem.getEntityItem().stackSize;

			if (l >= this.maxWeight)
			{
				break;
			}
		}

		if (l <= 0)
			return 0;

		float f = (float) Math.min(this.maxWeight, l) / (float) this.maxWeight;
		return MathHelper.ceiling_float_int(f * 15.0F);
	}
	
	protected int getPowerFromMeta(int meta)
	{
		return meta;
	}

	protected int getMetaFromWeight(int weight)
	{
		return weight;
	}

}