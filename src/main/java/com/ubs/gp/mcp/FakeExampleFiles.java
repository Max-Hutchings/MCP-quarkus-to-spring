package com.ubs.gp.mcp;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FakeExampleFiles {



    public String getExamplePom(){
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                    <modelVersion>4.0.0</modelVersion>
                    <groupId>org.mcp</groupId>
                    <artifactId>mcpfromspring</artifactId>
                    <version>1.0.0-SNAPSHOT</version>
                
                    <properties>
                        <compiler-plugin.version>3.14.0</compiler-plugin.version>
                        <maven.compiler.release>21</maven.compiler.release>
                        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
                        <quarkus.platform.artifact-id>quarkus-bom</quarkus.platform.artifact-id>
                        <quarkus.platform.group-id>io.quarkus.platform</quarkus.platform.group-id>
                        <quarkus.platform.version>3.24.1</quarkus.platform.version>
                        <skipITs>true</skipITs>
                        <surefire-plugin.version>3.5.3</surefire-plugin.version>
                    </properties>
                
                    <dependencyManagement>
                        <dependencies>
                            <dependency>
                                <groupId>${quarkus.platform.group-id}</groupId>
                                <artifactId>${quarkus.platform.artifact-id}</artifactId>
                                <version>${quarkus.platform.version}</version>
                                <type>pom</type>
                                <scope>import</scope>
                            </dependency>
                        </dependencies>
                    </dependencyManagement>
                
                    <dependencies>
                <!--        Golden Path Required Dependencies    -->
                        <dependency>
                            <groupId>io.quarkus</groupId>
                            <artifactId>quarkus-rest</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>io.quarkiverse.mcp</groupId>
                            <artifactId>quarkus-mcp-server-sse</artifactId>
                            <version>1.3.1</version>
                        </dependency>
                        <dependency>
                            <groupId>io.quarkus</groupId>
                            <artifactId>quarkus-rest-jackson</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>io.quarkus</groupId>
                            <artifactId>quarkus-arc</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>io.quarkus</groupId>
                            <artifactId>quarkus-junit5</artifactId>
                            <scope>test</scope>
                
                        </dependency>
                        <dependency>
                            <groupId>io.rest-assured</groupId>
                            <artifactId>rest-assured</artifactId>
                            <scope>test</scope>
                        </dependency>
                
                
                <!--        Additional Project Dependencies   -->
                    </dependencies>
                
                    <build>
                        <plugins>
                            <plugin>
                                <groupId>${quarkus.platform.group-id}</groupId>
                                <artifactId>quarkus-maven-plugin</artifactId>
                                <version>${quarkus.platform.version}</version>
                                <extensions>true</extensions>
                                <executions>
                                    <execution>
                                        <goals>
                                            <goal>build</goal>
                                            <goal>generate-code</goal>
                                            <goal>generate-code-tests</goal>
                                            <goal>native-image-agent</goal>
                                        </goals>
                                    </execution>
                                </executions>
                            </plugin>
                            <plugin>
                                <artifactId>maven-compiler-plugin</artifactId>
                                <version>${compiler-plugin.version}</version>
                                <configuration>
                                    <parameters>true</parameters>
                                </configuration>
                            </plugin>
                            <plugin>
                                <artifactId>maven-surefire-plugin</artifactId>
                                <version>${surefire-plugin.version}</version>
                                <configuration>
                                    <systemPropertyVariables>
                                        <java.util.logging.manager>org.jboss.logmanager.LogManager</java.util.logging.manager>
                                        <maven.home>${maven.home}</maven.home>
                                    </systemPropertyVariables>
                                </configuration>
                            </plugin>
                            <plugin>
                                <artifactId>maven-failsafe-plugin</artifactId>
                                <version>${surefire-plugin.version}</version>
                                <executions>
                                    <execution>
                                        <goals>
                                            <goal>integration-test</goal>
                                            <goal>verify</goal>
                                        </goals>
                                    </execution>
                                </executions>
                                <configuration>
                                    <systemPropertyVariables>
                                        <native.image.path>${project.build.directory}/${project.build.finalName}-runner</native.image.path>
                                        <java.util.logging.manager>org.jboss.logmanager.LogManager</java.util.logging.manager>
                                        <maven.home>${maven.home}</maven.home>
                                    </systemPropertyVariables>
                                </configuration>
                            </plugin>
                        </plugins>
                    </build>
                
                    <profiles>
                        <profile>
                            <id>native</id>
                            <activation>
                                <property>
                                    <name>native</name>
                                </property>
                            </activation>
                            <properties>
                                <skipITs>false</skipITs>
                                <quarkus.native.enabled>true</quarkus.native.enabled>
                            </properties>
                        </profile>
                    </profiles>
                </project>
                
                """;
    }


    public String getExampleApplicationProperties() {
        return """
                return ""\"
                            quarkus.http.port=8080
                            quarkus.log.level=INFO
                """;
    }

    public String getExampleDatabaseInteraction() {
        return """
                package com.copilot.springstart.demo;
                
                import io.quarkus.hibernate.orm.panache.PanacheRepository;
                import jakarta.enterprise.context.ApplicationScoped;
                import java.util.List;
                
                @ApplicationScoped
                public class BookRepository implements PanacheRepository<Book> {
                    public List<Book> findByAuthor(String author) {
                        return list("author", author);
                    }
                }
                """;
    }

    public String getExampleQuarkusLogging() {
        return """
                
                package com.quarkus.demo;
                
          
                
                import jakarta.ws.rs.GET;
                import jakarta.ws.rs.Path;
                
                @Path("/log-example")
                public class LogExampleResource {
                
                
                    @GET
                    public String logMessages() {
                        Log.info("This is an info message");
                        Log.debug("This is a debug message");
                        Log.error("This is an error message");
                
                        Log.infof("Infof: Hello %s!", "world");
                        Log.debugf("Debugf: Value is %d", 42);
                        Log.errorf("Errorf: Exception: %s", "Something went wrong");
                
                        return "Logged messages!";
                    }
                """;
    }


    public String getExampleConfigProperties(){
        return """
                @ConfigProperties(prefix="myapp")
                public class MyConfig {
                  public String host;
                  public int port;
                }
                @Inject MyConfig config;
                
                or for individual properties
                @ConfigProperty(name="myapp.host") String host;
                
                """;
    }

    public String getExampleRestController(){
        return """
                package com.example;
                
                import jakarta.inject.Inject;                         // CDI injection
                import jakarta.ws.rs.*;                               // JAX-RS annotations
                import jakarta.ws.rs.core.MediaType;
                import jakarta.ws.rs.core.Response;
                
                @Path("/hello")                                      // root path for this resource
                @Produces(MediaType.APPLICATION_JSON)                // all responses are JSON
                @Consumes(MediaType.APPLICATION_JSON)                // all request bodies are JSON
                public class HelloResource {
                
                    @Inject                                           // inject business-logic bean
                    HelloService helloService;
                
                    @GET
                    @Path("/{name}")                                  // handles GET /hello/{name}
                    public Response hello(@PathParam("name") String name) {
                        String greeting = helloService.greet(name);   // call service to build greeting
                        return Response.ok(greeting).build();         // return 200 OK with greeting
                    }
                
                    @POST
                    public Response createGreeting(GreetingRequest req) {
                        Greeting created =                            
                            helloService.createGreeting(req.getName()); // create new greeting via service
                        return Response.status(Response.Status.CREATED) // return 201 Created
                                       .entity(created)               
                                       .build();
                    }
                
                    @PUT
                    @Path("/{name}")                                  // handles PUT /hello/{name}
                    public Response updateGreeting(@PathParam("name") String name,
                                                   GreetingRequest req) {
                        Greeting updated =                           
                            helloService.updateGreeting(name, req.getName()); // update greeting via service
                        return Response.ok(updated).build();           // return 200 OK with updated entity
                    }
                }
                
                """;
    }
}
