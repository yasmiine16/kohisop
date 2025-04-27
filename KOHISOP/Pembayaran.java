public interface Pembayaran {
    double hitungTotal(double jumlah);
    String getNamaMetode();
    boolean validasiSaldo(Akun akun, double total);
}