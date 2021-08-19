package healpix;

import java.io.File;
import java.util.UUID;
import junit.framework.TestCase;

/**
 * Tests for the FitsUtil class
 *
 * @copyright 2014 Max-Planck-Society
 * @author Martin Reinecke
 */
public class FitsUtilTest extends TestCase {
  public void testHPmapf() throws Exception {
    String name = UUID.randomUUID().toString() + ".fits";
    HealpixMapFloat hpmf = new HealpixMapFloat(128, Scheme.NESTED);
    for (long i = 0; i < hpmf.getNpix(); ++i) hpmf.setPixel(i, (float) i);
    FitsUtil.writeFloatMap(hpmf, name);
    hpmf.setNsideAndScheme(64, Scheme.RING);
    hpmf = FitsUtil.getFloatMap(name, 2, 1);
    new File(name).delete();
    assertEquals("Scheme problem", Scheme.NESTED, hpmf.getScheme());
    assertEquals("Nside problem", 128, hpmf.getNside());
    for (long i = 0; i < hpmf.getNpix(); ++i)
      assertEquals("Value problem", (float) i, hpmf.getPixel(i));
  }

  public void testHPmapd() throws Exception {
    String name = UUID.randomUUID().toString() + ".fits";
    HealpixMapDouble hpmd = new HealpixMapDouble(128, Scheme.NESTED);
    for (long i = 0; i < hpmd.getNpix(); ++i) hpmd.setPixel(i, (double) i);
    FitsUtil.writeDoubleMap(hpmd, name);
    hpmd.setNsideAndScheme(64, Scheme.RING);
    hpmd = FitsUtil.getDoubleMap(name, 2, 1);
    new File(name).delete();
    assertEquals("Scheme problem", Scheme.NESTED, hpmd.getScheme());
    assertEquals("Nside problem", 128, hpmd.getNside());
    for (long i = 0; i < hpmd.getNpix(); ++i)
      assertEquals("Value problem", (double) i, hpmd.getPixel(i));
  }
}
