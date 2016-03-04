package gigabit101.simplechunkloader.block;

import java.util.LinkedList;
import java.util.List;

import gigabit101.simplechunkloader.SimpleChunkLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

public class TileChunkLoader extends TileEntity
{
	private ForgeChunkManager.Ticket chunkTicket;

	public List<ChunkCoordIntPair> getLoadArea() 
	{
		List<ChunkCoordIntPair> loadArea = new LinkedList();
		ChunkCoordIntPair chunkCoords = new ChunkCoordIntPair((this.getPos().getX() >> 4), (this.getPos().getZ() >> 4));

		loadArea.add(chunkCoords);
		return loadArea;
	}

	@Override
	public void validate() 
	{
		super.validate();
		if ((!this.worldObj.isRemote) && (this.chunkTicket == null)) 
		{
			ForgeChunkManager.Ticket ticket = ForgeChunkManager.requestTicket(SimpleChunkLoader.INSTANCE, this.worldObj, ForgeChunkManager.Type.NORMAL);
			if (ticket != null) 
			{
				forceChunkLoading(ticket);
			}
		}
	}

	@Override
	public void invalidate() 
	{
		super.invalidate();
		stopChunkLoading();
	}

	public void forceChunkLoading(ForgeChunkManager.Ticket ticket) 
	{
		stopChunkLoading();
		this.chunkTicket = ticket;
		for (ChunkCoordIntPair coord : getLoadArea()) 
		{
			ForgeChunkManager.forceChunk(this.chunkTicket, coord);
		}
	}

	public void unforceChunkLoading() 
	{
		for (Object obj : this.chunkTicket.getChunkList()) 
		{
			ChunkCoordIntPair coord = (ChunkCoordIntPair) obj;
			ForgeChunkManager.unforceChunk(this.chunkTicket, coord);
		}
	}

	public void stopChunkLoading() 
	{
		if (this.chunkTicket != null) 
		{
			ForgeChunkManager.releaseTicket(this.chunkTicket);
			this.chunkTicket = null;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) 
	{
		super.readFromNBT(par1NBTTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) 
	{
		super.writeToNBT(par1NBTTagCompound);
	}
}
