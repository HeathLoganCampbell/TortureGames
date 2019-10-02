package games.bevs.torturegames.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.torturegames.commoms.utils.PlayerUtils;

public class PlayerManager
{
	public void respawn(Player player)
	{
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20);
		player.setSaturation(10f);
		player.setLevel(0);
		player.setExp(0);
		player.setFallDistance(0);
		player.setFireTicks(0);
		//Spawn player
		this.spawn(player);
	}
	
	public void spawn(Player player)
	{
		World world = player.getWorld();
		WorldBorder border = world.getWorldBorder();
		Location borderCenter = border.getCenter();
		double size = 32;
		
		int x = MathUtils.getRandom().nextInt((int) size) - (int) ( size / 2);
		int z = MathUtils.getRandom().nextInt((int) size) - (int) ( size / 2);
		
		Block heightYBlock = world.getHighestBlockAt(borderCenter.getBlockX() + x, borderCenter.getBlockZ()+ z);
		Location location = heightYBlock.getLocation().add(0, 3.5, 0);
		
		player.teleport(location);
	}
	
	public void die(Player player, Player killer, DamageCause damageCause)
	{
		PlayerUtils.dropInventory(player);
		//
		player.setHealth(20);
		player.setGameMode(GameMode.SPECTATOR);
		
		this.broadcastDeath(player, killer);
	}
	
	public void broadcastDeath(Player player, Player killer)
	{
		if(killer == null)
		{
			Bukkit.broadcastMessage(CC.aqua + player.getName() + " died");
		}
		else
		{
			Bukkit.broadcastMessage(CC.aqua + player.getName() + " was slayed by " + killer.getName());
		}
		Bukkit.broadcastMessage(CC.aqua + this.getAlivePlayers().size() + " players remaining.");
	}
	
	public List<Player> getAlivePlayers()
	{
		return Bukkit.getOnlinePlayers()
				.stream()
				.filter(player -> player.getGameMode() == GameMode.SURVIVAL)
				.collect(Collectors.toList());
	}
	
	public void onConnect(Player player)
	{
		
	}
	
	public void onDisconnect(Player player)
	{
		if(player.getGameMode() == GameMode.SURVIVAL)
		{
			this.die(player, null, DamageCause.CUSTOM);
		}
	}
}
