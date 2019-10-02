package games.bevs.torturegames.gui;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.minecraftbut.commons.gui.Menu;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import games.bevs.torturegames.gui.submenu.BorderMenu;
import games.bevs.torturegames.managers.OptionManager;
import games.bevs.torturegames.managers.PlayerManager;
import games.bevs.torturegames.options.Option;
import games.bevs.torturegames.options.options.BlockBreakOption;
import games.bevs.torturegames.options.options.DamageOption;
import games.bevs.torturegames.options.options.PVPOption;

/**
 * 
 *  Inventory slots
 *   0 1 2 3 4 5 6 7 8
 *   9 ...
 * 
 *   slot 3, Damage Enabled
 *   slot 4, Block break
 * 	 Slot 5, PVP Enabled
 * 
 * 	 slot 19, play
 *   
 *   slot 25, worldborder
 * @author Sprock
 */
public class MainMenu extends Menu
{
	private OptionManager optionManager;
	private PlayerManager playerManager;
	private BorderMenu borderMenu;
	
	private JavaPlugin plugin;

	public MainMenu(JavaPlugin plugin, OptionManager optionManager, PlayerManager playerManager)
	{
		super("Torture Games, Main Menu", 9 * 6);
		
		this.optionManager = optionManager;
		this.playerManager = playerManager;
		this.borderMenu = new BorderMenu(plugin);
		
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		this.drawMenu();
	}

	public void drawMenu()
	{
		//Options
		this.drawIcon(3, this.optionManager.getOption(BlockBreakOption.OPTION_ID));
		this.drawIcon(4, this.optionManager.getOption(PVPOption.OPTION_ID));
		this.drawIcon(5, this.optionManager.getOption(DamageOption.OPTION_ID));
		
		
		//Border
		this.setIcon(19, new ItemStackBuilder(Material.IRON_BARDING).displayName(CC.b + "Border Menu"), (player) -> {
			this.borderMenu.open(player);
		});
		
		//Spawn
		this.setIcon(20, new ItemStackBuilder(Material.SKULL_ITEM).displayName(CC.b + "Respawn Dead"), (player) -> {
			long delayedTicks = 1l;
			int playersTeleported = 0;
			
			Iterator<? extends Player> playersIt = Bukkit.getOnlinePlayers().iterator();
			while(playersIt.hasNext())
			{
				Player gamer = playersIt.next();
				
				if(gamer.getGameMode() == GameMode.CREATIVE)
					return;
				
				if(gamer.getGameMode() == GameMode.SURVIVAL)
					return;
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
					this.playerManager.respawn(gamer);
				}, delayedTicks);
				
				playersTeleported++;
				if(playersTeleported % 10 == 0)
					delayedTicks += 2;
			}
			
		});
		
		this.setIcon(21, new ItemStackBuilder(Material.COMMAND).displayName(CC.b + "Teleport all alive"), (player) -> {
			long delayedTicks = 1l;
			int playersTeleported = 0;
			Iterator<? extends Player> playersIt = Bukkit.getOnlinePlayers().iterator();
			while(playersIt.hasNext())
			{
				Player gamer = playersIt.next();
				
				if(gamer.getGameMode() != GameMode.SURVIVAL)
					return;
				
				Location location = player.getLocation();
				Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
					gamer.teleport(location);
				}, delayedTicks);
				
				playersTeleported++;
				if(playersTeleported % 10 == 0)
					delayedTicks += 2;
			}
			
		});
	}
	
	public void drawIcon(int slot, Option option)
	{
		ItemStackBuilder icon = option.getIcon().clone();
		icon.displayName((option.isEnabled() ? CC.green : CC.red) + option.getDisplayName());
		
		this.setIcon(slot, icon, (player) -> {
			if(option.isEnabled())
			{
				//disable it with click
				option.disable();
				this.drawIcon(slot, option);
			}
			else
			{
				//enable when clicked
				option.enable();
				this.drawIcon(slot, option);
			}
		});
	}
}
