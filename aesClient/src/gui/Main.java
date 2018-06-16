package gui;
	
import client.Client;
import javafx.application.Application;
import javafx.stage.Stage;
import user.User;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static final String HOST_IP = "";
	public static final int HOST_PORT = 5555;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		Client c = new Client(HOST_IP, HOST_PORT);
		System.out.println(User.login(c, "111111112", "1234"));
		System.out.println(User.login(c, "111111112", "1234"));
		System.out.println(User.logout(c, "111111112"));

	}
}
