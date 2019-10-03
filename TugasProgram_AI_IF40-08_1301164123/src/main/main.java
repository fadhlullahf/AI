/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;
import java.io.IOException;
import main.prosesKNN;
/**
 *
 * @author Asus
 */
public class main {
    public static void main(String[] args) throws IOException {
        prosesKNN pro = new prosesKNN();
        pro.ambilDataTrain();
        pro.ambilDataTest();
        pro.prediksiDataTest();
        pro.tampilDataTest();
    }
}
