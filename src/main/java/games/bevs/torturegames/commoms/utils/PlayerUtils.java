package games.bevs.torturegames.commoms.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerUtils
{
	public static void dropInventory(Player player)
	{
		PlayerInventory inv = player.getInventory();
		for(ItemStack item : inv.getContents())
			if(item != null)
				player.getWorld().dropItemNaturally(player.getLocation(), item);
		
		for(ItemStack item : inv.getArmorContents())
			player.getWorld().dropItemNaturally(player.getLocation(), item);
		
		inv.clear();
		inv.setArmorContents(new ItemStack[4]);
	}
}
