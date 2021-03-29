import java.time.Clock;

public class fractionsAssignment {
	
	private int Numer,Denom; //Numerator Denominator
	int newNumer, newNumerTwo, finalNumer, finalDenom;
	
	public static void main(String[] args) {
		Clock Timer = Clock.systemUTC();
		long iniTime = Timer.millis();
		fractionsAssignment frac1 = new fractionsAssignment(1,2);
		fractionsAssignment fracEq = new fractionsAssignment(1,2);
		fractionsAssignment frac2 = new fractionsAssignment(3,4);
		
		fractionsAssignment fracSub = new fractionsAssignment(1,4);
		fractionsAssignment fracAdd = new fractionsAssignment(5,4);
		fractionsAssignment fracMult = new fractionsAssignment(3,8);
		fractionsAssignment fracDiv = new fractionsAssignment(3,2);
		
		fractionsAssignment fracAdd2 = new fractionsAssignment(5,2);
		fractionsAssignment fracSub2 = new fractionsAssignment(-1,4);
		fractionsAssignment fracMult2 = new fractionsAssignment(3,2);
		fractionsAssignment fracDiv2 = new fractionsAssignment(1,4);
		
		fractionsAssignment SimpFrac1 = new fractionsAssignment(21,28);
		fractionsAssignment SimpFrac2 = new fractionsAssignment(3,4);
		
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
		
		if (SimpFrac1.equals(SimpFrac2)) {
			System.out.println("Passed fraction simplification test");
		}else System.out.println("Failed fraction simplification test");
		
		System.out.println(String.format("Completed in %d milliseconds", (Timer.millis() - iniTime) ));	
	}
	
	public fractionsAssignment (int numer, int denom) { 
		this.Numer = numer; 
		this.Denom = denom;
	}
	
	public int getNum() {
		return this.Numer;
	}
	
	public int getDenom() {
		return this.Denom;
	}
	
	public fractionsAssignment add (fractionsAssignment frac) {
		if (this.Denom == frac.Denom) {
			finalNumer = this.Numer + frac.Numer;
			finalDenom = this.Denom;
		} else {
			newNumer = this.Numer * frac.Denom;
			newNumerTwo = frac.Numer * this.Denom;
			finalNumer =  newNumer + newNumerTwo;
			finalDenom = this.Denom * frac.Denom;
		}
		simplify(frac);
		return new fractionsAssignment(finalNumer,finalDenom);
	}
	
	public fractionsAssignment sub (fractionsAssignment frac) {
		if (this.Denom == frac.Denom) {
			finalNumer = this.Numer - frac.Numer;
			finalDenom = this.Denom;
		} else {
			newNumer = this.Numer * frac.Denom;
			newNumerTwo = frac.Numer * this.Denom;
			finalNumer =  newNumer - newNumerTwo;
			finalDenom = this.Denom * frac.Denom;
		}
		simplify(frac);
		return new fractionsAssignment(finalNumer,finalDenom);
	}
	
	public fractionsAssignment multi (fractionsAssignment frac) {
		finalNumer = this.Numer * frac.Numer;
		finalDenom = this.Denom * frac.Denom;
		
		simplify(frac);
		return new fractionsAssignment(finalNumer,finalDenom);
	}
	
	public fractionsAssignment div (fractionsAssignment frac) {
		finalNumer = this.Numer * frac.Denom;
		finalDenom = this.Denom * frac.Numer;
		simplify(frac);
		return new fractionsAssignment(finalNumer,finalDenom);
	}
	
	public String toString() {
		return String.format("[%d/%d]",this.Numer,this.Denom);
	}
	
	public double toDouble () {
		double fracDec = finalNumer / finalDenom;
		return (7);
	}
	
	public boolean equals (fractionsAssignment frac) {
		if (this.Numer == frac.Numer && this.Denom ==  frac.Denom) {
			return true;
		} else {
			return false;
		}
	}
	
	public fractionsAssignment add (int Integer) {
		fractionsAssignment intToFrac = new fractionsAssignment(Integer,1);
		add(intToFrac);
		return new fractionsAssignment(finalNumer,finalDenom);
	}
	
	public fractionsAssignment sub (int Integer) {
		fractionsAssignment intToFrac = new fractionsAssignment(Integer,1);
		sub(intToFrac);
		return new fractionsAssignment(finalNumer,finalDenom);
	}
	
	public fractionsAssignment multi (int Integer) {
		fractionsAssignment intToFrac = new fractionsAssignment(Integer,1);
		multi(intToFrac);
		return new fractionsAssignment(finalNumer,finalDenom);
	}
	
	public fractionsAssignment div (int Integer) {
		fractionsAssignment intToFrac = new fractionsAssignment(Integer,1);
		div(intToFrac);
		return new fractionsAssignment(finalNumer,finalDenom);
	}
	
	private fractionsAssignment simplify (fractionsAssignment frac) {
		if (finalNumer > finalDenom) {
			for (int divisor = finalNumer; divisor > 1; divisor--) {
				if (finalNumer % divisor == 0 && finalDenom % divisor == 0) {
					finalNumer = finalNumer / divisor;
					finalDenom = finalDenom / divisor;
				}
			}
		} else if (finalDenom > finalNumer) {
			for (int divisor = finalDenom; divisor > 1; divisor--) {
				if (finalNumer % divisor == 0 && finalDenom % divisor == 0) {
					finalNumer = finalNumer / divisor;
					finalDenom = finalDenom / divisor;
				}
			}
		} else {
			finalNumer = finalNumer / 1;
			finalDenom = finalDenom / 1;
			
		}
		return new fractionsAssignment(finalNumer,finalDenom);
	}
}

//fractionsAssignment fraction = new fractionsAssignment (Input.nextInt() , Input.nextInt());
//System.out.println(fraction.Numer + "/" + fraction.Denom);
//static Scanner Input = new Scanner (System.in);
//import java.util.Scanner;
//fractionsAssignment fractionOne = new fractionsAssignment(8,4);
//fractionsAssignment fractionTwo = new fractionsAssignment(4,2);
//System.out.println(fractionOne.sub(fractionTwo));
//System.out.println(fractionOne.add(fractionTwo));
//System.out.println(fractionOne.multi(fractionTwo));
//System.out.println(fractionOne.div(fractionTwo));