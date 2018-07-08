package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.DataFormatException;

public class Watchlist implements Iterable<DigitalEntertainment>{
	private String name;
	private ArrayList<DigitalEntertainment> alleDigitalEntertainments;

	public Watchlist(String name) throws DataFormatException {
		setName(name);
		alleDigitalEntertainments = new ArrayList<DigitalEntertainment>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws DataFormatException {
		if (!checkName(name))
			throw new DataFormatException("Name der Watchlist darf nicht leer sein");
		this.name = name;
	}

	public boolean checkName(String name) {
		return (name == null || name.equals("")) ? false : true;
	}

	public void linkDigitalEntertainment(DigitalEntertainment f) throws IllegalInputException {
		if (alleDigitalEntertainments.contains(f))
			throw new IllegalInputException("DigitalEntertainment schon vorhanden", f.getName());
		alleDigitalEntertainments.add(f);

	}

	public void unlinkDigitalEntertainment(DigitalEntertainment f) throws IllegalInputException {
		if (alleDigitalEntertainments.contains(f))
			throw new IllegalInputException("DigitalEntertainment nicht vorhanden", f.getName());
		alleDigitalEntertainments.remove(f);
	}

	

	@Override
	public Iterator<DigitalEntertainment> iterator() {
		return alleDigitalEntertainments.iterator();
	}
	
	public String toString() {
		String ausgabe = new String(getName());
		for (DigitalEntertainment d: this)
			ausgabe.concat(Integer.toString(d.getId()));
		return ausgabe;
	}


}
