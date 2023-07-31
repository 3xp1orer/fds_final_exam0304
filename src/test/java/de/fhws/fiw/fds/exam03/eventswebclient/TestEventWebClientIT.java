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

package de.fhws.fiw.fds.exam03.eventswebclient;

import de.fhws.fiw.fds.exam03.client.models.EventClientModel;
import de.fhws.fiw.fds.exam03.client.web.EventWebResponse;
import de.fhws.fiw.fds.exam03.client.web.EventWebClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEventWebClientIT
{
    private final static String BASE_URL = "http://localhost:8080/exam03/api/events";

    private EventWebClient client;

    @Before
    public void set_up() throws IOException {
        this.client = new EventWebClient();
        this.client.resetDatabaseOnServer(BASE_URL);
    }

    @Test
    public void test_get_events_must_be_empty() throws IOException {
        final EventWebResponse response = this.client.getCollectionOfEvents(BASE_URL);
        assertEquals(200, response.getLastStatusCode());
        assertEquals(0, response.getResponseData().size());
    }

    @Test
    public void test_post_event() throws IOException {
        final EventClientModel event = new EventClientModel();
        event.setTopicShort( "Indicting a journalist?" );
        event.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
        event.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
        event.setStartDateAndTime( "21.04.2019 16:00:00" );
        event.setEndDateAndTime( "tba" );
        event.setInstitution( "gov.uk" );
        event.setLecturer( "Stella Assange" );
        event.setLocation( "Outdoors: On the Street" );

        final EventWebResponse response = this.client.postNewEvent(BASE_URL,event);

        assertEquals(201, response.getLastStatusCode());
        assertTrue(response.getLocationHeader().isPresent());

        final String locationUrl = response.getLocationHeader().get();
        final EventWebResponse response2 = this.client.getSingleEvent( locationUrl );

        assertEquals( 200, response2.getLastStatusCode() );

        final EventClientModel responseEvent = response2.getFirstResponse( ).get( );
        assertEquals( event.getTopicShort(),responseEvent.getTopicShort() );
        assertEquals( event.getStartDateAndTime(), responseEvent.getStartDateAndTime() );
        assertEquals( event.getLecturer(), responseEvent.getLecturer());
        assertEquals( event.getLocation(), responseEvent.getLocation() );
    }

    @Test
    public void test_post_event_then_update() throws IOException
    {
        final EventClientModel event = new EventClientModel();
        event.setTopicShort( "Indicting a journalist?" );
        event.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
        event.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
        event.setStartDateAndTime( "21.04.2019 16:00:00" );
        event.setEndDateAndTime( "tba" );
        event.setInstitution( "gov.uk" );
        event.setLecturer( "Stella Assange" );
        event.setLocation( "Outdoors: On the Street" );

        final EventWebResponse response = this.client.postNewEvent(BASE_URL,event);
        final String eventUrl = response.getLocationHeader().get();

        final EventWebResponse response2 = this.client.getSingleEvent( eventUrl );
        final EventClientModel eventResponse = response2.getFirstResponse().get();

        eventResponse.setTopicShort( "Julian Assange - Freedom of press" );

        final EventWebResponse response3 = this.client.putEvent( response.getLocationHeader().get(), eventResponse );

        assertEquals( 204, response3.getLastStatusCode() );
    }

    @Test
    public void test_post_event_then_delete() throws IOException
    {
        final EventClientModel event = new EventClientModel();
        event.setTopicShort( "Indicting a journalist?" );
        event.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
        event.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
        event.setStartDateAndTime( "21.04.2019 16:00:00" );
        event.setEndDateAndTime( "tba" );
        event.setInstitution( "gov.uk" );
        event.setLecturer( "Stella Assange" );
        event.setLocation( "Outdoors: On the Street" );

        final EventWebResponse response = this.client.postNewEvent(BASE_URL,event);
        final String eventUrl = response.getLocationHeader().get();
        final EventWebResponse response3 = this.client.deleteEvent( eventUrl );

        assertEquals( 204, response3.getLastStatusCode() );

        final EventWebResponse response2 = this.client.getSingleEvent( eventUrl );

        assertEquals( 404, response2.getLastStatusCode() );
    }
}