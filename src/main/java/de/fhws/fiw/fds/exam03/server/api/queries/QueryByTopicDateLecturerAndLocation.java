// Copyright 2022 Peter Braun
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.exam03.server.api.queries;

import de.fhws.fiw.fds.exam03.server.api.models.Event;
import de.fhws.fiw.fds.exam03.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryByTopicDateLecturerAndLocation extends AbstractQuery<Event>
{
	private String searchValue;
	private String topicShort;
	private String startDateAndTime;
	private String lecturer;
	private String location;
	private final int waitingTime;

	public QueryByTopicDateLecturerAndLocation(String searchValue, String topicShort, String startDateAndTime, String lecturer, String location, int offset, int size, int waitingTime )//, int waitingTime
	{
		this.searchValue = searchValue;
		this.topicShort = topicShort;
		this.startDateAndTime = startDateAndTime;
		this.lecturer = lecturer;
		this.location = location;
		this.waitingTime = waitingTime;

		this.pagingBehavior = new PagingBehaviorUsingOffsetSize<Event>( offset, size );
	}

	public String getSearchValue() {
		return searchValue;
	}

	public String getTopicShort() {
		return topicShort;
	}

	public String getStartDateAndTime() {
		return startDateAndTime;
	}

	public String getLecturer() {
		return lecturer;
	}

	public String getLocation() {
		return location;
	}

	public int getWaitingTime( )
	{
		return waitingTime;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public void setTopicShort(String topicShort) {
		this.topicShort = topicShort;
	}

	public void setStartDateAndTime(String startDateAndTime) {
		this.startDateAndTime = startDateAndTime;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	protected CollectionModelResult<Event> doExecuteQuery(SearchParameter searchParameter ) throws DatabaseException
	{
		return DaoFactory.getInstance( ).getEventDao( ).readByTopicDateLecturerAndLocation(
			this.searchValue,
			this.topicShort,
			this.startDateAndTime,
			this.lecturer,
			this.location,
			searchParameter );
	}
}
