package uts;

import math.Vektor3D;

public class PolygonKoerper extends Koerper
{

	protected Polygon2D relativeForm;
	protected Vektor3D schwerpunkt;

	protected Polygon2D absoluteForm;

	public PolygonKoerper(Vektor3D pos, double masse, Polygon2D relativeForm)
	{
		super(pos, masse);
		this.relativeForm = relativeForm;
		this.absoluteForm = new Polygon2D();
		this.absoluteForm.npoints = 4;
	}

	@Override
	public void update()
	{
		super.update();
		calcAbsoluteForm();
	}

	private void calcAbsoluteForm()
	{
		for (int i = 0; i < absoluteForm.xpoints.length; i++) {
			absoluteForm.xpoints[i] = (float) (position.getX() + Math.cos(ausrichtung) * relativeForm.xpoints[i] + Math.sin(ausrichtung)
					* relativeForm.ypoints[i]);
			absoluteForm.ypoints[i] = (float) (position.getY() + Math.cos(ausrichtung) * relativeForm.ypoints[i] - Math.sin(ausrichtung)
					* relativeForm.xpoints[i]);
		}
		absoluteForm.calculatePath();

	}

	/**
	 * berechnet, ob 2D Vektor in X-Y Ebene innerhalb der Projektiondes Koerpers in X-Y Ebene liegt
	 * 
	 * @param p
	 *           (2D Vektor in X-y ebene)
	 * @return
	 */
	public boolean enthaelt(Vektor3D p)
	{
		return absoluteForm.contains(p.getX(), p.getY());
	}

}
