// Interface (Multiple part)
interface InterfaceRem {
    void rem();
}

// File: SuperKendaraan.java (Level 1)
class SuperKendaraan {
    public void infoUmum() {
        System.out.println("Ini adalah kendaraan bermesin.");
    }
}

// File: Mesin.java (Level 2 - Multilevel part)
class Mesin extends SuperKendaraan {
    public void startMesin() {
        System.out.println("Mesin dihidupkan.");
    }
}

// File: SepedaMotor.java (Level 3 - Hybrid)
class SepedaMotor extends Mesin implements InterfaceRem {
    @Override
    public void rem() {
        System.out.println("Sepeda motor mengerem.");
    }

    public void jalan() {
        System.out.println("Sepeda motor berjalan.");
    }

    // Metode utama untuk menjalankan
    public static void main(String[] args) {
        SepedaMotor motor = new SepedaMotor();

        System.out.println("--- Hybrid Inheritance ---");

        // Perilaku dari SuperKendaraan (Level 1 - Multilevel)
        motor.infoUmum();

        // Perilaku dari Mesin (Level 2 - Multilevel)
        motor.startMesin();
        
        // Perilaku dari SepedaMotor (Metode sendiri)
        motor.jalan(); 

        // Perilaku dari InterfaceRem (Multiple/Hybrid)
        motor.rem(); 
    }
}