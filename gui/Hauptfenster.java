package gui;

import java.awt.BorderLayout;
import java.awt.Button;
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

import java.util.Iterator;
import java.util.zip.DataFormatException;
import java.awt.event.WindowAdapter;
import java.awt.List;
import data.*;

public class Hauptfenster extends Frame implements ItemListener, ActionListener {


	private Button bFilm;
	private Button bList;
	private Button nList;
	private Button Obsp;
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
		film = new Film("TestFilm1", "reg", 1995, true, 4, 1);
		Obsp = new Button ("alle Objekte speichern");
		try {
			unique.linkFilm(film);
		} catch (IllegalInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(DigitalEntertainment d: unique){
			System.out.println("print Name: " + d.getName());
		}


		listfilm = new List(3, false);
		for(DigitalEntertainment d: unique){
			listfilm.add(d.getName());
		}

		listwatch = new List(3, false);
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


		listfilm.addItemListener(this);
		listwatch.addItemListener(this);
		nList.addActionListener(this);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing( WindowEvent e) {
				dispose();
			}
		});
		setLocationRelativeTo(null);
		pack();
		setVisible(true);

	}

	public void itemStateChanged(ItemEvent e) {

	}
	public void actionPerformed( ActionEvent e1) {
		if(e1.getSource().equals(nList)) {
			new NeueWatchlist(this);	
		}
		if(e1.getSource().equals(Obsp)) {
			save(Verwaltung unique); 
		}
	}


}
