package de.fhws.fiw.fds.exam03.server.database.hibernate.models;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table( name = "event", indexes = {
		//@Index( name = "topicShort", columnList = "topic_short" ),
})
public class EventDB extends AbstractDBModel
{
	@Column( name = "id" )
	private long id;

	@Column( name = "topicShort" )
	private String topicShort;

	@Column( name = "topicLong" )
	private String topicLong;

	@Column( name = "address" )
	private String address;

	@Column( name = "startDateAndTime" )
	private String startDateAndTime;

	@Column( name = "endDateAndTime" )
	private String endDateAndTime;

	@Column( name = "institution" )
	private String institution;

	@Column( name = "lecturer" )
	private String lecturer;

	@Column( name = "location" )
	private String location;

	public EventDB( )
	{
	}

	public void setTopicShort( String topicShort ) {
		this.topicShort = topicShort;
	}

	public void setTopicLong( String topicLong ) {
		this.topicLong = topicLong;
	}

	public void setAddress( String address ) {
		this.address = address;
	}

	public void setStartDateAndTime( String startDateAndTime ) {
		this.startDateAndTime = startDateAndTime;
	}

	public void setEndDateAndTime( String endDateAndTime ) {
		this.endDateAndTime = endDateAndTime;
	}

	public void setInstitution( String institution ) {
		this.institution = institution;
	}

	public void setLecturer( String lecturer ) {
		this.lecturer = lecturer;
	}

	public void setLocation( String location ) {
		this.location = location;
	}

	@Override
	public long getId( ) {
		return id;
	}

	public String getTopicShort( ) {
		return topicShort;
	}

	public String getTopicLong( ) {
		return topicLong;
	}

	public String getAddress( ) {
		return address;
	}

	public String getStartDateAndTime( ) {
		return startDateAndTime;
	}

	public String getEndDateAndTime( ) {
		return endDateAndTime;
	}

	public String getInstitution( ) {
		return institution;
	}

	public String getLecturer( ) {
		return lecturer;
	}

	public String getLocation( ) {
		return location;
	}
}