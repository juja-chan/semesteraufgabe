package gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;
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
	private Button speichern, abbrechen;
	private TextField name, regisseur, jahr;
	private Choice bewertung;
	private Checkbox gesehen;
	private Hauptfenster owner;

	EditFilm(Hauptfenster owner, DigitalEntertainment digi) {
		super(owner);
		this.owner = owner;
		setLayout(new GridLayout(6, 2, 5, 5));
		abbrechen = new Button("Abbrechen");
		speichern = new Button("Speichern");
		name = new TextField(digi.getName());
		jahr = new TextField(Integer.toString(digi.getJahr()));
		regisseur = new TextField(digi.getRegisseur());
		gesehen = new Checkbox("ja");
		gesehen.setState(digi.isGesehen());
		bewertung = new Choice();

		bewertung.add("");
		for (int i = 1; i <= 10; i++)
			bewertung.add(i + "");
		System.out.println(digi.getBewertung());
		bewertung.select(digi.getBewertung());
		if(!digi.isGesehen())
			bewertung.setEnabled(false);

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
		pack();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(speichern)) {
			try {
				System.out.println(name.getText() + regisseur.getText() + Integer.parseInt(jahr.getText())
						+ gesehen.getState() + Integer.parseInt(bewertung.getSelectedItem()));
				Film temp = new Film(name.getText(), regisseur.getText(), Integer.parseInt(jahr.getText()),
						gesehen.getState(), Integer.parseInt(bewertung.getSelectedItem()));
				Verwaltung.instance().linkDigitalEntertainment(temp);
				owner.refreshFilm();
				dispose();

			} catch (DataFormatException | IllegalInputException d) {
				owner.setMessage(d.getMessage());
			} catch (NumberFormatException n) {
				owner.setMessage("Keine Zahl als Jahr eingegeben");
			}
		}

		if (e.getSource().equals(abbrechen))
			dispose();
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(gesehen))
			bewertung.setEnabled(gesehen.getState());
	}
}
