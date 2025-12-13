import java.sql.Connection;
import koneksi.Koneksi;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class CetakLaporan {

    public static void main(String[] args) {
        try {
            // 1. Koneksi database
            Connection conn = Koneksi.getConnection();

            // 2. Path JRXML (WAJIB BENAR)
            String reportPath =
                "C:/Users/user/OneDrive/Documents/projectpbo/PraktikumPBO/" +
                "tugaspraktikum/praktikum16/jasperreport/src/report/" +
                "laporan_penjualan.jrxml";

            // 3. Compile JRXML → JasperReport
            JasperReport jasperReport =
                    JasperCompileManager.compileReport(reportPath);

            // 4. Isi laporan dengan data dari DB
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(jasperReport, null, conn);

            // 5. Preview laporan
            JasperViewer.viewReport(jasperPrint, false);

            // 6. Export ke PDF
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint,
                    "laporan_penjualan_pangkalan_gas.pdf"
            );

            System.out.println("✅ Laporan berhasil dibuat");

        } catch (Exception e) {
            System.out.println("❌ Gagal membuat laporan");
            e.printStackTrace();
        }
    }
}
