import java.time.Clock;

public class fraction {
	private int Numer,Denom; //Numerator Denominator

	public fraction (int numer, int denom) { //Constructor
		this.Numer = numer; 
		this.Denom = denom;
		simplifyCon();
	}

	//Bottom 2 are getter functions...
	public int getNum() {
		return this.Numer;
	}

	public int getDenom() {
		return this.Denom;
	}

	public fraction add (fraction frac) {
		int finalNumer,finalDenom;
		if (this.Denom == frac.Denom) { //if denominators are same...
			return new fraction(this.Numer + frac.Numer,this.Denom).simplify(frac);
		} else {
			finalNumer = this.Numer * frac.Denom + frac.Numer * this.Denom; //Cross multiply if denominators are not equal
			finalDenom = this.Denom * frac.Denom; 
			return simplify(new fraction(finalNumer,finalDenom));
		}
	}

	public fraction sub (fraction frac) {
		int finalNumer,finalDenom;
		if (this.Denom == frac.Denom) { //if denominators are same...
			return new fraction(this.Numer - frac.Numer,this.Denom).simplify(frac);
		} else {
			finalNumer = this.Numer * frac.Denom - frac.Numer * this.Denom; //cross multiply if denominators are not equal
			finalDenom = this.Denom * frac.Denom; 
			return simplify(new fraction(finalNumer,finalDenom));
		}
	}

	public fraction multi (fraction frac) {// Multiplies straight across
		int finalNumer,finalDenom;
		finalNumer = this.Numer * frac.Numer;
		finalDenom = this.Denom * frac.Denom;
		return simplify(new fraction(finalNumer,finalDenom));
	}

	public fraction div (fraction frac) { //Multiplies the reciprocal of the fraction...
		int finalNumer,finalDenom;
		finalNumer = this.Numer * frac.Denom;
		finalDenom = this.Denom * frac.Numer;
		return simplify(new fraction(finalNumer,finalDenom));
	}

	public String toString() {
		return String.format("[%d/%d]",this.Numer,this.Denom);
	}

	public double toDouble (fraction frac) {//Casts both variables so that they can be divided 
		return ((double)this.Numer / (double)this.Denom);
	}

	public boolean equals (fraction frac) {
		if (this.Numer == frac.Numer && this.Denom ==  frac.Denom) {
			return true;
		} else {
			return false;
		}
	}

	//Bottom 4 are overloaded operations for Integers
	public fraction add (int Integer) {
		fraction intToFrac = new fraction(Integer,1);
		return new fraction(this.Numer,this.Denom).add(intToFrac);
	}

	public fraction sub (int Integer) {
		fraction intToFrac = new fraction(Integer,1);
		return new fraction(this.Numer,this.Denom).sub(intToFrac);
	}

	public fraction multi (int Integer) {
		fraction intToFrac = new fraction(Integer,1);
		return new fraction(this.Numer,this.Denom).multi(intToFrac);
	}

	public fraction div (int Integer) {
		fraction intToFrac = new fraction(Integer,1);
		return new fraction(this.Numer,this.Denom).div(intToFrac);
	}

	private fraction simplify (fraction frac) {
		int finalNumer = frac.Numer;
		int finalDenom = frac.Denom;

			for (int divisor = Math.min(finalNumer, finalDenom); divisor > 1; divisor--) {//Sets divisor as the smaller number
				if (finalNumer % divisor == 0 && finalDenom % divisor == 0) {
					finalNumer /= divisor;
					finalDenom /= divisor;
				}
			}
			return new fraction(finalNumer,finalDenom);
	}

	private void simplifyCon() {
			for (int divisor = Math.min(this.Numer, this.Denom); divisor > 1; divisor--) {//Sets divisor as the smaller number
				if (this.Numer % divisor == 0 && this.Denom % divisor == 0) {
					this.Numer /= divisor;
					this.Denom /= divisor;
				}
			}
	}

	public static fraction Decimal2Fraction (double deci) {
		int multiplier = 1;
		while (deci % 1 != 0) { //Multiplies Numerator until its a whole number
			deci *= 10;
			multiplier *= 10; //Increases multiplier until Numerator is a whole number
		}
		return new fraction((int)deci,multiplier); //Returns deci as Numerator and the total multiplier as Denominator
	}
	
	//Bottom 4 are overloaded operations for decimals
	public fraction add (double deci) {
		fraction deciToFrac = fraction.Decimal2Fraction(deci);
		return new fraction(this.Numer,this.Denom).add(deciToFrac);
	}

