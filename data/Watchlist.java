package data;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Watchlist {
	
	private String name;
	private ArrayList<DigitalEntertainment> inhalt;

	public Watchlist(String name) throws DataFormatException {
		super();
		setName(name);
		inhalt = new ArrayList<DigitalEntertainment>();
		System.out.println("hat geklappt");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws DataFormatException {
		if (!checkName(name))
			throw new DataFormatException("Anlegen der neuen Watchlist fehlgeschlagen");
		this.name = name;

	}

	public boolean checkName(String name) {
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
