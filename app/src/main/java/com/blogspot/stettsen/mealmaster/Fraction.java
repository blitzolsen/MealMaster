package com.blogspot.stettsen.mealmaster;

public class Fraction {

    private int wholeNumber;
    private int numerator;
    private int denominator;

    public Fraction() {
        wholeNumber = 0;
        numerator = 0;
        denominator = 2;
    }

    public Fraction(int numerator, int denominator){
        wholeNumber = 0;
        setNumerator(numerator);
        setDenominator(denominator);
    }

    public String getString(){
        String string = "";

        if (wholeNumber != 0 && numerator != 0 && denominator != 0){
            string = wholeNumber + " " + numerator + "/" + denominator;
        } else if (wholeNumber == 0 && numerator != 0 && denominator != 0 ){
            string = numerator + "/" + denominator;
        }

        if (numerator != 0 && denominator == 0){
            string = "Error: denominator is 0";
        }

        return string;
    }
    public int getDecimal(){
        return numerator/denominator;
    }

    public void add(Fraction fraction2) {
        if (denominator == fraction2.getDenominator()){
            numerator += fraction2.getNumerator();
            simplify();
        } else {
            int tempDeminator = denominator;
            setDenominator(denominator * fraction2.getDenominator());
            setNumerator((numerator * fraction2.getDenominator()) + (fraction2.getNumerator() * tempDeminator));
            simplify();
        }
    }

    public void simplify(){
        wholeNumber = numerator / denominator;
        numerator = numerator % denominator;
        int gCD = greatestCommonDenominator(numerator, denominator);
        numerator = numerator / gCD;
        denominator = denominator / gCD;
    }

    /********************************
     * More efficient version of this.
     * function gcd(a, b)
         while a ≠ b
         if a > b
         a := a − b;
         else
         b := b − a;
        return a;
     * @param a
     * @param b
     * @return simplified fraction
     *******************************/
    public static int greatestCommonDenominator(int a, int b)
    {
        if(a == 0 || b == 0) return a+b;
        return greatestCommonDenominator(b,a%b);
    }

    public int tranferWholeNumber(){
        int temp = wholeNumber;
        wholeNumber = 0;
        return temp;
    }

    public void setNumerator(int numerator){
        this.numerator = numerator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public int getDenominator() {
        return denominator;
    }
}