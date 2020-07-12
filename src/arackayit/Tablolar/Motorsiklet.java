package arackayit.Tablolar;

public class Motorsiklet extends Arac {

    public Motorsiklet(String Plaka, String YakitTipi, String AracTipi, String Kullanici, String KayitTarihi) {
        super(Plaka, YakitTipi, AracTipi, Kullanici, KayitTarihi);
    }
    
    String ureticiFirma;

    public String getUreticiFirma() {
        return ureticiFirma;
    }

    public void setUreticiFirma(String ureticiFirma) {
        this.ureticiFirma = ureticiFirma;
    }
    
    
    @Override
    public String AracTipi() {
        return "Motorsiklet";
    }
    
}
