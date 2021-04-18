package com.example.veterineryardimcisi;


import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class HastaBilgileri extends RealmObject {
    private String hastaCinsi;
    private String hastaYasi;
    private String aciklama;
    private String hastaSahibiIsmiSoyismi;
    private String hastaSahibiNumarasi;
    private String hastaAdi;
    private String tarih;


    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }


    public String getHastaSahibiIsmiSoyismi() {
        return hastaSahibiIsmiSoyismi;
    }

    public void setHastaSahibiIsmiSoyismi(String hastaSahibiIsmiSoyismi) {
        this.hastaSahibiIsmiSoyismi = hastaSahibiIsmiSoyismi;
    }

    public String getHastaSahibiNumarasi() {
        return hastaSahibiNumarasi;
    }

    public void setHastaSahibiNumarasi(String hastaSahibiNumarasi) {
        this.hastaSahibiNumarasi = hastaSahibiNumarasi;
    }

    public String getHastaAdi() {
        return hastaAdi;
    }

    public void setHastaAdi(String hastaAdi) {
        this.hastaAdi = hastaAdi;
    }

    public String getHastaCinsi() {
        return hastaCinsi;
    }

    public void setHastaCinsi(String hastaCinsi) {
        this.hastaCinsi = hastaCinsi;
    }

    public String getHastaYasi() {
        return hastaYasi;
    }

    public void setHastaYasi(String hastaYasi) {
        this.hastaYasi = hastaYasi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "HastaBilgileri{" +
                "hastaCinsi='" + hastaCinsi + '\'' +
                ", hastaYasi='" + hastaYasi + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", hastaSahibiIsmiSoyismi='" + hastaSahibiIsmiSoyismi + '\'' +
                ", hastaSahibiNumarasi='" + hastaSahibiNumarasi + '\'' +
                ", hastaAdi='" + hastaAdi + '\'' +
                ", tarih='" + tarih + '\'' +
                '}';
    }

}
