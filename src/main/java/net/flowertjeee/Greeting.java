package net.flowertjeee;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;

public class Greeting extends JavaPlugin implements Listener {
    
    private HashSet<UUID> joinedPlayers = new HashSet<>();
    
    @Override
    public void onEnable() {
        saveDefaultConfig(); // Loads config file: src/main/resources/config.yml
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = getConfig(); // Uses config file: src/main/resources/config.yml
        UUID playerUUID = event.getPlayer().getUniqueId();

        String message;
        if (!joinedPlayers.contains(playerUUID)) {
            joinedPlayers.add(playerUUID);
            message = ChatColor.translateAlternateColorCodes('&', config.getString("messages.first_join"));
        } else {
            message = ChatColor.translateAlternateColorCodes('&', config.getString("messages.returning_player"));
        }
        event.getPlayer().sendMessage(message);
    }
}
