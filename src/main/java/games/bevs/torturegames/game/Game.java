package games.bevs.torturegames.game;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.torturegames.TortureGamesPlugin;
import games.bevs.torturegames.managers.PlayerManager;
import lombok.Getter;

public class Game implements Runnable
{
	@Getter
	private PlayerManager playerManager;
	
	public Game(TortureGamesPlugin plugin, PlayerManager playerManager)
	{
		this.playerManager = playerManager;
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 20l, 20l);
	}
	
	public boolean hasWinner(List<Player> alivePlayers)
	{
		if(alivePlayers.size() == 1)
			return true;
		return false;
	}
	
	public boolean isEmpty(List<Player> alivePlayers)
	{
		if(alivePlayers.size() == 0)
			return true;
		return false;
	}

	@Override
	public void run() 
	{
		//don't do anything if we don't have enough players
		if(Bukkit.getOnlinePlayers().size() < 2)
			return;
		
		List<Player> alivePlayers = this.getPlayerManager().getAlivePlayers();
		
		if(this.hasWinner(alivePlayers))
		{
			Bukkit.broadcastMessage(CC.red + alivePlayers.get(0).getName() + " has won");
		}
	}
}
