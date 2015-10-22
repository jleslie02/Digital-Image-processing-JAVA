   import java.lang.Math;

   public class Filters extends Binarization
   {
      protected final static int[][][] robertFilter = {{{0,0,0},{0,1,0},{0,0,-1}}, {{0,0,0},{0,0,1},{0,-1,0}}, {{0,0,1},{0,-2,0},{0,0,-1}}, {{0,0,1},{0,-2,-2},{0,0,0}}};
      protected final static int[][][] SobelFilter = {{{-1,0,-1},{-2,0,-2},{-1,0,1}}, {{-1,-2,-1},{0,0,0},{1,0,1}}, {{-2,1,0},{-1,0,1},{0,1,2}}, {{0,-1,-2},{-1,0,-1},{2,1,0}}};
      protected final static int[][][] PrewittFilter = {{{-1,0,1},{-1,0,1},{-1,0,1}}, {{-1,-1,-1},{0,0,0},{1,1,1}}, {{-1,-1,0},{-1,0,1},{0,1,1}}, {{0,-1,-1},{1,0,-1},{1,1,0}}};
      protected final static int[][][] KirshFilter = {{{-3,-3,5},{-3,0,5},{-3,-3,5}}, {{-3,-3,-3},{-3,0,-3},{5,5,5}}, {{-3,-3,-3},{-3,0,5},{-3,5,5}}, {{-3,-3,-3},{5,0,-3},{5,5,-3}}};	
      protected final static int [][][][] filters = {robertFilter,SobelFilter,PrewittFilter,KirshFilter};
      public int factor;
      protected final static double cst = 1.77245385;
      
   
   
      public Filters (int T1new, int T2new, int NLnew, int NCnew, int ifr, int nberBandnew, int brdrFll,  int q )
      {
         setT1(T1new);
         setT2(T2new);
         setNL(NLnew);
         setNC(NCnew);
         setNB(nberBandnew);
         originalImg = new int [nberBandnew][NLnew][NCnew];
         setImageFormat(ifr);
			
         if (ifr == 0)
         {
            imageBand = new int [NC * NL * nberBandnew];
         }
         else if (ifr == 1 || ifr == 2)
         {
            imageBand = new int [2 * NC * NL * nberBandnew];
         }
         setBf(brdrFll);
         setQ(q);
      }
   
      public Filters()
      {}
   	
   	
      public double gamma(int N)
      {
         double t = 1;
         double gaMa;
      	
         for (int k = 1; k <= N; k++)
         {
            t = t * (2*k - 1);
         }
      	
         gaMa = cst * t/(2 * N);
      	
         return gaMa;
      }
   	
      public int factoriel(int n)
      {
         if (n==1 || n == 0)
            return 1;
         else
            return n * factoriel(n-1);
      }
   	
   	
      public double SD_noise(int N, int factor)
      {
         double su= 0.0;
      	
         switch(factor)
         {
            case 0:
               {
                  su = 1/Math.sqrt(N);
                  break;
               }
            case 1:
               {
                  su = Math.sqrt(0.27/N);
                  break;
               }
            case 2:
               {
                  su = Math.sqrt(wx * Math.pow(factoriel(N-1)/gamma(N), 2) - 1);
                  break;
               }
         }
      	
         return su;
      }
   		
   
      public double localAverage(int x, int y, int [][][] image, int k)
      {			
         double avgTemp = 0.0;
         double avg = avgTemp;
      	
         for (int i = x - wx/2; i <= x + wx/2; i++)
         {
            for (int j = y - wy/2; j <= y + wy/2; j++)
            {
               avgTemp += image[k][i][j];
            }
         }
      		
         avg = avgTemp/(wx*wy);
      		
         return avg;
      	
      }
   	
      public double localVariance(int x, int y, int [][][] band, int k)
      {
         double avg = 0.0;
         double varTemp = avg;
         double variance = avg;
      	
         avg = localAverage(x, y, band, k);
      
         for (int i = x - wx/2; i <= x + wx/2; i++)
         {
            for (int j = y - wy/2; j <= y + wy/2; j++)
            {
               varTemp += Math.pow((band[k][i][j] - avg), 2);
            }
         }
      		
         variance = varTemp/(Math.pow((wx*wy),2) - 1);
      		
         return variance;
      }
   	
   		
     		
      public void setQ(int q)
      {
         factor = q;
      }
   	
      public int getQ()
      {
         return factor;
      }
   	
      public boolean isQvalid(int q)
      {
         return (q == 0 || q == 1 || q == 2);
      }
   
   }	
