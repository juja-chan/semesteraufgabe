package gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import data.DigitalEntertainment;
import data.Film;
import data.IllegalInputException;
import data.Verwaltung;

public class EdithFilm extends Dialog implements ActionListener, ItemListener {

	private Label namelb, regisseurlb, priolb, gesehenlb, jahrlb;
	private Button speichern, loeschen;
	private TextField name, regisseur, jahr;
	private Choice prio;
	private Checkbox gesehen;
	private Film f;

	public EdithFilm(Frame owner, Film f) {
		super(owner);
		this.f = f;

		namelb = new Label( "Name: ");
		regisseurlb = new Label("Regisseur: ");
		priolb = new Label("Bewertung: ");
		gesehenlb = new Label ("Gesehen: ");
		jahrlb = new Label("Jahr");
		speichern = new Button("speichern");
		loeschen = new Button("abbrechen");
		name = new TextField(" ");
		regisseur = new TextField(" ");
		jahr = new TextField("");
		gesehen = new Checkbox("ja");
		prio = new Choice();
		for (int i = 0; i < 11; ++i) { prio.add(i + "");;
		}
		prio.select("1");
		this.add(prio);

		prio.setEnabled(false);

		add(namelb);
		add(name);
		add(regisseurlb);
		add(regisseur);
		add(priolb);
		add(jahrlb);
		add(prio);
		add(jahr);
		add(gesehenlb);
		add(gesehen);
		add(speichern);
		add(loeschen);


		this.addWindowListener(new WindowAdapter() {
			public void windowClosing( WindowEvent e) {
				dispose();
			}
		});

		speichern.addActionListener(this);
		loeschen.addActionListener(this);

		gesehen.addItemListener(this);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		DigitalEntertainment f;
		if(e.getSource().equals(speichern)) {

			try {
				Verwaltung.instance().linkDigitalEntertainment(new EdithFilm); //alle set´s
			} catch (IllegalInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(loeschen)) {

			try {
				Verwaltung.instance().unlinkDigitalEntertainment(f);
			} catch (IllegalInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	}

