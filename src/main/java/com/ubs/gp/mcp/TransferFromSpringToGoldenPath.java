package com.ubs.gp.mcp;

import io.quarkiverse.mcp.server.*;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;


public class TransferFromSpringToGoldenPath {

    @Inject
    FakeExampleFiles fakeExampleFiles;

    @Tool(description = "Get instructions to convert spring to Golden Path application")
    public ToolResponse getGoldenPathInstructions(){
        Log.infof("Tool called: %s", getCurrentMethodName());
        return ToolResponse.success(new TextContent("""
                In order to turn a spring application into a golden path application that uses Quarkus and avoids Spring annotations, follow these steps:

                1. Call the tool that gets the example pom file. Use this to change the old pom file into the new one. 

                2. Call the tool that gets the example application.properties file.

                3. Call the tool Get Example database interaction example. 

                4. Call the tool Get Example Quarkus Logging. 

                5. Call the tool Instructions for converting Spring rest controllers.

                6. Call the tool that provides instructions for removing redundant Spring files. 
                
                7. Call the tool Get Instructions for converting Spring testing into Quarkus Testing

                9. Call the tool that provides instructions for converting Spring dependency injection to Quarkus CDI.
                
                10. Call the tool that gives you further changes that need to be implemented.
                
                11. Call the tool that checks for incompatible functionality with Quarkus.
                
                
                """
        ));
    }





    @Tool(description = "Get example pom file for quarkus")
    public ToolResponse getExampleGPPom(){
        Log.infof("Tool Called: %s", getCurrentMethodName());
        String response = """
                If any of the following spring dependencies exist, replace them. Don't add it not already used
                1. Remove all spring-boot-starter-* dependencies.
                2. Replace spring-boot-starter-web with the Quarkus REST layer
                3. Replace spring-boot-starter-data-jpa with Panache
                4. Replace spring-boot-starter-security with Quarkus SmallRye JWT
                5. Replace spring-boot-starter-actuator with SmallRye Health & Metrics
                
                See the example below and implement this structure
                """;
        return ToolResponse.success(new TextContent(response + fakeExampleFiles.getExamplePom()));
    }

    @Tool(description = "Get example application.properties file")
    public ToolResponse getExampleApplicationProperties(){
        Log.infof("Tool Called: %s", getCurrentMethodName());
        return ToolResponse.success(new TextContent(fakeExampleFiles.getExampleApplicationProperties()));
    }



    @Tool(description = "Instructions for converting Spring rest controllers")
    public ToolResponse getInstructionsForReplacingSpringRestControllers(){
        Log.infof("Tool called: %s", getCurrentMethodName());
        String response = String.format("""
                Go through all the files and look for rest controllers. If you find one, 
                convert it to a Quarkus rest controller.
                
                1. Remove @RestController, @RequestMapping, @GetMapping, etc.
                2. @RequestParam → @QueryParam, 
                @RequestHeader → @HeaderParam, 
                @PathVariable → @PathParam.
                
                3. See the example below 
                
                %s
                
                """, fakeExampleFiles.getExampleRestController());

        return ToolResponse.success(new TextContent(response));
    }

    @Tool(description = "Instructions to remove all Spring annotations and replace with Quarkus")
    public ToolResponse getInstructionsForReplacingSpringAnnotations(){
        Log.infof("Tool Called: %s", getCurrentMethodName());
        String response = String.format("""
                Go through all the files of the application. 
                
                1. Replace Spring stereotypes with CDI scopes:
                   @Component/@Service/@Repository → @ApplicationScoped (or @Singleton)
                   
                2. Replace @Autowired (Spring) with @Inject (Jakarta CDI)
                    Constructor injection remains best practice—Quarkus will generate the bean automatically.
                    
                3. Replace Spring’s @ConfigurationProperties & @Value with MicroProfile Config
                    %s
                """, fakeExampleFiles.getExampleConfigProperties());
        return ToolResponse.success(new TextContent(response));
    }



