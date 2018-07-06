package data;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Watchlist {
<<<<<<< HEAD

=======
	
>>>>>>> georg
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
<<<<<<< HEAD

	public void linkDigitalEntertainment(DigitalEntertainment f) throws IllegalInputException {
		if (this.inhalt.contains(f))
			throw new IllegalInputException("DigitalEntertainment schon vorhanden", f.getName());
		this.inhalt.add(f);

	}

	public void unlinkDigitalEntertainment(DigitalEntertainment f) throws IllegalInputException {
		if (this.inhalt.contains(f))
			throw new IllegalInputException("DigitalEntertainment nicht vorhanden", f.getName());
		this.inhalt.remove(f);
	}

	public ArrayList<DigitalEntertainment> getDigitalEntertainmente() {
		ArrayList<DigitalEntertainment> copy = new ArrayList<DigitalEntertainment>();
		for (int i = 0; i < inhalt.size(); i++) {
			copy.add(inhalt.get(i));
		}
		return copy;
	}

=======
	
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
	
	
>>>>>>> georg
}
