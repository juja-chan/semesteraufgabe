package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.DataFormatException;

public class Watchlist implements Iterable<Film> {
	private String name;
	private int id;
	private ArrayList<Film> alleFilme;

	public Watchlist(String name) throws DataFormatException {
		setName(name);
		alleFilme = new ArrayList<Film>();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Film getFilm(int index) {
		if (index >= 0 && index < alleFilme.size())
			return alleFilme.get(index);
		return null;

	}

	public ArrayList<Film> getAlleFilme() {
		ArrayList<Film> copy = new ArrayList<Film>();
		for (int i = 0; i < alleFilme.size(); i++)
			copy.add(alleFilme.get(i));
		return copy;
	}

	public void linkFilm(Film f) throws IllegalInputException {
		if (alleFilme.contains(f))
			throw new IllegalInputException("Film schon vorhanden", f.getName());
		alleFilme.add(f);
	}

	public void unlinkFilm(Film f) throws IllegalInputException {
		if (!alleFilme.contains(f))
			throw new IllegalInputException("Film nicht vorhanden", f.getName());
		alleFilme.remove(f);
	}

	@Override
	public Iterator<Film> iterator() {
		return alleFilme.iterator();
	}

	@Override
	public String toString() {
		String ausgabe = new String(getName() + ": Filme: ");
		for (DigitalEntertainment d : this)
			ausgabe.concat(d.getId() + " ");
		return ausgabe;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null) {
			if (o.getClass().equals(getClass())) {
				Watchlist d = (Watchlist) o;
				return (d.getName().equals(getName()) && d.getId() == getId());
			}
		}
		return false;
	}

}
