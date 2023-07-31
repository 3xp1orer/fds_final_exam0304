package de.fhws.fiw.fds.exam03.server.api.utils;

import javax.ws.rs.core.CacheControl;

public class Caching
{
    public static CacheControl cacheIt( )
    {
        final var returnValue = new CacheControl( );

        returnValue.setPrivate( false );
        returnValue.setMaxAge( 600 );
        returnValue.setNoTransform( false );

        return returnValue;
    }
}