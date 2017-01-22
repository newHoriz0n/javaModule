package uts;

import java.util.ArrayList;
import java.util.List;

import math.Vektor3D;

public class Verbindung
{

	private Koerper k1, k2;
	private double max_laenge;
	private double staerke;
	private double haltestaerke;
	private double reiss_staerke;
	private double akt_laenge;
	private List<Vektor3D> grund_rel_positionen; // Position, auf die k2 im Verhältnis zu k1 ohne Dehnung zurückgeht.
	private int akt_grund_position;

	public Verbindung(Koerper k1, Koerper k2, double max_dist, double staerke, double haltestaerke, double reiss_staerke)
	{
		this.k1 = k1;
		this.k2 = k2;
		this.max_laenge = max_dist;
		this.staerke = staerke;
		this.haltestaerke = haltestaerke;
		this.reiss_staerke = reiss_staerke;
		this.grund_rel_positionen = new ArrayList<Vektor3D>();
		this.akt_grund_position = 0;
	}

	public void addRelGrundPos(Vektor3D rel_grund_pos)
	{
		this.grund_rel_positionen.add(rel_grund_pos);
	}
	
	public void setAktGrundPos(int grundPosID) {
		this.akt_grund_position = grundPosID;
	}

	/**
	 * 
	 * @return false wenn verbindung reisst
	 */
	public boolean calcGegenkraefte()
	{
		calcRueckstellKraft();
		
		// Vektor3D dif = new Vektor3D(k1.getPosition(), k2.getPosition());
		// this.akt_laenge = dif.calcVektorLaenge();
		// System.out.println("a:" + akt_laenge);
		// if (akt_laenge > 0) {
		// double spann = calcSpannkraft();
		// System.out.println(spann);
		// double kraft1 = staerke * akt_laenge * k2.getMasse() + spann;
		// double kraft2 = -staerke * akt_laenge * k1.getMasse() - spann;
		// if (kraft1 + spann < reiss_staerke && kraft2 - spann > -reiss_staerke) {
		// k1.addAussenKraftEinwirkung(dif.normiere().scale(kraft1 + spann));
		// k2.addAussenKraftEinwirkung(dif.normiere().scale(kraft2 - spann));
		// return true;
		// } else {
		// return false;
		// }
		// }
		return true;
	}

	/**
	 * Berechnet die Kraft um k2 zurück zur Grundposition im Verhältnis zu k1 zu bringen
	 */
	private void calcRueckstellKraft()
	{

		if (akt_grund_position < grund_rel_positionen.size() && grund_rel_positionen.get(akt_grund_position) != null) {

			Vektor3D abs_sollpos = new Vektor3D(k1.getPosition().getX() + Math.cos(k1.getAusrichtung())
					* grund_rel_positionen.get(akt_grund_position).getX() - Math.sin(k1.getAusrichtung())
					* grund_rel_positionen.get(akt_grund_position).getY(), k1.getPosition().getY() + -Math.sin(k1.getAusrichtung())
					* grund_rel_positionen.get(akt_grund_position).getX() - Math.cos(k1.getAusrichtung())
					* grund_rel_positionen.get(akt_grund_position).getY(), 0);

			Vektor3D dif = new Vektor3D(abs_sollpos, k2.getPosition());

			k2.addAussenKraftEinwirkung(dif.scale(-1 * staerke));
			Vektor3D untergrundgeschw = new Vektor3D(k1.getUntergrundgeschwindigkeit()).add(k1.getGeschwindigkeit());
			k2.setUntergrundGeschwindigkeit(untergrundgeschw);
		}
	}

	public double calcSpannkraft()
	{
		return akt_laenge;
	}

	public double getAkt_laenge()
	{
		return akt_laenge;
	}

	public double getMax_dist()
	{
		return max_laenge;
	}

}
