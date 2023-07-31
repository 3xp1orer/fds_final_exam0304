package de.fhws.fiw.fds.exam03.server.database.hibernate.dao;

import de.fhws.fiw.fds.exam03.server.database.hibernate.models.EventDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDataAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface EventDaoHibernate extends IDataAccessObjectHibernate<EventDB>
{

	CollectionModelHibernateResult<EventDB> readByTopicDateLecturerAndLocation( String searchValue,
																				String topicShort, String startDateAndTime,
																	    		String lecturer, String location,
																				SearchParameter searchParameter );
}
