package healpix;

import junit.framework.TestCase;

/**
 * Tests for the HealpixMap class
 *
 * @copyright 2014 Max-Planck-Society
 * @author Martin Reinecke
 */
public class HealpixMapTest extends TestCase {

  public void test_swapScheme() throws Exception {
    System.out.println("Testing swapScheme()");

    for (int order = 0; order <= 8; ++order) {
      HealpixMapFloat map = new HealpixMapFloat(1L << order, Scheme.RING);
      for (int i = 0; i < map.getNpix(); ++i) map.setPixel(i, i);
      map.swapScheme();
      for (int i = 0; i < map.getNpix(); ++i)
        assertEquals("inconsistency", map.nest2ring(i), (int) map.getPixel(i));
      map.swapScheme();
      for (int i = 0; i < map.getNpix(); ++i)
        assertEquals("inconsistency", i, (int) map.getPixel(i));
    }
  }
}
