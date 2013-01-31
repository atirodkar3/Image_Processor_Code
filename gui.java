/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * gui.java
 *
 * Created on Jun 22, 2011, 1:20:46 PM
 */

/**
 *
 * @author ACER
 */

import java.io.*;
import java.awt.*;
import java.awt.Graphics;
import javax.imageio.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


class pix {
    public static int xray=0;
    static Scanner s=new Scanner(System.in);
   public static int hist[];
   public static BufferedImage image;

public static void xc(int xraa)
    {
   
       xray = 1;
       if(xraa==0)xray=0;
    }


   static int w,h,threshhold;
    public static void histo()
    {
    hist=new int[256];
   int qq;
   for(qq=0;qq<256;qq++)
   {
       hist[qq]=0;
   }
    }

   public static int[][] imagearray;
   public void imageGet() {
   
       try {
   image = ImageIO.read(new File("C:\\Users\\Roslynn\\Desktop\\ba.bmp"));
     createImage(image);
   } catch (IOException e) {
     System.err.println(e.getMessage());
   }
   for(int i=1;i<w*h+1;i++)
   {
        image.setRGB(imagearray[i][1],imagearray[i][0],imagearray[i][2]);
   }
    File fi = new File("C:\\Users\\Roslynn\\Desktop\\middlea.bmp");
            try{
                ImageIO.write(image, "bmp", fi);
                }
            catch(Exception e)
            {System.err.println(e.getMessage());
                }
   }

   public void createImage(BufferedImage image) {
   w = image.getWidth();
   h = image.getHeight();

try{
   imagearray = new int[h*w+1][7];
       }
catch(Exception e)
{System.out.println("FILE TOO LARGE");
       }

   int row=1;
   for (int i = 0; i < h; i++) {
     for (int j = 0; j < w; j++) {

       int pixel = image.getRGB(j, i);
       getpixels(pixel, i, j, row);
       row++;

     }
   
   }
   if(xray==1)
   {
 pix.xra();
       }

  

  
  /* for(int bv=0;bv<256;bv++)
   {
   System.out.println("Histogram Value "+bv+" :: "+hist[bv]);
   }*/
   }

   public void getpixels(int pixel, int i, int j, int row) {
   int alpha = (pixel >> 24) & 0xff;
   int r = (pixel >> 16) & 0xff;
   int g = (pixel >> 8) & 0xff;
   int b = (pixel) & 0xff;
   imagearray[row][0]=i;
   imagearray[row][1]=j;
   imagearray[row][2]=r;
   imagearray[row][3]=g;
   imagearray[row][4]=b;
   imagearray[row][5]=alpha;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
  if(xray==1)
  {
   if(r==0 ||b==0||g==0)
   {
       imagearray[row][2] = 0xFFFFFFFF;
       imagearray[row][3] = 0xFFFFFFFF;
       imagearray[row][4] = 0xFFFFFFFF;
   r=255;
   g=255;
   b=255;
   }
       }
  
  
   int x=(r+g+b)/3;


   



  hist[x]++;

   }

  public static void otsu()
    {

      int totpix=w*h;
     //  System.out.println("Size::"+totpix);
       double maxbcv=0.0;
       threshhold=0;
for(int co=0;co<256;co++)
                {
    double wb=0;
    double ub=0;
    double wf=0;
    double uf=0;
    for(int bco=co;bco>=0;bco--)
    {wb+=hist[bco];
    ub+=bco*hist[bco];
    }

     ub=ub/wb;
    wb=wb/totpix;

    for(int fco=co+1;fco<256;fco++)
    {wf+=hist[fco];
    uf+=fco*hist[fco];
    }

    uf=uf/wf;
    wf=wf/totpix;

    double bcv=wb*wf*(ub-uf)*(ub-uf);
    if(bcv>maxbcv)
    {maxbcv=bcv;
     threshhold=co;
    }


    }
  /*    System.out.println("Maximum Between Class Variance:: "+maxbcv+" Threshold:: "+threshhold);
               int total=w*h;

		float lowtot = 0;
		for (int t=0;t<256;t++)
                {
                    lowtot += t * hist[t];
        }
		float bsum=0;
		int wB=0;
		int wF=0;

		float Maxbtv=0;
		threshhold=0;

		for (int co=0 ; co<256 ; co++)
		{
			wB += hist[co];
			if (wB == 0) continue;

			wF = total - wB;
			if (wF == 0) break;

			bsum+= (float)(co * hist[co]);

			float uB = bsum / wB;
			float uF = (lowtot - bsum) / wF;


			float bv = (float)wB*(float)wF*(uB - uF)*(uB-uF);


			if (bv>Maxbtv)
                       {
				Maxbtv=bv;
				threshhold = co;
			}
		}
*/
    }

