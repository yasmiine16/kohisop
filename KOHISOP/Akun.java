public class Akun {
    private double saldo;

    public Akun(double saldoAwal) {
        this.saldo = saldoAwal;
    }

    public double getSaldo() {
        return saldo;
    }

    public void kurangiSaldo(double jumlah) {
        saldo -= jumlah;
    }
}
