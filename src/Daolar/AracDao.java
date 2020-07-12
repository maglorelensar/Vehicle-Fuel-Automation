package Daolar;

import arackayit.DosyaIslemleri.DosyaOkuma;
import arackayit.DosyaIslemleri.DosyaYazma;
import arackayit.Tablolar.Arac;
import java.util.ArrayList;

public class AracDao {

    DosyaYazma dosyaYazma;
    DosyaOkuma dosyaOkuma;

    public AracDao() {
        dosyaYazma = new DosyaYazma("aracdata.txt");
        dosyaOkuma = new DosyaOkuma("aracdata.txt");
    }

    public boolean Kaydet(Arac arac) {
        dosyaYazma.SatirlaraEkle(arac.getPlaka() + "@"
                + arac.getYakitTipi() + "@"
                + arac.getAracTipi() + "@"
                + arac.getKullanici() + "@"
                + arac.getKayitTarihi() + "#"
        );

        return true;
    }

    public ArrayList<Arac> ListeGetir() {
        ArrayList<Arac> aracListesi = new ArrayList<>();

        String aracDosyaStr = dosyaOkuma.TumSatirlariGetir();
        if (aracDosyaStr.length() == 1 || aracDosyaStr.length() == 0) {
            return aracListesi;
        }
        String[] araclarStr = aracDosyaStr.split("#");

        for (String aracDStr : araclarStr) {
            String[] aracStr = aracDStr.split("@");
            Arac arac = new Arac(aracStr[0], aracStr[1], aracStr[2], aracStr[3], aracStr[4]);
            aracListesi.add(arac);
        }

        return aracListesi;

    }

    public void Sil(Arac arac) {

        ArrayList<Arac> aracListesi = this.ListeGetir();

        aracListesi.removeIf(x -> (x.getPlaka() == null ? arac.getPlaka() != null : x.getPlaka().equals(arac.getPlaka())));

        dosyaYazma.SatirlariSil();

        for (Arac farac : aracListesi) {
            this.Kaydet(farac);
        }

    }

}
