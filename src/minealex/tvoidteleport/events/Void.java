package minealex.tvoidteleport.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import java.util.List;

public class Void implements Listener {
    private final FileConfiguration config;

    public Void(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double y = player.getLocation().getY();

        // Si el jugador está en el aire (en el vacío) y por debajo de Y=0
        if (y < 0 && !player.isInsideVehicle()) {
            // Obtener la lista de comandos desde la configuración
            List<String> commands = config.getStringList("Config.Void.commands");

            // Ejecutar cada comando desde la consola
            for (String command : commands) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("@p", player.getName()));
            }
        }
    }
}