  public static void xra()
    {
      
       double image_width = image.getWidth();
            double image_height = image.getHeight();


           BufferedImage bimage = new BufferedImage((int)image_width, (int)image_height,
                  BufferedImage.TYPE_INT_RGB );

          int rgb = 0xFF00FF00;
            for(int i=0;i<(w*h+1);i++)
            { // System.out.println("X:: "+finale[i][0]+" Y:: "+finale[i][1]);


                      bimage.setRGB(imagearray[i][1],imagearray[i][0],imagearray[i][2]);
        }
      File fi = new File("C:\\Users\\Roslynn\\Desktop\\middle.bmp");
            try{
                ImageIO.write(bimage, "bmp", fi);
                }
            catch(Exception e)
            {System.err.println(e.getMessage());
                }

     }


   public static void ave()
    {
       for(int i=1;i<(w*h+1);i++)
            {if(imagearray[i][0]==0&&imagearray[i][1]==0&&imagearray[i][2]==0&&imagearray[i][3]==0&&imagearray[i][4]==0&&imagearray[i][5]==0)
                    {i++;
                }

           int re=imagearray[i][2];
            int gr=imagearray[i][3];
             int bl=imagearray[i][4];

             double av=(re+gr+bl)/3;

             if(av>threshhold)
             {imagearray[i][2]= 0xFFFFFFFF;
              imagearray[i][3]= 0xFFFFFFFF;
              imagearray[i][4]= 0xFFFFFFFF;
              }
             if(av<=threshhold)
             {imagearray[i][2]= 0xFF000000;
              imagearray[i][3]= 0xFF000000;
              imagearray[i][4]= 0xFF000000;
              }
           }

            

   /*       for(int i=0;i<(w*h+1);i++)
            {
            System.out.println("Correct");
            for(int y=0;y<7;y++)
             {
             System.out.print("*"+imagearray[i][y]+"*");
             System.out.print("*"+finale[i][y]+"*");

             System.out.println();
                }
        }*/
     //  0124 414 7700

                             //saving black and white image onto drive
    // System.out.println("Param w h "+w+"fasfgd"+h);

        
    }
   
