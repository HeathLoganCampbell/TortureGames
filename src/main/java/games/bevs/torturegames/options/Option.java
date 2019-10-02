package games.bevs.torturegames.options;

import org.bukkit.event.Listener;

import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Option implements Listener
{
	private String displayName;
	private String nameId;
	
	private ItemStackBuilder icon;
	
	private boolean enabled = false;

	public Option(String displayName, ItemStackBuilder icon) 
	{
		this.displayName = displayName;
		this.nameId = displayName.toLowerCase().replaceAll(" ", "_");
		
		this.icon = icon;
	}
	
	public void enable()
	{
		
	}
	
	public void disable()
	{
		
	}
}
