package co.ryred.uuidcredits;

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

		plugin.getProxy().getScheduler().runAsync( plugin, new Credits.UserGetter() );
		plugin.getProxy().getPluginManager().registerListener( plugin, new BungeeListener() );

		Credits.inited = true;

	}

}
