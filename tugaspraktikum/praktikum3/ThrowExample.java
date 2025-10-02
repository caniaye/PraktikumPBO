public class ThrowExample {     
    public static void cekUmur(int umur) throws Exception {         
        if (umur < 18) {             
            throw new Exception("Umur belum cukup!");         
        } else {
            System.out.println("Umur valid.");
        }     }
    public static void main(String[] args) {         
        try {             
        cekUmur(15);         } 
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
