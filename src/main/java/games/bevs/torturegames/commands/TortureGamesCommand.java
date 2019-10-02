package games.bevs.torturegames.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import games.bevs.torturegames.gui.MainMenu;
import games.bevs.torturegames.managers.OptionManager;

/**
 * 
 * ./TG, will pop up with the gui
 * 
 * @author Sprock
 */
public class TortureGamesCommand extends Command
{
	private MainMenu mainMenu;
	
	public TortureGamesCommand(OptionManager optionManager) {
		super("TortureGames", 
			"Pops up with the gui to edit the set up",
			"/TortureGames", Arrays.asList("tg"));
		
		this.mainMenu = new MainMenu(optionManager);
	}
	
	@Override
	public boolean execute(CommandSender sender, String cmd, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			this.mainMenu.open(player);
		}
		return false;
	}

}
