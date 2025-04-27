public class Minuman extends MenuItem {
    public Minuman(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }
    
    public String getJenis() { return "Minuman"; }
    
    public double hitungPajak() {
        if (harga < 50) {
            return 0;
        } else if (harga <= 55) {
            return harga * 0.08;
        } else {
            return harga * 0.11;
        }
    }
}