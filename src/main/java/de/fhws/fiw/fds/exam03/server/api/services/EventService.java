/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.exam03.server.api.services;

import de.fhws.fiw.fds.exam03.server.database.utils.InitializeDatabase;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;
import de.fhws.fiw.fds.exam03.server.api.queries.QueryByTopicDateLecturerAndLocation;
import de.fhws.fiw.fds.exam03.server.api.states.events.*;
import de.fhws.fiw.fds.exam03.server.database.utils.ResetDatabase;
import de.fhws.fiw.fds.exam03.server.api.models.Event;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




@Path( "events" ) public class EventService extends AbstractService
{
	@GET @Produces( { MediaType.APPLICATION_JSON } )
	public Response getAllEvents(
		@DefaultValue( "" ) @QueryParam( "searchValue" ) final String searchValue,
		@DefaultValue( "" ) @QueryParam( "topicShort" ) final String topicShort,
		@DefaultValue( "" ) @QueryParam( "startDateAndTime" ) final String startDateAndTime,
		@DefaultValue( "" ) @QueryParam( "lecturer" ) final String lecturer,
		@DefaultValue( "" ) @QueryParam( "location" ) final String location,
		@DefaultValue( "0" ) @QueryParam( "offset" ) int offset,
		@DefaultValue( "10" ) @QueryParam( "size" ) int size,
		@DefaultValue( "0" ) @QueryParam( "wait" ) int waitingTime
		)
	{
		return new GetAllEvents.Builder( ).setQuery( new QueryByTopicDateLecturerAndLocation( searchValue, topicShort, startDateAndTime, lecturer, location, offset, size, waitingTime ) )
										  .setUriInfo( this.uriInfo )
										  .setRequest( this.request )
										  .setHttpServletRequest( this.httpServletRequest )
										  .setContext( this.context )
										  .build( )
										  .execute( );
	}

	@GET @Path( "{id: \\d+}" ) @Produces( { MediaType.APPLICATION_JSON } )
	public Response getSingleEvent(@PathParam( "id" ) final long id )
	{
		return new GetSingleEvent.Builder( ).setRequestedId( id )
											.setUriInfo( this.uriInfo )
											.setRequest( this.request )
											.setHttpServletRequest( this.httpServletRequest )
											.setContext( this.context )
											.build( )
											.execute( );
	}

	@POST @Consumes( { MediaType.APPLICATION_JSON } )
	public Response createSingleEvent(final Event eventModel )
	{
		return new PostNewEvent.Builder( ).setModelToCreate( eventModel )
										   .setUriInfo( this.uriInfo )
										   .setRequest( this.request )
										   .setHttpServletRequest( this.httpServletRequest )
										   .setContext( this.context )
										   .build( )
										   .execute( );
	}

	@PUT @Path( "{id: \\d+}" ) @Consumes( { MediaType.APPLICATION_JSON } )
	public Response updateSingleEvent(@PathParam( "id" ) final long id, final Event eventModel )
	{
		return new PutSingleEvent.Builder( ).setRequestedId( id )
											 .setModelToUpdate( eventModel )
											 .setUriInfo( this.uriInfo )
											 .setRequest( this.request )
											 .setHttpServletRequest( this.httpServletRequest )
											 .setContext( this.context )
											 .build( )
											 .execute( );
	}

	@DELETE @Path( "{id: \\d+}" ) @Consumes( { MediaType.APPLICATION_JSON } )
	public Response deleteSingleEvent( @PathParam( "id" ) final long id )
	{
		return new DeleteSingleEvent.Builder( ).setRequestedId( id )
												.setUriInfo( this.uriInfo )
												.setRequest( this.request )
												.setHttpServletRequest( this.httpServletRequest )
												.setContext( this.context )
												.build( )
												.execute( );
	}

	@GET @Path( "resetdatabase" ) @Produces( { MediaType.APPLICATION_JSON } ) public Response resetDatabase( )
	{
		System.out.println( "RESET DATABASE" );

		ResetDatabase.reset( );

		return Response.ok( ).build( );
	}
	@GET @Path( "filldatabase" ) @Produces( { MediaType.APPLICATION_JSON } ) public Response fillDatabase( )
	{
		System.out.println( "FILL DATABASE" );

		InitializeDatabase.initialize( );

		return Response.ok( ).build( );
	}
}

