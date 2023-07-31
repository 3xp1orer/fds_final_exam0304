package de.fhws.fiw.fds.exam03.server.api.messagebodywriter;

import de.fhws.fiw.fds.exam03.server.api.models.Event;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Produces( MediaType.TEXT_HTML )
public class EventHtmlWriter implements MessageBodyWriter<Event>
{
	@Override public boolean isWriteable( final Class<?> aClass, final Type type, final Annotation[] annotations,
		final MediaType mediaType )
	{
		return true;
	}

	@Override public long getSize( final Event event, final Class<?> aClass, final Type type,
		final Annotation[] annotations,
		final MediaType mediaType )
	{
		return -1;
	}

	@Override public void writeTo(final Event event, final Class<?> aClass, final Type type,
								  final Annotation[] annotations,
								  final MediaType mediaType, final MultivaluedMap<String, Object> multivaluedMap,
								  final OutputStream outputStream )
		throws IOException, WebApplicationException
	{
		final var osw = new OutputStreamWriter( outputStream );
		osw.append( "<html>" );
		osw.append( "<body>" );
		osw.append( "<h1>" + event.getTopicShort( ) + "</h1>" );
		osw.append( "</body>" );
		osw.append( "</html>" );
		osw.flush( );
	}
}
