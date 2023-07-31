package de.fhws.fiw.fds.exam03.client.rest;

import com.owlike.genson.GenericType;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.exam03.client.models.EventClientModel;

import java.io.IOException;
import java.util.List;

public class EventRestClient
{
	private static final String BASE_URL = "http://localhost:8080/exam03/api";

	private GenericWebClient<EventClientModel> client;

	public EventRestClient( )
	{
		this.client = new GenericWebClient<>( );
	}

	public EventRestResponse start() throws IOException
	{
		return createResponse( this.client.sendGetSingleRequest( BASE_URL ));

	}
	public EventRestResponse readSingleEvent( String url ) throws IOException
	{
		return createResponse( this.client.sendGetSingleRequest( url, EventClientModel.class ) );
	}

	public EventRestResponse readCollectionOfEvents( String url ) throws IOException
	{
		return createResponse( this.client.sendGetCollectionRequest( url,
			new GenericType<List<EventClientModel>>( )
			{

			} ) );
	}

	public EventRestResponse createNewEvent( String url, EventClientModel event )
		throws IOException
	{
		return createResponse( this.client.sendPostRequest( url, event ) );
	}

	public EventRestResponse updateEvent( String url, EventClientModel event ) throws IOException
	{
		return createResponse( this.client.sendPutRequest( url, event ) );
	}

	public EventRestResponse deleteEvent( String url ) throws IOException
	{
		return createResponse( this.client.sendDeleteRequest( url ) );
	}

	public EventRestResponse resetDatabaseOnServer( String url ) throws IOException
	{
		return createResponse( this.client.sendGetSingleRequest( url + "/resetdatabase" ) );
	}

	private EventRestResponse createResponse( WebApiResponse<EventClientModel> response )
	{
		return new EventRestResponse(response);
	}

}
