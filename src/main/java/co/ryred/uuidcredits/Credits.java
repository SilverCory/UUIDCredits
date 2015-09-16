package co.ryred.uuidcredits;

import com.google.gson.Gson;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 15/09/2015.
 */
public class Credits
{

	public static final Gson gson = new Gson();
	static boolean broken = true;
	static boolean inited = false;
	static HashMap<String, User> userMap;

	public static void initBukkit( Plugin plugin )
	{

		System.out.println( "1." );
		if ( inited || checkFile() ) return;

		System.out.println( "2." );
		Bukkit.getPluginManager().registerEvents( new BukkitListener(), plugin );
		new Thread( new UserGetter() ).start();

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
			textComponents.add( new TextComponent( c( "&dReason:  &9" + user.getReason() + "\n" ) ) );
		}

		if ( user.getProfile() != null ) {
			textComponents.add( new TextComponent( c( "&dProfile: &9" + user.getProfile() + "\n" ) ) );
		}

		textComponents.add( new TextComponent( "\n" ) );
		textComponents.add( new TextComponent( c( "  &cThis user has assisted in the upcoming of one or more\n&c  of the plugins this server uses! Please respect them." ) ) );

		TextComponent tc = new TextComponent( c( "&4&l\u2764\u2764 &eWelcome &o" + name + " &r&e the server! &4&l\u2764\u2764" ) );
		tc.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, textComponents.toArray( new TextComponent[ textComponents.size() ] ) ) );

		return tc;

	}

	protected static String c( String in )
	{
		return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes( '&', in );
	}

}
