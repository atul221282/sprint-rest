package io.javabrains.sbs;

import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author AtulChaudhary
 *
 */
@SpringBootApplication
@EnableAsync()
public class CourseApiApp {

	public static void main(String[] args) {
		// run method sets up the default configuration
		// Springboot create servlet container and host that container in servlet

		/*
		 * This is plain spring boot up process
		 */
		// SpringApplication.run(CourseApiApp.class, args);

		/*
		 * This way of booting spring gives flexibility of setting application property
		 */
		HashMap<String, Object> props = new HashMap<>();
		
		props.put("server.port", 8080);

		new SpringApplicationBuilder().sources(CourseApiApp.class).properties(props).run(args);
	}

}
