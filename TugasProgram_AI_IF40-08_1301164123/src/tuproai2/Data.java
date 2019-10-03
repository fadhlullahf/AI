/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuproai2;

import java.sql.Array;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Data {
    Data[] dt = new Data[99];
    private int pendapatan;
    private int hutang;
    private int jumlah;
    private String kesimpulan;

    public Data(String kesimpulan, int hutang, int pendapatan, int jumlah) {
        this.pendapatan=pendapatan;
        this.hutang=hutang;
        this.jumlah=jumlah;
        this.kesimpulan=kesimpulan;
    }
    public void isiData(String kesimpulan, int hutang, int pendapatan, int jumlah) {
        this.pendapatan=pendapatan;
        this.hutang=hutang;
        this.jumlah=jumlah;
        this.kesimpulan=kesimpulan;
    }
    public String getKesimpulan() {
        return kesimpulan;
    }

    public int hutang() {
        return hutang;
    }

    public int pendapatan() {
        return pendapatan;
    }

    public int jumlah() {
        return jumlah;
    }

    public void setKesimpulan(String kesimpulan) {
        this.kesimpulan = kesimpulan;
    }

    public void setHutang(int hutang) {
        this.hutang = hutang;
    }

    public void setPendapatan(int pendapatan) {
        this.pendapatan = pendapatan;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

}
