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

import de.fhws.fiw.fds.exam03.server.api.utils.Caching;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.exam03.server.database.DaoFactory;
import de.fhws.fiw.fds.exam03.server.api.models.Event;

public class GetSingleEvent extends AbstractGetState<Event>
{
	@Override
	protected void defineHttpCaching( )
	{
		this.responseBuilder.cacheControl(Caching.cacheIt());
	}
	public GetSingleEvent(final AbstractGetStateBuilder builder )
	{
		super( builder );
	}

	@Override
	protected SingleModelResult<Event> loadModel( )
	{
		return DaoFactory.getInstance( ).getEventDao( ).readById( this.requestedId );
	}

	@Override
	protected void authorizeRequest( )
	{
	}

	@Override
	protected void defineTransitionLinks( )
	{
		addLink( EventUri.REL_PATH, EventRelTypes.GET_ALL_EVENTS, getAcceptRequestHeader( ) );
		addLink( EventUri.REL_PATH_ID, EventRelTypes.UPDATE_SINGLE_EVENT, getAcceptRequestHeader( ) );
		addLink( EventUri.REL_PATH_ID, EventRelTypes.DELETE_SINGLE_EVENT, getAcceptRequestHeader( ) );
	}

	public static class Builder extends AbstractGetStateBuilder
	{
		@Override
		public AbstractState build( )
		{
			return new GetSingleEvent( this );
		}
	}
}
