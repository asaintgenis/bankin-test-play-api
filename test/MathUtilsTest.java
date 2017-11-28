import org.junit.Test;
import utils.MathUtils;

import static org.junit.Assert.assertEquals;

public class MathUtilsTest {

    @Test
    public void revertDoubleTest() {
        double x = MathUtils.revertDouble(10);
        assertEquals(-10,x,0.0);

        x= MathUtils.revertDouble(-10);
        assertEquals(10,x,0.0);

        x= MathUtils.revertDouble(0);
        assertEquals(0,x,0.0);
    }
    @Test
    public void roundToNextTenTest() {
        double x = MathUtils.roundToNextTen(0);
        assertEquals(10,x,0.0);

        x = MathUtils.roundToNextTen(4.45);
        assertEquals(10,x,0.0);

        x = MathUtils.roundToNextTen(10);
        assertEquals(20,x,0.0);
    }

    @Test
    public void toScaleTest() {
        double x = MathUtils.toScale(3.4,2);
        assertEquals("3.4",String.valueOf(x));

        x = MathUtils.toScale(3.4567,2);
        assertEquals("3.46",String.valueOf(x));

        x = MathUtils.toScale(-3.434,2);
        assertEquals("-3.43",String.valueOf(x));
    }
}