     public static void contrast()
    {
       for(int i=1;i<(w*h+1);i++)
            {if(imagearray[i][0]==0&&imagearray[i][1]==0&&imagearray[i][2]==0&&imagearray[i][3]==0&&imagearray[i][4]==0&&imagearray[i][5]==0)
                    {i++;
                }

           int re=imagearray[i][2];
            int gr=imagearray[i][3];
             int bl=imagearray[i][4];

             double av=(re+gr+bl)/3;

             if(av>threshhold)
             {if(imagearray[i][2]*1.2<255)
             {
                 imagearray[i][2]=(int)(imagearray[i][2]*1.2);
             }
             else
             {
                 imagearray[i][2]=255;
             }
             
             if(imagearray[i][3]*1.2<255)
             {    imagearray[i][3]=(int)(imagearray[i][3]*1.2);
             }
             else
             {
                 imagearray[i][3]=255;
             }
             
             if(imagearray[i][4]*1.2<255)
             {    imagearray[i][4]=(int)(imagearray[i][4]*1.2);
             }
             else
             {
                 imagearray[i][4]=255;
             }
             
              }
             
             if(av<threshhold)
             {if(imagearray[i][2]/1.2>0)
             {
                 imagearray[i][2]=(int)(imagearray[i][2]/1.2);
             }
             else
             {
                 imagearray[i][2]=0;
             }
             if(imagearray[i][3]/1.2>0)
             { imagearray[i][3]=(int)(imagearray[i][3]/1.2);
             }
             else
             {
                 imagearray[i][3]=0;
             }
             if(imagearray[i][4]/1.2>0)
             {imagearray[i][4]=(int)(imagearray[i][4]/1.2);
             }
             else
             {
                 imagearray[i][4]=0;
             }
             
              }
           }
    }
   public static String finpix(String filen,String dest)
   {

       double image_width = image.getWidth();
            double image_height = image.getHeight();


            image = new BufferedImage((int)image_width, (int)image_height,
                    BufferedImage.TYPE_INT_RGB);

            int rgb = 0xFF00FF00;
            for(int i=0;i<(w*h+1);i++)
            { // System.out.println("X:: "+finale[i][0]+" Y:: "+finale[i][1]);


                      image.setRGB(imagearray[i][1],imagearray[i][0],(imagearray[i][5]<<24|imagearray[i][4]|imagearray[i][3]<<8|imagearray[i][2]<<16));
            }
 String temp = "otsupic.jpg";
// System.out.println("Specify Output File Name");
 temp=filen;

 temp=temp+".bmp";
 if(temp.equals(".bmp"))
 {
     temp="otsupic.bmp";
 }
            File fi = new File("C:\\Users\\Roslynn\\Desktop\\" + temp);
            try{
                ImageIO.write(image, "bmp", fi);
                }
            catch(Exception e)
            {System.err.println(e.getMessage());
                }
            String retstr="C:\\Users\\Roslynn\\Desktop\\"+temp;
               imagearray=new int[0][0];
           return retstr;
            }
   
    public static String finpix1(String filen,String dest)
   {

       double image_width = image.getWidth();
            double image_height = image.getHeight();


            image = new BufferedImage((int)image_width, (int)image_height,
                    BufferedImage.TYPE_INT_RGB);

            int rgb = 0xFF00FF00;
            for(int i=0;i<(w*h+1);i++)
            { // System.out.println("X:: "+finale[i][0]+" Y:: "+finale[i][1]);


                      image.setRGB(imagearray[i][1],imagearray[i][0],imagearray[i][2]);
            }
 String temp = "otsupic.jpg";
// System.out.println("Specify Output File Name");
 temp=filen;

 temp=temp+".bmp";
 if(temp.equals(".bmp"))
 {
     temp="otsupic.bmp";
 }
            File fi = new File("C:\\Users\\Roslynn\\Desktop\\" + temp);
            try{
                ImageIO.write(image, "bmp", fi);
                }
            catch(Exception e)
            {System.err.println(e.getMessage());
                }
            String retstr="C:\\Users\\Roslynn\\Desktop\\"+temp;
               imagearray=new int[0][0];
           return retstr;
            }
   
