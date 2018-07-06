package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.DataFormatException;

import data.IllegalInputException;


public class Verwaltung implements Iterable<DigitalEntertainment>, Serializable{
	
	private static Verwaltung unique = null;
	private ArrayList<DigitalEntertainment> alleDigitalEntertainmente;
	private ArrayList<Watchlist> alleWatchlists;
	int id = 0;
	//private transient PersistenceSer store = null;
	
	private Verwaltung(){
		alleDigitalEntertainmente = new ArrayList<DigitalEntertainment>();
		alleWatchlists = new ArrayList<Watchlist>();
	}
	
	public static Verwaltung instance(){
		if (unique == null)
		    unique = new Verwaltung();
		return unique;
	}
	
	public void linkDigitalEntertainment(DigitalEntertainment f) throws IllegalInputException {
		if (this.alleDigitalEntertainmente.contains(f))
		    throw new IllegalInputException("DigitalEntertainment schon vorhanden", f.getName());
		this.alleDigitalEntertainmente.add(f);
		//f.setId(id)  Die id hinzufügen ;
		//id++ id hochzählen;
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
	
	public void unlinkWatchlist(DigitalEntertainment w)throws IllegalInputException {
		if (!this.alleWatchlists.contains(w))
		    throw new IllegalInputException("Watchlist nicht vorhanden", w.getName());
		this.alleWatchlists.remove(w);
	    }
	
	public Iterator<DigitalEntertainment> iterator() {
		return this.alleDigitalEntertainmente.iterator();
	    }
	public ArrayList<Watchlist> getAlleWatchlists(){
		ArrayList<Watchlist> copy = new ArrayList<Watchlist>(); 
		for(int i =0; i< alleWatchlists.size();i++){
			copy.add(alleWatchlists.get(i));
		}
		return copy;
	}
	
	
}

