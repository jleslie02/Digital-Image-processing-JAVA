import java.util.*;

public class Morphology extends ImageFiltering
 {
	public int imgE [][][];
	public int imgD [][][];
	public int imgO [][][];
	public int imgC [][][];
	public int wth  [][][];
	public int bth  [][][];
	public int imgSq [][][];
	public boolean [][][] imaB1, imaB2;
	
 public Morphology(int T1new, int T2new, int NLnew, int NCnew, int ifr, int nberBandnew, int brdrFll,  int q, int dir, int fltChz, int adFlt)
 {
        super(NLnew, NCnew, nberBandnew, ifr, brdrFll, dir, fltChz);
             
	setT1(T1new);
         setT2(T2new);
         setNL(NLnew);
         setNC(NCnew);
         setNB(nberBandnew);
         originalImg = new int [nberBandnew][NLnew][NCnew];
         copyImages(instantiateIm(originalImg,true), originalImg);
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
			
	imgB = new int [nberBandnew][NLnew][NCnew];
        copyImages(instantiateIm(imgB,true), imgB);	
	imgE = new int [nberBandnew][NLnew][NCnew];
        copyImages(instantiateIm(imgE,true), imgE);
   imgO = new int [nberBandnew][NLnew][NCnew];
   copyImages(instantiateIm(imgO,true), imgO);
	imgD = new int [nberBandnew][NLnew][NCnew];
        copyImages(instantiateIm(imgD,true), imgD);
	imgC = new int [nberBandnew][NLnew][NCnew];
       copyImages(instantiateIm(imgC,true), imgC);
	bth = new int [nberBandnew][NLnew][NCnew];
        copyImages(instantiateIm(bth,true), bth);
	wth = new int [nberBandnew][NLnew][NCnew];
        copyImages(instantiateIm(wth,true), wth);
	imgSq = new int [nberBandnew][NLnew][NCnew];
        copyImages(instantiateIm(imgSq,true), imgSq);
 }
 
 public Morphology(int NLnew, int NCnew, int nberBandnew)
 {
    super(NLnew, NCnew, nberBandnew);
 }
 
 public void setImages()
 {
	imgE = new  int [nberBand][NL][NC];
   imgO = new int [nberBand][NL][NC];
	imgD = new  int [nberBand][NL][NC];
	imgC = new int [nberBand][NL][NC];
	bth =  new int [nberBand][NL][NC];
	wth = new int [nberBand][NL][NC];
	imgSq =  new int [nberBand][NL][NC];
}
 
 public void initB1B2(int [][][] img)
 {
 	imaB1 = new boolean [nberBand][NL][NC];
	imaB2 = new boolean [nberBand][NL][NC];
	
	for (int k = 0; k< nberBand; k++)
	{	
		for (int i = 0; i < NL; i++)
		{
			for (int j=0; j<NC; j++)
			{
				if (img[k][i][j] > 0)
				imaB1[k][i][j] = true;
				else
				imaB1[k][i][j] = false;
				
				imaB2[k][i][j] = false;
			}
		}
	}
}


 public int [][][] erode(int [][][] imgBcopy, int ord)
 {
        boolean [][][] band = new boolean [nberBand][NL+2][NC+2];
                copyImages(instantiateIm(band, false), band);
 	
    for( int l = 0; l < ord; l++)
    {
          initB1B2(imgBcopy);
          

      
	for (int k = 0; k < nberBand; k++)
	{	
		fillTheBordersOf(imaB1, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i-1][j-1] && band[k][i-1][j] && band[k][i+1][j] && band[k][i+1][j+1] && band[k][i-1][j+1] && band[k][i+1][j-1] )
				
				imaB2[k][i-1][j-1] = band[k][i][j];
				
				else 
				
				imaB2[k][i-1][j-1] = false;
			}
		}
	}
	
		copyImages(fromBooleanToInt(imaB2, imgE), imgE);
                copyImages(imgE, imgBcopy);
                
    }
      return imgE;
}


 public int [][][] dilate(int [][][] imgBcopy, int ord)
 {
        boolean [][][] band = new boolean [nberBand][NL+2][NC+2];
                copyImages(instantiateIm(band, false), band);
                
        for (int l = 0; l < ord; l++)
        {
                initB1B2(imgBcopy);

	for (int k = 0; k < nberBand; k++)
	{	
            
		
		fillTheBordersOf(imaB1, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i-1][j-1] || band[k][i-1][j] ||  band[k][i+1][j] || band[k][i+1][j+1] || band[k][i-1][j+1] || band[k][i+1][j-1] )
				
				imaB2[k][i-1][j-1] = true;
				
				else 
				
				imaB2[k][i-1][j-1] =  band[k][i][j];
			}
		}
	}
	
	
	copyImages(fromBooleanToInt(imaB2, imgD), imgD);
       
                copyImages(imgD, imgBcopy);
 }
 return imgD;
}


 public int [][][] opening(int[][][] imgBcopy, int order)
 {
 	
		copyImages(dilate(erode(imgBcopy, order), order), imgO);
	return imgO;
 }
 
 

