package de.fhws.fiw.fds.exam03.client.web;

import com.owlike.genson.GenericType;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.exam03.client.models.EventClientModel;

import java.io.IOException;
import java.util.List;

public class EventWebClient
{

	private GenericWebClient<EventClientModel> client;

	public EventWebClient( )
	{
		this.client = new GenericWebClient<>( );
	}

	public EventWebResponse getSingleEvent( String url ) throws IOException
	{
		return createResponse( this.client.sendGetSingleRequest( url, EventClientModel.class ) );
	}

	public EventWebResponse getCollectionOfEvents( String url ) throws IOException
	{
		return createResponse( this.client.sendGetCollectionRequest( url,
			new GenericType<List<EventClientModel>>( )
			{
			} ) );
	}

	public EventWebResponse postNewEvent( String url, EventClientModel event )
		throws IOException
	{
		return createResponse( this.client.sendPostRequest( url, event ) );
	}

	public EventWebResponse putEvent( String url, EventClientModel event ) throws IOException
	{
		return createResponse( this.client.sendPutRequest( url, event ) );
	}

	public EventWebResponse deleteEvent( String url ) throws IOException
	{
		return createResponse( this.client.sendDeleteRequest( url ) );
	}

	public EventWebResponse resetDatabaseOnServer( String url ) throws IOException
	{
		return createResponse( this.client.sendGetSingleRequest( url + "/resetdatabase" ) );
	}

	private EventWebResponse createResponse( WebApiResponse<EventClientModel> response )
	{
		return new EventWebResponse( response.getResponseData( ), response.getResponseHeaders( ),
			response.getLastStatusCode( ) );
	}
}
