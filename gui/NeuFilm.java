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

import data.Verwaltung;
import data.Watchlist;

public class NeuFilm extends Dialog implements ActionListener, ItemListener  {

	private Label namelb, regisseurlb, priolb, gesehenlb;
	private Button speichern, abbrechen;
	private TextField name, regisseur;
	private Choice prio;
	private Checkbox gesehen;
	
	
	NeuFilm(Frame owner){
		super(owner);
		setLayout(new GridLayout(5,2));
		
		namelb = new Label( "Name: ");
		regisseurlb = new Label("Regisseur: ");
		priolb = new Label("Bewertung: ");
		gesehenlb = new Label ("Gesehen: ");
		speichern = new Button("speichern");
		abbrechen = new Button("abbrechen");
		name = new TextField(" ");
		regisseur = new TextField(" ");
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
		add(prio);
		add(gesehenlb);
		add(gesehen);
		add(speichern);
		add(abbrechen);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing( WindowEvent e) {
				dispose();
			}
		});
		
		speichern.addActionListener(this);
		abbrechen.addActionListener(this);
		
		gesehen.addItemListener(this);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		//"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
		
	}
	public void actionPerformed( ActionEvent e) {
		
		if(e.getSource().equals(speichern)) {
			//Verwaltung.instance().linkFilm(new NeuFilm);
		}
		if(e.getSource().equals(abbrechen)) {
			dispose();
		}
		
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(gesehen)) {
				prio.setEnabled(gesehen.getState());

		}
	}
	
}
