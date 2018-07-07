package gui;

import java.awt.BorderLayout;
import java.awt.Button;

import java.awt.Component;

import java.awt.FileDialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.DataFormatException;

import java.awt.event.WindowAdapter;
import java.awt.List;
import data.*;
import store.*;

public class Hauptfenster extends Frame implements ItemListener, ActionListener {


	private Button bFilm;
	private Button bList;
	private Button nList;
	private Button Obsp;
	private Button storeladen;
	private List listfilm;
	private List listwatch;
	private Label lFilm;
	private Label lList;


	Verwaltung unique;
	private Film film;

	public Hauptfenster() {

		super("Bloedes Fenster");
		setLayout(new GridLayout(5, 2, 10, 10));
		unique = Verwaltung.instance();
		bFilm = new Button ("neuer Film");
		bList = new Button ("neuer Listeneintrag");
		nList = new Button ("neue Watchlist anglegen");
		lFilm = new Label ("unsortierte Filme");
		lList = new Label("Watchlist");
		try {
			film = new Film("TestFilm1", "reg", 1995, true, 4);
		} catch (DataFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Obsp = new Button ("alle Objekte speichern");
		storeladen = new Button("Verwaltung laden");

		try {
			unique.linkDigitalEntertainment(film);
		} catch (IllegalInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(DigitalEntertainment d: unique){
			System.out.println("print Name: " + d.getName());

		}


		listfilm = new List(5, false);


		listwatch = new List(5, false);
		listwatch.add("asdfn");
		listwatch.add("sfmkl");

		add(lFilm);
		add(lList);
		add(listfilm);
		add(listwatch);
		add(bFilm);
		add(bList);
		add(nList);
		add(Obsp);
		add(storeladen);

		
		refreshFilm();
		listfilm.addItemListener(this);
		listwatch.addItemListener(this);
		nList.addActionListener(this);
		bList.addActionListener(this);
		bFilm.addActionListener(this);
		storeladen.addActionListener(this);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing( WindowEvent e) {
				dispose();
			}
		});
		setLocationRelativeTo(null);
		pack();
		setVisible(true);

	}

	public void itemStateChanged(ItemEvent e) { //get selectedItem
		int selection =  listwatch.getSelectedIndex();
		if(selection != 0) {
			new EditWatchlist(this, null, null);
		}
	}
	public void itemStateChanged1(ItemEvent e1){
		int selection1 = listwatch.getSelectedIndex();
		if(selection1 != 0) {
			//new EdithFilm(this);
		}
	}


	public void actionPerformed( ActionEvent e1) {
		if(e1.getSource().equals(nList)) {
			new NeueWatchlist(this);	
		}
		if(e1.getSource().equals(Obsp)) {
			//getWatchlist(unique); 
		}
		if(e1.getSource().equals(bList)) {
			new EditWatchlist(null, null, null);
		}
		if(e1.getSource().equals(bFilm)) {
			new NeuFilm(this);
		}
		if(e1.getSource().equals(storeladen)) {
			Store store = new Store("Platzhalter.txt");

			try {
				store.load(unique);
			} catch (LoadSaveException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void addWatchlist(Watchlist w) {
		listwatch.add(w.getName());
	}

	public void addDigitalEntertainment(DigitalEntertainment d) {
		listfilm.add(d.getName());
	}

	public void addFilm(Film f) {
		listwatch.add(f.getName());
	}
	public void refreshFilm() {
		listfilm.removeAll();
		for(DigitalEntertainment d: unique){
			listfilm.add(d.toString());
			}

	
	private void onLoad() {
		FileDialog fd = new FileDialog(this, "Load Parcels...", FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() != null) {
			String filename = fd.getDirectory() + fd.getFile();
			try {
				unique.load(filename);
				setMessage("Erfolgreich geladen");
			} catch (LoadSaveException e) {
				setMessage("Load" + e.getMessage());
			} 
		} else
			setMessage("Keine Datei zum Laden gewählt!");
	}

	private void onSave() {
		FileDialog fd = new FileDialog(this, "Save", FileDialog.SAVE);
		fd.setVisible(true);
		if (fd.getFile() != null) {
			String filename = fd.getDirectory() + fd.getFile();
			try {
				unique.save(filename);
				setMessage("Erfolgreich gespeichert");
			} catch (LoadSaveException e) {
				setMessage("Speicherfehler: " + e.getMessage());
			}
		} else
			setMessage("Keine Datei zum Speichern gewählt!");
	}

	private void setMessage(String string) {
		

	}


}
