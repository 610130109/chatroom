package com.lijun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.lijun.view.Window;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Component
@SuppressWarnings("restriction")
public class App extends Application {

	public final static String QUEUE_ID = "QUEUE_" + System.currentTimeMillis();

	private static Window window;
	public static ConfigurableApplicationContext springContext = null;

	@Autowired
	public void setWindow(Window window) {
		App.window = window;
	}

	public static void main(String[] args) {
		try {
			springContext = SpringApplication.run(App.class, args);
			launch(args);
		} catch (Exception e) {
			return;
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window.show(primaryStage, springContext);
	}
}