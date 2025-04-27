import java.util.Scanner;

public class MainApp {
    private static final int MAX_PESANAN = 10;
    private static Pesanan[] pesanan = new Pesanan[MAX_PESANAN];
    private static int jumlahPesanan = 0;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SELAMAT DATANG DI KOHISOP ===");

        while (jumlahPesanan < MAX_PESANAN) {
            MenuKOHISOP.tampilkanMenu();

            System.out.print("\nMasukkan kode menu (CC untuk selesai memesan): ");
            String kode = input.nextLine().toUpperCase();

            if (kode.equals("CC")) break;

            MenuItem item = MenuKOHISOP.cariMenu(kode);
            if (item == null) {
                System.out.println("Kode anda masukkan tidak valid!");
                continue;
            }

            int jumlah = mintaJumlah(item.getJenis().equals("Minuman") ? 3 : 2);

            if (jumlah == -1) {
                System.out.println("Pesanan dibatalkan.");
                continue;
            } else if (jumlah >= 0) {
                pesanan[jumlahPesanan++] = new Pesanan(item, jumlah);
                tampilkanPesanan();
            }

            if (jumlahPesanan < MAX_PESANAN) {
                System.out.print("Tambah menu lagi? (Y/N): ");
                if (!input.nextLine().equalsIgnoreCase("Y")) break;
            } else {
                System.out.println("Batas maksimal pesanan tercapai.");
            }
        }

        if (jumlahPesanan > 0) prosesPembayaran();
        else System.out.println("Tidak ada pesanan. Terima kasih.");
    }

    private static int mintaJumlah(int maks) {
        while (true) {
            System.out.print("Jumlah (1-" + maks + ", 0 atau s untuk batal): ");
            String userInput = input.nextLine().toLowerCase();
            if (userInput.equals("s") || userInput.equals("0")) {
                return -1;
            } else if (userInput.isEmpty()) return 1;
            try {
                int jumlah = Integer.parseInt(userInput);
                if (jumlah > 0 && jumlah <= maks) return jumlah;
                System.out.println("Jumlah tidak valid!");
            } catch (Exception e) {
                System.out.println("Masukkan angka valid!");
            }
        }
    }

    private static void tampilkanPesanan() {
        System.out.println("\n+============= DAFTAR PESANAN =============+");
        for (int i = 0; i < jumlahPesanan; i++) {
            Pesanan p = pesanan[i];
            System.out.printf("| %2d | %-4s | %-26s | %2d | %5.0f |\n", i + 1, p.getItem().getKode(), p.getItem().getNama(), p.getJumlah(), p.getTotal());
        }
        System.out.println("+==========================================+");
    }

    private static void prosesPembayaran() {
        double subtotal = 0, totalPajak = 0;
        for (int i = 0; i < jumlahPesanan; i++) {
            subtotal += pesanan[i].getSubtotal();
            totalPajak += pesanan[i].getTotalPajak();
        }

        Pembayaran metodeBayar = pilihMetodeBayar();
        double totalBayar = metodeBayar.hitungTotal(subtotal + totalPajak);

        Akun akunUser = new Akun(500000);

        boolean cukupSaldo = metodeBayar instanceof Tunai || metodeBayar.validasiSaldo(akunUser, subtotal + totalPajak);

        if (cukupSaldo) {
            if (!(metodeBayar instanceof Tunai)) {
                akunUser.kurangiSaldo(totalBayar);
            }

            MataUang mataUang = pilihMataUang();
            double jumlahTerkonversi = mataUang.konversiDariIDR(totalBayar);

            tampilkanKuitansiTerperinci(metodeBayar, mataUang, subtotal, totalPajak);

            System.out.println("\nTotal Bayar (" + mataUang.getSimbol() + "): " + jumlahTerkonversi);
            System.out.println("Terima kasih telah berbelanja di KohiSop.");

            if (!(metodeBayar instanceof Tunai)) {
                System.out.println("Saldo Anda tersisa: Rp " + akunUser.getSaldo());
            }
        } else {
            System.out.println("Saldo tidak mencukupi untuk pembayaran ini!");
        }
    }

    private static Pembayaran pilihMetodeBayar() {
        System.out.println("\nMetode Bayar:\n1. Tunai\n2. QRIS\n3. eMoney");
        while (true) {
            System.out.print("Pilih: ");
            int pilihan = Integer.parseInt(input.nextLine());
            switch (pilihan) {
                case 1: return new Tunai();
                case 2: return new Qris();
                case 3: return new EMoney();
                default: System.out.println("Pilihan anda pilih tidak valid!");
            }
        }
    }

    private static MataUang pilihMataUang() {
        MataUang[] mataUang = {new USD(), new JPY(), new MYR(), new EUR()};
        System.out.println("\nPilih Mata Uang:");
        for (int i = 0; i < mataUang.length; i++) System.out.printf("%d. %s\n", i + 1, mataUang[i].getKode());
        while (true) {
            System.out.print("Pilih: ");
            int pilihan = Integer.parseInt(input.nextLine());
            if (pilihan >= 1 && pilihan <= mataUang.length) return mataUang[pilihan - 1];
            System.out.println("Pilihan tidak valid!");
        }
    }

    private static void tampilkanKuitansiTerperinci(Pembayaran metodeBayar, MataUang mataUang, double subtotal, double totalPajak) {
        System.out.println("\n============= KUITANSI PEMESANAN =============");

        double totalMinuman = 0, totalPajakMinuman = 0;
        System.out.println("\n-- MAKANAN --");
        for (int i = 0; i < jumlahPesanan; i++) {
            Pesanan p = pesanan[i];
            if (p.getItem().getJenis().equals("Makanan")) {
                printDetailPesanan(p);
            }
        }

        System.out.println("\n-- MINUMAN --");
        for (int i = 0; i < jumlahPesanan; i++) {
            Pesanan p = pesanan[i];
            if (p.getItem().getJenis().equals("Minuman")) {
                printDetailPesanan(p);
                totalMinuman += p.getSubtotal();
                totalPajakMinuman += p.getTotalPajak();
            }
        }

        System.out.printf("\n>> Total Minuman (sebelum pajak): Rp %.0f\n", totalMinuman);
        System.out.printf(">> Pajak Minuman: Rp %.0f\n", totalPajakMinuman);
        System.out.printf(">> Total Minuman (dengan pajak): Rp %.0f\n", totalMinuman + totalPajakMinuman);

        System.out.println("\n-----------------------------------------------");
        System.out.printf(">> Diskon/Biaya Admin: %s\n", metodeBayar.getNamaMetode());

        double totalSebelumDiskon = subtotal + totalPajak;
        double dalamMataUangSebelum = mataUang.konversiDariIDR(totalSebelumDiskon);
        System.out.printf(">> Total Sebelum Diskon (%s): %.2f\n", mataUang.getSimbol(), dalamMataUangSebelum);

        double totalAkhir = metodeBayar.hitungTotal(totalSebelumDiskon);
        double dalamMataUangSesudah = mataUang.konversiDariIDR(totalAkhir);
        System.out.printf(">> Total Setelah Diskon (%s): %.2f\n", mataUang.getSimbol(), dalamMataUangSesudah);

        System.out.println("\nTerima kasih dan silakan datang kembali🙏");
    }

    private static void printDetailPesanan(Pesanan p) {
        System.out.printf("- %s | %s | Rp %d x %d = Rp %.0f (+ Pajak: Rp %.0f)\n",
            p.getItem().getKode(),
            p.getItem().getNama(),
            p.getItem().getHarga(),
            p.getJumlah(),
            p.getSubtotal(),
            p.getTotalPajak());
    }
}