   public static void edge()
   {
       for(int i=1;i<(w*h+1);i++)
            {
              if(i<=w && (i!=1 || i!=w))
                {if(imagearray[i][2]==0xFF000000 &&( imagearray[i+w][2]!=0xFF000000 || imagearray[i+w+1][2]!=0xFF000000 || imagearray[i+w-1][2]!=0xFF000000))
                       {imagearray[i][2]=0xFF000000;}
                 else{imagearray[i][2]=0xFFFFFFFF;
                    }
                 }

                 if(i%w==0 && (i!=w && i!=w*h))
                {if(imagearray[i][2]==0xFF000000 &&( imagearray[i-1][2]!=0xFF000000 || imagearray[i+w][2]!=0xFF000000 || imagearray[i-w][2]!=0xFF000000 || imagearray[i-w-1][2]!=0xFF000000 || imagearray[i+w-1][2]!=0xFF000000))
                       {imagearray[i][2]=0xFF000000;}
                 else{imagearray[i][2]=0xFFFFFFFF;
                    }
                 }

                if(i>(w*h-w)&& (i!=w*h-w+1 || i!=w*h))
             {if(imagearray[i][2]==0xFF000000 &&( imagearray[i-w][2]!=0xFF000000 || imagearray[i-w+1][2]!=0xFF000000 || imagearray[i-w-1][2]!=0xFF000000))
                       {imagearray[i][2]=0xFF000000;}
                 else{imagearray[i][2]=0xFFFFFFFF;
                    }
                }
                
               

                if(i==1)
                {if(imagearray[i][2]==0xFF000000 &&(imagearray[i+1][2]!=0xFF000000 ||imagearray[i+w][2]!=0xFF000000||imagearray[i+w+1][2]!=0xFF000000))
                         {imagearray[i][2]=0xFF000000;}
                 else{imagearray[i][2]=0xFFFFFFFF;
                    }
                }
                 
                if(i==w)
                {if(imagearray[i][2]==0xFF000000 &&(imagearray[i-1][2]!=0xFF000000 ||imagearray[i+w][2]!=0xFF000000||imagearray[i+w-1][2]!=0xFF000000))
                         {imagearray[i][2]=0xFF000000;}
                 else{imagearray[i][2]=0xFFFFFFFF;
                    }
                }

                if(i==(w*h-w+1))
                {if(imagearray[i][2]==0xFF000000 &&(imagearray[i+1][2]!=0xFF000000 ||imagearray[i-w][2]!=0xFF000000||imagearray[i-w+1][2]!=0xFF000000))
                         {imagearray[i][2]=0xFF000000;}
                 else{imagearray[i][2]=0xFFFFFFFF;
                    }
                }

                if(i==w*h)
                {if(imagearray[i][2]==0xFF000000 &&(imagearray[i-1][2]!=0xFF000000||imagearray[i-w][2]!=0xFF000000||imagearray[i-w+1][2]!=0xFF000000))
                         {imagearray[i][2]=0xFF000000;}
                 else{imagearray[i][2]=0xFFFFFFFF;
                    }
                }

               
              if(i>w && i%w!=0 && i<(w*h-w)&& (i-1)%(w)!=0 && i!=1 && i!=w && i!=w*h && i!=w*h-w+1)
              {
                   if(imagearray[i][2]==0xFF000000 && (imagearray[i-1][2]==0xFFFFFFFF ||imagearray[i-w][2]==0xFFFFFFFF ||imagearray[i-w+1][2]==0xFFFFFFFF ||imagearray[i-w-1][2]==0xFFFFFFFF||imagearray[i+w][2]==0xFFFFFFFF||imagearray[i+w+1][2]==0xFFFFFFFF || imagearray[i+w-1][2]==0xFFFFFFFF || imagearray[i+1][2]==0xFFFFFFFF)
                           )
                   {
                         imagearray[i][2]=0xFF000000;}
                 else{
                       int k[]=new int[8];
                       k[0]=imagearray[i-w-1][2];
                       k[1]=imagearray[i-w][2];
                       k[2]=imagearray[i-w+1][2];
                       k[3]=imagearray[i+w-1][2];
                       k[4]=imagearray[i+w+1][2];
                       k[5]=imagearray[i+w][2];
                       k[6]=imagearray[i-1][2];
                       k[7]=imagearray[i+1][2];
                       
                       int bcount=0;
                       int gcount=0;
                       int wcount=0;
                       for(int y=0;y<8;y++)
                       {if(k[y]==0xFF000000)
                               {bcount++;

                           }
                       if(k[y]==0xFFFFFF00)
                       {gcount++;
                           }
                        if(k[y]==0xFFFFFFFF)
                       {wcount++;
                           }
                       }

                       if(bcount<=3 && gcount!=0)
                       {imagearray[i][2]=0xFFFFFFFF;
                       }
                        else
                            if(wcount<=gcount)
                        {
                       imagearray[i][2]=0xFFFFFF00;
                       }
                    }
                    }


              if((i-1)%(w)==0 &&(i!=1 && i!=(w*h)-w+1))
                {if(imagearray[i][2]==0xFF000000 &&( imagearray[i+1][2]!=0xFF000000 || imagearray[i-w][2]!=0xFF000000 || imagearray[i-w+1][2]!=0xFF000000 || imagearray[i+w+1][2]!=0xFF000000 || imagearray[i+w][2]!=0xFF000000))
                       {imagearray[i][2]=0xFF000000;}
                 else{imagearray[i][2]=0xFFFFFFFF;
                    }
                }
        }
      for(int i=1;i<(w*h+1);i++)
      {    int gcount=0;
           int bcount=0;
           if((i>w && i%w!=0 && i<(w*h-w)&& (i-1)%(w)!=0 && i!=1 && i!=w && i!=w*h && i!=w*h-w+1))
            {
           int k[]=new int[8];
                       k[0]=imagearray[i-w-1][2];
                       k[1]=imagearray[i-w][2];
                       k[2]=imagearray[i-w+1][2];
                       k[3]=imagearray[i+w-1][2];
                       k[4]=imagearray[i+w+1][2];
                       k[5]=imagearray[i+w][2];
                       k[6]=imagearray[i-1][2];
                       k[7]=imagearray[i+1][2];

                       bcount=0;
                       gcount=0;
                       for(int y=0;y<8;y++)
                       {if(k[y]==0xFF000000)
                               {bcount++;

                           }
                        if(k[y]==0xFFFFFF00)
                        {gcount++;
                           }
                        }
          }
            if(gcount==2&&bcount==4)
            {imagearray[i][2]=0xFF000000;
        }
             if(imagearray[i][2]==0xFFFFFF00)
             {imagearray[i][2]=0xFFFFFFFF;}
         }
   }

