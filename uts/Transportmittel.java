package uts;

import java.util.ArrayList;
import java.util.List;

import math.Vektor3D;

public class Transportmittel extends PolygonKoerper
{

	private List<Koerper> drauf;
	private double antriebskraft;

	public Transportmittel(Vektor3D position, double masse, double antriebskraft, double ausrichtung, Polygon2D form)
	{

		super(position, masse, form);

		this.drauf = new ArrayList<Koerper>();
		this.antriebskraft = antriebskraft;
		this.ausrichtung = ausrichtung;
	}

	public void checkAufsteigen(Koerper k)
	{
		if (enthaelt(k.getPosition())) {
			aufsteigen(k);
		} else {
			absteigen(k);
		}
	}

	public void aufsteigen(Koerper k)
	{
		if (!drauf.contains(k)) {
			this.drauf.add(k);
		}
	}

	public void absteigen(Koerper k)
	{
		k.setUntergrundGeschwindigkeit(new Vektor3D());
		drauf.remove(k);
	}

	public void lenken(double richtung)
	{
		this.ausrichtung = richtung;
	}

	/**
	 * 
	 * @param staerke
	 *           : 0-1: 0 nix, 1 voll
	 */
	public void antreiben(double staerke)
	{
		Vektor3D antrieb = new Vektor3D(ausrichtung, staerke * antriebskraft);
		addAussenKraftEinwirkung(antrieb);
	}

	public void update()
	{
		super.update();
		setDraufStehBeschleunigungen();
	}

	private void setDraufStehBeschleunigungen()
	{
		for (Koerper k : drauf) {
			k.setUntergrundGeschwindigkeit(geschwindigkeit);
		}
	}

}
