// Copyright 2022 Peter Braun
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.exam03.client.web;

import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.exam03.client.models.EventClientModel;
import okhttp3.Headers;

import javax.ws.rs.core.HttpHeaders;
import java.util.Collection;
import java.util.Optional;

public class EventWebResponse extends WebApiResponse<EventClientModel>
{
	private final static String HEADER_LOCATION = HttpHeaders.LOCATION;

	public EventWebResponse( final Collection<EventClientModel> responseData,
		final Headers headers, final int lastStatusCode )
	{
		super( responseData, headers, lastStatusCode );
	}

	public Optional<String> getLocationHeader( )
	{
		return getResponseHeaders( ).values( HEADER_LOCATION ).stream( ).findFirst( );
	}
}
