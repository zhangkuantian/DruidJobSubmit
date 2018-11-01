package com.yuewen.data.druid;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
    	ClientConfig jerseyConfig = new ClientConfig();
    	jerseyConfig.connectorProvider(new ApacheConnectorProvider());
    	Client client = ClientBuilder.newClient(jerseyConfig);
    	WebTarget queryWebTarget = client.target("http://10.98.89.52:8082/druid/v2?pretty");
    	
    	String json = "{\"queryType\" : \"scan\",\"dataSource\" : \"wikiticker-kafka\",\"columns\":[],"
    			+ "\"intervals\":[\"2016-06-27/2016-06-28\"],"
    			+ "\"resultFormat\":\"list\","
    			+ "\"batchSize\":20480,"
    			+ "\"limit\":200"
    			+ "}";
    	Response response = queryWebTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(json, MediaType.APPLICATION_JSON));
    	
    	String ret = response.readEntity(String.class);
    	System.out.println(ret);
    	
    }
}
