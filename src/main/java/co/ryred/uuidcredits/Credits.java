package co.ryred.uuidcredits;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 15/09/2015.
 */
@SuppressWarnings("unchecked")
public abstract class Credits
{

	public static final Gson gson = new Gson();
	@Getter
	static final ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();
	@Getter
	static boolean broken = true;
	private static final Runnable userGetter = new Runnable()
	{
		@Override
		public void run()
		{
			try {
				Type listType = new TypeToken<HashMap<String, User>>() {}.getType();
				Credits.getUserMap().putAll(
						(Map<String, User>) Credits.gson.fromJson(
								new Scanner( new URL( "http://uuid.ryred.co/?min" ).openStream(), "UTF-8" ).useDelimiter( "\\A" ).next(),
								listType
						)
				);
				Credits.broken = false;
			} catch ( java.io.IOException e ) {
				Credits.broken = true;
			}
		}
	};
	@Getter
	static boolean inited = false;

	@Deprecated
	public static void initBukkit( Credits responder )
	{

		if ( inited || checkFile() ) return;
		inited = true;
		responder.startBukkit( new BukkitListener(), userGetter );

	}

	@Deprecated
	public static void initBungee( Credits responder )
	{

		if ( inited || checkFile() ) return;
		inited = true;
		responder.startBungee( new BungeeListener(), userGetter );

	}

	static boolean checkFile()
	{
		return new File( "ryred_co" ).exists();
	}

	static TextComponent formatUser( String name, User user )
	{

		if ( user == null ) throw new IllegalArgumentException( "user can not be null" );

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

		TextComponent tc = new TextComponent( c( "&4&l\u2764\u2764 &eWelcome &d&o" + name + " &r&ethe server! &4&l\u2764\u2764" ) );
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

	public void startBukkit( BukkitListener listener, Runnable getter ) {}

	public void startBungee( BungeeListener listener, Runnable getter ) {}

}
