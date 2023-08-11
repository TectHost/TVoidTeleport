package minealex.tvoidteleport;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import minealex.tvoidteleport.commands.Commands;
import minealex.tvoidteleport.events.Void;

public class TVoidTeleport extends JavaPlugin{
	public String rutaConfig;
	PluginDescriptionFile pdffile = getDescription();
	public String version = pdffile.getVersion();
	public String nombre = ChatColor.BLUE+"["+pdffile.getName()+ChatColor.BLUE+"]";
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.GREEN+" Has been activated (version: "+version+")");
		registerCommands();
		registerEvents();
		registerConfig();
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.RED+" Has been deactivated (version: "+version+")");
	}
	
	public void registerCommands() {
		this.getCommand("tvt").setExecutor(new Commands(this));
	}
	
	public void registerEvents() {
		FileConfiguration config = getConfig();
		getServer().getPluginManager().registerEvents(new Void(config), this);
	}
	
	public void registerConfig() {
		File config = new File(this.getDataFolder(),"config.yml");
		rutaConfig = config.getPath();
		if(!config.exists()) {
			this.getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}
}
