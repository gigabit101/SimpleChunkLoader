package gigabit101.simplechunkloader;

import gigabit101.simplechunkloader.block.BlockChunkLoader;
import gigabit101.simplechunkloader.block.TileChunkLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import reborncore.common.util.CraftingHelper;

@Mod(modid = "SimpleChunkLoader", name = "SimpleChunkLoader", version = "1.0.0", dependencies = "required-after:reborncore")
public class SimpleChunkLoader 
{
	@Mod.Instance
	public static SimpleChunkLoader INSTANCE;
	
	public static Block chunkloader;
	
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		chunkloader = new BlockChunkLoader(Material.iron);
		GameRegistry.registerBlock(chunkloader, chunkloader.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileChunkLoader.class, "simplechunkloader");
 		ForgeChunkManager.setForcedChunkLoadingCallback(INSTANCE, new ChunkLoadingHandler());
 		
        CraftingHelper.addShapedOreRecipe(new ItemStack(chunkloader, 4),
                "III",
                "ILI",
                "III",
                'I', Items.iron_ingot,
                'L', Items.dye, 4);
	}
}
