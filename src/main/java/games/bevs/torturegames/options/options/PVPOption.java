package games.bevs.torturegames.options.options;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import games.bevs.torturegames.options.Option;

public class PVPOption extends Option
{
	public static final String OPTION_NAME = "PVP";
	public static final String OPTION_ID = OPTION_NAME.toLowerCase().replace(" ", "_");
	private static final ItemStackBuilder OPTION_ICON = new ItemStackBuilder(Material.IRON_SWORD);

	public PVPOption() 
	{
		super(OPTION_NAME, OPTION_ICON);
	}
	
	@EventHandler
	public void onPVP(EntityDamageByEntityEvent e)
	{
		if(!(e.getDamager() instanceof Player && e.getEntity() instanceof Player))
			return;
		
		if(!this.isEnabled())
			e.setCancelled(true);
	}
}
