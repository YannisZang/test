package NBodySimulation;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class NBody {
    public static double readRadius(String fileName) {
        In input = new In(fileName);
        int N = input.readInt();
        double radius = input.readDouble();
        return radius;
    }
    public static Planet[] readPlanet(String fileName) {
        In input = new In(fileName);
        int n = input.readInt();
        double radius = input.readDouble();
        Planet[] allPlanet = new Planet[n];
        for (int i = 0; i < allPlanet.length; i++) {
            double xxPos = input.readDouble();
            double yyPos = input.readDouble();
            double xxVer = input.readDouble();
            double yyVer = input.readDouble();
            double mass = input.readDouble();
            String imfFileName = input.readString();
            allPlanet[i] = new Planet(xxPos, yyPos, xxVer, yyVer, mass, imfFileName);
        }
        return allPlanet;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        String backgroud = args[3];
        double radius = readRadius(filename);
        Planet[] allPlanet = readPlanet(filename);
        // draw the background
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.picture(0, 0, backgroud);
        for (int i = 0; i < allPlanet.length; i++) {
            allPlanet[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < T; i += dt) {
            double[] xForces = new double[allPlanet.length];
            double[] yForces = new double[allPlanet.length];
            for (int j = 0; j < allPlanet.length; j++) {
                xForces[j] = allPlanet[j].calcNetForceExertedByX(allPlanet);
                yForces[j] = allPlanet[j].calcNetForceExertedByY(allPlanet);
                allPlanet[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0, 0, backgroud);
            for (int k = 0; k < allPlanet.length; k++) {
                allPlanet[k].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
