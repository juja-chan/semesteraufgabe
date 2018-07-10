package data;

import java.util.ArrayList;
import java.util.Iterator;

import store.LoadSaveException;
import store.Store;

public class FilmContainer implements Iterable<Film>{
	private static FilmContainer unique = null;
	private ArrayList<Film> alleFilme;
	private static int id = 0;
	private Store store = null;

	private FilmContainer() {
		alleFilme = new ArrayList<Film>();
	}
	
	public static FilmContainer instance() {
		if (unique == null)
			unique = new FilmContainer();
		return unique;
	}

	public void linkFilm(Film f) throws IllegalInputException {
		if (this.alleFilme.contains(f))
			throw new IllegalInputException("Film schon vorhanden", f.getName());
		alleFilme.add(f);
		f.setId(id);
		id++;
	}
	
	public void unlinkFilm(Film f) throws IllegalInputException {
		if (!alleFilme.contains(f))
			throw new IllegalInputException("Film nicht vorhanden", f.getName());
		alleFilme.remove(f);
	}
	
	
	public Film getFilm(int index) {
		if (index >= 0 && index < alleFilme.size())
			return alleFilme.get(index);
		return null;

	}

	public Film searchAlleFilme(int id) {
		for (int i = 0; i < alleFilme.size(); i++) {
			if (alleFilme.get(i).getId() == id)
				return alleFilme.get(i);
		}
		return null;
	}
	
	public ArrayList<Film> getAlleFilme(){
		ArrayList<Film> copy = new ArrayList<Film>();
		for (int i = 0; i < alleFilme.size(); i++)
			copy.add(alleFilme.get(i));
		return copy;
	}
	
	@Override
	public Iterator<Film> iterator() {
		return alleFilme.iterator();
	}
	
	public void load(String filename) throws LoadSaveException {
		store = new Store(filename);
		ArrayList<Film> dBackup = new ArrayList<Film>(alleFilme);
		try {
			store.load(unique);
		} catch (LoadSaveException e) {
			alleFilme = dBackup;
			throw e;
		}
	}

	public void save(String filename) throws LoadSaveException {
		store = new Store(filename);
		store.saveFilme(unique);
	}


}
