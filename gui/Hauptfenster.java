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
	private Button hFilm;
	private Button lFilm;
	private Button hList;
	private Button lList;
	private List listFilm;
	private List listWatch;
	private Label labelList;
	private Label labelFilm;
	private WatchlistContainer wcon;
	private FilmContainer fcon;
	private TextField statusTextField;

	public Hauptfenster() {

		super("Filme und Watchlisten");

		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem loadMI = new MenuItem("Datenbank importieren");
		MenuItem saveFilm = new MenuItem("Filme speichern");
		MenuItem saveWatchlist = new MenuItem("Watchlisten speichern");
		MenuItem saveMI = new MenuItem("Alles speichern");

		wcon = WatchlistContainer.instance();
		fcon = FilmContainer.instance();
		statusTextField = new TextField();
		hFilm = new Button("Hinzufügen");
		lFilm = new Button("Löschen");
		hList = new Button("Hinzufügen");
		lList = new Button("Löschen");
		listFilm = new List(5, false);
		listWatch = new List(5, false);
		labelList = new Label("Watchlisten");
		labelFilm = new Label("Unsortierte Filme");

		Panel links = new Panel();
		Panel buttonLinks = new Panel();
		Panel buttonRechts = new Panel();
		Panel rechts = new Panel();
		Panel oben = new Panel();

		links.setLayout(new BorderLayout());
		rechts.setLayout(new BorderLayout());
		oben.setLayout(new GridLayout(1, 2, 5, 5));

		labelFilm.setAlignment(Label.CENTER);
		labelList.setAlignment(Label.CENTER);

		listFilm.addItemListener(this);
		listWatch.addItemListener(this);
		hList.addActionListener(this);
		hFilm.addActionListener(this);
		lList.addActionListener(this);
		lFilm.addActionListener(this);
		statusTextField.setEditable(false);

		loadMI.addActionListener(this);
		saveFilm.addActionListener(this);
		saveWatchlist.addActionListener(this);
		saveMI.addActionListener(this);

		links.add(labelFilm, BorderLayout.NORTH);
		links.add(listFilm, BorderLayout.CENTER);
		buttonLinks.add(hFilm);
		buttonLinks.add(lFilm);
		links.add(buttonLinks, BorderLayout.SOUTH);
		rechts.add(labelList, BorderLayout.NORTH);
		rechts.add(listWatch, BorderLayout.CENTER);
		buttonRechts.add(hList);
		buttonRechts.add(lList);
		rechts.add(buttonRechts, BorderLayout.SOUTH);

		menu.add(loadMI);
		menu.add(saveFilm);
		menu.add(saveWatchlist);
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

	}

	public void refreshWatchlist() {
		listWatch.removeAll();
		for (Watchlist w : wcon)
			listWatch.add(w.getName());
	}

	public void refreshFilm() {
		listFilm.removeAll();
		for (Film d : fcon)
			listFilm.add(d.getName());
	}

	public void setMessage(String s) {
		if (s != null)
			statusTextField.setText(s);
		else
			statusTextField.setText("");
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(listWatch)) {
			new EditWatchlist(this, wcon.getWatchlist(listWatch.getSelectedIndex()));
		} else {
			new EditFilm(this, fcon.getFilm(listFilm.getSelectedIndex()));
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Alles speichern"))
			onSave(true, true);
		else if (e.getActionCommand().equals("Filme speichern"))
			onSave(true, false);
		else if (e.getActionCommand().equals("Watchlisten speichern"))
			onSave(false, true);
		else if (e.getActionCommand().equals("Datenbank importieren"))
			onLoad();
		else if (e.getSource().equals(hFilm))
			new NeuerFilm(this);
		else if (e.getSource().equals(hList))
			new NeueWatchlist(this);
		else if (e.getSource().equals(lFilm)) {
			try {
				if (listFilm.getSelectedIndex() != -1) {
					fcon.unlinkFilm(fcon.getFilm(listFilm.getSelectedIndex()));
					refreshFilm();
				} else
					setMessage("Kein Film ausgewählt");
			} catch (IllegalInputException i) {
				setMessage(i.getMessage());
			}
		} else if (e.getSource().equals(lList)) {
			try {
				if (listWatch.getSelectedIndex() != -1) {
					wcon.unlinkWatchlist(wcon.getWatchlist(listWatch.getSelectedIndex()));
					refreshWatchlist();
				} else
					setMessage("Keine Watchlist ausgewählt");
			} catch (IllegalInputException i) {
				setMessage(i.getMessage());
			}
		}
	}

	private void onSave(boolean speicherFilm, boolean speicherWatchlist) {
		if (speicherFilm) {
			FileDialog fd = new FileDialog(this, "Filme speichern", FileDialog.SAVE);
			fd.setVisible(true);
			if (fd.getFile() != null) {
				String filename = fd.getDirectory() + fd.getFile();
				try {
					fcon.save(filename);
					setMessage("Filme erfolgreich gespeichert");
				} catch (LoadSaveException e) {
					setMessage("Speicherfehler: " + e.getMessage());
				}
			} else
				setMessage("Keine Datei zum Speichern gewählt!" + fd.getFile());
		}
		if (speicherWatchlist) {
			FileDialog fd = new FileDialog(this, "Watchlisten speichern", FileDialog.SAVE);
			fd.setVisible(true);
			if (fd.getFile() != null) {
				String filename = fd.getDirectory() + fd.getFile();
				try {
					wcon.save(filename);
					setMessage("Watchlisten erfolgreich gespeichert");
				} catch (LoadSaveException e) {
					setMessage("Speicherfehler: " + e.getMessage());
				}
			} else
				setMessage("Keine Datei zum Speichern gewählt!");
		}
	}

	private void onLoad() {
		FileDialog fd = new FileDialog(this, "Load Parcels...", FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() != null) {
			String filename = fd.getDirectory() + fd.getFile();
			try {
				wcon.load(filename);
			} catch (LoadSaveException e) {
				setMessage(e.getMessage());
			}
			refreshFilm();
			refreshWatchlist();
		} else
			setMessage("Keine Datei zum Laden gewählt!");
	}

}
