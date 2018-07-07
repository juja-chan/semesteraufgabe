package gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.zip.DataFormatException;

import data.*;

public class NeuFilm extends Dialog implements ActionListener, ItemListener {

	private Label namelb, regisseurlb, priolb, gesehenlb, jahrlb;
	private Button speichern, abbrechen;
	private TextField name, regisseur, jahr;
	private Choice prio;
	private Checkbox gesehen;
	private Hauptfenster hauptfenster;

	NeuFilm(Hauptfenster owner) {
		super(owner);
		hauptfenster = owner;
		setLayout(new GridLayout(7, 2));

		namelb = new Label("Name: ");
		jahrlb = new Label("Jahr: ");
		regisseurlb = new Label("Regisseur: ");
		priolb = new Label("Bewertung: ");
		gesehenlb = new Label("Gesehen: ");
		speichern = new Button("speichern");
		abbrechen = new Button("abbrechen");
		name = new TextField("");
		jahr = new TextField("");
		regisseur = new TextField("");
		gesehen = new Checkbox("ja");
		prio = new Choice();
		for (int i = 0; i < 11; ++i) {
			prio.add(i + "");
		}
		prio.select("0");
		this.add(prio);

		prio.setEnabled(false);

		add(namelb);
		add(name);
		add(jahrlb);
		add(jahr);
		add(regisseurlb);
		add(regisseur);
		add(priolb);
		add(prio);
		add(gesehenlb);
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

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(speichern)) {
			try {
					System.out.println(name.getText() + regisseur.getText() + Integer.parseInt(jahr.getText())
							+ gesehen.getState() + Integer.parseInt(prio.getSelectedItem()));
					Film temp = new Film(name.getText(), regisseur.getText(), Integer.parseInt(jahr.getText()),
							gesehen.getState(), Integer.parseInt(prio.getSelectedItem()));
					Verwaltung.instance().linkDigitalEntertainment(temp);
					hauptfenster.refreshFilm();

			} catch (DataFormatException | IllegalInputException d) {
				// TODO Auto-generated catch block
				d.printStackTrace();
			} catch(NumberFormatException n){
				System.err.println("Keine Zahl als Jahr eingegeben");
			}

			dispose();

		}

		if (e.getSource().equals(abbrechen))
			dispose();

	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(gesehen)) {
			prio.setEnabled(gesehen.getState());
		}
	}
}