package healpix;

/**
 * Class for storing a position on the unit sphere as a (z,phi)-tuple.
 *
 * @copyright (C) 2011 Max-Planck-Society
 * @author Martin Reinecke
 */
public final class Zphi {
  /** Cosine of the colatitude, or z component of unit vector; Range [-1;1]. */
  public double z;

  /** Longitude in radians; Range [0; 2Pi]. */
  public double phi;

  /** Default constructor */
  public Zphi() {}

  /** Creation from individual components */
  public Zphi(double z_, double phi_) {
    z = z_;
    phi = phi_;
  }

  /** Conversion from {@link Vec3} */
  public Zphi(Vec3 v) {
    z = v.z / v.length();
    phi = FastMath.atan2(v.y, v.x);
  }

  /** Conversion from {@link Pointing} */
  public Zphi(Pointing ptg) {
    z = FastMath.cos(ptg.theta);
    phi = ptg.phi;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("zphi(");
    s.append(z);
    s.append(",");
    s.append(phi);
    s.append(")");
    return s.toString();
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Zphi zphi = (Zphi) o;
    if (Double.compare(zphi.phi, phi) != 0) return false;
    if (Double.compare(zphi.z, z) != 0) return false;
    return true;
  }

  public int hashCode() {
    long temp = z != +0.0d ? Double.doubleToLongBits(z) : 0L;
    int result = (int) (temp ^ (temp >>> 32));
    temp = phi != +0.0d ? Double.doubleToLongBits(phi) : 0L;
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
