package healpix;

import java.util.Random;
import junit.framework.TestCase;

/**
 * Tests for the linear interpolative coding compression
 *
 * @copyright 2015 Max-Planck-Society
 * @author Martin Reinecke
 */
public class CompressorTest extends TestCase {

  public void testCompressDecompress() throws Exception {
    int num = 100000;
    Random randomGenerator = new Random();
    long[] input = new long[num];
    input[0] = 0;
    for (int i = 1; i < num; ++i) input[i] = input[i - 1] + 1 + randomGenerator.nextInt(100);
    byte[] compressed = Compressor.interpol_encode(input, 0, input.length);
    long[] output = Compressor.interpol_decode(compressed);
    assertEquals("inconsistency", input.length, output.length);
    for (int i = 0; i < output.length; ++i) assertEquals("inconsistency", input[i], output[i]);
  }
}
