package gigabit101.simplechunkloader.block;

import me.modmuss50.jsonDestroyer.api.ITexturedBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import reborncore.RebornCore;

public class BlockChunkLoader extends BlockContainer implements ITexturedBlock, ITileEntityProvider
{
    private final String prefix = "simplechunkloader:blocks/base";

	public BlockChunkLoader(Material materialIn) 
	{
		super(Material.iron);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("simplechunkloader");
		RebornCore.jsonDestroyer.registerObject(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileChunkLoader();
	}
	
	@Override
	public int getRenderType() 
	{
		return 3;
	}

	@Override
	public int amountOfStates() 
	{
		return 1;
	}
	
	@Override
	public String getTextureNameFromState(IBlockState arg0, EnumFacing arg1) 
	{
		return prefix;
	}
}
