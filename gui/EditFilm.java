package gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.zip.DataFormatException;

import data.*;

public class EditFilm extends Dialog implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField name, regisseur, jahr;
	private Choice bewertung;
	private Checkbox gesehen;
	private Hauptfenster owner;
	private Film film;

	public EditFilm(Hauptfenster owner, Film film) {
		super(owner, "Film verändern", true);
		this.owner = owner;
		this.film = film;
		setLayout(new GridLayout(6, 2, 5, 5));
		this.setSize(200,300);

		Button abbrechen = new Button("Abbrechen");
		Button speichern = new Button("Speichern");
		name = new TextField(film.getName());
		jahr = new TextField(Integer.toString(film.getJahr()));
		regisseur = new TextField(film.getRegisseur());
		gesehen = new Checkbox("ja");
		gesehen.setState(film.isGesehen());
		bewertung = new Choice();

		bewertung.add("");
		for (int i = 1; i <= 10; i++)
			bewertung.add(i + "");
		bewertung.select(film.getBewertung());
		bewertung.setEnabled(film.isGesehen());

		add(new Label("Name: "));
		add(name);
		add(new Label("Jahr: "));
		add(jahr);
		add(new Label("Regisseur: "));
		add(regisseur);
		add(new Label("Bewertung: "));
		add(bewertung);
		add(new Label("Gesehen: "));
		add(gesehen);
		add(speichern);
		add(abbrechen);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		speichern.addActionListener(this);
		abbrechen.addActionListener(this);
		gesehen.addItemListener(this);

		setLocationRelativeTo(null);
		setVisible(true);
		this.pack();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Speichern")) {
			try {
				film.setName(name.getText());
				film.setRegisseur(regisseur.getText());
				film.setJahr(Integer.parseInt(jahr.getText()));
				film.setGesehen(gesehen.getState());
				if (!gesehen.getState())
					film.setBewertung(0);
				else if (bewertung.getSelectedItem().equals(""))
					throw new DataFormatException("Bitte Bewerten!");
				else
					film.setBewertung(Integer.parseInt(bewertung.getSelectedItem()));

				owner.setMessage("Film erfolgreich verändert");
				dispose();
			} catch (DataFormatException d) {
				owner.setMessage(d.getMessage());
			} catch (NumberFormatException n) {
				owner.setMessage("Keine Zahl als Jahr eingegeben");
			}
		} else if (e.getActionCommand().equals("Abbrechen"))
			dispose();

	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(gesehen))
			bewertung.setEnabled(gesehen.getState());
	}
}