public int [][][] closure(int[][][] imgBcopy,int order)
 {
 	

	copyImages(erode(dilate(imgBcopy, order), order), imgC);
        
        return imgC;
}
	
 
public int [][][] whiteTopHat(int [][][]imgBcopy , int order)
 {
 	closure(imgBcopy, order);
	
 for (int k = 0; k < nberBand; k++)
	{
	 for (int i = 0; i < NL; i++)
		{
			for (int j = 0; j < NC; j++)
			{
				wth[k][i][j] = imgB[k][i][j] - imgO[k][i][j];
			}
		}
	}
 return wth;
 }
 
 
public int [][][] blackTopHat(int [][][] imgBcopy, int order)
 {
 	opening(imgBcopy, order);
	
 for (int k = 0; k < nberBand; k++)
	{
	 for (int i = 0; i < NL; i++)
		{
			for (int j = 0; j < NC; j++)
			{
				bth[k][i][j] = imgB[k][i][j] - imgC[k][i][j];
			}
		}
	}
 return bth;
 }
 
 
 public int [][][] squelettization(int [][][] img)
 {
        boolean [][][] band = new boolean [nberBand][NL+2][NC+2];
                copyImages(instantiateIm(band, false), band);
	initB1B2(img);
	int iter = 0;
	boolean stable = true;
	
	while (stable)
	{
		iter++;
		stable = false;
		
//east mask
	for (int k = 0; k < nberBand; k++)
	{	
		
		
		fillTheBordersOf(imaB1, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i-1][j-1] || band[k][i][j-1] || band[k][i+1][j-1] || ! ( band[k][i][j] && (band[k][i+1][j+1] || band[k][i-1][j+1] || band[k][i][j+1])))
				
				imaB2[k][i-1][j-1] = imaB1[k][i-1][j-1];
				
				else 
				{
					imaB2[k][i-1][j-1] =  false;
					stable = true;
				}
			}
		}
	}
	
//SE mask boolean [][][] band = new boolean [nberBand][NL+2][NC+2];
                copyImages(instantiateIm(band, false), band);
	for (int k = 0; k < nberBand; k++)
	{	
		fillTheBordersOf(imaB2, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i-1][j-1] || band[k][i-1][j] || band[k][i][j-1] || ! ( band[k][i][j] && (band[k][i+1][j+1] || band[k][i+1][j] || band[k][i][j+1])))
				
				imaB1[k][i-1][j-1] = imaB2[k][i-1][j-1];
				
				else 
				{
					imaB1[k][i-1][j-1] =  false;
					stable = true;
				}
			}
		}
	}

	
//S mask 
         
                copyImages(instantiateIm(band, false), band);
	for (int k = 0; k < nberBand; k++)
	{	
		
		fillTheBordersOf(imaB1, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i-1][j-1] || band[k][i-1][j] || band[k][i-1][j+1] || ! (band[k][i][j] && (band[k][i+1][j] || band[k][i+1][j-1] || band[k][i+1][j+1])))
				
				imaB2[k][i-1][j-1] = imaB1[k][i-1][j-1];
				
				else 
				{
					imaB2[k][i-1][j-1] =  false;
					stable = true;
				}
			}
		}
	}
	
