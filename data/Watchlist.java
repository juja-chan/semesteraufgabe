package data;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Watchlist {
	
	private String name;
	private ArrayList<DigitalEntertainment> inhalt;

	public Watchlist(String name){
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) throws DataFormatException {
		if (checkName(name))
			this.name = name;
		throw new DataFormatException("zu wenig zeichen");

	}
	
	public boolean checkName(String name){
		if (name == null || name.equals("")) {
			return false;
		}
		return true;
	}
	
	public void addFilm(Film f){
		if(inhalt.contains(f)){
			
		}else{
			inhalt.add(f);
		}
	}
	public void removeFilm(Film f){
		if(inhalt.contains(f)){
			
		}else{
			inhalt.remove(f);
		}
	}
	
	public ArrayList<DigitalEntertainment>getInhalt(){
		return inhalt;
	}
	
	
}
