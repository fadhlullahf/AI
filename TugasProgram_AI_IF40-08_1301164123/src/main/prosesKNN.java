/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import model.DataTest;
import model.DataTrain;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author Farhan
 */

public class prosesKNN {
    ArrayList<DataTrain> train = new ArrayList<DataTrain>();
    ArrayList<DataTest> test = new ArrayList<DataTest>();
    
    public double hitungJarakTest(DataTrain dtra, DataTest dtes) {
        return Math.sqrt(
                Math.pow((dtra.getX1() - dtes.getX1()), 2)
                + Math.pow((dtra.getX2() - dtes.getX2()), 2)
                + Math.pow((dtra.getX3() - dtes.getX3()), 2)
                + Math.pow((dtra.getX4() - dtes.getX4()), 2)
                + Math.pow((dtra.getX5() - dtes.getX5()), 2)
        );
    };
    public void prediksiDataTest() {
        ArrayList<DataTrain> jarakDataTest = new ArrayList<>();
        for (int i = 0; i < test.size(); i++) {
            jarakDataTest = new ArrayList<>();
            for (int j = 0; j < train.size(); j++) {
                double jarak = hitungJarakTest(train.get(j), test.get(i));
                DataTrain data = new DataTrain();
                data = train.get(j);
                data.setJarak(jarak);
                jarakDataTest.add(data);
            }
            Collections.sort(jarakDataTest, new Comparator<DataTrain>() {
                @Override
                public int compare(DataTrain o1, DataTrain o2) {
                    return Double.compare(o1.getJarak(), o2.getJarak());
                }
            });
            int k = 5;
            int increment0 = 0;
            int increment1 = 0;
            int increment2 = 0;
            int increment3 = 0;
            
            for (int l = 0; l < k; l++) {
                if (jarakDataTest.get(l).getY() == 0) {
                    increment0++;
                }else if (jarakDataTest.get(l).getY() == 1) {
                    increment1++;
                }else if (jarakDataTest.get(l).getY() == 2) {
                    increment2++;
                }else if(jarakDataTest.get(1).getY() ==3){
                    increment3++;
                }           
            }          
            if(increment0>=increment1 && increment0>= increment2 && increment0>= increment3){
                DataTest datatest = test.get(i);
                datatest.setY(0);
                test.set(i,datatest);
            }else if(increment1>= increment0 && increment1>= increment2 && increment1>= increment3){
                DataTest datatest = test.get(i);
                datatest.setY(1);
                test.set(i,datatest);
            }else if(increment2>= increment0 && increment2>= increment1 && increment2>= increment3){
                DataTest datatest = test.get(i);
                datatest.setY(2);
                test.set(i,datatest);
            }else if(increment3>= increment0 && increment3>= increment1 && increment3>= increment2){
                DataTest datatest = test.get(i);
                datatest.setY(3);
                test.set(i,datatest);
            }
        }
    };
     public ArrayList<String[]> ambilRecord(String path) {
        String[] arrayRecord = null;
        ArrayList<String[]> dataArray = new ArrayList<>();
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(new File(path)));
            String kata;
            dis.readLine();
            while ((kata = dis.readLine()) != null) {
                //untuk menghapus tab pada file txt
                arrayRecord = kata.split(",");
                dataArray.add(arrayRecord);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return dataArray;
    }
    public void ambilDataTrain() {
        ArrayList<String[]> txt = ambilRecord("DataTrain.csv");
        for (int i = 0; i < txt.size(); i++) {
            //untuk menyimpan nilai dari data set train
            String index_tr =String.valueOf(txt.get(i)[0]);
            double x1_tr = Double.parseDouble(txt.get(i)[1]);
            double x2_tr = Double.parseDouble(txt.get(i)[2]);
            double x3_tr = Double.parseDouble(txt.get(i)[3]);
            double x4_tr = Double.parseDouble(txt.get(i)[4]);
            double x5_tr = Double.parseDouble(txt.get(i)[5]);
            double y_tr = Double.parseDouble(txt.get(i)[6]);
            train.add(new DataTrain(index_tr,x1_tr,x2_tr,x3_tr,x4_tr,x5_tr,y_tr, 0, 0));
        }
    }
    public void ambilDataTest() {
        ArrayList<String[]> txt = ambilRecord("DataTest.csv");
        for (int i = 0; i < txt.size(); i++) {
            //untuk menyimpan nilai dari data set test
            String index_ts =String.valueOf(txt.get(i)[0]);
            double x1_ts = Double.parseDouble(txt.get(i)[1]);
            double x2_ts = Double.parseDouble(txt.get(i)[2]);
            double x3_ts = Double.parseDouble(txt.get(i)[3]);
            double x4_ts = Double.parseDouble(txt.get(i)[4]);
            double x5_ts = Double.parseDouble(txt.get(i)[5]);
            double y_ts  = 0;//Integer.valueOf(txt.get(i)[6]);
            test.add(new DataTest(index_ts, x1_ts, x2_ts, x3_ts, x4_ts, x5_ts));
        }
    }
    public void tampilDataTest() throws IOException {
        FileWriter filewriter = new FileWriter("TebakanTugas3.csv");
        for (DataTest dataTest : test) {
            System.out.println(dataTest.getIndex() + "\t"
                    + dataTest.getX1() + "\t"
                    + dataTest.getX2() + "\t"
                    + dataTest.getX3() + "\t"
                    + dataTest.getX4() + "\t"
                    + dataTest.getX5() + "\t"
                    + dataTest.getY()
            );
            try{
                filewriter.append(String.valueOf(dataTest.getY()));
                filewriter.append('\n');
            }catch(Exception e){     
            }
        }
        filewriter.flush();
        filewriter.close();
    }
}
