public class EMoney implements Pembayaran {
    public double hitungTotal(double jumlah) {
        return (jumlah * 0.93) + 20;
    }
    
    public String getNamaMetode() {
        return "eMoney (Diskon 7% + Admin Rp20)";
    }
    
    public boolean validasiSaldo(Akun akun, double total) {
        double totalBayar = hitungTotal(total);
        return akun.getSaldo() >= totalBayar;
    }
}
