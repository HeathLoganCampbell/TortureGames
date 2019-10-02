package games.bevs.torturegames;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.torturegames.commands.TortureGamesCommand;
import games.bevs.torturegames.managers.OptionManager;
import games.bevs.torturegames.managers.PlayerManager;

public class TortureGamesPlugin extends JavaPlugin
{
	private OptionManager optionManager;
	private PlayerManager playerManager;
	
	@Override
	public void onEnable()
	{
		this.playerManager = new PlayerManager();
		this.optionManager = new OptionManager();
		
		registerCommands();
		//Player does => spectator mode
		// stop players leaving spawned chunks
		// world border 500
		//GUI
			//Shrink border
			//toggle pvp
			//toggle block break
		
			//Smite all players
			//gamemode all players
			//potion effect all players
			//slap all player into air
			//burn all
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	private CommandMap getCommandMap()
	{
		try{
		    Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
		    commandMapField.setAccessible(true);
		    return (CommandMap) commandMapField.get(Bukkit.getServer());
		}
		catch(Exception exception){
		    exception.printStackTrace();
		}
		 
		return null;
	}
	
	private void registerCommands()
	{
		CommandMap commandMap = this.getCommandMap();
		if(commandMap == null)
		{
			for(int i = 0; i < 20; i++)
				Bukkit.broadcastMessage("");
			
			for(int i = 0; i < 20; i++)
			{
				Bukkit.broadcastMessage(" FUCK, I can't get access to the commandMap");
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("");
			}
			return;
		}
		 
		TortureGamesCommand tortureGamesCMD = new TortureGamesCommand(this, this.optionManager, this.playerManager);
		commandMap.register(tortureGamesCMD.getName(), tortureGamesCMD);
	}
}
