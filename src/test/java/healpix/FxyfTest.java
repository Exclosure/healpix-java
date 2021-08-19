package healpix;

import junit.framework.TestCase;

/**
 * Tests for the Fxyf class
 *
 * @copyright 2015 Max-Planck-Society
 * @author Martin Reinecke
 */
public class FxyfTest extends TestCase {

  public void testFxyf() throws Exception {
    System.out.println("Testing Fxyf");
    for (int i = 0; i < 12; ++i)
      for (int j = 0; j <= 100; ++j)
        for (int k = 0; k <= 100; ++k) {
          double fx = (0.01 * j) * (1 - 1e-14) + .5e-14, fy = (0.01 * k) * (1 - 1e-14) + .5e-14;
          Fxyf res = new Fxyf(new Fxyf(fx, fy, i).toVec3());
          assertEquals(res.face, i);
          assertEquals(res.fx, fx, 5e-15);
          assertEquals(res.fy, fy, 5e-15);
        }
  }
}
