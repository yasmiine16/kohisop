public class Makanan extends MenuItem {
    public Makanan(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }
    
    public String getJenis() { return "Makanan"; }
    
    public double hitungPajak() {
        if (harga > 50) {
            return harga * 0.08;
        } else {
            return harga * 0.11;
        }
    }
}