package arackayit.Tablolar;

public class Kamyon extends Arac {

    int yukKapasitesi;
    
    public Kamyon(String Plaka, String YakitTipi, String AracTipi, String Kullanici, String KayitTarihi) {
        super(Plaka, YakitTipi, AracTipi, Kullanici, KayitTarihi);
    }

    public int getYukKapasitesi() {
        return yukKapasitesi;
    }

    public void setYukKapasitesi(int yukKapasitesi) {
        this.yukKapasitesi = yukKapasitesi;
    }

    @Override
    public String AracTipi() {
        return "Kamyon";
    }
    
    
    
}
