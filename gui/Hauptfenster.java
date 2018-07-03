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
import java.awt.List;

public class Hauptfenster extends Frame implements ItemListener, WindowListener  {

	private Button bFilm;
	private Button bList;
	private List listfilm;
	private List listwatch;
	private Label lFilm;
	private Label lList;

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
		this.addWindowListener(this);


		pack();
		setVisible(true);

	}

	public void itemStateChanged(ItemEvent e) {

	}
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
	

}
