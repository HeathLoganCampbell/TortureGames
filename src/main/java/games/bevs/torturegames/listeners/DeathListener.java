package games.bevs.torturegames.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import games.bevs.torturegames.managers.PlayerManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeathListener implements Listener
{
	@Getter @NonNull
	private PlayerManager playerManager;
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onLastDamage(EntityDamageEvent e)
	{
		if(!(e.getEntity() instanceof Player))
			return;
		Player player = (Player) e.getEntity();
		
		if(e.getFinalDamage() < player.getHealth())
			return;
		
		if(player.getGameMode() != GameMode.SURVIVAL)
			return;
		
		Player killer = null;
		if(e instanceof EntityDamageByEntityEvent)
		{
			EntityDamageByEntityEvent murderedEvent = (EntityDamageByEntityEvent) e;
			if(murderedEvent.getDamager() instanceof Player)
			{
				killer = (Player) murderedEvent.getDamager();
			}
			else if(murderedEvent.getDamager() instanceof Projectile)
			{
				Projectile projectile = (Projectile) murderedEvent.getDamager();
				if(projectile.getShooter() instanceof Player)
					killer = (Player) projectile.getShooter() ;
			}
		}
		
		this.getPlayerManager().die(player, killer, e.getCause());
		e.setCancelled(true);
	}
}
