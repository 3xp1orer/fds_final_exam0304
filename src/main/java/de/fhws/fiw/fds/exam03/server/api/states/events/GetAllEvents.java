/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.fiw.fds.exam03.server.api.states.events;

import de.fhws.fiw.fds.exam03.server.api.queries.QueryByTopicDateLecturerAndLocation;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.exam03.server.api.models.Event;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;

public class GetAllEvents extends AbstractGetCollectionState<Event>
{

	public GetAllEvents( final Builder builder )
	{
		super( builder );
	}

	protected void defineHttpResponseBody( )
	{
		this.responseBuilder.entity( new GenericEntity<Collection<Event>>( this.result.getResult( ) )
		{
		} );
	}
	@Override
	protected void authorizeRequest( )
	{
		QueryByTopicDateLecturerAndLocation theQuery = (QueryByTopicDateLecturerAndLocation ) this.query;
		int waitingTime = theQuery.getWaitingTime();

		try
		{
			Thread.sleep( waitingTime );
		} catch ( Exception e  )
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void defineTransitionLinks( )
	{
		addLink( EventUri.REL_PATH_ID, EventRelTypes.GET_SINGLE_EVENT, getAcceptRequestHeader( ) );
		addLink( EventUri.REL_PATH, EventRelTypes.CREATE_EVENT, getAcceptRequestHeader( ) );
		addLink( EventUri.SEARCH_VALUE, EventRelTypes.SEARCH_VALUE, getAcceptRequestHeader( ) );
	}

	public static class Builder extends AbstractGetCollectionStateBuilder<Event>
	{
		@Override
		public AbstractState build( )
		{
			return new GetAllEvents( this );
		}
	}
}
