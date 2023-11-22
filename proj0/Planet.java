public class Planet{
        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName;

  public Planet(double xP,double yP,double xV,double yV,double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass= m;
        imgFileName = img;
 }

 public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
         xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
 }

 double calcDistance(Planet p){
      double r;
      r = Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
      return r;
 }
 double calcForceExertedBy(Planet p){
      double r = this.calcDistance(p);
      double f;
      f = 6.67*1E-11*this.mass*p.mass/(r*r);
      return f;
 }

 double calcForceExertedByX(Planet p){
      double r,f,fx;
      r = this.calcDistance(p);
      f = this.calcForceExertedBy(p);
      fx = f*((p.xxPos-this.xxPos)/r);
      return fx;
 }

    double calcForceExertedByY(Planet p){
        double r,f,fy;
        r = this.calcDistance(p);
        f = this.calcForceExertedBy(p);
        fy = f*((p.yyPos-this.yyPos)/r);
        return fy;
    }

    double calcNetForceExertedByX(Planet[] p){
      double fx=0;
      int i;
      for(i=0;i< p.length;i++){
          if(this.equals(p[i]))
              continue;
          fx += this.calcForceExertedByX(p[i]);
      }
      return fx;
    }

    double calcNetForceExertedByY(Planet[] p){
        double fy=0;
        int i;
        for(i=0;i< p.length;i++){
            if(this.equals(p[i]))
                continue;
            fy += this.calcForceExertedByY(p[i]);
        }
        return fy;
    }

    void update(double dt,double fx,double fy){
      double ax,ay,vx,vy,xpos,ypos;
      ax = fx/this.mass;
      ay = fy/this.mass;

      vx = this.xxVel+ax*dt;
      vy = this.yyVel+ay*dt;

      xpos = this.xxPos+vx*dt;
      ypos = this.yyPos+vy*dt;

      this.xxVel = vx;
      this.yyVel = vy;
      this.xxPos = xpos;
      this.yyPos = ypos;
    }

    void draw(){
      String img_root = "./images/" + this.imgFileName;
      StdDraw.picture(this.xxPos,this.yyPos,img_root);
    }
}
