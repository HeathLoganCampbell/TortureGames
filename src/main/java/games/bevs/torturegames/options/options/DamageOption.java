package games.bevs.torturegames.options.options;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import games.bevs.torturegames.options.Option;

public class DamageOption extends Option
{
	public static final String OPTION_NAME = "Damage";
	public static final String OPTION_ID = OPTION_NAME.toLowerCase().replace(" ", "_");
	private static final ItemStackBuilder OPTION_ICON = new ItemStackBuilder(Material.GRILLED_PORK);

	public DamageOption() 
	{
		super(OPTION_NAME, OPTION_ICON);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e)
	{
		if(!this.isEnabled())
			e.setCancelled(true);
	}
}
