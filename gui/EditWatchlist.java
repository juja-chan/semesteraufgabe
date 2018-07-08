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
	private Verwaltung unique;
	private Watchlist wl;
	private Hauptfenster owner;

	public EditWatchlist(Hauptfenster owner, Verwaltung unique, Watchlist wl) {
		super(owner);
		setTitle("Filme einer Watchlist hinzufuegen");
		setLayout(new GridLayout(1, 2, 10, 5));

		this.owner = owner;
		this.wl = wl;
		this.unique = unique;
		hinzufuegen = new Button("hinzufuegen");
		loeschen = new Button("loeschen");
		listFilm = new List(5, false);
		listWatch = new List(5, false);
		labelList = new Label("Filme der Watchlist");
		labelFilm = new Label("Unsortierte Filme");

		labelFilm.setAlignment(Label.CENTER);
		labelList.setAlignment(Label.CENTER);

		Panel links = new Panel();
		Panel rechts = new Panel();

		refreshWatchlist();
		refreshFilm();

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

		setLocationRelativeTo(owner);
		setVisible(true);
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(loeschen)) {
			/*
			 * Wird nicht funktionieren, du musst einen Weg finden den Film aus
			 * der Liste herauszulesen try {
			 * wl.unlinkDigitalEntertainment(unique.searchDigitalEntertainment(
			 * listWatch.getSelectedIndex())); } catch (IllegalInputException
			 * i){ owner.setMessage(i.getMessage()); }
			 */
		} else {
			try {
				wl.linkDigitalEntertainment(unique.searchDigitalEntertainment(listFilm.getSelectedIndex()));
			} catch (IllegalInputException i) {
				owner.setMessage(i.getMessage());
			}
		}
	}

	public void refreshWatchlist() {
		listWatch.removeAll();
		for (int i = 0; unique.getAlleWatchlists().size() > i; i++)
			listWatch.add(unique.getWatchlist(i).getName());
	}

	public void refreshFilm() {
		listFilm.removeAll();
		for (DigitalEntertainment d : unique)
			listFilm.add(d.getName());
	}

}
