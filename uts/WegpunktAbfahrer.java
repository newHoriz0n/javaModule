package uts;

import java.util.ArrayList;
import java.util.List;

import math.Vektor3D;

public class WegpunktAbfahrer
{
	private List<Vektor3D> wegpunkte;
	private int aktWegPunkt;
	private double sollradius;
	private boolean loop;
	private boolean gestartet;
	private boolean pausiert;

	public WegpunktAbfahrer(double soll_radius, boolean loop)
	{
		this.wegpunkte = new ArrayList<Vektor3D>();
		this.sollradius = soll_radius;
		this.loop = loop;
		this.aktWegPunkt = 0;
	}

	public void starten()
	{
		this.gestartet = true;
		this.pausiert = false;
		this.aktWegPunkt = 0;
	}

	public void pausieren()
	{
		this.pausiert = true;
	}

	public void fortsetzen()
	{
		this.pausiert = false;
	}

	public void stoppen()
	{
		this.gestartet = false;
	}

	public void addWegpunkt(Vektor3D wegpunkt)
	{
		wegpunkte.add(wegpunkt);
	}

	public Vektor3D getAktWegPunkt()
	{
		return wegpunkte.get(aktWegPunkt);
	}
	
	/**
	 * Berechnet, welcher Wegpunkt angefahren werden soll.
	 * 
	 * @return true wenn Weg komplett abgefahren wurde und loop = false;
	 */
	public boolean calcAktWegpunkt(Vektor3D aktPosition)
	{
		if (gestartet && !pausiert) {
			Vektor3D dif = new Vektor3D(aktPosition, wegpunkte.get(aktWegPunkt));
			if (dif.calcVektorLaenge() < sollradius) {
				aktWegPunkt++;
			}
			if (aktWegPunkt >= wegpunkte.size()) {
				if (loop) {
					aktWegPunkt = 0;
				} else {
					stoppen();
					return true;
				}
			}
		}
		return false;
	}

}
