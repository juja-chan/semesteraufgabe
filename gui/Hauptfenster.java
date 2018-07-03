package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
<<<<<<< HEAD
import java.awt.List;

public class Hauptfenster extends Frame implements ItemListener, WindowListener  {
=======
import java.util.Iterator;
import java.util.zip.DataFormatException;
import java.awt.event.WindowAdapter;
import java.awt.List;
import data.*;

public class Hauptfenster extends Frame implements ItemListener {
>>>>>>> master

	private Button bFilm;
	private Button bList;
	private List listfilm;
	private List listwatch;
	private Label lFilm;
	private Label lList;
<<<<<<< HEAD

	public Hauptfenster() {
		//setSize(500,500);
		
		setLayout(new GridLayout(5, 2, 10, 10));
		lFilm = new Label ("unsortierte Filme");
		lList = new Label("Watchlist");
		listfilm = new List(3, false);
		//for(int i=0; i < filme; i++){
		//listfilm.add(filme[i].toString());
		//}
		listfilm.add("Mercury");
		listfilm.add("Venus");
		listfilm.add("Earth");
		listfilm.add("JavaSoft");
		listfilm.add("Mars");
		listfilm.add("Jupiter");
		listfilm.add("Saturn");
		listfilm.add("Uranus");
		listfilm.add("Neptune");
		listfilm.add("Pluto");
=======
	Verwaltung unique;
	private Film film;

	public Hauptfenster() {
		//setSize(500,500);
		super("Bloedes Fenster");
		setLayout(new GridLayout(5, 2, 10, 10));
		unique = Verwaltung.instance();
		lFilm = new Label ("unsortierte Filme");
		lList = new Label("Watchlist");
		try {
			film = new Film("TestFilm1", "reg", 1995, true, 4);
		} catch (DataFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			unique.linkFilm(film);
		} catch (IllegalInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(DigitalEntertainment d: unique){
			System.out.println(d.getName());
			}

		
		listfilm = new List(3, false);
		for(DigitalEntertainment d: unique){
		listfilm.add(d.getName());
		}
		
>>>>>>> master
		listwatch = new List(3, false);
		listwatch.add("asdfn");
		listwatch.add("sfmkl");
		bFilm = new Button ("neuer Film");
		bList = new Button ("neuer Listeneintrag");
		
		add(lFilm);
		add(lList);
		add(listfilm);
		add(listwatch);
		add(bFilm);
		add(bList);


		listfilm.addItemListener(this);
		listwatch.addItemListener(this);
<<<<<<< HEAD
		this.addWindowListener(this);


=======
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing( WindowEvent e) {
				dispose();
			}
		});


		
>>>>>>> master
		pack();
		setVisible(true);

	}

	public void itemStateChanged(ItemEvent e) {

	}
<<<<<<< HEAD
	public void windowClosing( WindowEvent e) {
		dispose();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	// TODO Auto-generated method stub	
	}
	

=======
	


>>>>>>> master
}
