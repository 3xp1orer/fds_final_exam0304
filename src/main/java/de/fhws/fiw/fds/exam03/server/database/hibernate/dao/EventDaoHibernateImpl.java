package de.fhws.fiw.fds.exam03.server.database.hibernate.dao;

import de.fhws.fiw.fds.exam03.server.database.hibernate.models.EventDB;
import de.fhws.fiw.fds.exam03.server.database.hibernate.operations.*;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class EventDaoHibernateImpl implements EventDaoHibernate
{
	private static final EntityManagerFactory emf;

	static
	{
		final Map<String, Object> configOverrides = new HashMap<>( );

		final String env = System.getenv( "SUTTON_DB_HOST_PORT" );
		if ( env != null )
		{
			configOverrides.put( "jakarta.persistence.jdbc.url", "jdbc:mysql://" + env + "/PersonDemo" );
		}

		emf = Persistence.createEntityManagerFactory( "de.fhws.fiw.fds.exam03", configOverrides );
	}

	public EventDaoHibernateImpl( )
	{
		super( );
		populateDatabase();
	}
	private void populateDatabase( )
	{
		IntStream.range( 0, 20 ).forEach(i -> create( createEvent( i ) ) );
	}
	private EventDB createEvent( int i )
	{
		final EventDB returnValue = new EventDB( );
		returnValue.setTopicShort( i + " Indicting a journalist?" );
		returnValue.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
		returnValue.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
		returnValue.setStartDateAndTime( "21.04.2019 16:00:00" );
		returnValue.setEndDateAndTime( "tba" );
		returnValue.setInstitution( "gov.uk" );
		returnValue.setLecturer( "Stella Assange" );
		returnValue.setLocation( "Outdoors: On the Street" );
		return returnValue;
	}

	@Override public NoContentResult create( EventDB model )
	{
		return new PersistEventOperation( emf, model ).start( );
	}
	@Override public SingleModelHibernateResult<EventDB> readById(long id )
	{
		return new LoadEventById( emf, id ).start( );
	}

	@Override public CollectionModelHibernateResult<EventDB> readAll(SearchParameter searchParameter )
	{
		return new LoadAllEventsOperations( emf ).start( );
	}

	@Override
	public CollectionModelHibernateResult<EventDB> readByTopicDateLecturerAndLocation(String searchValue,
																					  String topicShort, String startDateAndTime,
																					  String lecturer, String location,
																					  SearchParameter searchParameter )
	{
		return new LoadAllEventsByTopicDateLecturerAndLocation( emf, searchValue, topicShort, startDateAndTime, lecturer, location, searchParameter ).start( );
	}

	@Override public NoContentResult update( EventDB model )
	{
		return new UpdateEventOperation( emf, model ).start( );
	}

	@Override public NoContentResult delete( long id )
	{
		return new DeleteEventByIdOperation( emf, id ).start( );
	}

}