   public static void soften()
   {
   for(int i=1;i<(w*h+1);i++)
   {
        if((i>w && i%w!=0 && i<(w*h-w)&& (i-1)%(w)!=0 && i!=1 && i!=w && i!=w*h && i!=w*h-w+1))
        {int cou=0;
        int cou1=0;
        int cou2=0;
        int cou3=0;
      //  imagearray[i][5]<<24|imagearray[i][4]|imagearray[i][3]<<8|imagearray[i][2]<<16)
        
                cou3+=imagearray[i-w-1][5];
                       cou3+=imagearray[i-w][5];
                       cou3+=imagearray[i-w+1][5];
                       cou3+=imagearray[i+w-1][5];
                       cou3+=imagearray[i+w+1][5];
                       cou3+=imagearray[i+w][5];
                       cou3+=imagearray[i-1][5];
                       cou3+=imagearray[i+1][5];
                       cou3+=imagearray[i][5];
                       cou3/=9;
                       imagearray[i][5]=cou3>>24;
             cou+=imagearray[i-w-1][2];
                       cou+=imagearray[i-w][2];
                       cou+=imagearray[i-w+1][2];
                       cou+=imagearray[i+w-1][2];
                       cou+=imagearray[i+w+1][2];
                       cou+=imagearray[i+w][2];
                       cou+=imagearray[i-1][2];
                       cou+=imagearray[i+1][2];
                       cou+=imagearray[i][2];
                       cou/=9;
                       imagearray[i][2]=cou;
                cou1+=imagearray[i-w-1][3];
                       cou1+=imagearray[i-w][3];
                       cou1+=imagearray[i-w+1][3];
                       cou1+=imagearray[i+w-1][3];
                       cou1+=imagearray[i+w+1][3];
                       cou1+=imagearray[i+w][3];
                       cou1+=imagearray[i-1][3];
                       cou1+=imagearray[i+1][3];
                       cou1+=imagearray[i][3];
                       cou1/=9;
                       imagearray[i][3]=cou1;
             cou2+=imagearray[i-w-1][4];
                       cou2+=imagearray[i-w][4];
                       cou2+=imagearray[i-w+1][4];
                       cou2+=imagearray[i+w-1][4];
                       cou2+=imagearray[i+w+1][4];
                       cou2+=imagearray[i+w][4];
                       cou2+=imagearray[i-1][4];
                       cou2+=imagearray[i+1][4];
                       cou2+=imagearray[i][4];
                       cou2/=9;
                       imagearray[i][4]=cou2;
                 
                       
        }
        }
   }
   
