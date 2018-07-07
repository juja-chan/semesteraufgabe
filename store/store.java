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
		try (BufferedReader reader = new BufferedReader(new FileReader(filename));) {
			String line = reader.readLine();
			while (!line.equals("ende")) {
				if (line.equals("film")) {
					line = reader.readLine();
					if (line == null)
						throw new LoadSaveException("Name erwartet");
					String name = line;
					line = reader.readLine();
					if (line == null)
						throw new LoadSaveException("Regisseur erwartet");
					String regisseur = line;
					line = reader.readLine();
					if (line == null)
						throw new LoadSaveException("Jahr erwartet");
					int jahr = Integer.parseInt(line);
					line = reader.readLine();
					if (line == null)
						throw new LoadSaveException("Gesehen-wert erwartet");
					boolean gesehen = Boolean.parseBoolean(line);
					line = reader.readLine();
					if (line == null)
						throw new LoadSaveException("Bewertung erwartet");
					int bewertung = Integer.parseInt(line);
					line = reader.readLine();
					if (line == null)
						throw new LoadSaveException("Id erwartet");
					int id = Integer.parseInt(line);
					try {
						Film film = new Film(name, regisseur, jahr, gesehen, bewertung);
						unique.linkDigitalEntertainment(film);
						film.setId(id);
					} catch (DataFormatException | IllegalInputException d) {
						throw new LoadSaveException("Datei geändert oder " + d.getMessage(), d);
					}
					line = reader.readLine();
				} else
					throw new LoadSaveException("'film' erwartet");
			}

			line = reader.readLine();
			while (!line.equals("ende")) {
				try {
					if (line.equals("watchlist")) {
						line = reader.readLine();
						if (line == null)
							throw new LoadSaveException("Name erwartet");
						Watchlist watchlist = new Watchlist(line);
						line = reader.readLine();
						while (!line.equals("watchlist")) {
							line = reader.readLine();
							if (line == null || !line.matches("[0-9]+") || !(line.length() > 0))
								throw new LoadSaveException("Id erwartet");
							DigitalEntertainment d = unique.searchDigitalEntertainment(Integer.parseInt(line));
							if (d != null) {
								try {
									watchlist.linkDigitalEntertainment(d);
								} catch (IllegalInputException i) {
									throw new LoadSaveException("Datei geändert oder " + i.getMessage(), i);
								}
							}
						}
							unique.linkWatchlist(watchlist);
						
					} else
						throw new LoadSaveException("'watchlist' erwartet");
				} catch (IllegalInputException e) {
					throw new LoadSaveException("Watchlist bereits vorhanden");
				} catch (DataFormatException e) {
					throw new LoadSaveException("Ungültiger Watchlistname");
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