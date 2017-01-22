package exe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class MainTastenHandler implements KeyListener
{

	protected boolean[] tasten;

	public MainTastenHandler()
	{
		this.tasten = new boolean[255];
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() < 256) {
			tasten[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() < 256) {
			tasten[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	public boolean isGedruecktPfeilLinks()
	{
		return tasten[37];
	}

	public boolean isGedruecktPfeilHoch()
	{
		return tasten[38];
	}

	public boolean isGedruecktPfeilRechts()
	{
		return tasten[39];
	}

	public boolean isGedruecktPfeilRunter()
	{
		return tasten[40];
	}

	public boolean isGedruecktW()
	{
		return tasten[87];
	}

	public boolean isGedruecktA()
	{
		return tasten[65];
	}

	public boolean isGedruecktS()
	{
		return tasten[83];
	}

	public boolean isGedruecktD()
	{
		return tasten[68];
	}

	public abstract void calcTastenFunktionen();

}
