import java.util.Scanner;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
public class Inventory
{
    // instance variables - replace the example below with your own
    private static Admin panggil = new Admin();
    private Layout display = new Layout();
    private Scanner baca = new Scanner(System.in);
    private Connection conn = new DBConnection().connect();
    private Statement stmt;
    private String sql,lagi;
    public void Inventory(String Nama,String Level) throws Exception
    {
        display.Header();
         System.out.println("[+] Hai, " + Nama + " (" + Level + ")");
         display.Inventory_View();
         String sql = "SELECT * FROM Inventory";
         try {
              stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql);
              // loop through the result set
              while (rs.next()) {
                  
                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols format = new DecimalFormatSymbols();
                    format.setCurrencySymbol("Rp. ");
                    format.setMonetaryDecimalSeparator(',');
                    format.setGroupingSeparator('.');
                    kursIndonesia.setDecimalFormatSymbols(format);
                    
                    System.out.format("|| %-5s || %-36s || %-14s || %-7s || %-4s ||",rs.getString("id"), rs.getString("name"),rs.getString("vendor"),kursIndonesia.format(rs.getInt("price")),rs.getInt("qty"));
                    System.out.println();   
                   }               
             } catch (Exception e) {System.out.println(e.getMessage());}
         System.out.println("============================================================================================\n"+
                            "[1] Add || [2] Update || [3] Delete || [X] Back");
         System.out.print("Masukan Pilihan : "); int Pilih = baca.nextInt();
         String lagi;
         switch(Pilih) {
                case 1: addInventory(Nama,Level); return;
                case 2: updateInventory(Nama,Level); return;
                case 3: deleteInventory(Nama,Level); return;
                default: panggil.Admin(Nama,Level);            
         }   
    }
    public void addInventory(String Nama,String Level) throws Exception {
         System.out.print("[!] Tekan Y, untuk terus melanjutkan. (y/t)");
         while (( lagi = baca.next()).equalsIgnoreCase("y")) {
                System.out.print("Id      = "); String add_id = baca.next();
                System.out.print("Nama    = "); String add_name = baca.next();
                System.out.print("Merk    = "); String add_vendor = baca.next();     
                System.out.print("Harga   = "); String add_price = baca.next();                                                    
                System.out.print("Stok    = "); String add_qty = baca.next();                
                sql = "INSERT INTO Inventory ('id','name','vendor','price','qty') VALUES (?,?,?,?,?)";
                try (Connection conn = new DBConnection().connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                     // set the corresponding param
                     pstmt.setString(1, add_id);
                     pstmt.setString(2, add_name);
                     pstmt.setString(3, add_vendor);
                     pstmt.setString(4, add_price);
                     pstmt.setString(5, add_qty);                     
                     // execute the delete statement
                     pstmt.executeUpdate();                    
                } catch (SQLException e) {
                     System.out.println(e.getMessage());
                }
                System.out.println("Data Inventory ID (" + add_id +") Berhasil Ditambahkan !");  
                System.out.print("Ingin Tambah Data Lagi ? ");  
         }
         Inventory(Nama,Level);
     }
     public void updateInventory(String Nama,String Level) throws Exception {
         System.out.print("[!] Tekan Y, untuk terus melanjutkan. (y/t)");
         while (( lagi = baca.next()).equalsIgnoreCase("y")) {
               System.out.print("ID Barang Yang Ingin Diubah = "); String primary_id = baca.next();
               System.out.print("Harga Baru        = "); String update_price = baca.next();
               System.out.print("Stok  Baru       = "); String update_qty = baca.next();     
               sql = "UPDATE Inventory SET price = ? , qty = ? WHERE id = ?";
               try (Connection conn = new DBConnection().connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {                          
                    // set the corresponding param
                    pstmt.setString(1, update_price);
                    pstmt.setString(2, update_qty);
                    pstmt.setString(3, primary_id);
                    // execute the delete statement
                    pstmt.executeUpdate();              
               } catch (SQLException e) {System.out.println(e.getMessage());}
               System.out.println("Data Inventory ID (" + primary_id +") Berhasil Diubah !");  
               System.out.print("Ingin Update Data Lagi ? ");  
         }
         Inventory(Nama,Level);
     }
     public void deleteInventory(String Nama,String Level) throws Exception {
         System.out.print("[!] Tekan Y, untuk terus melanjutkan. (y/t)");
         while (( lagi = baca.next()).equalsIgnoreCase("y")) {
             System.out.print("ID Yang Ingin Dihapus   = "); String delete_id = baca.next();
             sql = "DELETE FROM Inventory WHERE id = ?";
             try (Connection conn = new DBConnection().connect();
                  PreparedStatement pstmt = conn.prepareStatement(sql)) {                          
                  // set the corresponding param
                  pstmt.setString(1, delete_id);
                  // execute the delete statement
                  pstmt.executeUpdate();                   
             } catch (SQLException e) {System.out.println(e.getMessage());}
             System.out.println("Data Inventory ID (" + delete_id +") Berhasil Dihapus !");  
             System.out.print("Ingin Hapus Data Lagi ? ");  
         }
         Inventory(Nama,Level);
     }
}
