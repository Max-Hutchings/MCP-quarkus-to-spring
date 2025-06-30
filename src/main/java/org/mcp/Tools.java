package org.mcp;

import io.quarkiverse.mcp.server.*;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;


public class Tools {

    @Inject
    FakeExampleFiles fakeExampleFiles;

    @Tool(description = "Get instructions to convert spring to quarkus")
    public ToolResponse getGoldenPathInstructions(){
        return ToolResponse.success(new TextContent("""
                In order to turn a spring application into a golden path application, we need to carry out the following steps\
                
                1. Call the tool that gets the example pom file. Use this to change the old pom file into the new one. This should involve removing dependencies that are spring boot related and replacing them with Quarkus ones. Whats key here, if they have other dependencies, add them after the required golden path ones\
                
                2. Call the tool that gets the example application.properties file. Use this to make changes, mostly removing a lot of the configuration  that is not needed in Quarkus\
                
                3. Call the tool that gets the example Quarkus database interactions example. We need to remove all Spring data related files and re-create them to be Quarkus equivalent. Use the example to achieve this \
                
                4. Call the tool that demonstrate logging in Quarkus. Use this to change logging throughout every single file in the spring application. We don't need a logging library, Quarkus comes with in-built logging e.g. Log.infof('')"""
        ));
    }


    @Tool(description = "Get example pom file for quarkus")
    public ToolResponse getExampleGPPom(){
        Log.info("Tool Called: getExampleGPPom");
        return ToolResponse.success(new TextContent(fakeExampleFiles.getExamplePom()));
    }

    @Tool(description = "Get example application.properties file")
    public ToolResponse getExampleApplicationProperties(){
        Log.info("Tool Called: getExampleApplicationProperties");
        return ToolResponse.success(new TextContent(fakeExampleFiles.getExampleApplicationProperties()));
    }

    @Tool(description = "Get Example datbase interaction example")
    public ToolResponse getExampleDatabaseInteraction(){
        Log.info("Tool Called: getExampleDatabaseInteraction");
        return ToolResponse.success(new TextContent(fakeExampleFiles.getExampleDatabaseInteraction()));
    }

    @Tool(description = "Get Example Quarkus Logging")
    public ToolResponse getQuarkusLoggingExample(){
        Log.info("Tool Called: getQuarkusLoggingExample");
        return ToolResponse.success(new TextContent(fakeExampleFiles.getExampleQuarkusLogging()));
    }
}
