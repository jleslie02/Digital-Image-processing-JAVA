   import java.util.*;
   import java.lang.*;

   public class Images
   {
      public int originalImg  [][][];
      public int NL;
      public int NC;
      public int nberBand;
      protected final static int wx = 3;
      protected final static int wy = 3;
      public int borderFilling;
      public int imageFormat;
      public int [] imageBand;
   
      public Images (int NLnew, int NCnew, int nberBandnew, int ifr)
      {
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
   	
      public Images()
      {}
   
		
		public void setImageBand( int [] imgs)
		{
			if (imageFormat == 0)
			{
				imageBand = new int [NC * NL * nberBand];
			}
			else if (imageFormat == 1 || imageFormat == 2)
			{
				imageBand = new int [2 * NC * NL * nberBand];
			}

			copyBand(imgs, imageBand);
		}
		
		 public int [] getImageBand()
      {
         return imageBand;
      }
		
   
      public int [][][]  getOriginalImg()
      {
         return originalImg;
      }
   
      public int getNC()
      {
         return NC;
      }
   
      public int getNL()
      {
         return NL;
      }
   	
      public int getImageFormat()
      {
         return imageFormat;
      }
   
      public int getNB()
      {
         return nberBand;
      }
   
      public int getBf()
      {
         return borderFilling;
      }
   
      public void setOriginalImage(int [][][] imgS)
      {
         originalImg = new int [nberBand][NL][NC];
         copyImages(imgS, originalImg);
      }
   
   
      public void setNC(int ncNew)
      {
         NC = ncNew;
      }
   
      public void setBf(int bf)
      {
         borderFilling = bf;
      }
   	
      public void setImageFormat(int ifr)
      {
         imageFormat= ifr;
      }
   
   
      public void setNL(int nlNew)
      {
         NL = nlNew;
      }
   
      public void setNB(int nbnew)
      {
         nberBand = nbnew;
      }
   
      public void copyImages (int [][][] imgS, int [][][] imgD)
      {
         for (int k = 0; k < nberBand; k++)
         {
            for (int i = 0; i < NL; i++)
            {
               for (int j = 0; j < NC; j++)
               {
                  imgD[k][i][j] = imgS[k][i][j];
               }
            }
         }
      }
      
     
      public void copyImages (double[][][] imgS, double [][][] imgD)
      {
         for (int k = 0; k < nberBand; k++)
         {
            for (int i = 0; i < NL; i++)
            {
               for (int j = 0; j < NC; j++)
               {
                  imgD[k][i][j] = imgS[k][i][j];
               }
            }
         }
      }
      
           public void copyImages (boolean[][][] imgS, boolean [][][] imgD)
      {
         for (int k = 0; k < nberBand; k++)
         {
            for (int i = 0; i < NL; i++)
            {
               for (int j = 0; j < NC; j++)
               {
                  imgD[k][i][j] = imgS[k][i][j];
               }
            }
         }
      }
      
      
      
		
		 public void copyBand (int [] imgS, int []imgD)
      {
              for (int i = 0; i < imgS.length; i++)
               {
                  imgD[i] = imgS[i];
               }
      }

   
   
      public int [][][] fromBooleanToInt(boolean [][][]imgBo, int [][][]imgIn)
      {
         for (int k = 0; k < nberBand; k++)
         {	
            for (int i = 0; i < NL; i++)
            {
               for (int j = 0; j < NC; j++)
               {
                  if (imgBo[k][i][j])
                     imgIn[k][i][j] = 255;
                  else
                     imgIn[k][i][j] = 0;
               }
            }
         }
         return imgIn;
      }
   
      public int [][][] fillTheBordersOf(int[][][] imgNew, int n, int [][][] bd)
      {
           
      // zero's method
         if (borderFilling == 0)
         {
            for (int b = 0; b < NC+2; b++)
            {
               bd [n][0][b] = 0;
               bd [n][NL+1][b] = 0;
            }
            for (int b = 0; b < NL+2; b++)
            {
               bd [n][b][0] = 0;
               bd [n][b][NC+1] = 0;
            }

         }
         
         //symmetric border filling
         else if (borderFilling == 1)
         {
            for (int b = 0; b < NC; b++)
            {
               bd [n][0][b+1] = imgNew[n][1][b];
               bd [n][NL+1][b+1] = imgNew[n][NL-2][b];
            }
            for (int b = 0; b < NL; b++)
            {
               bd [n][b+1][0] = imgNew[n][b][1];
               bd [n][b+1][NC+1] = imgNew[n][b][NC-2];
            }
            
                        
          bd [n][0][0] = bd[n][0][2];
          bd [n][0][NC+1] = bd[n][0][NC-1];
          bd [n][NL+1][0] = bd[n][NL+1][2];
          bd [n][NL+1][NC+1] = bd[n][NL+1][NC-1];
         }
         
         //Circular symmetric border filling
         else if (borderFilling == 2)
         {
            for (int b = 0; b < NC; b++)
            {
               bd [n][0][b+1] = imgNew[n][NL-1][b];
               bd [n][NL+1][b+1] = imgNew[n][0][b];
            }
            for (int b = 0; b < NL; b++)
            {
               bd [n][b+1][0] = imgNew[n][b][NC-1];
               bd [n][b+1][NC+1] = imgNew[n][b][0];
            }
            
           bd [n][0][0] = bd[n][0][NC];
          bd [n][0][NC+1] = bd[n][0][1];
          bd [n][NL+1][0] = bd[n][NL+1][NC];
          bd [n][NL+1][NC+1] = bd[n][NL+1][1];
         }
      
      
         for (int i = 1; i < NL+1; i++)
         {
            for (int j = 1; j < NC+1; j++)
            {
               bd[n][i][j] = imgNew[n][i-1][j-1];
            }
         }
         
         return bd;
      }
      
      public boolean [][][] fillTheBordersOf(boolean [][][] imgNew, int n, boolean [][][] bd)
      {
     
      
       // zero's method
         if (borderFilling == 0)
         {
            for (int b = 0; b < NC+2; b++)
            {
               bd [n][0][b] = false;
               bd [n][NL+1][b] = false;
            }
            for (int b = 0; b < NL+2; b++)
            {
               bd [n][b][0] = false;
               bd [n][b][NC+1] = false;
            }

         }
         
         //symmetric border filling
         else if (borderFilling == 1)
         {
            for (int b = 0; b < NC; b++)
            {
               bd [n][0][b+1] = imgNew[n][1][b];
               bd [n][NL+1][b+1] = imgNew[n][NL-2][b];
            }
            for (int b = 0; b < NL; b++)
            {
               bd [n][b+1][0] = imgNew[n][b][1];
               bd [n][b+1][NC+1] = imgNew[n][b][NC-2];
            }
            
                        
          bd [n][0][0] = bd[n][0][2];
          bd [n][0][NC+1] = bd[n][0][NC-1];
          bd [n][NL+1][0] = bd[n][NL+1][2];
          bd [n][NL+1][NC+1] = bd[n][NL+1][NC-1];
         }
         
         //Circular symmetric border filling
         else if (borderFilling == 2)
         {
            for (int b = 0; b < NC; b++)
            {
               bd [n][0][b+1] = imgNew[n][NL-1][b];
               bd [n][NL+1][b+1] = imgNew[n][0][b];
            }
            for (int b = 0; b < NL; b++)
            {
               bd [n][b+1][0] = imgNew[n][b][NC-1];
               bd [n][b+1][NC+1] = imgNew[n][b][0];
            }
            
           bd [n][0][0] = bd[n][0][NC];
          bd [n][0][NC+1] = bd[n][0][1];
          bd [n][NL+1][0] = bd[n][NL+1][NC];
          bd [n][NL+1][NC+1] = bd[n][NL+1][1];
         }
      
      
         for (int i = 1; i < NL+1; i++)
         {
            for (int j = 1; j < NC+1; j++)
            {
               bd[n][i][j] = imgNew[n][i-1][j-1];
            }
         }
         
         return bd;
      
      }
   
   
      private int conv(int x0, int y0, int [][][] mask, int maskDir, int [][][] band, int m,  int tpt)
      {
         int [][] pix = new int [wx][wy];
         int k, l;
      
         tpt = 0;
      
         for (int i = x0 - wx/2; i <= x0 + wx/2; i++)
         {
            for (int j = y0 - wy/2; j <= y0 + wy/2; j++)
            {
               k = i - x0 + wx/2;
               l = j - y0 + wy/2;
               pix[k][l] = band[m][i][j];
            
               tpt = tpt + pix[k][l] * mask[maskDir][k][l];
            }
         }
      
      return tpt;
      }
   
      public int maxGrayScaleOf(int [][][] imag, int n)
      {
         int max = imag[n][0][0];	
         for (int i = 0; i < NL; i++)
         {
            for (int j = 0; j < NC; j++)
            {
               if (max < imag[n][i][j])
                  max = imag[n][i][j];
            }
         }
      		
         return max;
      }
   
      public double maxGrayScaleOf(double [][][] imag, int n)
      {
         double max = imag[n][0][0];	
         for (int i = 0; i < NL; i++)
         {
            for (int j = 0; j < NC; j++)
            {
               if (max < imag[n][i][j])
                  max = imag[n][i][j];
            }
         }
      		
         return max;
      }
   
   
      public void normalize(int [][][] imgOut, int [][][] imgIn)	
      {
         for (int k = 0; k < nberBand; k++)
         {
            int maxOr = maxGrayScaleOf(imgIn, k);
            int maxRs = maxGrayScaleOf(imgOut, k);
         if(maxRs != 0)
         {
            for (int i = 0; i < NL; i++)
            {
               for (int j = 0; j < NC; j++)
               {
                  imgOut[k][i][j] = imgOut[k][i][j] * maxOr/maxRs;
               }
            }
         }
        }
      }
   
   
   	
      public int [][][] convolution(int [][][] mask, int maskDirection, int [][][] imgIn, int [][][] imgOut)
      {
              
         int tpt = 0;
         int [][][] band = new int [nberBand][NL + 2][NC+2];
          
         for (int k = 0; k < nberBand; k++)
         {	
            copyImages(fillTheBordersOf(imgIn, k,  band), band);
         
            
            for (int i = wx/2; i < NL + 1; i++)
            {
               for (int j = wy/2; j < NC + 1; j++)
               {
                  tpt = conv (i, j, mask, maskDirection, band, k,  tpt);
                  imgOut[k][i - 1][j-1] = tpt;
               }
            }
         }

         normalize(imgOut, imgIn);
      	
         return imgOut;
      }
   	
   	
   	
      public int [][][] convertImage(int [] originalBand)
      {
         int [][][] img = new int [nberBand][NL][NC];
      	
         switch(imageFormat)
         {
            case 0:
               {
                  int countband = 0;
                  int line = 0;
                  int column = 0;
                  int j = 0;
               
                  while (line < NL)
                  {
                  
                     for (j = column ; j <= NC-1 + column; j ++)
                     {
                        img[countband][line][j - column] = (int)(originalBand[j]);
                     }
                  
                     if(countband < nberBand - 1)
                     {
                        countband++;
                     }
                     else if (countband ==  nberBand - 1)
                     {
                        countband = 0;
                        line ++;
                     }
                  
                     column = j;
                  }
                  break;
               }
            
            case 1:
               {
               
                  int countband = 0;
                  int j = 0;
                  int line = 0;
                  int column = 0;
               
                  while (line < NL)
                  {
                  
                     for (j = column ; j <= 2*NC -2 + column; j += 2)
                     {
                        img[countband][line][j/2 - column/2] = (int)(originalBand[j] * 256 + originalBand[j+1]);
                     }
                  
                     if(countband < nberBand - 1)
                     {
                        countband++;
                     }
                     else if (countband ==  nberBand - 1)
                     {
                        countband = 0;
                        line ++;
                     }
                  
                     column = j;
                  }
               
                  for (int k = 0; k < nberBand; k++)
                  {	
                     double miu = mean(img, k);
                     double avgSqr = averageSqr(img, k);
                     double sgm = java.lang.Math.sqrt(avgSqr - java.lang.Math.pow(miu,2));
                  
                     for (int i = 0; i < NL; i++)
                     {
                        for (int l = 0; l < NC ; l ++)
                        {
                           if (img[k][i][l] >= 255)
                           {
                              img[k][i][l] = 255;
                           }
                           else
                           {
                              img[k][i][l] = (int)((255 * img[k][i][l])/(miu + 3.0 * sgm));
                           }
                        }
                     }
                  }
               
                  break;
               }
            
            case 2:
               {
                  int countband = 0;
                  int j = 0;
                  int line = 0;
                  int column = 0;
               
                  while (line < NL)
                  {
                  
                     for (j = column ; j <= 2*NC -2 + column; j += 2)
                     {
                        img[countband][line][j/2 - column/2] = Math.min(255, originalBand[j] * 256 + originalBand[j+1]);
                     }
                  
                     if(countband < nberBand - 1)
                     {
                        countband++;
                     }
                     else if (countband ==  nberBand - 1)
                     {
                        countband = 0;
                        line ++;
                     }
                  
                     column = j;
                  }
               
                  break;
               }
         }
      
         return img;
      }
   
   
      public double mean(int [][][] img, int n)
      {
         double avg = 0.0;
         double mn = 0.0;
      
         for (int i = 0; i < NL; i++)
         {
            for (int j = 0; j < NC; j++)
            {
               avg += img[n][i][j] ;
            }
         }
      		
         mn = avg / (NC  * NL);
      		
         return mn;
      }
     
      public double averageSqr(int [][][] img, int n)
      {
         double avg = 0.0;
         double avgs = 0.0;
      
         for (int i = 0; i < NL; i++)
         {
            for (int j = 0; j < NC; j++)
            {
               avg += Math.pow(img[n][i][j], 2);
            }
         }
      		
         avgs = avg/(NC  * NL);
      		
         return avgs;
      
      }
      
        public double [][][] instantiateIm(double[][][] im, boolean ctrl)
  {
       if (ctrl)
       {
                 for (int i =0; i < nberBand    ; i++)
                 {
                    for (int k = 0; k < NL; k++)
                    {   
                        for (int j = 0; j < NC; j++ )
                        { 
                              //System.out.print (imgOut[i][k][j]) ;
                             im[i][k][j] =0;
                          }
                      
                    }
                 }
       }
       else
       {
              for (int i =0; i < nberBand    ; i++)
                 {
                    for (int k = 0; k < NL+2; k++)
                    {   
                        for (int j = 0; j < NC+2; j++ )
                        { 
                              //System.out.print (imgOut[i][k][j]) ;
                             im[i][k][j] = 0;
                          }
                      
                    }
                 }
       }
       
	return im;   
  }
  
      
   public int [][][] instantiateIm(int [][][] im, boolean ctrl)
  {
       if (ctrl)
       {
                 for (int i =0; i < nberBand    ; i++)
                 {
                    for (int k = 0; k < NL; k++)
                    {   
                        for (int j = 0; j < NC; j++ )
                        { 
                              //System.out.print (imgOut[i][k][j]) ;
                             im[i][k][j] =0;
                          }
                      
                    }
                 }
       }
       else
       {
              for (int i =0; i < nberBand    ; i++)
                 {
                    for (int k = 0; k < NL+2; k++)
                    {   
                        for (int j = 0; j < NC+2; j++ )
                        { 
                              //System.out.print (imgOut[i][k][j]) ;
                             im[i][k][j] = 0;
                          }
                      
                    }
                 }
       }
       
	return im;   
  }
  
    public boolean [][][] instantiateIm(boolean [][][] im, boolean ctrl)
  {
       if (ctrl)
       {
                 for (int i =0; i < nberBand    ; i++)
                 {
                    for (int k = 0; k < NL; k++)
                    {   
                        for (int j = 0; j < NC; j++ )
                        { 
                              //System.out.print (imgOut[i][k][j]) ;
                             im[i][k][j] =false;
                          }
                      
                    }
                 }
       }
       else
       {
              for (int i =0; i < nberBand    ; i++)
                 {
                    for (int k = 0; k < NL+2; k++)
                    {   
                        for (int j = 0; j < NC+2; j++ )
                        { 
                              
                             im[i][k][j] = false;
                          }
                      
                    }
                 }
       }
       
	return im;   
  }
  }
   
	
	
