package data;

import java.util.ArrayList;
import java.util.Iterator;

import store.LoadSaveException;
import store.Store;

public class WatchlistContainer implements Iterable<Watchlist> {

	private static WatchlistContainer unique = null;
	private ArrayList<Watchlist> alleWatchlists;
	private Store store = null;

	private WatchlistContainer() {
		alleWatchlists = new ArrayList<Watchlist>();
	}

	public static WatchlistContainer instance() {
		if (unique == null)
			unique = new WatchlistContainer();
		return unique;
	}

	public void linkWatchlist(Watchlist w) throws IllegalInputException {
		if (this.alleWatchlists.contains(w))
			throw new IllegalInputException("Watchlist schon vorhanden", w.getName());
		this.alleWatchlists.add(w);
	}

	public void unlinkWatchlist(Watchlist w) throws IllegalInputException {
		if (!this.alleWatchlists.contains(w))
			throw new IllegalInputException("Watchlist nicht vorhanden", w.getName());
		alleWatchlists.remove(w);
	}

	public Watchlist getWatchlist(int index) {
		if (index >= 0 && index < alleWatchlists.size())
			return alleWatchlists.get(index);
		return null;
	}

	public ArrayList<Watchlist> getAlleWatchlists() {
		ArrayList<Watchlist> copy = new ArrayList<Watchlist>();
		for (int i = 0; i < alleWatchlists.size(); i++)
			copy.add(alleWatchlists.get(i));
		return copy;
	}

	@Override
	public Iterator<Watchlist> iterator() {
		return alleWatchlists.iterator();
	}

	public void load(String filename) throws LoadSaveException {
		store = new Store(filename);
		ArrayList<Watchlist> wBackup = new ArrayList<Watchlist>(alleWatchlists);
		try {
			FilmContainer.instance().load(filename);
		} catch (LoadSaveException e) {
			alleWatchlists = wBackup;
			throw e;
		}
	}

	public void save(String filename) throws LoadSaveException {
		store = new Store(filename);
		store.saveWatchlist(unique);
	}

}
