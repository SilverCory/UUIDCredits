package co.ryred.uuidcredits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BukkitListener implements Listener
{

	@EventHandler
	public void onJoin( PlayerJoinEvent e )
	{

		if ( Credits.broken ) return;

		String uuidString = e.getPlayer().getUniqueId().toString().replace( "-", "" );
		if ( Credits.userMap.containsKey( uuidString ) ) {
			e.setJoinMessage( null );

			try {
				Class.forName( "net.md_5.bungee.api.chat.TextComponent" );
				try {
					Bukkit.spigot().broadcast( Credits.formatUser( e.getPlayer().getName(), Credits.getUserMap().get( uuidString ) ) );
				} catch ( Exception ex ) {}
			} catch ( ClassNotFoundException ex ) {
				e.setJoinMessage( ChatColor.translateAlternateColorCodes( '&', "&4&l\u2764\u2764 &eWelcome &d&o" + e.getPlayer().getName() + " &r&ethe server! &4&l\u2764\u2764" ) );
			}
		}

	}

}
