package ocp;

public class Main {
    public static void main(String[] args) {
        PembayaranCustomer pembayaran = new PembayaranCustomer();
        
        pembayaran.menerimaPembayaran(new Debit());
        pembayaran.menerimaPembayaran(new Kredit());
        pembayaran.menerimaPembayaran(new Cash());
    }
}