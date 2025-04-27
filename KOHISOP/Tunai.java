public class Tunai implements Pembayaran {
    public double hitungTotal(double jumlah) {
        return jumlah;
    }
    
    public String getNamaMetode() {
        return "Tunai";
    }
    
    public boolean validasiSaldo(double saldo, double total) {
        return true; 
    }

    @Override
    public boolean validasiSaldo(Akun akunUser, double total) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validasiSaldo'");
    }
}