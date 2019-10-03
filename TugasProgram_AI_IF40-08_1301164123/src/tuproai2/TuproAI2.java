package tuproai2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Farhan Fadhlullah
 */
public class TuproAI2 {
    private int i=0;
    private String[] data=new String[100];
    
    
    double low(double a,double b, double x){
       if (x<a) return 1;
       else if (x<b) return (b-x)/(b-a);
       else return 0;
    }
    double mid(double a,double b, double c,double d,double x){
        if(x<a) return 0;
        else if(x<b) return (x-a)/(b-a);
        else if (x<c) return 1;
        else if (x<d) return (d-x)/(d-c);
        else return 0;
    }
    double high(double a,double b,double x){
        if (x<a) return 0;
        else if (x<b) return (x-a)/(b-a);
        else return 1;
    }
    double considerd(String[] row){
        return Math.max(Math.min(Double.parseDouble(row[5]),Double.parseDouble(row[8])),
               Math.max(Math.min(Double.parseDouble(row[5]),Double.parseDouble(row[7])),
               Math.max(Math.min(Double.parseDouble(row[4]),Double.parseDouble(row[7])),
               Math.min(Double.parseDouble(row[3]),Double.parseDouble(row[6])))));   
    }
    double accepted(String[] row){
        return Math.max(Math.min(Double.parseDouble(row[4]),Double.parseDouble(row[8])),
               Math.max(Math.min(Double.parseDouble(row[3]),Double.parseDouble(row[8])),
               Math.min(Double.parseDouble(row[3]),Double.parseDouble(row[7]))));   
    }
    double rejected(String[] row){
        return Math.max(Math.min(Double.parseDouble(row[5]),Double.parseDouble(row[6])),
               Math.min(Double.parseDouble(row[4]),Double.parseDouble(row[6])));   
    }
    double dfuzy(String row, double[] rej, double[] con, double[] acc){
        String[] data = row.split(",");
        double rejected=Double.parseDouble(data[9]);
        double considered = Double.parseDouble(data[10]);
        double accepted = Double.parseDouble(data[11]);
        if(rejected<=0.25){
            double[] f = {rejected,rejected,rejected,rejected,rejected,rejected,0,0,0,0};
            rej = f;
        }
        else if(rejected<=0.75){
            double[] f = {rejected,rejected,rejected,rejected,rejected,0.25,0,0,0,0};
            rej = f;
        }
        else {
            double[] f ={rejected,rejected,rejected,rejected,0.75,0.25,0,0,0,0};
            rej = f;
        }
        if(considered<=0.25){
            double [] f={0,0,0,0,considered,considered,considered,considered,0,0};
            con=f;
        }
        else if(considered<=0.75){
             double [] f={0,0,0,0,0.25,considered,considered,0.25,0,0};
             con=f;
        }
        else {
            double [] f={0,0,0,0,0.25,0.75,0.75,0.25,0,0};
            con=f;
        }
        if(accepted<=0.25){
           double [] f={0,0,0,0,0,0,accepted,accepted,accepted,accepted};
           acc=f;
        }
        else if(accepted<=0.75){
             double [] f={0,0,0,0,0,0,0.25,accepted,accepted,accepted};
             acc=f;
        }
        else {
           double [] f={0,0,0,0,0,0,0.25,0.75,accepted,accepted};
           acc=f;
        }
        double[] b = {
            Math.max(Math.max(rej[0], con[0]), acc[0]),
            Math.max(Math.max(rej[1], con[1]), acc[1]),
            Math.max(Math.max(rej[2], con[2]), acc[2]),
            Math.max(Math.max(rej[3], con[3]), acc[3]),
            Math.max(Math.max(rej[4], con[4]), acc[4]),
            Math.max(Math.max(rej[5], con[5]), acc[5]),
            Math.max(Math.max(rej[6], con[6]), acc[6]),
            Math.max(Math.max(rej[7], con[7]), acc[7]),
            Math.max(Math.max(rej[8], con[8]), acc[8]),
            Math.max(Math.max(rej[9], con[9]), acc[9]),
        };   
        double atas = (5*b[0])+(15*b[1])+(25*b[2])+(35*b[3])+(45*b[4])+(55*b[5])+(65*b[6])+(75*b[7])+(85*b[8])+(95*b[9]);
        double bawa = b[0]+b[1]+b[2]+b[3]+b[4]+b[5]+b[6]+b[7]+b[8]+b[9];
        return atas/bawa;
    }
    boolean isada(int[] data,int a){
        int i=0;
        while(i<data.length){
            if(data[i]==a) i=20;
            i++;
        }
        return i==20;
    }
    int[] urutkan(String[] data){
        int[] hasil={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=0; i<20; i++){
            double max=0;
            for(int j=0; j<data.length; j++){
                String[] cell=data[j].split(",");
                if(( max < Double.parseDouble(cell[12]) ) && ( isada(hasil, Integer.parseInt(cell[0])) )){
                    hasil[i]=Integer.parseInt(cell[0]);
                    max=Double.parseDouble(cell[12]);
                }
            }
        }
        return hasil;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        double[] rej={1,1,1,1,0.75,0.25,0,0,0,0};
        double[] con={0,0,0,0,0.25,0.75,0.75,0.25,0,0};
        double[] acc={0,0,0,0,0,0,0.25,0.75,1,1};

        double pa=1,pb=1.25,pc=1,pd=1.25,pe=1.5,pf=1.75,pg=1.5,ph=1.75;
        double ha=40,hb=60,hc=50,hd=60,he=70,hf=80,hg=70,hh=90;
        int[] hasil=new int[20];
        TuproAI2 ai = new TuproAI2();
        File a = new File("DataTugas2.csv");
        Scanner p = new Scanner(a);
        p.nextLine();
        while (p.hasNext()){
            ai.data[ai.i]=p.nextLine();
            ai.i++;
        }
        ai.i=0;
        while(ai.i<100){
            ai.data[ai.i]+=", "+ai.low(pa, pb, Double.parseDouble(ai.data[ai.i].split(",")[1]));//3
            ai.data[ai.i]+=", "+ai.mid(pc, pd, pe, pf, Double.parseDouble(ai.data[ai.i].split(",")[1]));//4
            ai.data[ai.i]+=", "+ai.high(pg, ph, Double.parseDouble(ai.data[ai.i].split(",")[1]));//5
            ai.data[ai.i]+=", "+ai.low(ha, hb, Double.parseDouble(ai.data[ai.i].split(",")[2]));//6
            ai.data[ai.i]+=", "+ai.mid(hc, hd, he, hf, Double.parseDouble(ai.data[ai.i].split(",")[2]));//7
            ai.data[ai.i]+=", "+ai.high(hg, hh, Double.parseDouble(ai.data[ai.i].split(",")[2]));//8
            //fuzification
            ai.data[ai.i]+=", "+ai.rejected(ai.data[ai.i].split(","));//9
            ai.data[ai.i]+=", "+ai.considerd(ai.data[ai.i].split(","));//10
            ai.data[ai.i]+=", "+ai.accepted(ai.data[ai.i].split(","));//11
            //inference
            ai.data[ai.i]+=", "+ai.dfuzy(ai.data[ai.i], rej, con, acc);//12
            //defuzi
            ai.i++;
        }
        hasil=ai.urutkan(ai.data);    
        FileWriter filewriter = new FileWriter("TebakanTugas2.csv");
        try{
            for(int i=0; i<hasil.length; i++){
                filewriter.append(String.valueOf(hasil[i]));
                filewriter.append('\n');
            }
        }catch(Exception e){     
        }
        filewriter.flush();
        filewriter.close();
    }  
}
