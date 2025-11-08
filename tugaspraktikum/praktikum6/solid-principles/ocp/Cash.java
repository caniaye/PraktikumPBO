package ocp;

public class Cash extends TipePembayaran {
    @Override
    public void memprosesPembayaran() {
        System.out.println("Proses pembayaran dengan Cash");
    }
}
