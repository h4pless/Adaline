package adaline;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;



public class Adaline {
    
    public static float activationFunc(double u){
        if(u <= 0)
            return (float)-1;
        else
            return (float)1;
    }
        
    public static int training(Neuron n, double[][] sampling){
           
        double eps = 0.0001;
        double u;
        double erro=0;
        double aux2=0;
        int epoca = 0;
        
        ArrayList aux = new ArrayList();
        int erroteste = 0;
        
        int fold[][] = {{0, 5},
                        {6, 11},
                        {12, 17},
                        {18, 23},
                        {24, 29}
                       };
        
        int erroAP = 0;
        do{
            aux2 = erro;
            for(int i = 0 ; i < 30 ; i++){
                double g = n.guess(sampling[i]);
         
                System.out.println("palpite ["+i+"]:    "+ activationFunc(g));
                System.out.println("esperada["+i+"]:   " + sampling[i][4]+ "\n");
                if(activationFunc(g) != sampling[i][4]){    
                    System.out.println("###ERROU###");
                    System.out.println("Pesos Antes:");   
                    for(int f = 0; f < 4; f++){
                        System.out.println("    Pesos: w["+f+"]:    "+n.weight[f]);
                    }
                    System.out.println("Pesos Depois:");  
                    for(int j = 0; j < 4; j++){
                        
                        n.weight[j] = n.weight[j] + n.lr*(sampling[i][4] - g)*sampling[i][j];
                        System.out.println("    Pesos: w["+j+"]:    "+n.weight[j]);
                        erro += Math.pow(sampling[i][4] - g, 2);
                    }
                    System.out.println("threshold antes:  "+n.threshold);
                    System.out.println("threshold depois:  "+n.threshold);  
                    System.out.println("_____________________________________________");
                }            
            }
 
            
            epoca++;
        }while(eps < (erro - aux2));
        
        return epoca;
    }

    public static void main(String[] args){
        
        double[][] sampling = {
            {-1, -0.6508, 0.1097, 4.0009, -1.0000}  //0     0
            ,{-1, -1.4492, 0.8896, 4.4005, -1.0000} //1     0
            ,{-1, 2.0850, 0.6876, 12.0710, -1.0000} //2     0
            ,{-1, 0.2626, 1.1476, 7.7985, 1.0000}   //3     0
            ,{-1, 0.6418, 1.0234, 7.0427, 1.0000}   //4     0
            ,{-1, 0.2569, 0.6730, 8.3265, -1.0000}  //5     0
            ,{-1, 1.1155, 0.6043, 7.4446, 1.0000}   //6     1
            ,{-1, 0.0914, 0.3399, 7.0677, -1.0000}  //7     1
            ,{-1, 0.0121, 0.5256, 4.6316, 1.0000}   //8     1
            ,{-1, -0.0429, 0.4660, 5.4323, 1.0000}  //9     1
            ,{-1, 0.4340, 0.6870, 8.2287, -1.0000}  //10    1
            ,{-1, 0.2735, 1.0287, 7.1934, 1.0000}   //11    1
            ,{-1, 0.4839, 0.4851, 7.4850, -1.0000}  //12    2
            ,{-1, 0.4089, -0.1267, 5.5019, -1.0000} //13    2
            ,{-1, 1.4391, 0.1614, 8.5843, -1.0000}  //14    2
            ,{-1, -0.9115, -0.1973, 2.1962, -1.0000}//15    2
            ,{-1, 0.3654, 1.0475, 7.4858, 1.0000}   //16    2
            ,{-1, 0.2144, 0.7515, 7.1699, 1.0000}   //17    2
            ,{-1, 0.2013, 1.0014, 6.5489, 1.0000}   //18    3
            ,{-1, 0.6483, 0.2183, 5.8991, 1.0000}   //19    3
            ,{-1, -0.1147, 0.2242, 7.2435, -1.0000} //20    3
            ,{-1, -0.7970, 0.8795, 3.8762, 1.0000}  //21    3
            ,{-1, -1.0625, 0.6366, 2.4707, 1.0000}  //22    3
            ,{-1, 0.5307, 0.1285, 5.6883, 1.0000}   //23    3
            ,{-1, -1.2200, 0.7777, 1.7252, 1.0000}  //24    4
            ,{-1, 0.3957, 0.1076, 5.6623, -1.0000}  //25    4
            ,{-1, -0.1013, 0.5989, 7.1812, -1.0000} //26    4
            ,{-1, 2.4482, 0.9455, 11.2095, 1.0000}  //27    4
            ,{-1, 2.0149, 0.6192, 10.9263, -1.0000} //28    4
            ,{-1, 0.2012, 0.2611, 5.4631, 1.0000}   //29    4
        };
        
        
        
        Neuron n1 = new Neuron();
        int epoca;
        double aux;
        int edge = 0;
        
        
        
        epoca = training(n1, sampling);
                   
            
        
        
               
        n1.printWeight();
        System.out.println("EPOCAS:  " + epoca+"\n");
        
        for(int i = 0; i < 30; i++){          
            System.out.println("teste["+i+"]"+activationFunc(n1.guess(sampling[i])));
            System.out.println("teste["+i+"]"+sampling[i][4]);  
            System.out.println();
            if(activationFunc((n1.guess(sampling[i]))) != sampling[i][4])
                System.out.println("FUDEU");
        }
        
        double amostrateste1[] = {-1, 2.7128, 0.8928, 8.9821, 1};
        double amostrateste2[] = {-1, 0.4029, 0.6052, 7.9210, -1};
        
        for(int h = 0; h<100; h++){
            System.out.println(activationFunc(n1.guess(amostrateste1)));
            if(activationFunc(n1.guess(amostrateste1)) != 1)
                System.out.println("FUDEU");
        }         
        System.out.println("\n");
        for(int h = 0; h<100; h++){  
            System.out.println(activationFunc(n1.guess(amostrateste2)));
            if(activationFunc(n1.guess(amostrateste2)) != -1)
                System.out.println("FUDEU");
        }
    }  
}
