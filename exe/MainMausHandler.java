package exe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainMausHandler implements MouseListener, MouseMotionListener
{

	protected int panelBreite;
	protected int panelHoehe;

	protected double filterung;
	
	protected double mausX_soll;
	protected double mausY_soll;
	protected double mausX;
	protected double mausY;

	protected double d_x_mitte; // Abstand der Maus zur Mitte X
	protected double d_y_mitte; // Abstand der Maus zur Mitte Y
	protected double d_mitte; // Abstand der Maus zur Mitte
	protected double dir_mitte; // Richtung der Maus zur Mitte

	@Override
	public void mouseDragged(MouseEvent e)
	{		
		this.mausX_soll = e.getX();
		this.mausY_soll = e.getY();
		updateMausBewegung();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		this.mausX_soll = e.getX();
		this.mausY_soll = e.getY();
		updateMausBewegung();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * 0: kein Filter, 1: 100% filter
	 * @param filter_cutoff
	 */
	public void setBewegungsFilterung(double filter_cutoff) {
		this.filterung = filter_cutoff;
	}
	
	public void setPanelGroesse(int width, int height)
	{
		this.panelBreite = width;
		this.panelHoehe = height;
	}

	public double getMausX()
	{
		return mausX;
	}

	public double getMausY()
	{
		return mausY;
	}
	
	public void updateMausBewegung() {
		this.mausX = ((1-filterung) * mausX_soll) + (filterung * mausX);
		this.mausY = ((1-filterung) * mausY_soll) + (filterung * mausY);
		
		this.d_x_mitte = panelBreite / 2 - mausX;
		this.d_y_mitte = panelHoehe / 2 - mausY;
		this.d_mitte = Math.sqrt(d_x_mitte * d_x_mitte + d_y_mitte * d_y_mitte);
		this.dir_mitte = Math.atan2(d_x_mitte, d_y_mitte);
	}

}
