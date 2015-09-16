package co.ryred.uuidcredits;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 15/09/2015.
 */
public class Credits
{

	protected static final Gson gson = new Gson();
	protected static boolean broken = true;
	protected static boolean inited = false;
	protected static HashMap<String, User> userMap;

	public static void initBukkit( final JavaPlugin plugin )
	{

		System.out.println( "1." );
		if ( inited || checkFile() ) return;

		System.out.println( "2." );
		plugin.getServer().getPluginManager().registerEvents( new BukkitListener(), plugin );
		plugin.getServer().getScheduler().runTaskAsynchronously( plugin, new UserGetter() );

		inited = true;

	}

	protected static boolean checkFile()
	{
		return new File( "ryred_co" ).exists();
	}

	protected static TextComponent formatUser( String name, User user )
	{

		ArrayList<TextComponent> textComponents = new ArrayList<>();

		if ( user.getReason() != null ) {
			textComponents.add( new TextComponent( c( "&dReason:  &9" + user.getReason() ) ) );
		}

		if ( user.getProfile() != null ) {
			textComponents.add( new TextComponent( c( "&dProfile: &9" + user.getProfile() ) ) );
		}

		textComponents.add( new TextComponent( "" ) );
		textComponents.add( new TextComponent( c( "  &cThis user has assisted in the upcoming of one or more of the plugins this server uses! Please respect them." ) ) );

		TextComponent tc = new TextComponent( c( "&4&l?? &eWelcome &o" + name + " &r&e the server! &4&l??" ) );
		tc.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, textComponents.toArray( new TextComponent[ textComponents.size() ] ) ) );

		return tc;

	}

	protected static String c( String in )
	{
		return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes( '&', in );
	}

	protected static class UserGetter implements Runnable
	{
		@Override
		public void run()
		{
			try {
				URL url = new URL( "http://uuid.ryred.co/?min" );
				Type listType = new TypeToken<HashMap<String, User>>() {}.getType();
				userMap = gson.fromJson( new Scanner( url.openStream(), "UTF-8" ).useDelimiter( "\\A" ).next(), listType );
				System.out.println( "Size: " + userMap.size() );
				broken = false;
			} catch ( java.io.IOException e ) {
				broken = true;
				e.printStackTrace();
			}
		}
	}

}
