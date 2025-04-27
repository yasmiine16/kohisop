public class Pesanan {
    private MenuItem item;
    private int jumlah;
    
    public Pesanan(MenuItem item, int jumlah) {
        this.item = item;
        this.jumlah = jumlah;
    }
    
    public MenuItem getItem() { return item; }
    public int getJumlah() { return jumlah; }
    
    public double getSubtotal() {
        return item.getHarga() * jumlah;
    }
    
    public double getTotalPajak() {
        return item.hitungPajak() * jumlah;
    }
    
    public double getTotal() {
        return getSubtotal() + getTotalPajak();
    }
}