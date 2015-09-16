package co.ryred.uuidcredits;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 16/09/2015.
 */
public class CreditsBungee
{

	public static void initBungee( final Plugin plugin )
	{

		if ( Credits.inited || Credits.checkFile() ) return;

		ProxyServer.getInstance().getPluginManager().registerListener( plugin, new BungeeListener() );
		ProxyServer.getInstance().getScheduler().runAsync( plugin, new UserGetter() );

		Credits.inited = true;

	}

}
