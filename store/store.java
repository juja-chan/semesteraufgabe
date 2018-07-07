package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import data.*;

public class Store {
	private String filename;

	public Store(String filename) {
		this.filename = filename;
	}

	public void load(Verwaltung unique) throws LoadSaveException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename));){
			
			while (!reader.readLine().equals("ende")) {
				try {
					unique.linkDigitalEntertainment(new Film(reader.readLine(), reader.readLine(),
							Integer.parseInt(reader.readLine()), Boolean.parseBoolean(reader.readLine()),
							Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine())));
				} catch (IllegalInputException i) {
					throw new LoadSaveException("Datei geändert oder " + i.getMessage(), i);
				}
			}

			while (!reader.readLine().equals("ende")) {
				try {
					Watchlist watchlist = new Watchlist(reader.readLine());
					String line = reader.readLine();
					while (!line.equals("watchlist")) {
						DigitalEntertainment d = unique.searchDigitalEntertainment(Integer.parseInt(line));
						if (d != null) {
							try {
								watchlist.linkDigitalEntertainment(d);
							} catch (IllegalInputException i) {
								throw new LoadSaveException("Datei geändert oder " + i.getMessage() , i);
							}
						}
					}
					unique.linkWatchlist(watchlist);
				} catch (DataFormatException d) {
					throw new LoadSaveException("Ungültiger Watchlistname; watchlist wird nicht geladen.", d);
				} catch (IllegalInputException i) {
					throw new LoadSaveException("Bereits vorhandene Watchlist kann nicht geladen werden", i);
				}
			}
		} catch (FileNotFoundException e) {
			throw new LoadSaveException("Datei wurde nicht gefunden", e);
		} catch (IOException e) {
			throw new LoadSaveException("Input/OutputException", e);
		}
	}

	public void save(Verwaltung unique) throws LoadSaveException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename));) {

			for (DigitalEntertainment d : unique) {
				writer.println("film");
				writer.println(d.getName());
				writer.println(d.getRegisseur());
				writer.println(Integer.toString(d.getJahr()));
				writer.println(Boolean.toString(d.isGesehen()));
				writer.println(Integer.toString(d.getBewertung()));
				writer.println(Integer.toString(d.getId()));
			}
			writer.println("ende");
			ArrayList<Watchlist> watchlist = unique.getAlleWatchlists();
			for (int i = 0; i < watchlist.size(); i++) {
				writer.println("watchlist");
				writer.println(watchlist.get(i).getName());
				for (int j = 0; j < watchlist.get(i).getDigitalEntertainmente().size(); j++)
					writer.println(watchlist.get(i).getDigitalEntertainmente().get(i).getId());
			}
			writer.println("ende");
		} catch (IOException e) {
			throw new LoadSaveException("Datei wurde nicht gefunden", e);
		}
	}
}
