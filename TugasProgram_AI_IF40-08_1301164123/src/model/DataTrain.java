/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Asus
 */
public class DataTrain {
    private String index;
    private double x1;
    private double x2;
    private double x3;
    private double x4;
    private double x5;
    private double y;
    private double kelas;
    private double jarak;
    public DataTrain(){
        
    }
    public DataTrain(String index,double x1,double x2,double x3,double x4,double x5,double y,double kelas,double jarak){
    this.index=index;
    this.x1=x1;
    this.x2=x2;
    this.x3=x3;
    this.x4=x4;
    this.x5=x5;
    this.y=y;
    this.kelas=kelas;
    this.jarak=jarak;
    }
    public void setIndex(String index){
        this.index=index;
    }
    public String getIndex(){
        return index;
    }
    public void setX1(double x1){
        this.x1=x1;
    }
    public double getX1(){
        return x1;
    }
    public void setX2(double x2){
        this.x2=x2;
    }
    public double getX2(){
        return x2;
    }
    public void setX3(double x3){
        this.x3=x3;
    }
    public double getX3(){
        return x3;
    }
    public void setX4(double x4){
        this.x4=x4;
    }
    public double getX4(){
        return x4;
    }
    public void setX5(double x5){
        this.x5=x5;
    }
    public double getX5(){
        return x5;
    }
    public void setY(double y){
        this.y=y;
    }
    public double getY(){
        return y;
    }
     public void setKelas(double kelas){
        this.kelas=kelas;
    }
    public double getKelas(){
        return kelas;
    }
    public void setJarak(double jarak){
        this.jarak=jarak;
    }
    public double getJarak(){
        return jarak;
    }
}
