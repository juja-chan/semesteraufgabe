package gui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import data.*;

public class EditWatchlist extends Dialog implements ActionListener {

	private Label filmelb;
	private Label auswahlwatchlistlb;
	private List listfilm;
	private List listwatch;
	private Button hinzufuegen;
	private Button loeschen;

	public EditWatchlist(Frame owner, Verwaltung unique, Watchlist wl) {
		super(owner);

		setLayout(new GridLayout(2,1));
		setTitle("Filme einer Watchlist hinzufuegen");

		add(new Label("Filme"));
		add(new Label("Watchlist"));
		hinzufuegen = new Button("hinzufuegen");
		loeschen = new Button("loeschen");

		listwatch = new List();
		listfilm = new List();
		for (int i = 0; i < wl.getDigitalEntertainmente().size(); i++) {
			listwatch.add( wl.getDigitalEntertainmente().get(i).getName());
		}
		
		for (DigitalEntertainment d: unique) {
			listfilm.add(d.getName());
		}

		add(hinzufuegen);
		add(loeschen);
		add(listwatch);
		add(listfilm);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		hinzufuegen.addActionListener(this);
		loeschen.addActionListener(this);
		setVisible(true);
		pack();
	}
	// Notizen: getselectedItem!

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(hinzufuegen)) {

		}
	}

}
