package me.redredstone.Signalling.commands;

import me.redredstone.Signalling.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SignalCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	
	private Main plugin;
	public SignalCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("signal").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("No. I am not dealing with you");
			return true;
		}
		Player plr = (Player) sender;
		if (plr.isOp()) {
			if (!(args.length == 0)) {
				String action = args[0];
				if (action.toLowerCase().equals("add")) {
					if (args.length < 2) {
						plr.sendMessage("[Signalling] You need more than this");
					} else {
						
						Location l = plr.getLocation();
						String item = args[1];
						if (item.toLowerCase().equals("signal")) {
							try {
								//Location
								int plrLoc = plr.getLocation().getBlockY();
								Location origin = plr.getEyeLocation();
								
								int remove = plrLoc - origin.getBlockY();
								
								Vector dir = origin.getDirection();
								Location center = origin.clone().add(dir).add(0,remove,0);
								
								
								//Rotation
								Location rotated = origin.clone();
								rotated.setPitch(0);
								rotated.setYaw(origin.getYaw());
								Vector rot = rotated.getDirection();
								
								//Building a signal
								Location blockLocation = center.clone().add(rot);
								
								blockLocation.getBlock().setType(Material.OAK_FENCE);
								blockLocation.add(0,1,0).getBlock().setType(Material.BLACK_CONCRETE);
								blockLocation.add(0,1,0).getBlock().setType(Material.BLACK_CONCRETE);
								
								//Item frames
								ItemFrame frame1 = (ItemFrame) plr.getWorld().spawnEntity(blockLocation.add(dir), EntityType.ITEM_FRAME);
								ItemFrame frame2 = (ItemFrame) plr.getWorld().spawnEntity(blockLocation.add(0,-1,0), EntityType.ITEM_FRAME);
								
								//Item frame properties
								ItemStack red = new ItemStack(Material.RED_CONCRETE);
								ItemStack black = new ItemStack(Material.BLACK_CONCRETE);
								
								//Set to Invulnerable
								frame1.setInvulnerable(true);
								frame2.setInvulnerable(true);
								
								//Set to invisible
								frame1.setVisible(false);
								frame2.setVisible(false);
								
								//Set the aspect of the signal
								frame1.setItem(black);
								frame2.setItem(red);
								
								//Rotate the frame
								frame1.setRotation(Rotation.CLOCKWISE_45);
								frame2.setRotation(Rotation.CLOCKWISE_45);
								
								plr.sendMessage(ChatColor.GREEN + "[Signalling] You have created a signal at " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());
								
							}
							catch(Exception e) {
								plr.sendMessage(ChatColor.RED + "[AUTOMATED MESSAGE: SIGNALLING] An internal error has been detected! Please report this to the Server Technician.");
								return false;
							}
						} else if (item.toLowerCase().equals("sensor")) {
							try {
								plr.sendMessage(ChatColor.GREEN + "[Signalling] You have created a sensor at " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());
								Location blockLocation = plr.getLocation().add(0,-1,0);
								blockLocation.getBlock().setType(Material.BLUE_CONCRETE);
							}
							catch(Exception e) {
								plr.sendMessage(ChatColor.RED + "[AUTOMATED MESSAGE: SIGNALLING] An internal error has been detected! Please report this to the Server Technician");
							}
						} else {
							plr.sendMessage(ChatColor.RED + "[Signalling] You are invalid");
						}
						
					}
					return true;
				} else if (action.toLowerCase().equals("remove")) {
					if (args.length < 2) {
						plr.sendMessage(ChatColor.RED + "[Signalling] You need more than this");
					} else {
						plr.sendMessage(ChatColor.GREEN + "[Signalling] This feature is not done yet.");
					}
					return true;
				} else if (action.toLowerCase().equals("set")) {
					if (args.length < 2) {
						plr.sendMessage(ChatColor.RED + "[Signalling] You need more than this");
					} else {
						plr.sendMessage(ChatColor.GREEN + "[Signalling] This feature is not done yet.");
					}
					return true;
				} else {
					plr.sendMessage(ChatColor.RED + "[Signalling] Can you please explain what this is meant to be?");
					return true;
				}
			} else {
				plr.sendMessage(ChatColor.RED + "[Signalling] You need to give me some arguments");
				plr.sendMessage(ChatColor.RED + "[Signalling] /signal <action> <item> <SignalID>");
			}
			return false;
		} else {
			plr.sendMessage(ChatColor.RED + "Leave this command alone you don't have anything to do with it");
		}
		return true;
	}
}
