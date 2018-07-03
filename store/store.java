package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class store {
	private String filename;

	public store(String filename) {
		this.filename = filename;
	}

	public void load(Verwaltung unique) throws LoadSaveException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while (!reader.readLine().equals("ende")) {
				unique.linkfilm(new Film(reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(),
						reader.readLine(), reader.readLine()));
			}
			
			while (!reader.readLine().equals("ende")){
				unique.linkWatchList(reader.readLine());
			}
			
			for (DigitalEntertainment d : unique) {

		} catch (FileNotFoundException e) {
			throw new LoadSaveException("Datei wurde nicht gefunden", e);
		}
	}

	public void save(Verwaltung unique) throws LoadSaveException {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(filename));
			for (DigitalEntertainment d : unique) {
				writer.println("film");
				writer.println(d.getName());
				writer.println(d.getRegisseur());
				writer.println(Integer.toString(d.getJahr()));
				writer.println(Boolean.toString(d.getGesehen()));
				writer.println(Integer.toString(d.getBewertung()));
				writer.println(Integer.toString(d.getId()));
			}
			writer.println("ende");
			ArrayList<Watchlist> watchlist = unique.getWatchlist();
			for (int i = 0; i < watchlist.length(); i++) {
				writer.println("watchlist");
				writer.println(watchlist.getItem(i).getId());
			}
			writer.println("ende");
		} catch (IOException e) {
			throw new LoadSaveException("Datei wurde nicht gefunden", e);
		}
	}
}
