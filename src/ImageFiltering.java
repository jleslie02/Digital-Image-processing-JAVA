import java.util.*;

public class ImageFiltering extends Filters
 {
	public int direction;
	public int filterChosen;
	public int [][][] imageFiltered;
	public int adFilterChosen;
	
	
 public ImageFiltering (int T1new, int T2new, int NLnew, int NCnew, int ifr, int nberBandnew, int brdrFll,  int q, int dir, int fltChz, int adFlt)
      {
         super(T1new, T2new, NLnew, NCnew, ifr, nberBandnew, brdrFll, q );
         originalImg = new int [nberBandnew][NLnew][NCnew];
        
			setDirection(dir);
			setFilterChosen(fltChz);
                        setAdFilterChosen(adFlt);
                        
			 imageFiltered = new int [nberBandnew][NLnew][NCnew];
                        for (int i =0; i <NLnew; i++)
                 {
                    for (int k = 0; k < nberBandnew; k++)
                    {   
                        for (int j = 0; j < NCnew; j++ )
                        { 
                                imageFiltered[k][i][j]=0;        
                          }
                          
                    }
                 }
			
  }
 
 
 public ImageFiltering (int NLnew, int NCnew, int nberBandnew, int ifr, int brdrFll, int dir, int fltChz)
      {
                        setBf(brdrFll);
			setDirection(dir);
			setFilterChosen(fltChz);
			imageFiltered = new int [nberBandnew][NLnew][NCnew];
                        for (int i =0; i <NLnew; i++)
                 {
                    for (int k = 0; k < nberBandnew; k++)
                    {   
                        for (int j = 0; j < NCnew; j++ )
                        { 
                                imageFiltered[k][i][j]=0;        
                          }
                          
                    }
                 }
     }


 
  public ImageFiltering (int NLnew, int NCnew, int nberBandnew)
 {
        imageFiltered = new int [nberBandnew][NLnew][NCnew];
                        for (int i =0; i <NLnew; i++)
                 {
                    for (int k = 0; k < nberBandnew; k++)
                    {   
                        for (int j = 0; j < NCnew; j++ )
                        { 
                                imageFiltered[k][i][j]=0;        
                          }
                          
                    }
                 }
    }
	
  
	public void linearFiltering(int filterChosen)
	{   
		int [][][] imgOut = new int [nberBand][NL][NC];
                 copyImages(instantiateIm(imgOut, true), imgOut);
		
		if (filterChosen == 0)
                {   
     
                    copyImages(convolution(robertFilter, direction, imageFiltered, imgOut), imgOut);
                    
                                copyImages(imgOut, imageFiltered);
                                
                 }
      
                else if (filterChosen == 1)
			{
				copyImages(convolution(SobelFilter, direction, imageFiltered, imgOut), imageFiltered);
				
			}
                else if (filterChosen == 2)
			{
				copyImages(convolution(PrewittFilter, direction, imageFiltered, imgOut), imageFiltered);
				
			}
			else
			{
				copyImages(convolution(KirshFilter, direction,  imageFiltered, imgOut), imageFiltered);
				
			}
		}
	
	
	
	public void AdaptativeFiltering(int adflt)
	{
		int [][][] imAf = new int[nberBand][NL][NC];
                  copyImages(instantiateIm(imAf, true), imAf);
		int [][][] band = new int[nberBand][NL+wx-1][NC+wy-1];
                  copyImages(instantiateIm(band, false), band);
                  
		double [][][] imBf = new double[nberBand][NL][NC];
                  copyImages(instantiateIm(imBf, true), imBf);
                  
		copyImages(imageFiltered, imAf);
		
		switch(adflt)
		{
			case 0:
				{
					for (int k=0; k < nberBand; k++)
					{
						copyImages(fillTheBordersOf(imAf, k, band), band);
						
						for (int i = 1; i < NL+1; i++)
						{
							for(int j = 1; j < NC+1; j++)
							{
								 double localAvg = 0.0;
	 							 double localVar = 0.0;
								 double sdNoise = 0.0;
								 double avgApriori =0.0;
								 double varApriori = 0.0;
								 
								 localAvg = localAverage(i, j, band, k);
								 localVar = localVariance(i, j,  band, k);
								 sdNoise = SD_noise(nberBand, factor);
								 avgApriori = localAvg;
								 varApriori = (Math.pow(localVar, 2) - Math.pow(sdNoise,2))/(Math.pow(sdNoise, 2) + 1);
								 
								 if( varApriori < 0)
								 varApriori = 0;
								 
								 double b = varApriori/((Math.pow(localAvg,2) * Math.pow(sdNoise, 2)) + varApriori);
								 
								 imAf[k][i-1][j-1] =(int) (localAvg + b* (imAf[k][i-1][j-1] - localAvg));
								 
							}
						}
					}
					break;
				}
				
				case 1:
				{
					
					for (int k=0; k < nberBand; k++)
					{
						fillTheBordersOf(imAf, k, band);
						
						for (int i = 1; i < NL + 1; i++)
						{
							for(int j = 1; j < NC + 1; j++)
							{
								 double localAvg = 0.0;
	 							 double localVar = 0.0;
								 double sdNoise = 0.0;
								 double avgApriori =0.0;
								 double varApriori = 0.0;
								 double sdApriori = 0.0;
								 
								 localAvg = localAverage(i, j, band, k);
								 localVar = localVariance(i, j,  band, k);
								 sdNoise = SD_noise(nberBand, factor);
								 avgApriori = localAvg;
								 varApriori = (Math.pow(localVar, 2) - Math.pow(sdNoise,2))/(Math.pow(sdNoise, 2) + 1);
								 
								 if( varApriori < 0)
								 varApriori = 0;
								 
								 if (localAvg != 0)
								 sdApriori = Math.sqrt(localVar)/localAvg;
								 else
								 sdApriori = 1;
								 
								 if (sdApriori < sdNoise)
								 {
								 	imAf[k][i-1][j-1]=(int)(localAvg);
								 }
								 else
								 {
								 	double a = varApriori + Math.pow(sdNoise, 4);
									double b = (imAf[k][i-1][j-1] * (varApriori - Math.pow(sdNoise, 2)) + localAvg * (Math.pow(sdNoise, 2) + Math.pow(sdNoise, 4)))/a;
									
									imAf[k][i-1][j-1] = (int)(b);
								} 
							}
						}
					}
					break;
				}
				case 2:
				{
						
					for (int k=0; k < nberBand; k++)
					{
						fillTheBordersOf(imAf, k, band);
						
						for (int i = 1; i < NL + 1; i++)
						{
							for(int j = 1; j < NC + 1; j++)
							{
								 double localAvg = 0.0;
	 							 double localVar = 0.0;
								 double sdNoise = 0.0;
								 double avgApriori =0.0;
								 double varApriori = 0.0;
								 double sdApriori = 0.0;
								 
								 localAvg = localAverage(i, j, band, k);
								 localVar = localVariance(i, j,  band, k);
								 sdNoise = SD_noise(nberBand, factor);
								 avgApriori = localAvg;
								 varApriori = (Math.pow(localVar, 2) - Math.pow(sdNoise,2))/(Math.pow(sdNoise, 2) + 1.0);
								 
								 if( varApriori < 0.0)
								 varApriori = 0.0;
								 
								 if (localAvg != 0.0)
								 sdApriori = Math.sqrt(localVar)/localAvg;
								 else
								 sdApriori = 0.0;
								 
								 if (sdApriori < sdNoise)
								 {
								 	imAf[k][i-1][j-1]=(int)(localAvg);
								 }
								 else
								 {
								 	double a = varApriori - Math.pow(sdNoise, 2);
									double c = Math.pow(sdNoise, 2) + 1.0;
									double b = (imAf[k][i-1][j-1] * a + localAvg * Math.pow(sdNoise, 2) * c)/( varApriori * c);
									
									imAf[k][i-1][j-1] = (int)(b);
								} 
							}
						}
					}
					break;
				}
				
				case 3:
				{
					for (int k=0; k < nberBand; k++)
					{
						fillTheBordersOf(imAf, k, band);
						
						for (int i = 1; i < NL + 1; i++)
						{
							for(int j = 1; j < NC + 1; j++)
							{
								 double localAvg = 0.0;
	 							 double localVar = 0.0;
								 double sdNoise = 0.0;
								 double avgApriori =0.0;
								 double varApriori = 0.0;
								 double sdApriori = 0.0;
								 
								 localAvg = localAverage(i, j, band, k);
								 localVar = localVariance(i, j,  band, k);
								 sdNoise = SD_noise(nberBand, factor);
								 avgApriori = localAvg;
								 varApriori = (Math.pow(localVar, 2) - Math.pow(sdNoise,2))/(Math.pow(sdNoise, 2) + 1.0);
								 
								 if( varApriori < 0.0)
								 varApriori = 0.0;
								 
								 if (localAvg != 0.0)
								 sdApriori = Math.sqrt(localVar)/localAvg;
								 else
								 sdApriori = 0.0;
								 
								 if (!(Math.abs(varApriori - Math.pow(sdNoise, 2)) <= Math.pow(10,-8)))
								 {
								 	imAf[k][i-1][j-1]=(int)(localAvg);
								 }
								 else
								 {
								 	double a = varApriori - Math.pow(sdNoise, 2);
									double c = Math.pow(sdNoise, 2) + 1.0;
									double b = c/a;
									double t = localAvg * (b - nberBand - 1.0);
									double tt = ( t + Math.sqrt(t))/(2.0 * b);
									
									imAf[k][i-1][j-1] = (int)(tt);
								} 
							}
						}
					}
					break;
				}
				
				case 4:
				{
					for (int k=0; k < nberBand; k++)
					{
						fillTheBordersOf(imAf, k, band);
						
						for (int i = 1; i < NL + 1; i++)
						{
							for(int j = 1; j < NC + 1; j++)
							{
								 double localAvg = 0.0;
	 							 double localVar = 0.0;
								 double sdNoise = 0.0;
								 double varApriori = 0.0;
								 
								 localAvg = localAverage(i, j, band, k);
								 localVar = localVariance(i, j,  band, k);
								 sdNoise = SD_noise(nberBand, factor);
								 varApriori = (Math.pow(localVar, 2) - Math.pow(sdNoise,2))/(Math.pow(sdNoise, 2) + 1.0);
								 
								 if( varApriori < 0.0)
								 varApriori = 0.0;
								 
								 	double a = 4/(nberBand * Math.pow(sdNoise, 2));
									double b = a * localVar/Math.pow(localAvg, 2);
									double tp = 0.0;
									
									for (int l = i - wx/2; l <= i + wx/2; l++)
									{
										for(int m = j - wy/2; m < j + wy/2; m++)
										{
											tp += b* band[k][l][m] * Math.exp( -b * (Math.abs(m - i) + Math.abs(l - j)));
										}
									}
									
									imBf[k][i-1][j-1] = tp;
								} 
							}
						}
						
				 for (int k=0; k < nberBand; k++)
					{
						for (int i = 1; i < NL + 1; i++)
						{
							double mx = maxGrayScaleOf(imBf, k);
							
							for(int j = 1; j < NC + 1; j++)
							{
								imAf[k][i-1][j-1] = (int)(imBf[k][i-1][j-1] * 255 / mx);
							}
						}
					}
					
					break;
				}
			}
				
				copyImages(imAf, imageFiltered);
				
		}		
	
	public void setDirection(int n)
	{
		direction = n;
	}
	
	 public void setImageFiltered()
 {
	imageFiltered = new  int [nberBand][NL][NC];
}

	
	public void setFilterChosen(int fc)
	{
		filterChosen = fc;
	}
	public void setAdFilterChosen(int adflt)
	{
		adFilterChosen = adflt;
	}
	
	public int getDirection()
	{
		return direction;
	}
        
        public int [][][] getImageFiltered()
	{
		return imageFiltered;
	}
	
	public int getFilterChosen()
	{
		return filterChosen;
	}
	
	public int getAdFilterChosen()
	{
		return adFilterChosen;
	}

			
}
				
 	