package games.bevs.torturegames.options.options;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import games.bevs.torturegames.options.Option;

public class BlockBreakOption extends Option
{
	public static final String OPTION_NAME = "Block Break";
	public static final String OPTION_ID = OPTION_NAME.toLowerCase().replace(" ", "_");
	private static final ItemStackBuilder OPTION_ICON = new ItemStackBuilder(Material.DIRT);

	public BlockBreakOption() 
	{
		super(OPTION_NAME, OPTION_ICON);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
			return;
		
		if(!this.isEnabled())
			e.setCancelled(true);
	}
}
