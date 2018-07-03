package data;

public class Film extends DigitalEntertainment{
	
	
	
	public Film(String Name, String regisseur, int jahr, boolean gesehen, int bewertung){
		super(Name, regisseur, bewertung, gesehen, bewertung);
		System.out.println("Film anlegen hat geklappt");
	}
}
