package games.bevs.torturegames.managers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.torturegames.options.Option;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OptionManager
{
	private HashMap<String, Option> options = new HashMap<>();

	@NonNull
	private JavaPlugin plugin;
	
	public void registerOption(Option option)
	{
		this.options.put(option.getNameId(), option);
		Bukkit.getPluginManager().registerEvents(option, this.plugin);
	}
	
	public Option getOption(String nameId)
	{
		return this.options.get(nameId);
	}
}
