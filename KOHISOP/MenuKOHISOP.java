public class MenuKOHISOP {
    private static MenuItem[] daftarMenu = {

        new Minuman("A1", "Caffe Latte", 46),
        new Minuman("A2", "Cappuccino", 46),
        new Minuman("E1", "Caffe Americano", 37),
        new Minuman("E2", "Caffe Mocha", 55),
        new Minuman("E3", "Caramel Macchiato", 59),
        new Minuman("E4", "Asian Dolce Latte", 55),
        new Minuman("E5", "Double Shots Iced Shaken Espresso", 50),
        new Minuman("B1", "Freshly Brewed Coffee", 23),
        new Minuman("B2", "Vanilla Sweet Cream Cold Brew", 50),
        new Minuman("B3", "Cold Brew", 44),
        
        new Makanan("M1", "Petemania Pizza", 112),
        new Makanan("M2", "Mie Rebus Super Mario", 35),
        new Makanan("M3", "Ayam Bakar Goreng Rebus Spesial", 72),
        new Makanan("M4", "Soto Kambing Iga Guling", 124),
        new Makanan("S1", "Singkong Bakar A La Carte", 37),
        new Makanan("S2", "Ubi Cilembu Bakar Arang", 58),
        new Makanan("S3", "Tempe Mendoan", 18),
        new Makanan("S4", "Tahu Bakso Extra Telur", 28)
    };
    
    public static MenuItem[] getDaftarMenu() {
        return daftarMenu;
    }
    
    public static MenuItem cariMenu(String kode) {
        for (MenuItem menu : daftarMenu) {
            if (menu.getKode().equalsIgnoreCase(kode)) {
                return menu;
            }
        }
        return null;
    }
    
    public static void tampilkanMenu() {
        System.out.println("\n+===============================================+");
        System.out.println("|                   MENU KOHISOP                |");
        System.out.println("+=======+===============================+=======+");
        System.out.println("| Kode  | Nama Menu                    | Harga |");
        System.out.println("+=======+===============================+=======+");
        
        System.out.println("|       \t\tMINUMAN\t\t       |");
        System.out.println("+-------+-------------------------------+-------+");
        for (MenuItem menu : daftarMenu) {
            if (menu.getJenis().equals("Minuman")) {
                System.out.printf("| %-5s | %-29s | %5d |\n", 
                    menu.getKode(), menu.getNama(), menu.getHarga());
            }
        }
        
        System.out.println("+-------+-------------------------------+-------+");
        System.out.println("|       \t\tMAKANAN\t\t       |");
        System.out.println("+-------+-------------------------------+-------+");
        for (MenuItem menu : daftarMenu) {
            if (menu.getJenis().equals("Makanan")) {
                System.out.printf("| %-5s | %-29s | %5d |\n", 
                    menu.getKode(), menu.getNama(), menu.getHarga());
            }
        }
        
        System.out.println("+=======+===============================+=======+");
    }
}