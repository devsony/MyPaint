package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

	
public class Main extends Application {
	
	private static Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MyPaint JavaFX © by Johannes Hetzer");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream( "icon.png" )));
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static boolean showNewZeichnungDialog(Zeichnung z) {
		try {
			 System.out.println("showNewZeichnungDialog!");
			 
		        FXMLLoader fl = new FXMLLoader();
		        fl.setLocation(Main.class.getResource("NewZeichnungDialog.fxml"));
		        fl.load();
		        Parent root = fl.getRoot();
		       
		        Stage modal_dialog = new Stage(StageStyle.DECORATED);
		        modal_dialog.initModality(Modality.WINDOW_MODAL);
		        modal_dialog.initOwner(primaryStage);
		        Scene scene = new Scene(root);
		        
		        // NewZeichnungDialogController controller = loader.getController();
		       
		        NewZeichnungDialogController nzdc = (NewZeichnungDialogController)fl.getController();
		        nzdc.setDialogStage(modal_dialog);
		        modal_dialog.setScene(scene);
		        modal_dialog.setTitle("Neue Zeichnung anlegen...");
		        modal_dialog.showAndWait();
			
			
			return nzdc.isOkClicked();
			
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
			return false;
		}
	}
}
