/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.evidencija_robe.data;
import java.io.Serializable;

public class Proizvod implements Serializable{
    private int proizvod_id = -1;
    private Prostor prostor;
    private String naziv;
    private String tip;
    private String tezina;
    private int kolicina;
    private String napomena;

    public Proizvod() {
    }

    public Proizvod(int proizvod_id, Prostor prostor, String naziv, String tip, String tezina, int kolicina, String napomena) {
        this.proizvod_id = proizvod_id;
        this.prostor = prostor;
        this.naziv = naziv;
        this.tip = tip;
        this.tezina = tezina;
        this.kolicina = kolicina;
        this.napomena = napomena;
    }
    
    public Proizvod(Prostor prostor, String naziv, String tip, String tezina, int kolicina, String napomena) {
        this.prostor = prostor;
        this.naziv = naziv;
        this.tip = tip;
        this.tezina = tezina;
        this.kolicina = kolicina;
        this.napomena = napomena;
    }
    
    public int getIdProizvod() {
        return proizvod_id;
    }
    

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    
    public String getTezina() {
        return tezina;
    }

    public void setTezina(String tezina) {
        this.tezina = tezina;
    }
    
    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    
    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
    
    public Prostor getProstor() {
        return prostor;
    }

    public void setProstor(Prostor prostor) {
        this.prostor = prostor;
    }
    
}
