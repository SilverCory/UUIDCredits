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

		System.out.println( "5." );
		if ( Credits.broken ) return;

		System.out.println( "6." );
		String uuidString = e.getPlayer().getUniqueId().toString().replace( "-", "" );
		if ( Credits.userMap.containsKey( uuidString ) ) {
			e.setJoinMessage( null );

			System.out.println( "7." );
			try {
				Class.forName( "net.md_5.bungee.api.chat.TextComponent" );
				try {
					Bukkit.spigot().broadcast( Credits.formatUser( e.getPlayer().getName(), Credits.userMap.get( uuidString ) ) );
				} catch ( Exception ex ) {ex.printStackTrace();}
			} catch ( ClassNotFoundException ex ) {
				ex.printStackTrace();
				e.setJoinMessage( ChatColor.translateAlternateColorCodes( '&', "&4&\u2764\u2764 &eWelcome &o" + e.getPlayer().getName() + " &r&e the server! &4&l\u2764\u2764" ) );
			}
		}
	}

}