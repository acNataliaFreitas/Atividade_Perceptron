package Reconhecimento_10_digitos;
/**
 *
 * @author Clarimundo
 */
public class Aprendizagem {
    
    private double x[][]={{1,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1},
                          {1,0,1,0,1,1,0,0,1,0,0,1,0,1,1,1},
                          {1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1},
                          {1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1},
                          {1,1,0,1,1,0,1,1,1,1,0,0,1,0,0,1},
                          {1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,1},
                          {1,1,1,1,1,0,0,1,1,1,1,0,1,1,1,1},
                          {1,1,1,1,0,0,1,0,0,1,0,0,1,0,0,1},
                          {1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1},
                          {1,1,1,1,1,0,1,1,1,1,0,0,1,1,1,1}};
   
    private double w[][]= new double [16][4];
    private double t[][]={{1,1,1,1},
{1,1,1,-1},
{1,1,-1,1},
{1,1,-1,-1},
{1,-1,1,1},
{1,-1,1,-1},
{1,-1,-1,1},
{1,-1,-1,-1},
{-1,1,1,1},
{-1,1,1,-1},
{-1,1,-1,1},
{-1,1,-1,-1},
{-1,-1,1,1},
{-1,-1,1,1},
{-1,-1,1,-1},
{-1,-1,-1,1},
{-1,-1,-1,-1}};      
    private int epocas;
        
    public Aprendizagem(){  
        epocas=0;
    }
    
    public double [][] getw(){
        return w;
    }
    
    public double [][] gett(){
        return t;
    }    
    
    public int getepocas(){
        return epocas;
    }
    
    public double somatorio(int i,int k){
        double yent[][]= new double[10][4];       
                for(int j=0;j<16;j++){
                    yent[i][k] = yent[i][k] + x[i][j]*w[j][k];
                }
            return yent[i][k];
    }

    public double saida(double yent, double limiar){
       double f;
       
          if(yent > limiar)
            f = 1;
        else 
            if(yent < -limiar)
                f = -1;          
            else
                f = 0;
       return f;
    }
    
    public void atualiza(double alfa,double f[][],int k){
                   for(int i=0;i<4;i++)
                    for(int j=0; j<16; j++)
                            w[j][i] = w[j][i] + alfa * (t[k][i]-f[k][i]) * x[k][j];
    }
    
    public void algoritmo(double alfa, double limiar){
       double yent[][] = new double[10][4];
       double f[][] = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},
       {0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
       boolean mudou;       
       
       for(int i=0;i<16;i++)
            for(int j=0;j<4;j++)
               w[i][j]=0;       
       do{
           mudou=false;
          
            for(int i=0;i<10;i++){
              for(int j=0;j<4;j++){
              yent[i][j] = somatorio(i,j);
              f[i][j] = saida(yent[i][j],limiar);
                  if(f[i][j] != t[i][j]){                  
                     mudou=true;
                  }
                  if(mudou){
                  atualiza(alfa,f,i); 
                  epocas++;
                 }
              }
            }
       }while(mudou==true);      
    }   
}
