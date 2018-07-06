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

}
