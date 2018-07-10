package gui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.zip.DataFormatException;

import data.*;

public class NeueWatchlist extends Dialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField nametf;
	private Hauptfenster owner;

	public NeueWatchlist(Hauptfenster owner) {
		super(owner, "Neue Watchlist", true);
		this.owner = owner;
		setLayout(new GridLayout(2, 2));
		nametf = new TextField();
		Button speichern = new Button("Speichern");
		Button abbrechen = new Button("Abbrechen");
		add(new Label("Name der Watchlist: "));
		add(nametf);
		add(speichern);
		add(abbrechen);

		speichern.addActionListener(this);
		abbrechen.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Speichern")) {
			try {
				Watchlist w = new Watchlist(nametf.getText());
				WatchlistContainer.instance().linkWatchlist(w);
				owner.refreshWatchlist();
				owner.setMessage("Watchlist erfolgreich angelegt");
				dispose();
			} catch (IllegalInputException i) {
				owner.setMessage(i.getMessage());
			} catch (DataFormatException d) {
				owner.setMessage(d.getMessage());
			}
		} else if (e.getActionCommand().equals("Abbrechen"))
			dispose();
	}
}
