package com.lijun.view;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.lijun.sender.MessageSender;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
@Component
public class Window {

	@Autowired
	private MessageSender messageSender;

	private TextField userName = null;
	private TextArea history = null;
	private TextField message = null;

	public void show(Stage primaryStage, ConfigurableApplicationContext springContext) throws Exception {

		// 舞台名称
		primaryStage.setTitle("let's chat");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(4);
		grid.setVgap(10);
		grid.setPadding(new Insets(50, 50, 50, 50));

		userName = new TextField();
		userName.setText(((int) (Math.random() * 100)) + "");
		grid.add(userName, 0, 0);

		history = new TextArea();
		history.setPrefHeight(300);
		history.setPrefWidth(350);
		history.setEditable(false);
		grid.add(history, 0, 1);

		message = new TextField();
		message.setOnKeyPressed(e -> {
			if ("enter".equals(e.getCode().getName().toLowerCase())) {
				sendMessage();
			}
		});
		grid.add(message, 0, 2);

		HBox hbBtn = new HBox(10);
		Button btn = new Button("send");
		btn.setOnAction(e -> {
			sendMessage();
		});
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 2);

		Scene scene = new Scene(grid, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			SpringApplication.exit(springContext, () -> 0);
		});
		primaryStage.show();
	}

	@SuppressWarnings("deprecation")
	protected void sendMessage() {
		String msg = message.getText();
		if (msg != null && msg.length() > 0) {
			messageSender.send(userName.getText() + " " + new Date().toLocaleString() + "\r\n" + msg);
			message.setText("");
		}
	}

	public void addHistory(String msg) {
		String oldText = history.getText();
		history.setText(oldText + "\r\n" + msg);
	}

}