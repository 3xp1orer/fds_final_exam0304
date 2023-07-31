package de.fhws.fiw.fds.exam03.database.hibernate;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.exam03.server.database.hibernate.dao.EventDaoHibernate;
import de.fhws.fiw.fds.exam03.server.database.hibernate.dao.EventDaoHibernateImpl;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.exam03.server.database.hibernate.models.EventDB;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestHibernate
{
	@Test
	public void test_db_save_successful( ) throws Exception
	{
		EventDB event = new EventDB( );
		event.setTopicShort( "Indicting a journalist?" );
		event.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
		event.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
		event.setStartDateAndTime( "21.04.2019 16:00:00" );
		event.setEndDateAndTime( "tba" );
		event.setInstitution( "gov.uk" );
		event.setLecturer( "Stella Assange" );
		event.setLocation( "Outdoors: On the Street" );

		EventDaoHibernate dao = new EventDaoHibernateImpl( );
		NoContentResult resultSave = dao.create( event );

		assertFalse( resultSave.hasError( ) );

		CollectionModelHibernateResult<EventDB> resultGetAll = dao.readAll( );
		assertEquals( 1, resultGetAll.getResult( ).size( ) );
	}

	@Test
	public void test_db_load_by_id( ) throws Exception
	{
		EventDB event = new EventDB( );
		event.setTopicShort( "Indicting a journalist?" );
		event.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
		event.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
		event.setStartDateAndTime( "21.04.2019 16:00:00" );
		event.setEndDateAndTime( "tba" );
		event.setInstitution( "gov.uk" );
		event.setLecturer( "Stella Assange" );
		event.setLocation( "Outdoors: On the Street" );

		EventDaoHibernate dao = new EventDaoHibernateImpl( );
		NoContentResult resultSave = dao.create( event );

		assertFalse( resultSave.hasError( ) );

		SingleModelHibernateResult<EventDB> resultGetById = dao.readById( event.getId( ) );
		assertEquals( event.getStartDateAndTime( ), resultGetById.getResult( ).getStartDateAndTime( ) );
	}

	@Test
	public void test_db_delete_by_id( ) throws Exception
	{
		EventDB event = new EventDB( );
		event.setTopicShort( "Indicting a journalist?" );
		event.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
		event.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
		event.setStartDateAndTime( "21.04.2019 16:00:00" );
		event.setEndDateAndTime( "tba" );
		event.setInstitution( "gov.uk" );
		event.setLecturer( "Stella Assange" );
		event.setLocation( "Outdoors: On the Street" );

		EventDaoHibernate dao = new EventDaoHibernateImpl( );
		NoContentResult resultSave = dao.create( event );

		assertFalse( resultSave.hasError( ) );

		NoContentResult resultDelete = dao.delete( event.getId( ) );
		assertFalse( resultDelete.hasError( ) );
	}
	@Test
	public void test_db_update( ) throws Exception
	{
		EventDB event = new EventDB( );
		event.setTopicShort( "Indicting a journalist?" );
		event.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
		event.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
		event.setStartDateAndTime( "21.04.2019 16:00:00" );
		event.setEndDateAndTime( "tba" );
		event.setInstitution( "gov.uk" );
		event.setLecturer( "Stella Assange" );
		event.setLocation( "Outdoors: On the Street" );

		EventDaoHibernate dao = new EventDaoHibernateImpl( );
		NoContentResult resultSave = dao.create( event );

		assertFalse( resultSave.hasError( ) );

		SingleModelHibernateResult<EventDB> resultGetById = dao.readById( event.getId( ) );
		EventDB eventInDB = resultGetById.getResult();
		eventInDB.setTopicShort( "Julian Assange - Freedom of press" );

		NoContentResult resultUpdate = dao.update( eventInDB );
		assertFalse( resultUpdate.hasError( ) );

		SingleModelHibernateResult<EventDB> resultGetByIdAfterUpdate = dao.readById( event.getId( ) );
		assertEquals( "Julian Assange - Freedom of press", resultGetByIdAfterUpdate.getResult().getTopicShort() );
	}


	@Test
	public void test_db_load_by_names( ) throws Exception
	{
		EventDB event = new EventDB( );
		event.setTopicShort( "Indicting a journalist?" );
		event.setTopicLong( "What the new charges against Julian Assange mean for free speech" );
		event.setAddress( "HMP Belmarsh, Western Way, Thamesmead, London, SE28 0EB" );
		event.setStartDateAndTime( "21.04.2019 16:00:00" );
		event.setEndDateAndTime( "tba" );
		event.setInstitution( "gov.uk" );
		event.setLecturer( "Stella Assange" );
		event.setLocation( "Outdoors: On the Street" );

		EventDaoHibernate dao = new EventDaoHibernateImpl( );
		NoContentResult resultSave = dao.create( event );

		assertFalse( resultSave.hasError( ) );

		CollectionModelHibernateResult<EventDB> resultByTerm = dao.readByTopicDateLecturerAndLocation( "","", "", "Stella Assange", "", new SearchParameter() );
		assertEquals( 1, resultByTerm.getResult().size());
	}
}
