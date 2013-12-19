package application;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

import com.sun.prism.paint.Stop;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class NewZeichnungDialogController {


	  @FXML
	  private TextField zeichnungHoehe;
	  @FXML
	  private TextField zeichnungBreite;
	  @FXML
	  private TextField zeichnungAutor;
	  @FXML
	  private TextField zeichnungKurzBeschr;
	  @FXML
	  private TextField zeichnungDatum;
	  
	  
	  
	  private Stage dialogStage = new Stage();
	  private static Zeichnung zeichnung;
	  private boolean okClicked = false;
	  
	  /**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  @FXML
	  private void initialize() {
	      System.out.println("initialize vom NewZeichnungDialogController");
	  }
	  
	  /**
	  * Sets the stage of this dialog.
	  * @param dialogStage
	  */
	  public void setDialogStage(Stage dialogStage) {
	      this.dialogStage = dialogStage;
	  }
	  
	  public static Zeichnung GetZeichnung() {
		  return zeichnung;
	}

	/**
	  * Returns true if the user clicked OK, false otherwise.
	  * @return
	  */
	  public boolean isOkClicked() {
	      return okClicked;
	  }
	  

	  
	  /**
	  * Called when the user clicks ok.
	  */
	  @FXML
	  private void handleOk() {
	      if (isInputValid()) {
	    	  
	    	  System.out.println("Zeichnung füllen");
	    	  
	    	  zeichnung = new Zeichnung();
	    	  
	    	  zeichnung.setHoehe(Integer.parseInt(zeichnungHoehe.getText()));
	    	  zeichnung.setBreite(Integer.parseInt(zeichnungBreite.getText()));
	    	  zeichnung.setAutor(zeichnungAutor.getText());
	    	  zeichnung.setKurzBeschr(zeichnungKurzBeschr.getText());
	    	  zeichnung.setDatum(zeichnungDatum.getText());
	    	  
	    	  System.out.println("Zeichnung ist gefüllt");
	    	  
	          okClicked = true;
	          dialogStage.close();
	      }
	  }
	  
	  /**
	  * Called when the user clicks cancel.
	  */
	  @FXML
	  private void handleCancel() {
	      dialogStage.close();
	  }
	  
	  /**
	  * Validates the user input in the text fields.
	  * 
	  * @return true if the input is valid
	  */
	  private boolean isInputValid() {
	      String errorMessage = "";

	      if (zeichnungHoehe.getText() == null || zeichnungHoehe.getText().length() == 0) {
	          errorMessage += "Keine Höhe angegeben!\n";
	      } else {
	          // Höhe auf einen Integer-Wert überprüfen
	          try {
	              Integer.parseInt(zeichnungHoehe.getText());
	          } catch (NumberFormatException e) {
	              errorMessage += "Keine gültige Höhe angegeben (muss eine Zahl sein)!\n";
	          }
	      }
	      
	      if (zeichnungBreite.getText() == null || zeichnungBreite.getText().length() == 0) {
	          errorMessage += "Keine Breite angegeben!\n";
	      } else {
	          // Breite auf einen Integer-Wert überprüfen
	          try {
	              Integer.parseInt(zeichnungBreite.getText());
	          } catch (NumberFormatException e) {
	              errorMessage += "Keine gültige Breite angegeben (muss eine Zahl sein)!\n";
	          }
	      }
	   
	      if (zeichnungAutor.getText() == null || zeichnungAutor.getText().length() == 0) {
	          errorMessage += "Keinen Autor angegeben!\n";
	      }
	      if (zeichnungKurzBeschr.getText() == null || zeichnungKurzBeschr.getText().length() == 0) {
	          errorMessage += "Keinen kurz Beschreibung angegeben!\n";
	      }
	      if (zeichnungDatum.getText() == null || zeichnungDatum.getText().length() == 0) {
	          errorMessage += "Kein Datum angegeben!\n";
	      }
	      
	  

	      
	      if (errorMessage.length() == 0) {
	          return true;
	      } else {
	        //  Show the error message
	          
	          
	          Action response = Dialogs.create()
	        	      .title("Bitte korrigieren Sie Ihre Angaben!")
	        	      .message( errorMessage )
	        	      .showError();
	          			
	          return false;
	      }
	  }
	}