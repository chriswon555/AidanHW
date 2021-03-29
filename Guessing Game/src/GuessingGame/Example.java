package GuessingGame;

public class Example {

	private double X,Y;
	
	public Example (double x, double y) {
		X = x;
		Y = y;
	}
	
	public String toString() {
		return String.format("[%f,%f]", X,Y);
	}
	
	public boolean equals(Example Vec2) {
		if (this.X == Vec2.X && this.Y == Vec2.Y) {
			return true;
		} else return false;
	}
	
	public double getX() {
		return this.X;
	}
	
}
