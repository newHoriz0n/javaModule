package uts;

import java.util.ArrayList;
import java.util.List;

import math.Kollision;

public class Welt
{

	private double luftwiderstand;
	private double gravitation;
	
	private List<Koerper> weltobjekte;
	private List<Transportmittel> transportmittel;
	private List<Koerper> transportierbar;
	private List<Collidable> collObjekte;
	private List<Gebaeude> gebaeude;
	
	
	public Welt() {
		this.weltobjekte = new ArrayList<Koerper>();
		this.collObjekte = new ArrayList<Collidable>();
		this.transportierbar = new ArrayList<Koerper>();
		this.transportmittel = new ArrayList<Transportmittel>();
		this.gebaeude = new ArrayList<Gebaeude>();
	}
	
	public void platziere(Koerper k) {
		this.weltobjekte.add(k);
	}
	
	public void baue(Gebaeude g) {
		this.gebaeude.add(g);
		this.collObjekte.add(g);
	}
	
	public void addKollisionsKoerper(Collidable k) {
		collObjekte.add(k);
	}
	
	public void addTransportmittel(Transportmittel t) {
		this.transportmittel.add(t);
	}
	
	public void addTransportierbar(Koerper k) {
		this.transportierbar.add(k);
	}
	
	public void update() {
		for (Koerper k : weltobjekte) {
			k.update();
		}
		
		calcCollision();
		calcAufsteigen();
		
	}

	private void calcAufsteigen()
	{
		for (Transportmittel tm : transportmittel) {
			for (Koerper k : transportierbar) {
				tm.checkAufsteigen(k);
			}
		}
	}


	private void calcCollision()
	{

		for (int i = 0; i < collObjekte.size(); i++) { // letzte Koerper wird nicht betrachtet
			for (int i2 = i + 1; i2 < collObjekte.size(); i2++) { // nur verbleibende Koerper beruecksichtigen
				if (Kollision.calcCollision(collObjekte.get(i), collObjekte.get(i2))) {
					// Hier zusatzsachen bei Kollision programmieren
				}
			}
		}

	}
	
}