	public fraction sub (double deci) {
		fraction deciToFrac = fraction.Decimal2Fraction(deci);
		return new fraction(this.Numer,this.Denom).sub(deciToFrac);
	}

	public fraction multi (double deci) {
		fraction deciToFrac = fraction.Decimal2Fraction(deci);
		return new fraction(this.Numer,this.Denom).multi(deciToFrac);
	}

	public fraction div (double deci) {
		fraction deciToFrac = fraction.Decimal2Fraction(deci);
		return new fraction(this.Numer,this.Denom).div(deciToFrac);
	}
	
	public static void main(String[] args) {
		Clock Timer = Clock.systemUTC();
		long iniTime = Timer.millis();
		long startTime = System.nanoTime();
		
		fraction frac1 = new fraction(1,2);
		fraction fracEq = new fraction(1,2);
		fraction frac2 = new fraction(3,4);

		fraction fracSub = new fraction(1,4);
		fraction fracAdd = new fraction(5,4);
		fraction fracMult = new fraction(3,8);
		fraction fracDiv = new fraction(3,2);

		fraction fracAdd2 = new fraction(5,2);
		fraction fracSub2 = new fraction(-1,4);
		fraction fracMult2 = new fraction(3,2);
		fraction fracDiv2 = new fraction(1,4);

		fraction Dec2Frac = new fraction(123,1000);
		fraction Dec2Frac2 = new fraction(314,100);

		fraction SimpFrac1 = new fraction(21,28);
		fraction SimpFrac2 = new fraction(3,4);

		if (!frac1.equals(frac2)) {
			System.out.println("Passed first equality test");
		} else System.out.println("Failed first equality test");
		if (frac1.equals(fracEq)) {
			System.out.println("Passed second equality test");
		} else System.out.println("Failed second equality test");

		if (frac1.add(frac2).equals(fracAdd)) {
			System.out.println("Passed fraction add test");
		}else System.out.println("Failed fraction add test");
		if (frac2.sub(frac1).equals(fracSub)) {
			System.out.println("Passed fraction subtraction test");
		}else System.out.println("Failed fraction subtraction test");
		if (frac2.multi(frac1).equals(fracMult)) {
			System.out.println("Passed fraction multiplication test");
		}else System.out.println("Failed fraction multiplication test");
		if (frac2.div(frac1).equals(fracDiv)) {
			System.out.println("Passed fraction division test");
		}else System.out.println("Failed fraction division test");

		if (frac1.add(2).equals(fracAdd2)) {
			System.out.println("Passed integer add test");
		}else System.out.println("Failed integer add test");
		if (frac2.sub(1).equals(fracSub2)) {
			System.out.println("Passed integer subtraction test");
		}else System.out.println("Failed integer subtraction test");
		if (frac2.multi(2).equals(fracMult2)) {
			System.out.println("Passed integer multiplication test");
		}else System.out.println("Failed integer multiplication test");
		if (frac1.div(2).equals(fracDiv2)) {
			System.out.println("Passed integer division test");
		}else System.out.println("Failed integer division test");

		if (fraction.Decimal2Fraction(0.123).equals(Dec2Frac)) {
			System.out.println("Passed first decimal to fraction test");
		} else System.out.println("Failed first decimal to fraction test");
		if (fraction.Decimal2Fraction(3.14).equals(Dec2Frac2)) {
			System.out.println("Passed second decimal to fraction test");
		} else System.out.println("Failed second decimal to fraction test");

		if (frac1.add(0.75).equals(fracAdd)) {
			System.out.println("Passed decimal add test");
		}else System.out.println("Failed decimal add test");
		if (frac2.sub(0.5).equals(fracSub)) {
			System.out.println("Passed decimal subtraction test");
		}else System.out.println("Failed decimal subtraction test");
		if (frac2.multi(0.5).equals(fracMult)) {
			System.out.println("Passed decimal multiplication test");
		}else System.out.println("Failed decimal multiplication test");
		if (frac2.div(0.5).equals(fracDiv)) {
			System.out.println("Passed decimal division test");
		}else System.out.println("Failed decimal division test");

		if (SimpFrac1.equals(SimpFrac2)) {
			System.out.println("Passed fraction simplification test");
		}else System.out.println("Failed fraction simplification test");

		System.out.println(String.format("Completed in %d nanoseconds", (System.nanoTime() - startTime) ));		
	}

}
