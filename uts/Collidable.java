package uts;

import java.util.ArrayList;
import java.util.List;

import math.Vektor3D;

public class Collidable extends Koerper
{

	
	private double radius;
	protected int team;
	protected List<Collidable> teile;
	protected boolean fixiert;
	
	
	public Collidable(Vektor3D pos, double masse, double radius, int team)
	{
		super(pos, masse);
		this.radius = radius;
		this.team = team;
		this.teile = new ArrayList<Collidable>();
	}
	
	public Collidable(Vektor3D pos, int team, List<Collidable> teile)
	{
		super(pos, 0);
		this.team = team;
		this.masse = 0;
		this.teile = new ArrayList<Collidable>();
		this.teile.addAll(teile);
	}
	
	public void setFixiert(boolean fisiert) {
		this.fixiert = fisiert;
	}

	public double getRadius()
	{
		return radius;
	}

	public int getTeam()
	{
		return team;
	}
	
	public boolean isFixiert() {
		return fixiert;
	}
	
	/**
	 * Rekursiv alle Koerperteile holen
	 * 
	 * @param teile
	 */
	public void getCollidableTeile(List<Collidable> cTeile)
	{
		cTeile.add(this);
		for (Collidable cs : teile) {
			cs.getCollidableTeile(cTeile);
		}	
	}

	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collidable other = (Collidable) obj;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

}
