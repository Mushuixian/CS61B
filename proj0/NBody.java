public class NBody{
    static double readRadius(String filename){
        In in=new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    static Planet[] readPlanets(String filename){
        In in=new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        Planet[] myPlanet = new Planet[firstItemInFile];
        for(int i=0;i<firstItemInFile;i++){
            myPlanet[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
                                     in.readDouble(),in.readDouble(),in.readString());
        }
        return myPlanet;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double t = Double.parseDouble(args[1]);
        String filename = args[2];



        Planet[] p = readPlanets("./data/planets.txt");
        readPlanets("./data/planets.txt");
        double universe_radius;
        universe_radius = readRadius("./data/planets.txt");
        StdDraw.enableDoubleBuffering();


        double time;
        for(time = 0;time <= T;time++){
            double xForces[] = new double[p.length];
            double yForces[] = new  double[p.length];
            for (int n = 0;n < p.length;n++){
                xForces[n] = p[n].calcNetForceExertedByX(p);
                yForces[n] = p[n].calcNetForceExertedByY(p);
                p[n].update(t,xForces[n],yForces[n]);
                StdDraw.setScale(-universe_radius,universe_radius);
                StdDraw.clear();
                StdDraw.picture(0,0,"./images/starfield.jpg");
                for (int i = 0;i < p.length;i++){
                    p[i].draw();
                }
                StdDraw.show();
                StdDraw.pause(1);
                time += t;
            }
        }
    }
}