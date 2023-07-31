package de.fhws.fiw.fds.exam03.server.database.utils;

import de.fhws.fiw.fds.exam03.server.database.DaoFactory;

public class ResetDatabase
{
	public static void reset( )
	{
		DaoFactory.getInstance( ).getEventDao( ).resetDatabase( );
	}
}
