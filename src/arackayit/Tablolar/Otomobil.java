package arackayit.Tablolar;

public class Otomobil extends Arac {

    public Otomobil(String Plaka, String YakitTipi, String AracTipi, String Kullanici, String KayitTarihi) {
        super(Plaka, YakitTipi, AracTipi, Kullanici, KayitTarihi);
    }
    
    int uretimYili;

    public int getUretimYili() {
        return uretimYili;
    }

    public void setUretimYili(int uretimYili) {
        this.uretimYili = uretimYili;
    }
    
    
    
    @Override
    public String AracTipi() {
        return "Otomobil";
    }
    
}
