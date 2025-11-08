package ocp;

public class Debit extends TipePembayaran {
    @Override
    public void memprosesPembayaran() {
        System.out.println("Proses pembayaran dengan Debit");
    }
}
