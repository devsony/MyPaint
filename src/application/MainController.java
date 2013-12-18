package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
	
	//Path path;
	
	double x1;
	double y1;
	
	double x2;
	double y2;
	
	/*
	 * 
	 * Auswahl an Geometrischen Formen
	 * 
	 * 0 = leer
	 * 1 = Linie
	 * 2 = PolyLinie
	 * 3 = Kreis
	 * 4 = Ellipse
	 * 5 = Quadrat
	 * 6 = Rechteck
	 */
	
	int form = 0;
	
	
//	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
//
//	    @Override
//	    public void handle(MouseEvent mouseEvent) {
//	      if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
//	        path.getElements().clear();
//	        path.getElements()
//	            .add(new MoveTo(mouseEvent.getX(), mouseEvent.getY()));
//	      } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
//	        path.getElements()
//	            .add(new LineTo(mouseEvent.getX(), mouseEvent.getY()));
//	      }
//
//	    }
//
//	  };
	

	  @Override // This method is called by the FXMLLoader when initialization is complete
	    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
	        

	    }
	  
//	  @FXML
//	  private Canvas malFlaeche;
	  

//	@FXML
//	  private Pane malPane;
	  
	  @FXML
	  private TabPane tabPane;
	  
		@FXML
		private void handleNewLine() {
			System.out.println("handleNewLine Handle");
			
			form = 1;
			
		}
		
		@FXML
		private void handleNewPolyLine() {
			System.out.println("handleNewPolyLine Handle");
			
			form = 2;
		}
		
		@FXML
		private void handleNewCircle() {
			System.out.println("handleNewCircle Handle");
			
			form = 3;
		}
		
		@FXML
		private void handleNewEllipse() {
			System.out.println("handleNewEllipse Handle");
			
			form = 4;
		}
	  
		@FXML
		private void handleNewSquare() {
			System.out.println("handleNewSquare Handle");
			
			form = 5;
		}
		
		@FXML
		private void handleNewrectangle() {
			System.out.println("handleNewrectangle Handle");
			
			form = 6;
		}
		
		@FXML
		private void handleLoadZeichnung() {
			
			System.out.println("handleLoadZeichnung Handle");
			
			  FileChooser fileChooser = new FileChooser();
			  
              //Set extension filter
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
              fileChooser.getExtensionFilters().add(extFilter);
             
              //Show open file dialog
              fileChooser.showOpenDialog(null);
             
              
			
		}
		
	  
		@FXML
		private void handleSaveZeichnung() {
			System.out.println("handleSaveZeichnung Handle");
			
			 FileChooser fileChooser = new FileChooser();
			  
             //Set extension filter
             FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
             fileChooser.getExtensionFilters().add(extFilter);
             
             //Show save file dialog
             File file = fileChooser.showSaveDialog(Main.getPrimaryStage());
             
             
             
             if(file != null){
                 SaveFile("canvas as JSON", file);
             }
			
		}
             

             private void SaveFile(String content, File file){
                 try {
                     FileWriter fileWriter = null;
                      
                     fileWriter = new FileWriter(file);
                     fileWriter.write(content);
                     fileWriter.close();
                 } catch (IOException ex) {
                     Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  
             }
		
	  
		/**
		 * Called when the user clicks the new button.
		 */
		@FXML
		private void handleNewZeichnung() {
			
			System.out.println("handleNewZeichnung erreicht!");
			
			Zeichnung tempZeichnung = new Zeichnung();
			boolean okClicked = Main.showNewZeichnungDialog(tempZeichnung);
			if (okClicked) {
				
				System.out.println("OK Clicked!");
							
				tempZeichnung = NewZeichnungDialogController.GetZeichnung();
		
				
				Tab tab = new Tab();
				 tab.setText(tempZeichnung.getKurzBeschr());
				 //tab.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black;");
				
				Canvas canvas = new Canvas(tempZeichnung.getBreite(),tempZeichnung.getHoehe());
				
				//canvas.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black;");
				
				
				Pane drawPane = new Pane();
				drawPane.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black;");
				drawPane.setPrefSize(tempZeichnung.getBreite(), tempZeichnung.getHoehe());
				drawPane.setMaxSize(tempZeichnung.getBreite(), tempZeichnung.getHoehe());
				drawPane.getChildren().addAll(canvas);
								
				
				final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
				
				 initDraw(graphicsContext);
				 			
		         
			        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
			                new EventHandler<MouseEvent>(){
			 
			            @Override
			            public void handle(MouseEvent event) {
			            	
			            	
			            	x1 = event.getX();
			            	
			            	y1 = event.getY();
			            	
//			                graphicsContext.beginPath();
//			                graphicsContext.moveTo(event.getX(), event.getY());
//			                graphicsContext.stroke();
			            }
			        });
			         
			        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
			                new EventHandler<MouseEvent>(){
			 
			            @Override
			            public void handle(MouseEvent event) {
//			                graphicsContext.lineTo(event.getX(), event.getY());
//			                graphicsContext.stroke();
			            }
			        });
			 
			        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
			                new EventHandler<MouseEvent>(){
			 
			            @Override
			            public void handle(MouseEvent event) {
			            	
			            	x2 = event.getX();
			            	
			            	y2 = event.getY();
			            	
			            	
			            	System.out.println("wert von form: " + form);
			            	
			            	switch(form){
			            	 case 0: 
			                     System.out.println("noch keine Auswahl getroffen"); 
			                     break; 
			                 case 1: 
			                     System.out.println("Male eine Linie");
			                     
			                     graphicsContext.strokeLine(x1, y1, x2, y2);
			                     
								
			                     
			                     break; 
			                 case 2: 
			                     System.out.println("Male eine PolyLinie"); 
			                     break; 
			                 case 3: 
			                     System.out.println("Male ein Kreis"); 
			                     
			                     graphicsContext.strokeOval(x1, y1, x2-x1, y2-y1);
			                     
			                     
			                     
			                     break; 
			                 case 4: 
			                     System.out.println("Male ein Ellipse"); 
			                     
			                     graphicsContext.strokeOval(x1, y1, (x2-x1)-10, (y2-y1)-10);
			                     
			                     break; 
			                 case 5: 
			                     System.out.println("Male ein Quadrat");
			                     
			                     graphicsContext.strokeRect(x1, y1, x2-x1, x2-x1);
			                     
			                     break; 
			                 case 6: 
			                     System.out.println("Male ein Rechteck");
			                     
			                     graphicsContext.strokeRect(x1, y1, x2-x1, y2-y1);
			                     
			                     break; 
			                 default: 
			                     System.out.println("i liegt nicht zwischen null und drei"); 
			            	}
			            	
			            	
			 
			            }
			        });
				
				
			        tab.setContent(
							 
			        		drawPane
							 
							 );
					 
					 tab.isClosable();
					 tabPane.getTabs().add(tab);
					 
					
			}
		}

		private void initDraw(GraphicsContext gc) {
			
			// Setzen von Pinsel Farbe
		        gc.setStroke(Color.BLUE);
		        
			// Setzen von Pinsel Stärke
		        gc.setLineWidth(1);
		        
			
		}
}
