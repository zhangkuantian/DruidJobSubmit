package com.yuewen.data.druid;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import com.yuewen.data.druid.cli.CliOptions;
import com.yuewen.data.druid.cli.CliOptionsParser;
import com.yuewen.data.druid.exceptions.TaskException;
import com.yuewen.data.druid.util.FileUtils;

public class SubmitJob {
	
	public static void main(String[] args) {
		if(0 >= args.length){
            CliOptionsParser.printHelpClient();
            return;
        }
        if(args[0].equalsIgnoreCase("-h")){
            CliOptionsParser.printHelpClient();
            return;
        }
        CliOptions options = CliOptionsParser.parseEmbeddedModeClient(args);
        if(options.isPrintHelp()){
            CliOptionsParser.printHelpClient();
        }
        
        String jsonBody = FileUtils.readStringFromFile(options.getTemplate(), options.getPaths(), options.getIntervals());
        System.out.println(jsonBody);
        try {
			String result = submit(jsonBody, options.getHost());
			System.out.println(result);
		} catch (TaskException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static String submit(String jsonMsg, String hostPath) throws TaskException{
		ClientConfig jerseyConfig = new ClientConfig();
    	jerseyConfig.connectorProvider(new ApacheConnectorProvider());
    	Client client = ClientBuilder.newClient(jerseyConfig);
    	WebTarget queryWebTarget = client.target(hostPath);
    	
    	try (Response response = queryWebTarget.request(MediaType.APPLICATION_JSON)
    			.post(Entity.entity(jsonMsg, MediaType.APPLICATION_JSON))){
    		return response.readEntity(String.class);
		} catch (Exception e) {
			throw new TaskException(e);
		}
	}
}
