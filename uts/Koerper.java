package uts;

import java.util.ArrayList;
import java.util.List;

import math.Vektor3D;

public abstract class Koerper
{

	protected List<Koerper> koerperteile;

	protected Vektor3D position;
	protected Vektor3D geschwindigkeit;
	protected Vektor3D beschleunigung;

	protected Vektor3D aussenbeschleunigungen;
	protected Vektor3D untergrundgeschwindigkeit;

	protected double masse;
	protected double ausrichtung;

	protected boolean inDerLuft;

	protected double reibung;

	protected double haltekraft;

	public Koerper(Vektor3D pos, double masse)
	{
		this.koerperteile = new ArrayList<Koerper>();
		this.position = new Vektor3D(pos.getX(), pos.getY(), pos.getZ());
		this.masse = masse;
		this.geschwindigkeit = new Vektor3D();
		this.beschleunigung = new Vektor3D();
		this.aussenbeschleunigungen = new Vektor3D();
		this.untergrundgeschwindigkeit = new Vektor3D();
	}

	public Koerper(Vektor3D pos, List<Koerper> teile)
	{
		this.koerperteile = new ArrayList<Koerper>();
		this.koerperteile.addAll(teile);
		this.position = new Vektor3D(pos);
		this.masse = 0;
		this.geschwindigkeit = new Vektor3D();
		this.beschleunigung = new Vektor3D();
		this.aussenbeschleunigungen = new Vektor3D();
		this.untergrundgeschwindigkeit = new Vektor3D();
	}

	public Vektor3D getGeschwindigkeit()
	{
		return geschwindigkeit;
	}

	public Vektor3D getPosition()
	{
		return position;
	}

	public Vektor3D getBeschleunigung()
	{
		return beschleunigung;
	}

	public double getMasse()
	{
		return masse;
	}

	public double getAusrichtung()
	{
		return ausrichtung;
	}

	public void setKraftEinwirkung(Vektor3D kraft)
	{
		this.beschleunigung.set(kraft.scale(1 / masse));
	}

	public void addAussenKraftEinwirkung(Vektor3D kraft)
	{
		this.aussenbeschleunigungen.add(kraft.scale(1 / masse));
	}

	public void setUntergrundGeschwindigkeit(Vektor3D geschwindigkeit)
	{
		this.untergrundgeschwindigkeit.set(geschwindigkeit);
	}

	/**
	 * Wert zwischen 0 für keine Reibung und 1 für 100% reibung;
	 * 
	 * @param reibung
	 */
	public void setReibung(double reibung)
	{
		this.reibung = reibung;
	}

	/**
	 * Kraft um Gegen Aussenkräfte zu bestehen
	 * 
	 * @param kraft
	 */
	public void setHalteKraftr(double kraft)
	{
		this.haltekraft = kraft;
	}

	public void update()
	{

		aussenbeschleunigungen.scale(Math.max(0, aussenbeschleunigungen.calcVektorLaenge() - haltekraft));
		beschleunigung.add(aussenbeschleunigungen);

		this.geschwindigkeit.add(beschleunigung);
		this.geschwindigkeit.scale(1 - reibung);
		if (inDerLuft == false) {
			this.position.add(untergrundgeschwindigkeit);
		}
		this.position.add(geschwindigkeit);
		this.beschleunigung.scale(0);

		aussenbeschleunigungen.scale(0);

	}

	public double calcAbstand(Koerper k)
	{

		Vektor3D dif = new Vektor3D(position, k.getPosition());
		return dif.calcVektorLaenge();
	}

	public Vektor3D getUntergrundgeschwindigkeit()
	{
		return untergrundgeschwindigkeit;
	}

	/**
	 * Rekursiv alle Koerperteile holen
	 * 
	 * @param teile
	 */
	public void getKoerperTeile(List<Koerper> teile)
	{
		teile.add(this);
		for (Koerper ks : koerperteile) {
			ks.getKoerperTeile(teile);
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ausrichtung);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((beschleunigung == null) ? 0 : beschleunigung.hashCode());
		result = prime * result + ((geschwindigkeit == null) ? 0 : geschwindigkeit.hashCode());
		temp = Double.doubleToLongBits(masse);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		temp = Double.doubleToLongBits(reibung);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Koerper other = (Koerper) obj;
		if (Double.doubleToLongBits(ausrichtung) != Double.doubleToLongBits(other.ausrichtung))
			return false;
		if (beschleunigung == null) {
			if (other.beschleunigung != null)
				return false;
		} else if (!beschleunigung.equals(other.beschleunigung))
			return false;
		if (geschwindigkeit == null) {
			if (other.geschwindigkeit != null)
				return false;
		} else if (!geschwindigkeit.equals(other.geschwindigkeit))
			return false;
		if (Double.doubleToLongBits(masse) != Double.doubleToLongBits(other.masse))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (Double.doubleToLongBits(reibung) != Double.doubleToLongBits(other.reibung))
			return false;
		return true;
	}

}
