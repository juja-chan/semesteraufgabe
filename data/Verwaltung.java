package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.DataFormatException;

import data.IllegalInputException;


public class Verwaltung implements Iterable<DigitalEntertainment>, Serializable{
	
	private static Verwaltung unique = null;
	private ArrayList<DigitalEntertainment> alleFilme;
	private ArrayList<Watchlist> alleWatchlists;
	//private transient PersistenceSer store = null;
	
	private Verwaltung(){
		alleFilme = new ArrayList<DigitalEntertainment>();
	}
	
	public static Verwaltung instance(){
		if (unique == null)
		    unique = new Verwaltung();
		return unique;
	}
	
	public void linkFilm(DigitalEntertainment f) throws IllegalInputException {
		if (this.alleFilme.contains(f))
		    throw new IllegalInputException("Film schon vorhanden", f.getName());
		this.alleFilme.add(f);
	    }
	
	public void linkWatchlist(Watchlist w) throws IllegalInputException {
		if (this.alleWatchlists.contains(w))
		    throw new IllegalInputException("Watchlist schon vorhanden", w.getName());
		this.alleWatchlists.add(w);
	}
	
	public void unlinkFilm(DigitalEntertainment f) throws IllegalInputException {
		if (!this.alleFilme.contains(f))
			throw new IllegalInputException("Film nicht vorhanden", f.getName());
		this.alleFilme.remove(f);
	    }
	
	public void unlinkWatchlist(DigitalEntertainment w)throws IllegalInputException {
		if (!this.alleWatchlists.contains(w))
		    throw new IllegalInputException("Watchlist nicht vorhanden", w.getName());
		this.alleWatchlists.remove(w);
	    }
	
	public Iterator<DigitalEntertainment> iterator() {
		return this.alleFilme.iterator();
	    }
	public ArrayList<Watchlist> getAlleWatchlists(){
		return alleWatchlists;
	}
	
	
}

