package fr.insa.soa.client;

import java.io.IOException;

/**
 * Interface for the client part
 *
 */
public interface ClientInterface {
	public Response get(String url) throws IOException;

	public Response post(String url)
			throws IOException;
}

