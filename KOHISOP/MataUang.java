public abstract class MataUang {
    protected String kode;
    protected double nilaiTukar;
    
    public MataUang(String kode, double nilaiTukar) {
        this.kode = kode;
        this.nilaiTukar = nilaiTukar;
    }
    
    public String getKode() { return kode; }
    
    public double konversiDariIDR(double jumlahIDR) {
        return jumlahIDR / nilaiTukar;
    }
    
    public abstract String getSimbol();
}