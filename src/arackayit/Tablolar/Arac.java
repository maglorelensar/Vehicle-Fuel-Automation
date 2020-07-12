package arackayit.Tablolar;

import java.util.ArrayList;

public class Arac {

    String Plaka;
    String YakitTipi;
    String AracTipi;
    String Kullanici;
    String KayitTarihi;

    public Arac(String Plaka, String YakitTipi, String AracTipi, String Kullanici, String KayitTarihi) {
        this.Plaka = Plaka;
        this.YakitTipi = YakitTipi;
        this.AracTipi = AracTipi;
        this.Kullanici = Kullanici;
        this.KayitTarihi = KayitTarihi;
    }

    public String getPlaka() {
        return Plaka;
    }

    public void setPlaka(String Plaka) {
        this.Plaka = Plaka;
    }

    public String getYakitTipi() {
        return YakitTipi;
    }

    public void setYakitTipi(String YakitTipi) {
        this.YakitTipi = YakitTipi;
    }

    public String getAracTipi() {
        return AracTipi;
    }

    public void setAracTipi(String AracTipi) {
        this.AracTipi = AracTipi;
    }

    public String getKullanici() {
        return Kullanici;
    }

    public void setKullanici(String Kullanici) {
        this.Kullanici = Kullanici;
    }

    public String getKayitTarihi() {
        return KayitTarihi;
    }

    public void setKayitTarihi(String KayitTarihi) {
        this.KayitTarihi = KayitTarihi;
    }

    public String AracTipi() {
        return "Arac";
    }

//    //overload
//    public Arac aracAra(ArrayList<Arac> list, String plaka) {
//        return list.stream().
//                filter(p -> p.Plaka.equals(plaka)).
//                findFirst().orElse(null);
//    }
//    
//    //overload
//    public Arac aracAra(ArrayList<Arac> list, String AracTipi, String Kullanici) {
//        return list.stream().
//                filter(p -> p.AracTipi.equals(AracTipi) && p.Kullanici.equals(Kullanici)).
//                findFirst().orElse(null);
//    }

    @Override
    public String toString() {
        return "Arac{" + "Plaka=" + Plaka + ", YakitTipi=" + YakitTipi + ", AracTipi=" + AracTipi + ", Kullanici=" + Kullanici + ", KayitTarihi=" + KayitTarihi + '}';
    }
    

}
