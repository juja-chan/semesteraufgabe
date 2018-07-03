package gui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import data.*;

import javax.swing.JCheckBox;

public class NeuerFilm extends Dialog implements ActionListener, ItemListener {
	private TextField regisseurtf, nametf, jahrtf;
	private Label regisseurl, namel, jahrl, bewertungl, gesehenl;
	private Scrollbar bewertung;
	private JCheckBox gesehen;
	private Button speichern;
	private boolean checkGesehen = false;
	private int id = 0;
	private NeuerFilm unique;
	
	
	private NeuerFilm(Hauptfenster owner){
		super(owner, true);
		setTitle("NeuerFilm");
		setSize(500,500);
		setLayout(new GridLayout(6,2, 4, 4));
		
		namel = new Label("Name:");
		add(namel);
		
		nametf = new TextField(15);
		add(nametf);
		
		regisseurl = new Label("Regisseur:");
		add(regisseurl);
		
		regisseurtf = new TextField(15);
		add(regisseurtf);
		
		jahrl = new Label("Jahr:");
		add(jahrl);
	
		jahrtf = new TextField(15);
		add(jahrtf);
		
		gesehenl = new Label("Gesehen:");
		add(gesehenl);
		
		gesehen = new JCheckBox();
		add(gesehen);
		gesehen.addItemListener(this);
		
		bewertungl = new Label("Bewertung:");
		add(bewertungl);
		
		bewertung = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 10);
		add(bewertung);
		
		speichern = new Button("Speichern");
		add(speichern);
		speichern.addActionListener(this);
		
		
		
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
			dispose();
		    }
		});
		
		pack();
		setVisible(true);	
	}
	
	
	
	public static void main(String [] args){
		new NeuerFilm(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(speichern)){
			id++;
			new Film(nametf.getText(), regisseurtf.getText(), getJahr(), getCheckGesehen(), 3, id);
		}
	}
	
	public int getJahr(){
		return Integer.parseInt(jahrtf.getText());
	}

	public boolean getCheckGesehen(){
		return checkGesehen;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			checkGesehen = true;
        } else {
        	
        }
		
	}
	
	public NeuerFilm instance(){
		if(unique == null){
			unique = new NeuerFilm(null);
		}
		return unique;
	}
}
