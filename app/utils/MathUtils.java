package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

    public static double revertDouble(double a) {
        return a * -1;
    }

    public static double roundToNextTen(double roundedAmount) {
        roundedAmount = Math.floor(roundedAmount) + 1;

        while(roundedAmount % 10 != 0) {
            roundedAmount = roundedAmount + 1;
        }
        return roundedAmount;
    }

    public static double toScale(double diffAmount, int scale) {
        return BigDecimal.valueOf(diffAmount).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }
}
