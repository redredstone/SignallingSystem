package me.redredstone.Signalling;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.redredstone.Signalling.commands.SignalCommand;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new SignalCommand(this);
		
		//Load Data
		File signallingData = new File(getDataFolder(), "Data.yml");
		
		if (!(signallingData.exists())) {
			saveResource("Data.yml", false);
		}
	    
	}
	
	public void onDisable() {
		//Save data
		try {
			File signallingData = new File(getDataFolder(), "Data.yml");
			YamlConfiguration yamlData = YamlConfiguration.loadConfiguration(signallingData);
			
			yamlData.save(signallingData);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Signals
	
	public void getSignal(String signalID) {
		
	}
	
	public void addSignal(String signalID, Location signalLocation) {
		YamlConfiguration yaml = new YamlConfiguration();
		File signallingData = new File(getDataFolder(), "Data.yml");
		
		yaml.createSection("signallingData");
		yaml.createSection("signallingData.signals");
		
		ArrayList<Object> signalInfo = new ArrayList<Object>();
		
		signalInfo.add(signalLocation.getBlockX());
		signalInfo.add(signalLocation.getBlockY());
		signalInfo.add(signalLocation.getBlockZ());
		signalInfo.add(signalLocation.getPitch());
		signalInfo.add(signalLocation.getYaw());
		
		yaml.set("signallingData.signals." + signalID, signalInfo);
		try {
			yaml.save(signallingData);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Sensors
	
	public void addSensor(String sensorID, Location sensorLocaiton) {
		YamlConfiguration yaml = new YamlConfiguration();
		File signallingData = new File(getDataFolder(), "Data.yml");
		
		yaml.createSection("signallingData");
		yaml.createSection("signallingData.signals");
		
		ArrayList<Object> signalInfo = new ArrayList<Object>();
		
		signalInfo.add(sensorLocaiton.getBlockX());
		signalInfo.add(sensorLocaiton.getBlockY());
		signalInfo.add(sensorLocaiton.getBlockZ());
		signalInfo.add(sensorLocaiton.getPitch());
		signalInfo.add(sensorLocaiton.getYaw());
		
		yaml.set("signallingData.signals." + sensorID, signalInfo);
		try {
			yaml.save(signallingData);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setProperty(String type) {
		if (type.toLowerCase().equals("signal")) {
			
		} else if (type.toLowerCase().equals("")) {
			
		}
	}
}

