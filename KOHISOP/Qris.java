public class Qris implements Pembayaran {
    public double hitungTotal(double jumlah) {
        return jumlah * 0.95;
    }
    
    public String getNamaMetode() {
        return "QRIS (Diskon 5%)";
    }
    
    public boolean validasiSaldo(Akun akun, double total) {
        double totalBayar = hitungTotal(total);
        return akun.getSaldo() >= totalBayar;
    }
}