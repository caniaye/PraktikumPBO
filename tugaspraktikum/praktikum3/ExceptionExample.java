public class ExceptionExample {     
    public static void main(String[] args) {         
        try {             
            int hasil = 10 / 0; // Akan menimbulkan ArithmeticException         
            } catch (ArithmeticException e) {
            System.out.println("Terjadi error: " + e.getMessage());
        } finally {
            System.out.println("Blok finally selalu dijalankan.");
        }
    }
}
