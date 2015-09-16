package co.ryred.uuidcredits;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class BungeeListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin( PostLoginEvent e )
	{

		if ( Credits.broken ) return;

		String uuidString = e.getPlayer().getUniqueId().toString().replace( "-", "" );
		if ( Credits.userMap.containsKey( uuidString ) ) {
			try {
				Class.forName( "net.md_5.bungee.api.chat.TextComponent" );
				try {
					ProxyServer.getInstance().broadcast( Credits.formatUser( e.getPlayer().getName(), Credits.userMap.get( uuidString ) ) );
				} catch ( Exception ex ) {}
			} catch ( ClassNotFoundException ex ) {
				ProxyServer.getInstance().broadcast( ChatColor.translateAlternateColorCodes( '&', "&4&l\u2764\u2764 &eWelcome &o" + e.getPlayer().getName() + " &r&e the server! &4&l\u2764\u2764" ) );
			}
		}

	}
}