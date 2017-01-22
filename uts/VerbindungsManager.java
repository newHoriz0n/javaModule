package uts;

import java.util.ArrayList;
import java.util.List;

public class VerbindungsManager
{

	private List<Verbindung> verbindungen;

	public VerbindungsManager()
	{
		this.verbindungen = new ArrayList<Verbindung>();
	}

	public void addVerbindung(Verbindung v)
	{
		this.verbindungen.add(v);
	}

	public void update() {
		calcVerbindungskraefte();
	}
	
	public void calcVerbindungskraefte()
	{
		for (int i = verbindungen.size() - 1; i >= 0; i--) {
			if(!verbindungen.get(i).calcGegenkraefte()) {
				verbindungen.remove(i);
			}
		}

	}

}
