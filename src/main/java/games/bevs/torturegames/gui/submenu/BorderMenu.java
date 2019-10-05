package games.bevs.torturegames.gui.submenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.minecraftbut.commons.gui.Menu;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import lombok.Getter;

public class BorderMenu extends Menu
{
	@Getter
	private boolean instantMode = false;
	
	private int redraws = 0;
	
	public BorderMenu(JavaPlugin plugin) {
		super("Border Menu", 9 * 6);
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		drawMenu();
	}

	public void drawMenu()
	{
		setInstantModeIcon(11);
		
		updateBorderOptions();
	}
	
	private void updateBorderOptions()
	{
		
		redraws++;
		addBorderOption(13, "Grow", 150);
		addBorderOption(14, "Grow", 50);
		addBorderOption(15, "Grow", 5);
		
		addBorderOption(13 + 9, "Shrink", -150);
		addBorderOption(14 + 9, "Shrink", -50);
		addBorderOption(15 + 9, "Shrink", -5);
	}
	
	private void setInstantModeIcon(int slot)
	{
		this.setIcon(slot, new ItemStackBuilder(Material.SUGAR).displayName(CC.b + (this.instantMode ? CC.green : CC.red) + "Instant Mode").addLore(CC.gray + "instantmode will make the border move instantly"), (player) -> {
			this.instantMode = !this.instantMode;
			setInstantModeIcon(slot);
			updateBorderOptions();
			Bukkit.broadcastMessage(CC.gray + "redraw menu");
		});
	}
	
	
	private void addBorderOption(int slot, String action, int amount)
	{
		int id = 1;
		if(Math.abs(amount) >= 50) id = 2;
		if(Math.abs(amount) >= 150) id = 3;
		
		//other methods don't work
		if(this.isInstantMode())
		{
			this.setIcon(slot, new ItemStackBuilder(Material.IRON_FENCE).amount(id).displayName(CC.b + action + " border " + amount + " blocks"), (player) -> {
				World world = player.getWorld();
				WorldBorder border = world.getWorldBorder();
				border.setSize(border.getSize() + amount);
			});
		}
		else
		{
			this.setIcon(slot, new ItemStackBuilder(Material.IRON_FENCE).amount(id).displayName(CC.b + action + " border " + amount + " blocks"), (player) -> {
				World world = player.getWorld();
				WorldBorder border = world.getWorldBorder();
				border.setSize(border.getSize() + amount, Math.abs(amount) / 5);
			});
		}
	}
}
