package healpix;

import java.util.Objects;

/**
 * An angular position on the unit sphere.
 *
 * @copyright 2011 Max-Planck-Society
 * @author Martin Reinecke
 */
public class Pointing {
  /** Colatitude in radians (0 is North Pole; Pi is South Pole) */
  public double theta;

  /** Longitude in radians */
  public double phi;

  /** Default constructor */
  public Pointing() {}

  public Pointing(Pointing ptg) {
    this.theta = ptg.theta;
    this.phi = ptg.phi;
  }

  /**
   * Simple constructor initializing both values.
   *
   * @param theta in radians [0,Pi]
   * @param phi in radians [0,2*Pi]
   */
  public Pointing(double theta, double phi) {
    this.theta = theta;
    this.phi = phi;
  }

  /** Conversion from {@link Vec3} */
  public Pointing(Vec3 vec) {
    theta = FastMath.atan2(Math.sqrt(vec.x * vec.x + vec.y * vec.y), vec.z);
    phi = FastMath.atan2(vec.y, vec.x);
    if (phi < 0.) phi += 2 * Math.PI;
    if (phi >= 2 * Math.PI) phi -= 2 * Math.PI;
  }

  /** Conversion from {@link Zphi} */
  public Pointing(Zphi zphi) {
    double xy = Math.sqrt((1. - zphi.z) * (1. + zphi.z));
    theta = FastMath.atan2(xy, zphi.z);
    phi = zphi.phi;
  }
  // for some reason, the alternative below is much slower...
  // { theta=FastMath.acos(zphi.z); phi=zphi.phi; }

  /** Normalize theta range */
  public void normalizeTheta() {
    theta = HealpixUtils.fmodulo(theta, 2 * Math.PI);
    if (theta > Math.PI) {
      phi += Math.PI;
      theta = 2 * Math.PI - theta;
    }
  }

  /** Normalize theta and phi ranges */
  public void normalize() {
    normalizeTheta();
    phi = HealpixUtils.fmodulo(phi, 2 * Math.PI);
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("ptg(");
    s.append(theta);
    s.append(",");
    s.append(phi);
    s.append(")");
    return s.toString();
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if ((o == null) || (getClass() != o.getClass())) return false;
    Pointing pointing = (Pointing) o;
    if (Double.compare(pointing.phi, phi) != 0) return false;
    if (Double.compare(pointing.theta, theta) != 0) return false;
    return true;
  }

  public int hashCode() {
    return Objects.hash(theta, phi);
  }
}
