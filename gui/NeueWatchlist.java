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
import data.Watchlist;
import data.IllegalInputException;
import data.Verwaltung;

public class NeueWatchlist extends Dialog   implements ActionListener  {
	
	private TextField nametf;
	private Button speichern;
	private Button abbrechen; 
	private Label namelb;
	
	public NeueWatchlist( Frame owner ) {
		super(owner, "nList");
		
		setLayout(new GridLayout (2,2));
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
			public void windowClosing( WindowEvent e) {
				dispose();
			}
		});
		pack();
		setLocationRelativeTo(null);
		setVisible(true);		
	}
	
	public void actionPerformed( ActionEvent e) {
		if(e.getSource().equals(speichern)) {
			try {
			Verwaltung.instance().linkWatchlist(new Watchlist(nametf.getText()));
		}
			catch (IllegalInputException e1) {
				System.err.println(e1.getMessage());
			}
			}
			else if(e.getSource().equals(abbrechen)) {
				dispose();
			}
	}
	}


