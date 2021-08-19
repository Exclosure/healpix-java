package healpix;

/**
 * Healpix-internal class for specifying locations on the sphere.
 *
 * @copyright 2011 Max-Planck-Society
 * @author Martin Reinecke
 */
final class Hploc {
  public double z, phi, sth;
  public boolean have_sth;

  /** Default constructor. */
  public Hploc() {}

  public Hploc(Vec3 v) {
    double xl = 1. / v.length();
    z = v.z * xl;
    phi = FastMath.atan2(v.y, v.x);
    if (Math.abs(z) > 0.99) {
      sth = Math.sqrt(v.x * v.x + v.y * v.y) * xl;
      have_sth = true;
    }
  }

  public Hploc(Zphi zphi) {
    z = zphi.z;
    phi = zphi.phi;
    have_sth = false;
  }

  public Hploc(Pointing ptg) throws Exception {
    HealpixUtils.check((ptg.theta >= 0.) && (ptg.theta <= Math.PI), "invalid theta value");
    z = FastMath.cos(ptg.theta);
    phi = ptg.phi;
    if (Math.abs(z) > 0.99) {
      sth = FastMath.sin(ptg.theta);
      have_sth = true;
    }
  }

  public Zphi toZphi() {
    return new Zphi(z, phi);
  }

  public Pointing toPointing() {
    double st = have_sth ? sth : Math.sqrt((1.0 - z) * (1.0 + z));
    return new Pointing(FastMath.atan2(st, z), phi);
  }

  public Vec3 toVec3() {
    double st = have_sth ? sth : Math.sqrt((1.0 - z) * (1.0 + z));
    return new Vec3(st * FastMath.cos(phi), st * FastMath.sin(phi), z);
  }
}
