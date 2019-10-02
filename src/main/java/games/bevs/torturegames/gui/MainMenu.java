package games.bevs.torturegames.gui;

import org.bukkit.Material;

import games.bevs.minecraftbut.commons.gui.Menu;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import games.bevs.torturegames.managers.OptionManager;
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

	public MainMenu(OptionManager optionManager)
	{
		super("Torture Games, Main Menu", 9 * 6);
		
		this.optionManager = optionManager;
		
		this.drawMenu();
	}

	public void drawMenu()
	{
		//Options
		this.drawIcon(3, this.optionManager.getOption(BlockBreakOption.OPTION_ID));
		this.drawIcon(4, this.optionManager.getOption(PVPOption.OPTION_ID));
		this.drawIcon(5, this.optionManager.getOption(DamageOption.OPTION_ID));
		
		
		//Border
		this.setIcon(19, new ItemStackBuilder(Material.IRON_BARDING).displayName(CC.b + "Border Menu"));
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
