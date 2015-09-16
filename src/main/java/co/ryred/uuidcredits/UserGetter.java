package co.ryred.uuidcredits;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 16/09/2015.
 */
public class UserGetter implements Runnable
{
	@Override
	public void run()
	{
		try {
			Type listType = new TypeToken<HashMap<String, User>>() {}.getType();
			Credits.userMap = Credits.gson.fromJson( new Scanner( new URL( "http://uuid.ryred.co/?min" ).openStream(), "UTF-8" ).useDelimiter( "\\A" ).next(), listType );
			System.out.println( "Size: " + Credits.userMap.size() );
			Credits.broken = false;
		} catch ( java.io.IOException e ) {
			Credits.broken = true;
			e.printStackTrace();
		}
	}
}
