package de.fhws.fiw.fds.exam03.server.database.hibernate.datafaker;

import com.github.javafaker.Faker;
import de.fhws.fiw.fds.exam03.server.database.hibernate.models.EventDB;

public class EventDataFaker
{
    final private Faker faker;

    public EventDataFaker( )
    {
        this.faker = new Faker( );
    }

    public EventDB createEvent( )
    {
        final EventDB returnValue = new EventDB( );
        returnValue.setTopicShort( " Indicting a journalist?" );
        returnValue.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
        returnValue.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
        returnValue.setStartDateAndTime( "21.04.2019 16:00:00" );
        returnValue.setEndDateAndTime( "tba" );
        returnValue.setInstitution( "gov.uk" );
        returnValue.setLecturer( "Stella Assange" );
        returnValue.setLocation( "Outdoors: On the Street" );
        return returnValue;
    }
}