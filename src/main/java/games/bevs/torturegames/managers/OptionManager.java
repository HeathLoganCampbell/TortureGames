package games.bevs.torturegames.managers;

import java.util.HashMap;

import games.bevs.torturegames.options.Option;

public class OptionManager
{
	private HashMap<String, Option> options = new HashMap<>();
	
	public OptionManager()
	{
		
	}
	
	public void registerOption(Option option)
	{
		this.options.put(option.getNameId(), option);
	}
	
	public Option getOption(String nameId)
	{
		return this.options.get(nameId);
	}
}
