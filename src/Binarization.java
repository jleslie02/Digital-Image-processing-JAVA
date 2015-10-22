
   public class Binarization extends Images
   {
      public int imgB  [][][];
      public int treshold1;
      public int treshold2;
   
      public Binarization(int T1new, int T2new, int NLnew, int NCnew, int nberBandnew, int ifr)
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
      }
   
      public Binarization()
      {}
   
      public void binarize()
      {
          int [][][] im = new int [nberBand][NL][NC];
          copyImages(instantiateIm(im, true), im);
         for (int k = 0; k< nberBand; k++)
         {	
            for (int i = 0; i < NL; i++)
            {
               for (int j=0; j<NC; j++)
               {
                  if (treshold1 < originalImg[k][i][j] &&  originalImg[k][i][j] < treshold2 )
                     im[k][i][j] = 0;
                  else if (treshold1 >= originalImg[k][i][j] || originalImg[k][i][j] >=  treshold2 )
                     im[k][i][j] = 255;
               }
            }
         }
         
         copyImages(im, imgB);
      }
   
   
      public int [][][]  getBinarizedImg()
      {
         return imgB;
      }
   
   
      public void setT1(int T1new)
      {
         treshold1 = T1new;
      }
   
      public void setT2(int T2new)
      {
         treshold2 = T2new;
      }
   
      public int getT1()
      {
         return treshold1;
      }
   
      public int getT2()
      {
         return treshold2;
      }
   
      public boolean isTresholdValid()
      {
         return (treshold1 != treshold2);
      }
   
   }
				
