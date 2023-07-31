package de.fhws.fiw.fds.exam03.server.database.hibernate.operations;

import de.fhws.fiw.fds.exam03.server.database.hibernate.models.EventDB;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class LoadAllEventsByTopicDateLecturerAndLocation
		extends AbstractDatabaseOperation<EventDB, CollectionModelHibernateResult<EventDB>>
{
	private final String searchValue;
	private final String topicShort;
	private final String startDateAndTime;
	private final String lecturer;
	private final String location;

	private final SearchParameter searchParameter;

	public LoadAllEventsByTopicDateLecturerAndLocation( EntityManagerFactory emf, String searchValue,
														String topicShort, String startDateAndTime,
														String lecturer, String location,
														SearchParameter searchParameter )
	{
		super( emf );
		this.searchValue = searchValue.toLowerCase();
		this.topicShort = topicShort.toLowerCase();
		this.startDateAndTime = startDateAndTime.toLowerCase();
		this.lecturer = lecturer.toLowerCase();
		this.location = location.toLowerCase();
		this.searchParameter = searchParameter;
	}

	@Override protected CollectionModelHibernateResult<EventDB> run( )
	{
		var returnValue = new CollectionModelHibernateResult<>( readResult( ) );
		returnValue.setTotalNumberOfResult( getTotalNumberOfResults( ) );
		return returnValue;
	}

	@Override protected CollectionModelHibernateResult<EventDB> errorResult( )
	{
		final CollectionModelHibernateResult<EventDB> returnValue = new CollectionModelHibernateResult<>( );
		returnValue.setError( );
		return returnValue;
	}

	private List<EventDB> readResult( )
	{
		final CriteriaBuilder cb = em.getCriteriaBuilder( );
		final CriteriaQuery<EventDB> cq = cb.createQuery( EventDB.class );
		final Root<EventDB> root = cq.from( EventDB.class );
		final Predicate predicate = createPredicate( cb, root );
		cq.select( root ).where( predicate );
		return this.em.createQuery( cq )
				.setHint( "org.hibernate.cacheable", true )
				.setFirstResult( this.searchParameter.getOffset( ) )
				.setMaxResults( this.searchParameter.getSize( ) + this.searchParameter.getOffset( ) )
				.getResultList( );
	}

	private int getTotalNumberOfResults( )
	{
		final CriteriaBuilder cb = em.getCriteriaBuilder( );
		final CriteriaQuery<Long> cq = cb.createQuery( Long.class );
		final Root<EventDB> root = cq.from( EventDB.class );
		final Predicate predicate = createPredicate( cb, root );
		cq.select( cb.count( root ) ).where( predicate );
		return this.em.createQuery( cq )
				.setHint("org.hibernate.cacheable", true )
				.getSingleResult( ).intValue( );
	}

	private Predicate createPredicate( CriteriaBuilder cb, Root<EventDB> root )
	{
		final Predicate matchTopicShort = cb.like( cb.lower( root.get( "topicShort" ) ), searchValue + "%" );
		final Predicate matchStartDateAndTime = cb.like( cb.lower( root.get( "startDateAndTime" ) ), searchValue + "%" );
		final Predicate matchLecturer = cb.like( cb.lower( root.get( "lecturer" ) ), searchValue + "%" );
		final Predicate matchLocation = cb.like( cb.lower( root.get( "location" ) ), searchValue + "%" );
		return cb.or( matchTopicShort, matchStartDateAndTime, matchLecturer, matchLocation );
	}
}
