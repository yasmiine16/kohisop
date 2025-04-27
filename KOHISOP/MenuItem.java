
public abstract class MenuItem {
    protected String kode;
    protected String nama;
    protected int harga;
    
    public MenuItem(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }
    
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    
    public abstract String getJenis();
    public abstract double hitungPajak();
}