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

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.exam03.server.database.DaoFactory;
import de.fhws.fiw.fds.exam03.server.api.models.Event;

public class PostNewEvent extends AbstractPostState<Event>
{
	public PostNewEvent(final Builder builder )
	{
		super( builder );
	}

	@Override
	protected NoContentResult saveModel( )
	{
		return DaoFactory.getInstance( ).getEventDao( ).create( this.modelToStore );
	}

	@Override
	protected void authorizeRequest( )
	{
	}

	@Override
	protected void defineTransitionLinks( )
	{
		addLink( EventUri.REL_PATH_ID, EventRelTypes.GET_SINGLE_EVENT, getAcceptRequestHeader( ), this.modelToStore.getId( ) );
		addLink( EventUri.REL_PATH, EventRelTypes.GET_ALL_EVENTS, getAcceptRequestHeader( ) );
	}

	public static class Builder extends AbstractPostStateBuilder<Event>
	{
		@Override
		public AbstractState build( )
		{
			return new PostNewEvent( this );
		}
	}
}
