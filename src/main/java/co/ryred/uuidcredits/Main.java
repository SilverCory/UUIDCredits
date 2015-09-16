package co.ryred.uuidcredits;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 17/09/2015.
 */
public class Main
{

	public static void main( String... args )
	{

		ConcurrentHashMap<String, User> sMap = new ConcurrentHashMap<>();

		try {
			Type listType = new TypeToken<HashMap<String, User>>() {}.getType();
			//noinspection unchecked
			sMap.putAll(
					(Map<? extends String, ? extends User>) Credits.gson.fromJson(
							new Scanner( new URL( "http://uuid.ryred.co/?min" ).openStream(), "UTF-8" ).useDelimiter( "\\A" ).next(),
							listType
					)
			);

			for ( Map.Entry<String, User> entry : sMap.entrySet() ) {
				System.out.println( entry.getKey() );
				System.out.println( "  Name   : " + entry.getValue().getName() );
				System.out.println( "  Profile: " + entry.getValue().getProfile() );
				System.out.println( "  Reason : " + entry.getValue().getReason() );
			}

		} catch ( IOException e ) {
			e.printStackTrace();
		}

	}

}