// SW mask
                copyImages(instantiateIm(band, false), band);
	for (int k = 0; k < nberBand; k++)
	{	
		
		
		fillTheBordersOf(imaB2, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i-1][j+1] || band[k][i-1][j] || band[k][i][j+1] || ! ( band[k][i][j] && (band[k][i+1][j] || band[k][i+1][j-1] || band[k][i][j-1])))
				
				imaB1[k][i-1][j-1] = imaB2[k][i-1][j-1];
				
				else 
				{
					imaB1[k][i-1][j-1] =  false;
					stable = true;
				}
			}
		}
	}
	
//W mask
        copyImages(instantiateIm(band, false), band);
	for (int k = 0; k < nberBand; k++)
	{	
	
		fillTheBordersOf(imaB1, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i-1][j-1] || band[k][i][j-1] || band[k][i+1][j-1] || ! ( band[k][i][j] && (band[k][i+1][j+1] || band[k][i][j+1] || band[k][i-1][j+1])))
				
				imaB2[k][i-1][j-1] = imaB1[k][i-1][j-1];
				
				else 
				{
					imaB2[k][i-1][j-1] =  false;
					stable = true;
				}
			}
		}
	}
	
// NW mask
        copyImages(instantiateIm(band, false), band);
	for (int k = 0; k < nberBand; k++)
	{	
	
		
		fillTheBordersOf(imaB2, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i+1][j+1] || band[k][i+1][j] || band[k][i][j+1] || ! ( band[k][i][j] && (band[k][i-1][j-1] || band[k][i-1][j] || band[k][i][j-1])))
				
				imaB1[k][i-1][j-1] = imaB2[k][i-1][j-1];
				
				else 
				{
					imaB1[k][i-1][j-1] =  false;
					stable = true;
				}
			}
		}
	}
	
//North mask
        copyImages(instantiateIm(band, false), band);
	for (int k = 0; k < nberBand; k++)
	{	
		
		fillTheBordersOf(imaB1, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i+1][j] || band[k][i+1][j-1] || band[k][i+1][j+1] || ! (band[k][i][j] && (band[k][i-1][j-1] || band[k][i-1][j] || band[k][i-1][j+1])))
				
				imaB2[k][i-1][j-1] = imaB1[k][i-1][j-1];
				
				else 
				{
					imaB2[k][i-1][j-1] =  false;
					stable = true;
				}
			}
		}
	}

//NE mask
        copyImages(instantiateIm(band, false), band);
for (int k = 0; k < nberBand; k++)
	{	
		
		
		fillTheBordersOf(imaB2, k, band);
		
		for (int i = 1; i < NL+1; i++)
		{
			for (int j = 1; j < NC+1; j++)
			{
				if (band[k][i+1][j] || band[k][i+1][j-1] || band[k][i][j-1] || ! ( band[k][i][j] && (band[k][i-1][j+1] || band[k][i-1][j] || band[k][i][j+1])))
				
				imaB1[k][i-1][j-1] = imaB2[k][i-1][j-1];
				
				else 
				{
					imaB1[k][i-1][j-1] =  false;
					stable = true;
				}
			}
		}
	}
	
	}
	
	copyImages(fromBooleanToInt(imaB1, imgSq), imgSq);
        return imgSq;
}



 public int [][][]  getImgE()
 {
 	return imgE;
 }
 
 public int [][][]  getImgD()
 {
 	return imgD;
 }
 
 public int [][][]  getImgO()
 {
 	return imgO;
 }
 
 public int [][][]  getImgC()
 {
 	return imgC;
 }
 
 public int [][][]  getbth()
 {
 	return bth;
 }

public int [][][]  getwth()
 {
 	return wth;
 }

public int [][][]  getImgSq()
 {
 	return imgSq;
 }

}
				
 	