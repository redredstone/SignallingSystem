package me.redredstone.Signalling;

import org.bukkit.plugin.java.JavaPlugin;

import me.redredstone.Signalling.commands.SignalCommand;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new SignalCommand(this);
		
		//Load Data
	}
	
	public void onDisable() {
		//Save data
	}
}

