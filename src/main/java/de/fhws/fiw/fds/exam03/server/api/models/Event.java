/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.exam03.server.api.models;

import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.server.api.converter.JsonServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.api.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import java.time.LocalDate;

public class Event extends AbstractModel
{
	private String topicShort;
	private String topicLong;
	private String address;
	private String startDateAndTime;
	private String endDateAndTime;
	private String institution;
	private String lecturer;
	private String location;

	@InjectLink( title = "self", value="/events/${instance.id}", rel = "self", style = InjectLink.Style.ABSOLUTE, type = "application/json")
	private Link self;

	public Event( )
	{
	}

	public Event( final String topicShort, final String topicLong, final String address,
				  final String startDateAndTime, final String endDateAndTime, final String institution,
				  final String lecturer, final String location )
	{
		this.topicShort = topicShort;
		this.topicLong = topicLong;
		this.address = address;
		this.startDateAndTime = startDateAndTime;
		this.endDateAndTime = endDateAndTime;
		this.institution = institution;
		this.lecturer = lecturer;
		this.location = location;

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

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getSelf( )
	{
		return self;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public void setSelf( Link self )
	{
		this.self = self;
	}

	@Override
	public String toString( )
	{
		return "Event{" + "id=" + id
				+  ", topicShort='" + topicShort + '\'' + ", topicLong='" + topicLong + '\''
				+ ", address='" + address + '\'' + ", startDateAndTime='" + startDateAndTime + '\''
				+ ", endDateAndTime='" + endDateAndTime + '\'' + ", institution='" + institution + '\''
				+ ", lecturer='" + lecturer + '\'' + ", location='" + location + '\'' + '}';
	}
}
