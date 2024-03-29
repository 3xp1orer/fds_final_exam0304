package de.fhws.fiw.fds.exam03.server.database.hibernate;

import de.fhws.fiw.fds.exam03.server.api.models.Event;
import de.fhws.fiw.fds.exam03.server.database.EventDao;
import de.fhws.fiw.fds.exam03.server.database.hibernate.dao.EventDaoHibernate;
import de.fhws.fiw.fds.exam03.server.database.hibernate.dao.EventDaoHibernateImpl;
import de.fhws.fiw.fds.exam03.server.database.hibernate.datafaker.EventDataFaker;
import de.fhws.fiw.fds.exam03.server.database.hibernate.models.EventDB;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class is the adapter between the business logic layer and the database layer.
 * <p>
 * The business logic must use this class to access the database and it can use its own
 * model classes. This class takes care of translating models from the business logic into
 * models of the database layer and vice-versa.
 */
public class EventDaoAdapter implements EventDao
{
	private EventDaoHibernate dao = new EventDaoHibernateImpl( );
	private final EventDataFaker faker = new EventDataFaker();

	public EventDaoAdapter( )
	{
	}

	@Override public NoContentResult create( Event model )
	{
		final EventDB dbModel = createFrom( model );
		final NoContentResult returnValue = this.dao.create( dbModel );
		model.setId( dbModel.getId( ) );
		return returnValue;
	}

	@Override public SingleModelResult<Event> readById( long id )
	{
		return createResult( this.dao.readById( id ) );
	}

	@Override public CollectionModelResult<Event> readAll( )
	{
		return this.readAll( new SearchParameter( ) );
	}

	@Override public CollectionModelResult<Event> readAll( SearchParameter searchParameter )
	{
		return createResult( this.dao.readAll( searchParameter ) );
	}

	@Override public NoContentResult update( Event model )
	{
		return this.dao.update( createFrom( model ) );
	}

	@Override public CollectionModelResult<Event> readByTopicDateLecturerAndLocation( String searchValue,
																					  String topicShort, String startDateAndTime,
																					  String lecturer, String location,
																					  SearchParameter searchParameter )
	{
		return createResult(  this.dao.readByTopicDateLecturerAndLocation( searchValue, topicShort, startDateAndTime, lecturer, location, searchParameter ));
	}

	@Override public void initializeDatabase( )
	{
		this.populateDatabase();
	}

	@Override public void resetDatabase( )
	{
		final CollectionModelHibernateResult<EventDB> all = this.dao.readAll();
		all.getResult().stream().forEach( e -> this.dao.delete( e.getId() ) );
	}

	@Override public NoContentResult delete( long id )
	{
		return this.dao.delete( id );
	}

	private Collection<Event> createFrom( Collection<EventDB> models )
	{
		return models.stream( ).map( m -> createFrom( m ) ).collect( Collectors.toList( ) );
	}

	private Event createFrom( EventDB model )
	{
		final Event returnValue = new Event( );
		returnValue.setId( model.getId( ) );
		returnValue.setTopicShort( model.getTopicShort( ) );
		returnValue.setTopicLong( model.getTopicLong( ) );
		returnValue.setAddress( model.getAddress( ) );
		returnValue.setStartDateAndTime( model.getStartDateAndTime( ) );
		returnValue.setEndDateAndTime( model.getEndDateAndTime( ) );
		returnValue.setInstitution( model.getInstitution( ) );
		returnValue.setLecturer( model.getLecturer( ) );
		returnValue.setLocation( model.getLocation( ) );
		return returnValue;
	}

	private EventDB createFrom( Event model )
	{
		final EventDB returnValue = new EventDB( );
		returnValue.setId( model.getId( ) );
		returnValue.setTopicShort( model.getTopicShort( ) );
		returnValue.setTopicLong( model.getTopicLong( ) );
		returnValue.setAddress( model.getAddress( ) );
		returnValue.setStartDateAndTime( model.getStartDateAndTime( ) );
		returnValue.setEndDateAndTime( model.getEndDateAndTime( ) );
		returnValue.setInstitution( model.getInstitution( ) );
		returnValue.setLecturer( model.getLecturer( ) );
		returnValue.setLocation( model.getLocation( ) );
		return returnValue;
	}

	private SingleModelResult<Event> createResult( SingleModelHibernateResult<EventDB> result )
	{
		if( result.isEmpty( ) )
		{
			return new SingleModelResult<>(  );
		}
		if ( result.hasError( ) )
		{
			final SingleModelResult<Event> returnValue = new SingleModelResult<>( );
			returnValue.setError( );
			return returnValue;
		}
		else
		{
			return new SingleModelResult<>( createFrom( result.getResult( ) ) );
		}
	}

	private CollectionModelResult<Event> createResult( CollectionModelHibernateResult<EventDB> result )
	{
		if ( result.hasError( ) )
		{
			final CollectionModelResult<Event> returnValue = new CollectionModelResult<>( );
			returnValue.setError( );
			return returnValue;
		}
		else
		{
			final CollectionModelResult returnValue = new CollectionModelResult<>( createFrom( result.getResult( ) ) );
			returnValue.setTotalNumberOfResult( result.getTotalNumberOfResult() );
			return returnValue;
		}
	}
	private void populateDatabase( )
	{
		IntStream.range( 0, 21 ).forEach( i -> this.dao.create( createEvent( ) ) );
	}

	private EventDB createEvent( )
	{
		return this.faker.createEvent();
	}
}
