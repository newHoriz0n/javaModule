package uts;

import math.Vektor3D;

public class Lebewesen extends Collidable
{

	private double maxLebenskraft;
	private double aktlebenskraft;
	private double gesundheit;
	private double widerstandskraft;

	private boolean bewusstlos;

	private boolean tot;

	public Lebewesen(Vektor3D pos, double masse, double radius, int team, double maxLebenskraft, double widerstandskraft)
	{
		super(pos, masse, radius, team);
		this.maxLebenskraft = maxLebenskraft;
		this.aktlebenskraft = maxLebenskraft;
		this.gesundheit = 1.0;
		this.widerstandskraft = widerstandskraft;
	}

	public void update()
	{
		super.update();
		calcErholung();
	}

	private void calcErholung()
	{
		double heilfaktor  = 0.01;
		double dif = -(0.5 - gesundheit);
		aktlebenskraft += dif * heilfaktor;
		aktlebenskraft = Math.min(aktlebenskraft, maxLebenskraft);

		gesundheit += -(0.5 - getLebenskraftAnteil()) * heilfaktor; 
		
		if (aktlebenskraft < 0) {
			tot = true;
		}		
	}

	/**
	 * schwere: 0-1, 0: kleiner Schnitt, 1: tötlich
	 * 
	 * @param schwere
	 */
	public void verletzen(double schwere)
	{
		this.aktlebenskraft *= (1 - schwere*(1-widerstandskraft));
		this.gesundheit *= (1 - schwere*(1-widerstandskraft));
	}

	public void setBewusstsein(boolean b)
	{
		this.bewusstlos = b;
	}

	public boolean isBewusstlos()
	{
		return bewusstlos;
	}

	public boolean isTot()
	{
		return tot;
	}

	public double getGesundheit()
	{
		return gesundheit;
	}
	
	public double getWiderstandskraft()
	{
		return widerstandskraft;
	}

	public double getLebenskraftAnteil()
	{
		return aktlebenskraft / maxLebenskraft;
	}

	public double getMaxLebenskraft()
	{
		return maxLebenskraft;
	}

	public double getAktlebenskraft()
	{
		return aktlebenskraft;
	}

}
