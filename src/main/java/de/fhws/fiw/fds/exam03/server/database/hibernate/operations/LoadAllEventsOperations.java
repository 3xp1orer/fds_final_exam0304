package de.fhws.fiw.fds.exam03.server.database.hibernate.operations;

import de.fhws.fiw.fds.exam03.server.database.hibernate.models.EventDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractReadAllOperation;
import jakarta.persistence.EntityManagerFactory;

public class LoadAllEventsOperations extends AbstractReadAllOperation<EventDB>
{
	public LoadAllEventsOperations(EntityManagerFactory emf )
	{
		super( emf, EventDB.class );
	}
}
