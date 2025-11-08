package ocp;

public class Kredit extends TipePembayaran {
    @Override
    public void memprosesPembayaran() {
        System.out.println("Proses pembayaran dengan Kredit");
    }
}