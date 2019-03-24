package gykizmir.com.customlistview;

/**
 * Created by Gamze on 3/17/2019.
 */

public class Kisi {

    private String isim;
    private String tarih;
    private int resim;

    public Kisi(String isim, String tarih, int resim) {
        this.isim = isim;
        this.tarih = tarih;
        this.resim = resim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public int getResim() {
        return resim;
    }

    public void setResim(int resim) {
        this.resim = resim;
    }
}
