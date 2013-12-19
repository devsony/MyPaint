package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zeichnung {

	private int hoehe;
	   private int breite;
	   private String autor;
	   private String datum;
	   private String kurzBeschr;
	   List<javafx.scene.shape.Shape> list = new ArrayList<javafx.scene.shape.Shape>();
	   
	   public void addShape(javafx.scene.shape.Shape s)
	   {
		   list.add(s);
	   }
	   

	   public void setHoehe(int value) {
	      this.hoehe = value;
	   }
	   
	   public int getHoehe() {
	      return this.hoehe;
	   }
	   
	   
	   public void setBreite(int value) {
	      this.breite = value;
	   }
	   
	   public int getBreite() {
	      return this.breite;
	   }
	   
	  
	   
	   public void setAutor(String value) {
	      this.autor = value;
	   }
	   
	   public String getAutor() {
	      return this.autor;
	   }
	   
	   
	   
	   public void setDatum(String value) {
	      this.datum = value;
	   }
	   
	   public String getDatum() {
	      return this.datum;
	   }
	   
	   
	   public void setKurzBeschr(String value) {
	      this.kurzBeschr = value;
	   }
	   
	   public String getKurzBeschr() {
	      return this.kurzBeschr;
	   }
}
