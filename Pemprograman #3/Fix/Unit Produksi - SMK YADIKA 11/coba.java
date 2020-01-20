import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
public class coba
{
    static Connection conn = new DBConnection().connect();
    static Statement stmt;

    static String lagi,sql;
    
    static Scanner baca = new Scanner(System.in);
    public static void main(String[] args) {
         println("                                 Tabel Rute Kereta                              \n\n");
         sql = "SELECT * FROM Rute_Kereta;";
         try {
             stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             // loop through the result set
             headers();
             while (rs.next()) {
                 System.out.format("    %-14s %-15s %-17s %-18s %-14s",rs.getString("Kode_Kereta"), rs.getString("Kereta_Api"),rs.getString("Stasiun_Awal"),rs.getString("Stasiun_Tujuan"),rs.getString("Harga"));
                 println("");  
             }               
         } catch (Exception e) {println(e.getMessage());}
             lagi = "y";
             while(lagi.equalsIgnoreCase("y")) {
                println("\nPencarian Data Rute Kereta\n-----------------------------");
                print("Masukan Keyword Pencarian : ");
                String cari = baca.next();   
                sql = "SELECT * FROM Rute_Kereta where Stasiun_Awal like '%" + cari +"%';";
                try {
                     stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql);
                     // loop through the result set
                     headers();
                     while (rs.next()) {
                            System.out.format("    %-14s %-15s %-17s %-18s %-14s",rs.getString("Kode_Kereta"), rs.getString("Kereta_Api"),rs.getString("Stasiun_Awal"),rs.getString("Stasiun_Tujuan"),rs.getString("Harga"));
                            println("");  
                     }                    
                } catch (Exception e) {println(e.getMessage());}
                 print("Ingin Cari Data Lagi? (y/t) : "); lagi = baca.next();
            }
        
    }
    private static void headers() {
        println("Kode Kereta       Kereta Api      Stasiun Awal      Stasiun Akhir      Harga");
    }
    private static void print(String word) {System.out.print(word);}
    private static void println(String word) {System.out.println(word);}
}
