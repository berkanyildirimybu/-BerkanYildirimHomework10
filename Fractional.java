public class Fractional {
    public final long numerator;
    public final long denominator;
    public static final String NotANumber = "Not a Number";
    public static final String PositiveInfinity = "+Infinity";
    public static final String NegativeInfinity = "-Infinity";

    Fractional(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    Fractional simplify() {

        if (isNaN())
            throw new IllegalArgumentException("NaN number! Cannot simplify.");

        if (isInfinity())
            throw new IllegalArgumentException("infinity! Cannot simplify...");

        if (numerator == 0)
            return new Fractional(this.numerator, this.denominator);

        long number1 = (this.numerator > 0) ? this.numerator : -this.numerator;
        long number2 = (this.denominator > 0) ? this.denominator : -this.denominator;
        while (number1 != number2) {
            if (number1 > number2)
                number1 -= number2;
            else
                number2 -= number1;
        }

        return new Fractional(this.numerator / number1, this.denominator / number1);
    }

    // Not a Number (NaN)
    boolean isNaN() {
        return this.denominator == 0 && this.numerator == 0;
    }

    boolean isInfinity() {
        return this.denominator == 0 && this.numerator != 0;
    }

    static int sign(long numerator, long denominator) {

        if (numerator == 0 && denominator == 0) {
            throw new ArithmeticException(NotANumber);
        } else if (numerator == 0)
            return 0;

        if (denominator >= 0)
            if (numerator > 0)
                return +1;
            else
                return -1;
        else if (numerator > 0)
            return -1;
        else return 1;
    }

    int sign() {
        return sign(numerator, denominator);
    }

    double getValue() {
        return (double) numerator / denominator;
    }

    @Override
    public boolean equals(Object obj) {
        Fractional fractional = (Fractional) obj;
        return fractional.getValue()==this.getValue();
    }

    @Override
    public String toString() {

        if (isNaN()) {
            return NotANumber;
        }

        if (isInfinity()) {
            if (sign() > 0) {
                return PositiveInfinity;
            } else {
                return NegativeInfinity;
            }
        }

        if (denominator == 1)
            return numerator + "";
        else if (denominator == -1)
            return -numerator + "";
        else if (numerator == 0 && denominator != 0)
            return "0";
        else if (numerator < 0 && denominator < 0)
            return Math.abs(numerator) + "/" + Math.abs(denominator);
        else if (numerator > 0 && denominator < 0)
            return -Math.abs(numerator) + "/" + Math.abs(denominator);

        return numerator + "/" + denominator;
    }
}