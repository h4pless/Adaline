package adaline;

import java.util.Random;



public class Neuron {
    
    public double[] weight = new double[4];
    public double threshold;
    public float lr;
    public int edge;
    
    Random rand = new Random();
    
    public Neuron(){
        edge = 0;
        lr = (float)0.01;
        threshold = -1;
        
        for(int i = 0; i < 4 ; i++){
            weight[i] = rand.nextFloat();
        }        
        /*
        weight[0] = 0.25424839183688164;
        weight[1] = 0.45258447563983506;
        weight[2] = 0.7613240961402643;
        weight[3] = -0.22145526243783537;
        threshold = -1.1799999959766865;
*/
    }
    
    public void printWeight(){
        for(int i = 0; i < weight.length ; i++)
            System.out.println("peso["+i+"]:"+weight[i]);
        System.out.println("threshold:  "+threshold);
    }

    
    public double guess(double[] input){
        
        //System.out.println("teste dentro do guess\n"+input[0]+"\n"+input[1]+"\n"+input[2]+"\n"+input[3]+"\n\n");
        
        double aux = 0;
        for(int i = 0; i < input.length-1; i++){
            aux += input[i]*weight[i];
        }
        aux = lr * (aux - threshold); 
        
        return aux;    // ?
    }
    
    
    
}


