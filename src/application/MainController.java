package application;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
	
	

	  @Override // This method is called by the FXMLLoader when initialization is complete
	    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
	        

	    }
	  
//	  @FXML
//	  private Canvas malFlaeche;
	  

//	@FXML
//	  private Pane malPane;
	  
	  @FXML
	  private TabPane tabPane;
	  
	  
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
			                graphicsContext.beginPath();
			                graphicsContext.moveTo(event.getX(), event.getY());
			                graphicsContext.stroke();
			            }
			        });
			         
			        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
			                new EventHandler<MouseEvent>(){
			 
			            @Override
			            public void handle(MouseEvent event) {
			                graphicsContext.lineTo(event.getX(), event.getY());
			                graphicsContext.stroke();
			            }
			        });
			 
			        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
			                new EventHandler<MouseEvent>(){
			 
			            @Override
			            public void handle(MouseEvent event) {
			 
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
			
			 double canvasWidth = gc.getCanvas().getWidth();
		        double canvasHeight = gc.getCanvas().getHeight();
		         
		        gc.setFill(Color.LIGHTGRAY);
		        gc.setStroke(Color.BLACK);
		        gc.setLineWidth(5);
		 
		        gc.fill();
		        gc.strokeRect(
		                0,              //x of the upper left corner
		                0,              //y of the upper left corner
		                canvasWidth,    //width of the rectangle
		                canvasHeight);  //height of the rectangle
		         
		        gc.setFill(Color.RED);
		        gc.setStroke(Color.BLUE);
		        gc.setLineWidth(1);
			
		}
}
