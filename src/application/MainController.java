package application;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.google.gson.*;

public class MainController implements Initializable {
	
	//Path path;
	
	double x1;
	double y1;
	
	double x2;
	double y2;
	
	Canvas canvas;
	
	Zeichnung z;
	
	Line line;
	
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
	  private void handleRotate() {
		  System.out.println("handleRotate Handle");
		  
          String response = Dialogs.create()
        	      .title("Bitte Winkel nennen")
        	      .message("Winkel (in Grad): ")
        		  .showTextInput()
          			;
          
          double winkel = 0;
          
          try
          {
        	  
        	  
        	  winkel = Double.parseDouble(response);
        	  
          }
          catch (Exception ex)
          {
        	  Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
		  
		  line.setRotate(winkel);
		  
	  }
	  
	  @FXML
	  private void handleScale() {
		  System.out.println("handleScale Handle");
		  
		  String response = Dialogs.create()
        	      .title("Bitte Faktor der Vergrößerung nennen")
        	      .message("Faktor der Vergrößerung: ")
        		  .showTextInput()
          			;
          
          double zoom = 0;
          
          try
          {
        	  
        	  
        	  zoom = Double.parseDouble(response);
        	  
          }
          catch (Exception ex)
          {
        	  Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
		  
		  
		  line.setScaleX(line.getScaleX() * zoom);
		  line.setScaleY(line.getScaleY() * zoom);
		  
	  }
	  
	  @FXML
	  private TextField txtPinselStaerke;
	  
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
		  private void handleExit() {
			  Platform.exit();
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
             
             //Zeichnung to JSON
             Gson gson = new Gson();
             String jsonZeichnung = gson.toJson(z);
             
             System.out.println(jsonZeichnung);

           if(file != null){
           //	SavePNGFile(file);
        	   SaveFile(jsonZeichnung, file);
       }
		
           
             
//             if(file != null){
//                 SaveFile(jsonCanvas, file);
//             }
			
		}
		

		  private void SaveFile(String jsonZeichnung, File file) {
				
			  FileWriter fileWriter = null;
		        try {
		            fileWriter = new FileWriter(file);
		            fileWriter.write(jsonZeichnung);
		            fileWriter.close();
		        } catch (IOException ex) {
		            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		        } finally {
		            try {
		                fileWriter.close();
		            } catch (IOException ex) {
		                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
				
			}
             

//             private void SavePNGFile(File file){
//            	  if(file != null){
//                      try {
//                    	  
//                    	  int cW = (int) (canvas.getWidth());
//                    	  int cH = (int) (canvas.getHeight());
//                    	  
//                          WritableImage writableImage = new WritableImage(cW, cH);
//                          canvas.snapshot(null, writableImage);
//                          RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
//                          ImageIO.write(renderedImage, "png", file);
//                      } catch (IOException ex) {
//                          Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                      }
//                  }
//             }
		


	private int pinselStaerke()
	  {
		  try
		  {
			  return Integer.parseInt(txtPinselStaerke.getText());
		  }
		  catch (Exception ex)
		  {
			  txtPinselStaerke.setText("2");
			  return 2;
		  }
	  }
             
             
		/**
		 * Called when the user clicks the new button.
		 */
		@FXML
		private void handleNewZeichnung() {
			
			System.out.println("handleNewZeichnung erreicht!");
			
			z = new Zeichnung();
			boolean okClicked = Main.showNewZeichnungDialog(z);
			if (okClicked) {
				
				System.out.println("OK Clicked!");
							
				z = NewZeichnungDialogController.GetZeichnung();
		
				
				Tab tab = new Tab();
				 tab.setText(z.getKurzBeschr());
				 
				 
				 //tab.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black;");
				
				canvas = new Canvas(z.getBreite(),z.getHoehe());
				
				//canvas.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black;");
				
				
				final Pane drawPane = new Pane();
				drawPane.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black;");
				drawPane.setPrefSize(z.getBreite(), z.getHoehe());
				drawPane.setMaxSize(z.getBreite(), z.getHoehe());
				
				//drawPane.getChildren().addAll(canvas);
								
				
				//final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
				
				//initDraw(graphicsContext);
				 			
		         
				drawPane.addEventHandler(MouseEvent.MOUSE_PRESSED,
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
			         
				drawPane.addEventHandler(MouseEvent.MOUSE_DRAGGED,
			                new EventHandler<MouseEvent>(){
			 
			            @Override
			            public void handle(MouseEvent event) {
//			                graphicsContext.lineTo(event.getX(), event.getY());
//			                graphicsContext.stroke();
			            }
			        });
			 
				drawPane.addEventHandler(MouseEvent.MOUSE_RELEASED,
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
			                     
			                     line = new Line();
			                     	line.setStartX(x1);
			                     	line.setStartY(y1);
			                     	line.setEndX(x2);
			                     	line.setEndY(y2);
			                     	line.setStrokeWidth(pinselStaerke());
			                     
			                     
			                     	drawPane.getChildren().add(line);
			                     
			                     	// Linie in die Shape Collection der Zeichnung aufnehmen
			                     	z.addShape(line);
			                     	
			                     //graphicsContext.strokeLine(x1, y1, x2, y2);
			                     
			                    
			                     
			                     
			                     break; 
			                 case 2: 
			                     System.out.println("Male eine PolyLinie"); 
			                     break; 
			                 case 3: 
			                     System.out.println("Male ein Kreis"); 
			                     
			                     //graphicsContext.strokeOval(x1, y1, x2-x1, y2-y1);
			                     
			                     double rad = 0;
			                     
			                     if(x2-x1 > 0)
			                    	 rad = x2-x1;
			                     else
			                    	 rad = x1-x2;

			                     	Circle circle = new Circle(x1,y1,rad,Color.TRANSPARENT);
			                        circle.setStrokeWidth(pinselStaerke());
			                        circle.setStroke(Color.BLACK);
			                        
			                     
			                     	drawPane.getChildren().add(circle);
			                     	
			                     	// Kreis in die Shape Collection der Zeichnung aufnehmen
			                     	z.addShape(circle);
			                     
			                     break; 
			                 case 4: 
			                     System.out.println("Male ein Ellipse"); 
			                     
			                     //graphicsContext.strokeOval(x1, y1, (x2-x1)-10, (y2-y1)-10);
			                     
//			                     Ellipse ellipse = EllipseBuilder.create()
//			                             .centerX(x1)
//			                             .centerY(y1)
//			                             .radiusX(x2-x1)
//			                             .radiusY(y2-y1)
//			                             .strokeWidth(3)
//			                             .stroke(Color.BLACK)
//			                             .fill(Color.WHITE)
//			                             .build();
			                     
			                     double xrad = 0;
			                     
			                     if(x2-x1 > 0)
			                    	 xrad = x2-x1;
			                     else
			                    	 xrad = x1-x2;
			                     
			                     double yrad = 0;
			                     
			                     if(y2-y1 > 0)
			                    	 yrad = y2-y1;
			                     else
			                    	 yrad = y1-y2;
			                     
			                     Ellipse ellipse = new Ellipse(x1,y1,xrad,yrad);
			                     ellipse.setStrokeWidth(pinselStaerke());
			                     ellipse.setStroke(Color.BLACK);
			                     ellipse.setFill(Color.TRANSPARENT);
			                     drawPane.getChildren().add(ellipse);
			                     
			                     ellipse.setId("meineEllipse");
			                     
			                     System.out.println("eillipse id: " + ellipse.getId());
			                     
			                     	// Ellipse in die Shape Collection der Zeichnung aufnehmen
			                     	z.addShape(ellipse);
			                     
			                     break; 
			                 case 5: 
			                     System.out.println("Male ein Quadrat");
			                     
			                     //graphicsContext.strokeRect(x1, y1, x2-x1, x2-x1);
			                     
			                     double sW = 0;
			                     
			                     if(x2-x1 > 0)
			                    	 sW = x2-x1;
			                     else
			                    	 sW = x1-x2;
			                     
			                     double sH = 0;
			                     
			                     if(y2-y1 > 0)
			                    	 sH = y2-y1;
			                     else
			                    	 sH = y1-y2;
			                     
			                     Rectangle quadrat = new Rectangle();
			                     quadrat.setX(x1);
			                     quadrat.setY(y1);
			                     quadrat.setWidth(sW);
			                     quadrat.setHeight(sH);
			                     quadrat.setStrokeWidth(pinselStaerke());
			                     quadrat.setStroke(Color.BLACK);
			                     quadrat.setFill(Color.TRANSPARENT);
			                     drawPane.getChildren().add(quadrat);
			                     
			                     	// Quadrat in die Shape Collection der Zeichnung aufnehmen
			                     	z.addShape(quadrat);
			                     
			                     break; 
			                 case 6: 
			                     System.out.println("Male ein Rechteck");
			                     
			                     //graphicsContext.strokeRect(x1, y1, x2-x1, y2-y1);
			                     double sWr = 0;
			                     
			                     if(x2-x1 > 0)
			                    	 sWr = x2-x1;
			                     else
			                    	 sWr = x1-x2;
			                     
			                     double sHr = 0;
			                     
			                     if(y2-y1 > 0)
			                    	 sHr = y2-y1;
			                     else
			                    	 sHr = y1-y2;
			                     
			                     Rectangle rechteck = new Rectangle();
			                     rechteck.setX(x1);
			                     rechteck.setY(y1);
			                     rechteck.setWidth(sWr);
			                     rechteck.setHeight(sHr);
			                     rechteck.setStrokeWidth(pinselStaerke());
			                     rechteck.setStroke(Color.BLACK);
			                     rechteck.setFill(Color.TRANSPARENT);
			                     drawPane.getChildren().add(rechteck);
			                     
			                     	// Rechteck in die Shape Collection der Zeichnung aufnehmen
			                     	z.addShape(rechteck);
			                     
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

//		private void initDraw(GraphicsContext gc) {
//			
//			// Setzen von Pinsel Farbe
//		        gc.setStroke(Color.BLUE);
//		        
//			// Setzen von Pinsel Stärke
//		        gc.setLineWidth(1);
//		        
//			
//		}
}
