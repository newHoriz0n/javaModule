package math;

public class Vektor3D
{
	private double x;
	private double y;
	private double z;

	public Vektor3D()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vektor3D(Vektor3D v2, Vektor3D bezug2)
	{
		this.x = bezug2.getX() - v2.getX();
		this.y = bezug2.getY() - v2.getY();
		this.z = bezug2.getZ() - v2.getZ();
	}

	/**
	 * Erzeugt ein Vektor in X-Y Ebene mit z = 0;
	 * 
	 * @param richtung
	 *           : Rad, Oben = 0; Gegen Uhrzeigersinn steigend
	 * @param laenge
	 *           :
	 */
	public Vektor3D(double richtung, double laenge)
	{
		this.x = -Math.sin(richtung) * laenge;
		this.y = -Math.cos(richtung) * laenge;
		this.z = 0;
	}

	public Vektor3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vektor3D(Vektor3D v3d)
	{
		this.x = v3d.getX();
		this.y = v3d.getY();
		this.z = v3d.getZ();
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	public int getIntX()
	{
		return (int) x;
	}

	public int getIntY()
	{
		return (int) y;
	}

	public int getIntZ()
	{
		return (int) z;
	}

	public Vektor3D set(Vektor3D v3d)
	{
		this.x = v3d.getX();
		this.y = v3d.getY();
		this.z = v3d.getZ();

		return this;

	}

	public Vektor3D add(Vektor3D v3d)
	{
		this.x += v3d.getX();
		this.y += v3d.getY();
		this.z += v3d.getZ();

		return this;
	}

	public Vektor3D scale(double d)
	{
		this.x *= d;
		this.y *= d;
		this.z *= d;

		return this;
	}

	public Vektor3D normiere()
	{
		double l = calcVektorLaenge();
		if (l == 0) {
			l = 1;
		}
		this.x = x / l;
		this.y = y / l;
		this.z = z / l;

		return this;
	}

	public double calcVektorLaenge()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Rad, 0° oben, Gegen Uhrzeigersinn
	 * 
	 * @return
	 */
	public double calcVektorDir()
	{
		return Math.atan2(x, y);
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public void setZ(double z)
	{
		this.z = z;
	}

	public String toString()
	{
		return "" + x + "," + y + "," + z;
	}

	public void set(double x2, double y2, int z2)
	{
		this.x = x2;
		this.y = y2;
		this.z = z2;
	}

	public Vektor3D substract(Vektor3D v3d)
	{
		this.x -= v3d.getX();
		this.y -= v3d.getY();
		this.z -= v3d.getZ();

		return this;
	}

	public Vektor3D add(int x2, int y2, int z2)
	{
		this.x += x2;
		this.y += y2;
		this.z += z2;

		return this;
	}

	/**
	 * 
	 * @param v3d
	 * @param cut_off
	 *           0: nur neu, 1: nur alt
	 * @return
	 */
	public Vektor3D setVektorGefiltert(Vektor3D set, double cut_off)
	{
		this.x = set.getX() * (1 - cut_off) + x * cut_off;
		this.y = set.getY() * (1 - cut_off) + y * cut_off;
		this.z = set.getZ() * (1 - cut_off) + z * cut_off;

		return this;

	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
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
		Vektor3D other = (Vektor3D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

}
