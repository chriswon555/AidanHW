
public class Vector {
	double X,Y;
	
	public Vector (double x, double y) {
		this.X = x;
		this.Y = y;
	}
	
	public String toString () {
		return String.format("[%f,%f]", this.X, this.Y);
	}
	
	public boolean equals (Vector vec) {
		if (this.X == vec.X && this.Y ==  vec.Y) {
			return true;
		} else {
			return false;
		}
	}
	
	public Vector add (Vector vec) {
		double newX = this.X + vec.X;
		double newY = this.Y + vec.Y;
		return new Vector(newX,newY);
	}
	
	public double length() {
		return Math.sqrt(this.X*this.X + this.Y*this.Y);
	}
	
	public Vector subtract (Vector vec) {
		double newX = this.X - vec.X;
		double newY = this.Y - vec.Y;
		return new Vector(newX,newY);
	}
	
	public double getX () {
		return this.X;
	}
	
	private void setX (double value) {
		this.X = value;
	}
	
	public static void main(String[] args) {
		Vector Vec1 = new Vector(1,2);
		Vector Vec2 = new Vector(2,1);
		System.out.println(Vec1.add(Vec2).length());
	}

}