    public static void sharpen()
   {
   for(int i=1;i<(w*h+1);i++)
   {
        if((i>w && i%w!=0 && i<(w*h-w)&& (i-1)%(w)!=0 && i!=1 && i!=w && i!=w*h && i!=w*h-w+1))
        {int cou=0;
        int cou1=0;
        int cou2=0;
        int cou3=0;
      //  imagearray[i][5]<<24|imagearray[i][4]|imagearray[i][3]<<8|imagearray[i][2]<<16)
        
                cou3+=imagearray[i-w-1][5];
                       cou3+=imagearray[i-w][5];
                       cou3+=imagearray[i-w+1][5];
                       cou3+=imagearray[i+w-1][5];
                       cou3+=imagearray[i+w+1][5];
                       cou3+=imagearray[i+w][5];
                       cou3+=imagearray[i-1][5];
                       cou3+=imagearray[i+1][5];
                       
                       cou3>>=3;
                       
                       
                    
                           if((2*imagearray[i][5]-cou3)>=255)
                       {imagearray[i][5]=255;
                       }
                       else
                               if((2*imagearray[i][5]-cou3<=0))
                               {
                                   imagearray[i][5]=0;
                               }
                             else
                                   {
                       imagearray[i][5]+=(imagearray[i][5]-cou3)>>24;
                       }
        
                       
                       
                       cou+=imagearray[i-w-1][2];
                       cou+=imagearray[i-w][2];
                       cou+=imagearray[i-w+1][2];
                       cou+=imagearray[i+w-1][2];
                       cou+=imagearray[i+w+1][2];
                       cou+=imagearray[i+w][2];
                       cou+=imagearray[i-1][2];
                       cou+=imagearray[i+1][2];
                       
                       cou>>=3;
                      // if((2*imagearray[i][2]-cou)<255 && (2*imagearray[i][2]-cou)>0)
                       
                      
                           if((2*imagearray[i][2]-cou)>=255)
                       {imagearray[i][2]=255;
                       }
                       else
                               if((2*imagearray[i][2]-cou<=0))
                               {
                                   imagearray[i][2]=0;
                               }
                           else
                                   {
                       imagearray[i][2]+=(imagearray[i][2]-cou);
                       }
                           
                       cou1+=imagearray[i-w-1][3];
                       cou1+=imagearray[i-w][3];
                       cou1+=imagearray[i-w+1][3];
                       cou1+=imagearray[i+w-1][3];
                       cou1+=imagearray[i+w+1][3];
                       cou1+=imagearray[i+w][3];
                       cou1+=imagearray[i-1][3];
                       cou1+=imagearray[i+1][3];
                       
                       cou1>>=3;
                     //  if((2*imagearray[i][3]-cou1)<255 && (2*imagearray[i][3]-cou1)>0)
                      
                      
                           if((2*imagearray[i][3]-cou1)>=255)
                       {imagearray[i][3]=255;
                       }
                       else
                               if((2*imagearray[i][3]-cou1<=0))
                               {
                                   imagearray[i][3]=0;
                               }
                           else
                                    {
                       imagearray[i][3]+=(imagearray[i][3]-cou1);
                       }
                       
                       
                        cou2+=imagearray[i-w-1][4];
                       cou2+=imagearray[i-w][4];
                       cou2+=imagearray[i-w+1][4];
                       cou2+=imagearray[i+w-1][4];
                       cou2+=imagearray[i+w+1][4];
                       cou2+=imagearray[i+w][4];
                       cou2+=imagearray[i-1][4];
                       cou2+=imagearray[i+1][4];
                       
                       cou2>>=3;
                       //if((2*imagearray[i][4]-cou2)<255 && (2*imagearray[i][4]-cou2)>0)
                       
                     
                           if((2*imagearray[i][4]-cou2)>=255)
                       {imagearray[i][4]=255;
                       }
                       else
                               if((2*imagearray[i][4]-cou2<=0))
                               {
                                   imagearray[i][4]=0;
                               }
                           else
                                   {
                       imagearray[i][4]+=(imagearray[i][4]-cou2);
                       }
                       
        }
        }
   }
public static void imout()
    {for(int i=0;i<(w*h+1);i++)
            {System.out.println("Image Array Row:: NO"+i+"VALUES:: "+imagearray[i][0]+"***"+imagearray[i][1]+"***"+imagearray[i][2]+"***"+imagearray[i][3]+"***"+imagearray[i][4]+"***"+imagearray[i][5]+"***"+imagearray[i][6]);
}

    }
}
public class gui extends javax.swing.JFrame {
    
