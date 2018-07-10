package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.DataFormatException;

import data.*;

public class Store {
	private String filename;

	public Store(String filename) {
		this.filename = filename;
	}

	public void load(FilmContainer unique) throws LoadSaveException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename));) {
			String line = reader.readLine();
			if (line == null)
				throw new LoadSaveException("Leere Datei");
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
						unique.linkFilm(film);
						film.setId(id);
					} catch (DataFormatException | IllegalInputException d) {
						throw new LoadSaveException("Datei geändert oder " + d.getMessage(), d);
					}
					line = reader.readLine();
				} else
					throw new LoadSaveException("'film' erwartet");
			}

			line = reader.readLine();
			while (line != null && !line.equals("ende")) {
				try {
					if (line.equals("watchlist")) {
						line = reader.readLine();
						if (line == null)
							throw new LoadSaveException("Name erwartet");
						Watchlist watchlist = new Watchlist(line);
						line = reader.readLine();
						while (!line.equals("watchlist") && !line.equals("ende")) {
							if (line == null || !line.matches("[0-9]+") || !(line.length() > 0))
								throw new LoadSaveException("Id erwartet");
							Film f = unique.searchAlleFilme(Integer.parseInt(line));
							if (f != null) {
								try {
									watchlist.linkFilm(f);
								} catch (IllegalInputException i) {
									throw new LoadSaveException("Datei geändert oder " + i.getMessage(), i);
								}
							}
							line = reader.readLine();
						}
						WatchlistContainer.instance().linkWatchlist(watchlist);
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

	public void saveWatchlist(WatchlistContainer unique) throws LoadSaveException {
		if (!filename.endsWith(".txt"))
			throw new LoadSaveException("Muss eine .txt-datei sein");
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
			writer.println("ende");
			for (Watchlist w : unique) {
				writer.println("watchlist");
				writer.println(w.getName());
				for (DigitalEntertainment d : w)
					writer.println(d.getId());
			}
			writer.println("ende");
		} catch (IOException e) {
			throw new LoadSaveException("Datei wurde nicht gefunden", e);
		}
	}

	public void saveFilme(FilmContainer unique) throws LoadSaveException {
		if (!filename.endsWith(".txt"))
			throw new LoadSaveException("Muss eine .txt-datei sein");
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
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
		} catch (IOException e) {
			throw new LoadSaveException("Datei wurde nicht gefunden", e);
		}
	}
}
