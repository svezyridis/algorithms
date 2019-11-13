package app;

/**
 * Rational
 */
public class Rational {
    private int numerator;
    private int denominator;

    Rational(int numerator, int denominator) {
        this.denominator = denominator;
        this.numerator = numerator;
    }

    Rational plus(Rational b) {
        return new Rational(this.numerator * b.denominator + b.numerator * this.denominator,
                this.denominator * b.denominator);
    }

    Rational minus(Rational b) {
        return new Rational(this.numerator * b.denominator - b.numerator * this.denominator,
                this.denominator * b.denominator);
    }

    Rational times(Rational b) {
        return new Rational(this.numerator * b.numerator, this.denominator * this.denominator);
    }

    Rational divides(Rational b) {
        return new Rational(this.numerator * b.denominator, this.denominator * this.numerator);
    }

    boolean equals(Rational that) {
        int maxNumerator = Math.max(this.numerator, that.numerator);
        int minNumerator = Math.min(this.numerator, that.numerator);
        int maxDenominator = Math.max(this.denominator, that.denominator);
        int minDenominator = Math.min(this.denominator, that.denominator);
        return maxDenominator / minDenominator == maxNumerator / minNumerator;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.numerator + "/" + this.denominator;
    }
}