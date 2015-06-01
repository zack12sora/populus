package edu.umn.ecology.populus.model.eov;
import edu.umn.ecology.populus.math.*;

public class EOVDeriv extends Derivative {
   public static final int kX = 0;
   public static final int kY = 1;
   private int type;
   private double a, a0, a1, b, d, e, p, c1, c2, c3;
   private double bop, eop;

   public void doDerivative( double t, double[] N, double[] dN ) {
      double x = N[kX];
      double y = N[kY];
      a = a0 - a1 * (x - y);
      bop = ( x - c2)/ (2*c3);
      eop = c1 + c2 * bop + c3 * bop * bop;
      if ( x < 0 ) x = 0;
      if ( y < 0 ) y = 0;
      if( Math.abs( x ) < 1e-6 ) {
	}

      switch( type ) {
         case EOVParamInfo.ALTER:
            dN[kX] = ( a - d ) * x + a * p * y - b * x * y;//H
            dN[kY] = ( b * x -  d - e ) * y;//I
            break;

         case EOVParamInfo.COUPLED:

            dN[kX] = ( a - d ) * x + a * p * y - bop * x * y;
            dN[kY] = (bop * x * y) - (d * y) - eop * y;
            break;
      }
      return ;
   }

   public EOVDeriv( int modelType, double a0, double a1, double b, double d, double p, double e, double c1, double c2, double c3 ) {
      this.type = modelType;
      this.numVariables = 2;
      this.a0 = a0;
      this.a1 = a1;
      this.b = b;
      this.d = d;
      this. p = p;
      this.e = e;
      this.c1 = c1;
      this.c2 = c2;
      this.c3 = c3;
   }
}

