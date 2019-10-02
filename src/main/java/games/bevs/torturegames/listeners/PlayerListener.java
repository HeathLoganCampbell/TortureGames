package games.bevs.torturegames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import games.bevs.torturegames.managers.PlayerManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerListener implements Listener
{
	@Getter @NonNull
	private PlayerManager playerManager;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		this.playerManager.onConnect(player);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		Player player = e.getPlayer();
		this.playerManager.onDisconnect(player);
	}
	
	@EventHandler
	public void onQuit(PlayerKickEvent e)
	{
		Player player = e.getPlayer();
		this.playerManager.onDisconnect(player);
	}
}
