package NBodySimulation;

import edu.princeton.cs.algs4.StdDraw;

public class Planet {
    public double xxPos; // its current x position
    public double yyPos; // its current y position
    public double xxVer; // its current velocity in the x direction
    public double yyVer; // its current velocity in the y direction
    public double mass; // its mass
    public String imfFileName; // The name of the file that corresponds to the image that depicts the planet
    private final double G = 6.67 * 1.0e-11; // gravity is constant

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVer = xV;
        yyVer = yV;
        mass = m;
        imfFileName = img;
    }
    public Planet(Planet p) {
    }
    public double calcDistance(Planet b) {
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }
    public double calcForceExertedBy(Planet b) {
        return G * this.mass * b.mass / (this.calcDistance(b) * this.calcDistance(b));
    }
    public double calcForceExertedByX(Planet b) {
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / (this.calcDistance(b));
    }
    public double calcForceExertedByY(Planet b) {
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }
    public double calcNetForceExertedByX(Planet[] allPlanet) {
        double forceX = 0;
        int index = 0;
        while (index < allPlanet.length) {
            if (!this.equals(allPlanet[index])) {
                forceX += this.calcForceExertedByX(allPlanet[index]);
            }
            index++;
        }
        return forceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanet) {
        double forceY = 0;
        int index = 0;
        while (index < allPlanet.length) {
            if (!this.equals(allPlanet[index])) {
                forceY += this.calcForceExertedByY(allPlanet[index]);
            }
            index++;
        }
        return forceY;
    }
    public boolean equals(Planet b) {
        return (xxPos == b.xxPos && yyPos == b.yyPos && xxVer == b.xxVer && yyVer == b.yyVer && mass == b.mass &&
                imfFileName == b.imfFileName);
    }
    public void update(double dt, double fX, double fY) {
        double accX = fX / this.mass;
        double accY = fY / this.mass;
        xxVer = xxVer + dt * accX;
        yyVer = yyVer + dt * accY;
        xxPos = xxPos + dt * xxVer;
        yyPos = yyPos + dt * yyVer;
    }
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, this.imfFileName);
    }
    public static void main(String[] args) {
        Planet samh = new Planet(1.0, 0, 3, 5, 10, "earth.gif");
        Planet aegir = new Planet(3, 3, 3, 5, 5, "moon.gif");
        Planet rocinan = new Planet(5, -3, 3, 5, 50, "virus.gif");
        Planet[] allplants = {samh, aegir, rocinan};
        System.out.println(samh.calcNetForceExertedByY(allplants));
    }

}