    @Tool(description = "Get Example database interaction example")
    public ToolResponse getExampleDatabaseInteraction(){
        Log.infof("Tool Called: %s", getCurrentMethodName());
        String response = """
                Quarkus uses Hibernate ORM with Panache for repositories. Therefore, please go through every single
                file in the repo and check if it uses Spring Data JPA or Spring jdbc. If it does convert it into a repository implementing PanacheRepository.
                
                Make sure to add the pom dependency.
                Please see the example below.
                
                
                """;
        return ToolResponse.success(new TextContent(response + fakeExampleFiles.getExampleDatabaseInteraction()));
    }

    @Tool(description = "Get Example Quarkus Logging")
    public ToolResponse getQuarkusLoggingExample(){
        Log.infof("Tool Called: %s", getCurrentMethodName());
        String response = """
                
                Quarkus has built in Log functionality. Don't use SLF4J or jboss.logging, simple convert logs to be as in the example.
                This doesnt require any imports. It simply works out the box. No static logger is needed at the start of a class.
                
                
                """;
        return ToolResponse.success(new TextContent(response + fakeExampleFiles.getExampleQuarkusLogging()));
    }

    @Tool(description = "Instructions for removing redundant Spring files")
    public ToolResponse getInstructionsForRemovingRedundantSpringFiles(){
        Log.infof("Tool Called: %s", getCurrentMethodName());
        String response = """
                1. Remove the file that contains @SpringBootApplication and the PSVM. Quarkus does not need this to run
                
                """;

        return ToolResponse.success(new TextContent(response));
    }

    @Tool(description = "Get Instructions for converting Spring testing into Quarkus Testing")
    public ToolResponse getInstructionsForConvertingSpringTestingIntoQuarkus(){
        Log.infof("Tool called: %s", getCurrentMethodName());
        String response = """
                Go through all testing files
                
                1. Remove @SpringBootTest; use @QuarkusTest.
                2. Replace @MockBean with @InjectMock from quarkus-junit5-mockito.
                
                Check all the dependencies are updating in pom.xml
                """;

        return ToolResponse.success(new TextContent(response));
    }

    @Tool(description = "Check for incompatible functionality with Quarkus")
    public ToolResponse getInstructionsForIncompatibleChecks(){
        Log.infof("Tool Called: %s", getCurrentMethodName());
        String response = """
                Please write a .md file in the main repo that lists all the items we cannot convert from Spring to Quarkus.
                To achieve this, you'll need to look at all the files in the repo and compare if Quarkus can cover that functionality.
                For example, Quarkus has its own version for Spring security - thats great! 
                But does Eureka Netflex Server work with Quarkus? If not, it must be added to the list. 
                Go through everything and add to the list if Quarkus cannot cover it.
               
      
                """;

        return ToolResponse.success(new TextContent(response));
    }

    @Tool(description = "Further changes that need to be implemented")
    public ToolResponse getFurtherChangesNeededForImplementation(){
        Log.infof("Tool called: %s", getCurrentMethodName());
        String response = """
                Go through each file in the repo, if any of the following is used, create a FutureNeededChanges.md file
                in the main repo that lists all the required changes to be implemented.
                1. Security
                   Remove WebSecurityConfigurerAdapter and Spring Security annotations.
                   Configure Quarkus security via application.properties (quarkus.http.auth.form.enabled, quarkus.security.users.*, etc.).
                   Use @RolesAllowed or SmallRye JWT RBAC annotations on JAX-RS methods.
                   Inject security identity via @Inject JsonWebToken jwt;
                   
               
                2. Replace Spring @Scheduled with Quarkus @Scheduled from io.quarkus.scheduler\s
                   lordofthejars.github.io
                   Replace Spring Cache annotations (@Cacheable, @CacheEvict) with Quarkus Cache extension (@CacheResult, @CacheInvalidate).                
                   
                3. Actuator → SmallRye Health (/q/health) & Metrics (/q/metrics)\s
                   medium.com
                   Logback config → Quarkus logging (quarkus.log.*) in application.properties.
                """;

        return ToolResponse.success(new TextContent(response));
    }




    public String getCurrentMethodName(){
        return new Throwable()
                .getStackTrace()[1]
                .getMethodName();
    }
}
