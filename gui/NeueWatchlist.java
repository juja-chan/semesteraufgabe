package gui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.zip.DataFormatException;

import data.Watchlist;
import data.IllegalInputException;
import data.Verwaltung;

public class NeueWatchlist extends Dialog implements ActionListener {

	private TextField nametf;
	private Button speichern;
	private Button abbrechen;
	private Label namelb;
	private Hauptfenster owner;

	public NeueWatchlist(Frame owner) {
		super(owner, "nList");

		this.owner = (Hauptfenster) owner;
		setLayout(new GridLayout(2, 2));
		setTitle("Anlegen einer Watchlist");
		nametf = new TextField(" ");
		speichern = new Button("speichern");
		abbrechen = new Button("abbrechen");
		namelb = new Label("Name der Watchlist: ");

		add(namelb);
		add(nametf);
		add(speichern);
		add(abbrechen);

		speichern.addActionListener(this);
		abbrechen.addActionListener(this);
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
		if (e.getSource().equals(speichern)) {
			try {
				Watchlist w;
				try {
					w = new Watchlist(nametf.getText());
					Verwaltung.instance().linkWatchlist(w);
					owner.addWatchlist(w);
				} catch (DataFormatException e1) {
					System.err.println(e1.getMessage());
				}
				
			} catch (IllegalInputException e1) {
				System.err.println(e1.getMessage());
			}
			nametf.setText("");
		} else if (e.getSource().equals(abbrechen)) {
			dispose();
		}
	}
}
