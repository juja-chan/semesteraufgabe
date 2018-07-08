package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.zip.DataFormatException;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextField;

import java.awt.event.WindowAdapter;
import data.*;
import store.*;

public class Hauptfenster extends Frame implements ItemListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button nFilm;
	private Button nList;
	private List listFilm;
	private List listWatch;
	private Label labelList;
	private Label labelFilm;
	private Verwaltung unique;
	private TextField statusTextField;
	private Film film;

	public Hauptfenster() {

		super("Filme und Watchlisten");

		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem loadMI = new MenuItem("Load...");
		MenuItem saveMI = new MenuItem("Save...");

		unique = Verwaltung.instance();
		statusTextField = new TextField();
		nFilm = new Button("Neuer Film");
		nList = new Button("Neue Watchlist");
		listFilm = new List(5, false);
		listWatch = new List(5, false);
		labelList = new Label("Watchlisten");
		labelFilm = new Label("Unsortierte Filme");

		Panel links = new Panel();
		Panel rechts = new Panel();
		Panel oben = new Panel();

		links.setLayout(new BorderLayout());
		rechts.setLayout(new BorderLayout());
		oben.setLayout(new GridLayout(1, 2, 5, 5));

		labelFilm.setAlignment(Label.CENTER);
		labelList.setAlignment(Label.CENTER);

		listFilm.addItemListener(this);
		listWatch.addItemListener(this);
		nList.addActionListener(this);
		nFilm.addActionListener(this);
		statusTextField.setEditable(false);

		loadMI.addActionListener(this);
		saveMI.addActionListener(this);

		links.add(labelFilm, BorderLayout.NORTH);
		links.add(listFilm, BorderLayout.CENTER);
		links.add(nFilm, BorderLayout.SOUTH);
		rechts.add(labelList, BorderLayout.NORTH);
		rechts.add(listWatch, BorderLayout.CENTER);
		rechts.add(nList, BorderLayout.SOUTH);

		menu.add(loadMI);
		menu.add(saveMI);
		menubar.add(menu);
		setMenuBar(menubar);

		oben.add(links);
		oben.add(rechts);
		add(oben, BorderLayout.NORTH);
		add(statusTextField, BorderLayout.SOUTH);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		setLocationRelativeTo(null);
		pack();
		setVisible(true);

		try {
			film = new Film("Star Wars", "George Lucas", 1977, true, 10);
			Film s = new Film("Der weisse Hai", "S. Spielberg", 1978, true, 8);
			unique.linkDigitalEntertainment(film);
			unique.linkDigitalEntertainment(s);
			refreshFilm();
		} catch (DataFormatException | IllegalInputException e) {
			setMessage(e.getMessage());
		}

	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(listWatch)) {
			for (int i = 0; i < unique.getAlleWatchlists().size(); i++) {
				if (unique.getWatchlist(i).getName().equals(listWatch.getSelectedItem()))
					new EditWatchlist(this, unique, unique.getAlleWatchlists().get(i));
			}
		} else {
			new EditFilm(this, unique.searchDigitalEntertainment(listFilm.getSelectedIndex()));
		}
	}

	public void actionPerformed(ActionEvent e1) {

		if (e1.getActionCommand().equals("Save..."))
			onSave();
		else if (e1.getActionCommand().equals("Load..."))
			onLoad();
		else if (e1.getSource().equals(nFilm))
			new NeuerFilm(this);
		else if (e1.getSource().equals(nList))
			new NeueWatchlist(this);
	}

	public void refreshWatchlist() {
		listWatch.removeAll();
		for (int i = 0; unique.getAlleWatchlists().size() > i; i++)
			listWatch.add(unique.getWatchlist(i).getName());
	}

	public void refreshFilm() {
		listFilm.removeAll();
		for (DigitalEntertainment d : unique)
			listFilm.add(d.getName());
	}

	private void onLoad() {
		FileDialog fd = new FileDialog(this, "Load Parcels...", FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() != null) {
			String filename = fd.getDirectory() + fd.getFile();
			try {
				unique.load(filename);
				setMessage("Erfolgreich geladen");
				for (DigitalEntertainment d : unique)
					setMessage(d.getName());
			} catch (LoadSaveException e) {
				setMessage(e.getMessage());
			}
			refreshFilm();
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

	public void setMessage(String s) {
		if (s != null)
			statusTextField.setText(s);
		else
			statusTextField.setText("");
	}
}
