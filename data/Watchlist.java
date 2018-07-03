package data;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Watchlist extends ArrayList<Film> {
	
	private String name;

	public Watchlist(String name){
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) throws DataFormatException {
		if (checkName(name))
			this.name = name;
		throw new DataFormatException("zu wenig zeichen");

	}
	
	public boolean checkName(String name){
		if (name == null || name.equals("")) {
			return false;
		}
		return true;
	}
	
	public void addFilm(Film f){
		
	}
	public void removeFilm(Film f){
		
	}
	/*
	public getFilme(){
		return ArrayList;
	}
	*/
}
