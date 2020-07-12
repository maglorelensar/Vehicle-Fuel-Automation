package Daolar;

import arackayit.DosyaIslemleri.DosyaOkuma;
import arackayit.DosyaIslemleri.DosyaYazma;
import arackayit.Tablolar.YakitAlim;
import java.util.ArrayList;

public class YakitAlimDao {

    DosyaYazma dosyaYazma;
    DosyaOkuma dosyaOkuma;

    public YakitAlimDao() {
        dosyaYazma = new DosyaYazma("yakitdata.txt");
        dosyaOkuma = new DosyaOkuma("yakitdata.txt");
    }

    public boolean Kaydet(YakitAlim model) {
        dosyaYazma.SatirlaraEkle(model.getPlaka() + "@"
                + model.getLitre()+ "@"
                + model.getFiyat()+ "@"
                + model.getTarih()+ "@"
                + model.getAdres()+ "#"
        );

        return true;
    }

    public ArrayList<YakitAlim> ListeGetir() {
        ArrayList<YakitAlim> list = new ArrayList<>();

        String tumDosyaStr = dosyaOkuma.TumSatirlariGetir();
        if (tumDosyaStr.length() == 1 || tumDosyaStr.length() == 0) {
            return list;
        }
        String[] dosyaStr = tumDosyaStr.split("#");

        for (String dosyaDStr : dosyaStr) {
            String[] modelStr = dosyaDStr.split("@");
            YakitAlim model = new YakitAlim(modelStr[0], Double.parseDouble(modelStr[1]), Double.parseDouble(modelStr[2]), modelStr[3], modelStr[4]);
            list.add(model);
        }

        return list;

    }

    public void Sil(YakitAlim model) {

        ArrayList<YakitAlim> list = this.ListeGetir();

        list.removeIf(x -> 
                (x.getPlaka() == null ? model.getPlaka() != null : x.getPlaka().equals(model.getPlaka())) &&
                (x.getTarih()== null ? model.getTarih() != null : x.getTarih().equals(model.getTarih()))
        );

        dosyaYazma.SatirlariSil();

        list.stream().forEach((fYakitAlim) -> {
            this.Kaydet(fYakitAlim);
        });

    }

}
