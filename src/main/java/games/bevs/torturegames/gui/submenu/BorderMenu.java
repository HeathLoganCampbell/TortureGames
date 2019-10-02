package games.bevs.torturegames.gui.submenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.minecraftbut.commons.gui.Menu;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;

public class BorderMenu extends Menu
{

	public BorderMenu(JavaPlugin plugin) {
		super("Border Menu", 9 * 6);
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		drawMenu();
	}

	public void drawMenu()
	{
		addBorderOption(13, "Grow", 150);
		addBorderOption(14, "Grow", 50);
		addBorderOption(15, "Grow", 5);
		
		addBorderOption(13 + 9, "Shrink", -150);
		addBorderOption(14 + 9, "Shrink", -50);
		addBorderOption(15 + 9, "Shrink", -5);
	}
	
	private void addBorderOption(int slot, String action, int amount)
	{
		int id = 1;
		if(Math.abs(amount) >= 50) id = 2;
		if(Math.abs(amount) >= 150) id = 3;
		
		this.setIcon(slot, new ItemStackBuilder(Material.IRON_BARDING).amount(id).displayName(CC.b + action + " border " + amount + " blocks"), (player) -> {
			World world = player.getWorld();
			WorldBorder border = world.getWorldBorder();
			border.setSize(border.getSize() + amount);
		});
	}
}
