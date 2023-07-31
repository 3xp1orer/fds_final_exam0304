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

package de.fhws.fiw.fds.exam03.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.exam03.server.database.EventDao;
import de.fhws.fiw.fds.exam03.server.api.models.Event;
import org.apache.commons.lang.StringUtils;

import java.util.function.Predicate;

public class EventInMemoryStorage extends AbstractInMemoryStorage<Event> implements EventDao
{
	public EventInMemoryStorage( )
	{
		super( );
		populateData( );
	}

	@Override
	public CollectionModelResult<Event> readByTopicDateLecturerAndLocation( final String searchValue,
																			final String topicShort,
																			final String startDateAndTime,
																			final String lecturer,
																			final String location,
																			SearchParameter searchParameter )
	{
		return readByPredicate( byTopicDateLecturerAndLocation( topicShort, startDateAndTime,
																lecturer, location),
																searchParameter );
	}

	public void resetDatabase( )
	{
		super.deleteAll( );
	}

	@Override public void initializeDatabase( )
	{
		// Not implemented
	}

	private void populateData( )
	{
		create( new Event( "Indicting a journalist?",
				"What the new charges against Julian Assange mean for free speech",
				"HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB",
				"21.04.2019 16:00:00",
				"tba",
				"gov.uk",
				"Stella Assange",
				"Outdoors: On the Street" )
		);	}

	private Predicate<Event> byTopicDateLecturerAndLocation( final String topicShort,
															 final String startDateAndTime,
															 final String lecturer,
															 final String location ) {
		return e -> matchTopicShort(e, topicShort) && matchStartDateAndTime(e, startDateAndTime)
				&& matchLecturer(e, lecturer) && matchLocation(e, location);
	}
	private boolean matchTopicShort(final Event event, final String topicShort )
	{
		return StringUtils.isEmpty( topicShort ) || event.getTopicShort( ).equals( topicShort );
	}

	private boolean matchStartDateAndTime(final Event event, final String startDateAndTime )
	{
		return StringUtils.isEmpty( startDateAndTime ) || event.getStartDateAndTime( ).equals( startDateAndTime );
	}

	private boolean matchLecturer(final Event event, final String lecturer )
	{
		return StringUtils.isEmpty( lecturer ) || event.getStartDateAndTime( ).equals( lecturer );
	}

	private boolean matchLocation(final Event event, final String location )
	{
		return StringUtils.isEmpty( location ) || event.getStartDateAndTime( ).equals( location );
	}
}
