package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import data.*;

public class EditWatchlist extends Dialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List listFilm;
	private List listWatch;
	private Button hinzufuegen;
	private Button loeschen;
	private Label labelList;
	private Label labelFilm;
	private FilmContainer fcon;
	private Watchlist wl;
	private Hauptfenster owner;
	private ArrayList<Film> inwatch;
	private ArrayList<Film> filme;

	public EditWatchlist(Hauptfenster owner, Watchlist wl) {
		super(owner, "Watchlist verändern", true);
		setLayout(new GridLayout(1, 2, 10, 5));

		this.owner = owner;
		this.wl = wl;
		inwatch = wl.getAlleFilme();
		fcon = FilmContainer.instance();
		hinzufuegen = new Button("Hinzufuegen");
		loeschen = new Button("Entfernen");
		listFilm = new List(5, false);
		listWatch = new List(5, false);
		labelList = new Label("Filme der Watchlist");
		labelFilm = new Label("Unsortierte Filme");

		labelFilm.setAlignment(Label.CENTER);
		labelList.setAlignment(Label.CENTER);

		Panel links = new Panel();
		Panel rechts = new Panel();

		refresh();

		links.setLayout(new BorderLayout());
		rechts.setLayout(new BorderLayout());

		links.add(labelFilm, BorderLayout.NORTH);
		links.add(listFilm, BorderLayout.CENTER);
		links.add(hinzufuegen, BorderLayout.SOUTH);
		rechts.add(labelList, BorderLayout.NORTH);
		rechts.add(listWatch, BorderLayout.CENTER);
		rechts.add(loeschen, BorderLayout.SOUTH);

		add(links);
		add(rechts);

		hinzufuegen.addActionListener(this);
		loeschen.addActionListener(this);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Entfernen")) {
			try {
				Film f = inwatch.get(listWatch.getSelectedIndex());
				wl.unlinkFilm(f);
				inwatch.remove(f);
				refresh();
			} catch (IllegalInputException i) {
				owner.setMessage("Entfernen des Films fehlgeschlagen: " + i.getMessage());
			} catch (ArrayIndexOutOfBoundsException a) {
				owner.setMessage("Bitte einen Film auswählen");
			}
		} else {
			try {
				Film f = filme.get(listFilm.getSelectedIndex());
				wl.linkFilm(f);
				inwatch.add(f);
				listFilm.remove(listFilm.getSelectedItem());
				listWatch.add(f.getName());
			} catch (IllegalInputException i) {
				owner.setMessage("Hinzufügen des Films fehlgeschlagen: " + i.getMessage());
			} catch (ArrayIndexOutOfBoundsException a) {
				owner.setMessage("Bitte einen Film auswählen");
			}
		}
	}

	public void refresh() {
		listWatch.removeAll();
		listFilm.removeAll();
		filme = fcon.getAlleFilme();
		for (int i = 0; i < inwatch.size(); i++)
			listWatch.add(inwatch.get(i).getName());
		for (int i = 0; i < inwatch.size(); i++)
			filme.remove(inwatch.get(i));
		for (int i = 0; i < filme.size(); i++)
			listFilm.add(filme.get(i).getName());
	}
}
