package arackayit.Tablolar;


public class YakitAlim {
    String plaka;
    double litre;
    double fiyat;
    String tarih;
    String adres;

    public YakitAlim(String plaka, double litre, double fiyat, String tarih, String adres) {
        this.plaka = plaka;
        this.litre = litre;
        this.fiyat = fiyat;
        this.tarih = tarih;
        this.adres = adres;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public double getLitre() {
        return litre;
    }

    public void setLitre(double litre) {
        this.litre = litre;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
    
    
    
}
