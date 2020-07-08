public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int number = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int number = in.readInt();
		Body[] bodies = new Body[number];
		double radius = in.readDouble();
		for (int i = 0; i < 5; i ++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
		}
		return bodies;
	}
	public static void main(String[] args) {
		/* Collect all needed input. */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		Body[] bodies = NBody.readBodies(filename);
		/* Drawing the backgroud. */
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		/* Drawing more than one body. */
		for (Body body : bodies) {
			body.draw();
		}
		/* Shows the drawing to the screen. */
		StdDraw.show();
		for (double time = 0; time <= T; time = time + dt) {
			double[] xForces = new double[5];
			double[] yForces = new double[5];
			for (int i = 0; i < 5; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
				bodies[i].update(dt, xForces[i], yForces[i]);
				StdDraw.picture(0, 0, "images/starfield.jpg");
				for (Body body : bodies) {
					body.draw();
				}
				StdDraw.show();
				StdDraw.pause(10);
			}
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}
}