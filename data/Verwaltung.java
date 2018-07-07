package data;

import java.util.ArrayList;
import java.util.Iterator;

import store.*;

public class Verwaltung implements Iterable<DigitalEntertainment>{

	private static Verwaltung unique = null;
	private ArrayList<DigitalEntertainment> alleDigitalEntertainmente;
	private ArrayList<Watchlist> alleWatchlists;
	int id = 0;
	private Store store = null;

	private Verwaltung() {
		alleDigitalEntertainmente = new ArrayList<DigitalEntertainment>();
		alleWatchlists = new ArrayList<Watchlist>();
	}

	public static Verwaltung instance() {
		if (unique == null)
			unique = new Verwaltung();
		return unique;
	}

	public void linkDigitalEntertainment(DigitalEntertainment f) throws IllegalInputException {
		if (this.alleDigitalEntertainmente.contains(f))
			throw new IllegalInputException("DigitalEntertainment schon vorhanden", f.getName());
		this.alleDigitalEntertainmente.add(f);
		f.setId(id);
		id++;
	}

	public void linkWatchlist(Watchlist w) throws IllegalInputException {
		if (this.alleWatchlists.contains(w))
			throw new IllegalInputException("Watchlist schon vorhanden", w.getName());
		this.alleWatchlists.add(w);
	}

	public void unlinkDigitalEntertainment(DigitalEntertainment f) throws IllegalInputException {
		if (!this.alleDigitalEntertainmente.contains(f))
			throw new IllegalInputException("DigitalEntertainment nicht vorhanden", f.getName());
		this.alleDigitalEntertainmente.remove(f);
	}

	public void unlinkWatchlist(DigitalEntertainment w) throws IllegalInputException {
		if (!this.alleWatchlists.contains(w))
			throw new IllegalInputException("Watchlist nicht vorhanden", w.getName());
		this.alleWatchlists.remove(w);
	}

	public Iterator<DigitalEntertainment> iterator() {
		return this.alleDigitalEntertainmente.iterator();
	}

	public ArrayList<Watchlist> getAlleWatchlists() {
		ArrayList<Watchlist> copy = new ArrayList<Watchlist>();
		for (int i = 0; i < alleWatchlists.size(); i++)
			copy.add(alleWatchlists.get(i));
		return copy;
	}

	public DigitalEntertainment searchDigitalEntertainment(int id) {
		for (int i = 0; i < alleDigitalEntertainmente.size(); i++) {
			if (alleDigitalEntertainmente.get(i).getId() == id)
				return alleDigitalEntertainmente.get(i);
		}
		return null;
	}

	public void load(String filename) throws LoadSaveException{
		store = new Store(filename);
		ArrayList<Watchlist> wBackup = new ArrayList<Watchlist>(alleWatchlists);
		alleWatchlists.clear();
		ArrayList<DigitalEntertainment> dBackup = new ArrayList<DigitalEntertainment>(alleDigitalEntertainmente);
		alleDigitalEntertainmente.clear();
		try {
			store.load(unique);
		} catch (LoadSaveException e) {
			alleWatchlists = wBackup;
			alleDigitalEntertainmente = dBackup;
			throw e;
		}
	}

	public void save(String filename) throws LoadSaveException{
		store = new Store(filename);
		store.save(unique);
		
	}

}
