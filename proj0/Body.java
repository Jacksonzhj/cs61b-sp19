import java.lang.Math; 
public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	public double calcDistance(Body b) {
		double dis = Math.sqrt(Math.pow((this.xxPos - b.xxPos), 2) + Math.pow((this.yyPos - b.yyPos), 2));
		return dis;
	}
	public double calcForceExertedBy(Body b) {
		double dis = this.calcDistance(b);
		double forceBy = (G * this.mass * b.mass) / (dis * dis);
		return forceBy;
	}
	public double calcForceExertedByX(Body b) {
		double forceByX = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos)/ this.calcDistance(b);
		return forceByX;
	}
	public double calcForceExertedByY(Body b) {
		double forceByY = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos)/ this.calcDistance(b);
		return forceByY;
	}
	public double calcNetForceExertedByX(Body[] bs) {
		double netForceByX = 0;
		for (Body b : bs) {
			if (!this.equals(b)) {
				netForceByX = netForceByX + this.calcForceExertedByX(b);
			}
		}
		return netForceByX;
	}
	public double calcNetForceExertedByY(Body[] bs) {
		double netForceByY = 0;
		for (Body b : bs) {
			if (!this.equals(b)) {
				netForceByY = netForceByY + this.calcForceExertedByY(b);
			}
		}
		return netForceByY;
	}
	public void update(double dt, double fX, double fY) {
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel = this.xxVel + dt * ax;
		this.yyVel = this.yyVel + dt * ay;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}
	public void draw() {
		StdDraw.enableDoubleBuffering();
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}