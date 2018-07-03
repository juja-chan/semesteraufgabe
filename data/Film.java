package data;

public class Film extends DigitalEntertainment{
	
	
	
	public Film(String Name, String regisseur, int jahr, boolean gesehen, int bewertung, int id){
		super(Name, regisseur, jahr, gesehen, bewertung, id);
		System.out.println("Film anlegen hat geklappt");
	}
}
