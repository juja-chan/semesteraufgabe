package gui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import data.DigitalEntertainment;
import data.Verwaltung;
import data.Watchlist;

public class EditWatchlist extends Dialog implements ActionListener{
	
	private Label filmelb;
	private Label auswahlwatchlistlb;
	private Button hinzufuegen;
	private Button loeschen;
	
	public EditWatchlist(Frame owner, ArrayList<DigitalEntertainment> alleFilme, Watchlist wl) {  
		super(owner);
		
		setLayout(new GridLayout());
		setTitle("Filme einer Watchlist hinzufuegen");
		
		filmelb = new Label("Filme");
		auswahlwatchlistlb = new Label("Watchlist");
		hinzufuegen = new Button("hinzufuegen");
		loeschen = new Button("loeschen");
		
		add(filmelb);
		add(auswahlwatchlistlb);
		add(hinzufuegen);
		add(loeschen);
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing( WindowEvent e) {
				dispose();
			}
		});
		
		hinzufuegen.addActionListener(this);
		loeschen.addActionListener(this);
		setVisible(true);
		pack();
	}
	//Notizen: getselectedItem!
	 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(hinzufuegen)) {
			
		}
	}

	
}
