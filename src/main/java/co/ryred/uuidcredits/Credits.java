package co.ryred.uuidcredits;

import com.google.gson.Gson;
import lombok.Getter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentHashMap;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 15/09/2015.
 */
public abstract class Credits
{

	public static final Gson gson = new Gson();

	@Getter
	static final ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();

	@Getter
	static boolean broken = true;

	@Getter
	static boolean inited = false;

	@Deprecated
	public static void initBukkit( Credits responder )
	{

		if ( inited || checkFile() ) return;
		inited = true;
		responder.startBukkit( new BukkitListener(), new UserGetter() );

	}

	@Deprecated
	public static void initBungee( Credits responder )
	{

		if ( inited || checkFile() ) return;
		inited = true;
		responder.startBungee( new BungeeListener(), new UserGetter() );

	}

	static boolean checkFile()
	{
		return new File( "ryred_co" ).exists();
	}

	static TextComponent formatUser( String name, User user )
	{

		ArrayList<TextComponent> textComponents = new ArrayList<>();

		if ( user.getReason() != null ) {
			textComponents.add( new TextComponent( c( "&dReason:  &9" + user.getReason() + "\n" ) ) );
		}

		if ( user.getProfile() != null ) {
			textComponents.add( new TextComponent( c( "&dProfile: &9" + user.getProfile() + "\n" ) ) );
		}

		if( user.getProfile() != null || user.getReason() != null )
			textComponents.add( new TextComponent( "\n" ) );
			
		textComponents.add( new TextComponent( c( "  &cThis user has assisted in the upcoming of one or more\n&c  of the plugins this server uses! Please respect them." ) ) );

		TextComponent tc = new TextComponent( c( "&4&l\u2764\u2764 &eWelcome &o" + name + " &r&e the server! &4&l\u2764\u2764" ) );
		tc.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, textComponents.toArray( new TextComponent[ textComponents.size() ] ) ) );

		if ( user.getProfile() != null ) {
			tc.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, user.getProfile() ) );
		}

		return tc;

	}

	static String c( String in )
	{
		return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes( '&', in );
	}

	public void startBukkit( BukkitListener listener, UserGetter getter ) {}

	public void startBungee( BungeeListener listener, UserGetter getter ) {}

}