    public gui() {

        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        source = new javax.swing.JTextField();
        may = new javax.swing.JLabel();
        dest = new javax.swing.JTextField();
        Convert = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        filen = new javax.swing.JTextField();
        xray = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Otsu");

        jLabel1.setText("Source Path:: Max Size:: 2400*1900");

        may.setText("Destination Path");

        dest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destActionPerformed(evt);
            }
        });

        Convert.setText("Convert");
        Convert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConvertActionPerformed(evt);
            }
        });

        jLabel3.setText("File Name");

        filen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filenActionPerformed(evt);
            }
        });

        xray.setText("X-Ray");
        xray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xrayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(Convert)
                        .addGap(160, 160, 160))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filen, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(may)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                                        .addComponent(xray))
                                    .addComponent(source, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(source, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(xray))
                .addGap(18, 18, 18)
                .addComponent(filen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(Convert)
                .addGap(9, 9, 9)
                .addComponent(may, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void destActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destActionPerformed

    private void ConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConvertActionPerformed
        // TODO add your handling code here:
        try {
           /* String stringl="";
            System.out.println("Enter String");
            stringl=s.next();*/

            //colored image path

            String strings=source.getText().toString();

            strings.replace( "/","//" );
            BufferedImage imager = ImageIO.read(new File(strings));
 //getting width and height of imag
            double image_width = imager.getWidth();
            double image_height = imager.getHeight();

            BufferedImage bimg = null;
            BufferedImage img = imager;
            bimg = new BufferedImage((int)image_width, (int)image_height,BufferedImage.TYPE_BYTE_GRAY);
            //bimg = new BufferedImage((int)image_width, (int)image_height,BufferedImage.TYPE_INT_RGB);
            Graphics2D gg = bimg.createGraphics();
        gg.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);

                             //saving black and white image onto drive
            String temp = "ba.bmp";
            File fi = new File("C:\\Users\\Roslynn\\Desktop\\" + temp);
            ImageIO.write(bimg, "bmp", fi);
        }
        catch (Exception e)
        {                             System.out.println(e);
        }



        String desty="C:\\Users\\Roslynn\\Desktop\\otsupic.jpg";
       pix.histo();

       pix img=new pix();
       //XXXXXX
     int xraa=0;
         if(xray.isSelected())
      {xraa=1;
       pix.xc(xraa);
      img.imageGet();
       pix.otsu();
       pix.ave();
      pix.edge();
       
       String fille=filen.getText().toString();
       String des=dest.getText().toString();
    
     desty=pix.finpix1(fille,des);
     dest.setText(desty);
    
      }
         else
         {
       img.imageGet();
       pix.otsu();
       pix.ave();
       pix.edge();
       
       String fille=filen.getText().toString();
       String des=dest.getText().toString();
    
     desty=pix.finpix1(fille,des);
    dest.setText(desty);
         }
     
     //SOFTEN
     
  //   img.imageGet();
    //  pix.soften(); 
      //pix.otsu();
       //pix.contrast();
    // pix.sharpen();
   //  pix.otsu();
     //  pix.ave();
       //pix.edge();
       
   //    String fille=filen.getText().toString();
    //   String des=dest.getText().toString();
    
   //  desty=pix.finpix(fille,des);
   // dest.setText(desty);
  
    //SOFTEN
    
    }//GEN-LAST:event_ConvertActionPerformed

    private void filenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filenActionPerformed

    private void xrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xrayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xrayActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Convert;
    public static javax.swing.JTextField dest;
    public static javax.swing.JTextField filen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel may;
    public static javax.swing.JTextField source;
    private javax.swing.JCheckBox xray;
    // End of variables declaration//GEN-END:variables

}
