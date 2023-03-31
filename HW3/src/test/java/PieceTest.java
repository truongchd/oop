import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
    // You can create data to be used in the your test cases like this.
    // For each run of a test method, a new PieceTest object is created
    // and setUp() is called automatically by JUnit.
    // For example, the code below sets up some pyramid and s pieces
    // in instance variables that can be used in tests.
    private Piece pyr1, pyr2, pyr3, pyr4;
    private Piece l, lRotated;
    private Piece stick, stickRotated;
    private Piece s, sRotated;
    private Piece[] pieces;
    @Before
    public void setUp() {
        pyr1 = new Piece(Piece.PYRAMID_STR);
        pyr2 = pyr1.computeNextRotation();
        pyr3 = pyr2.computeNextRotation();
        pyr4 = pyr3.computeNextRotation();

        l = new Piece(Piece.L2_STR);
        lRotated = l.computeNextRotation();
        
        stick = new Piece(Piece.STICK_STR);
        stickRotated = stick.computeNextRotation();

        s = new Piece(Piece.S1_STR);
        sRotated = s.computeNextRotation();

        pieces = Piece.getPieces();
    }

    // Here are some sample tests to get you started
    @Test
    public void testSize() {
        // Check size of pyr piece
        assertEquals(3, pyr1.getWidth());
        assertEquals(2, pyr1.getHeight());

        // Now try after rotation
        // Effectively we're testing size and rotation code here
        assertEquals(2, pyr2.getWidth());
        assertEquals(3, pyr2.getHeight());

        assertEquals(3, pyr3.getWidth());
        assertEquals(2, pyr3.getHeight());

        assertEquals(2, pyr4.getWidth());
        assertEquals(3, pyr4.getHeight());

        // Now try with some other piece, made a different way
        Piece l = new Piece(Piece.STICK_STR);
        assertEquals(1, l.getWidth());
        assertEquals(4, l.getHeight());
    }


    // Test the skirt returned by a few pieces
    @Test
    public void testSkirt() {
        // Note must use assertTrue(Arrays.equals(... as plain .equals does not work
        // right for arrays.
        assertTrue(Arrays.equals(new int[]{0, 0, 0}, pyr1.getSkirt()));
        assertTrue(Arrays.equals(new int[]{1, 0}, pyr2.getSkirt()));
        assertTrue(Arrays.equals(new int[]{1, 0, 1}, pyr3.getSkirt()));
        assertTrue(Arrays.equals(new int[]{0, 1}, pyr4.getSkirt()));

        assertTrue(Arrays.equals(new int[]{0, 0, 1}, s.getSkirt()));
        assertTrue(Arrays.equals(new int[]{1, 0}, sRotated.getSkirt()));
    }
    
    // Test equals function
    @Test
    public void testEquals() {
        // if 'equals' works then after 4 rotation it should be equal
        Piece tempPyr = pyr4.computeNextRotation();
        assertTrue(pyr1.equals(tempPyr));

        // how about compare with precomputed pieces, will it still be equal?
        assertTrue(pyr1.equals(pieces[6]));
    }

    @Test
    public void testFastRotation() {
        // fastRotation test
        assertTrue(pyr2.equals(pieces[6].fastRotation()));

        // now test fastRotation of that precomputed array
        Piece temp = pieces[6].fastRotation().fastRotation().fastRotation().fastRotation();
        assertTrue(pyr1.equals(temp));

        temp = pieces[2];
        assertTrue(l.equals(temp));
        assertTrue(lRotated.equals(temp.fastRotation()));

        temp = pieces[0];
        assertTrue(stick.equals(temp));
        assertTrue(stickRotated.equals(temp.fastRotation()));

        assertTrue(pyr3.equals(pieces[6].fastRotation().fastRotation()));
    }
}
