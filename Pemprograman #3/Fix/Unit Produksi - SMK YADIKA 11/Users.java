import java.util.Scanner;
import java.sql.*;
public class Users
{
    // instance variables - replace the example below with your own
    private static Admin panggil = new Admin();
    private Layout display = new Layout();
    private Scanner baca = new Scanner(System.in);
    private Connection conn = new DBConnection().connect();
    private Statement stmt;
    private String sql,lagi;
    public void Users(String Nama,String Level) throws Exception
    {
         display.Header();
         System.out.println("[+] Hai, " + Nama + " (" + Level + ")");
         display.Users_View();
         String sql = "SELECT * FROM Users";
         try {
              stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql);
              // loop through the result set
              while (rs.next()) {
                    System.out.format("|| %-15s || %-17s || %-28s || %-14s ||",rs.getString("id"), rs.getString("key"),rs.getString("name"),rs.getString("level"));
                    System.out.println();   
                   }               
             } catch (Exception e) {System.out.println(e.getMessage());}
         System.out.println("============================================================================================\n"+
                            "[1] Add || [2] Update || [3] Delete || [X] Back");
         System.out.print("Masukan Pilihan : "); int Pilih = baca.nextInt();
         String lagi;
         switch(Pilih) {
                case 1: addUsers(Nama,Level); return;
                case 2: updateUsers(Nama,Level); return;
                case 3: deleteUsers(Nama,Level); return;
                default: panggil.Admin(Nama,Level);            
         }   
       }
       private void addUsers(String Nama,String Level) throws Exception {
         System.out.print("[!] Tekan Y, untuk terus melanjutkan. (y/t)");
         while (( lagi = baca.next()).equalsIgnoreCase("y")) {
                System.out.print("Id      = "); String add_id = baca.next();
                System.out.print("Key     = "); String add_key = baca.next();
                System.out.print("Name    = "); String add_name = baca.nextLine();     
                System.out.print("Level   = "); String add_level = baca.next();                                                    
                sql = "INSERT INTO Users ('id','key','name','level') VALUES (?,?,?,?)";
                try (Connection conn = new DBConnection().connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                     // set the corresponding param
                     pstmt.setString(1, add_id);
                     pstmt.setString(2, add_key);
                     pstmt.setString(3, add_name);
                     pstmt.setString(4, add_level);
                     // execute the delete statement
                     pstmt.executeUpdate();                    
                } catch (SQLException e) {System.out.println(e.getMessage());}
                System.out.println("Data User ID (" + add_id +") Berhasil Ditambahkan !");  
                System.out.print("Ingin Tambah Data Lagi ? ");  
         }
         Users(Nama,Level);
        }
       private void updateUsers(String Nama,String Level) throws Exception {
         System.out.print("[!] Tekan Y, untuk terus melanjutkan. (y/t)");
         while (( lagi = baca.next()).equalsIgnoreCase("y")) {
               System.out.print("ID Pengguna Yang Ingin Diubah = "); String primary_id = baca.next();
               System.out.print("Key Baru        = "); String update_key = baca.next();
               System.out.print("Name Baru       = "); String update_name = baca.nextLine();     
               System.out.print("Level Baru      = "); String update_level = baca.next();    
               sql = "UPDATE Users SET key = ? , name = ? , level = ? WHERE id = ?";
               try (Connection conn = new DBConnection().connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {                          
                    // set the corresponding param
                    pstmt.setString(1, update_key);
                    pstmt.setString(2, update_name);
                    pstmt.setString(3, update_level);
                    pstmt.setString(4, primary_id);
                    // execute the delete statement
                    pstmt.executeUpdate();              
               } catch (SQLException e) {System.out.println(e.getMessage());}
               System.out.println("Data User ID (" + primary_id +") Berhasil Diubah !");  
               System.out.print("Ingin Update Data Lagi ? ");  
         }
         Users(Nama,Level);
       }
       private void deleteUsers(String Nama,String Level) throws Exception {
         System.out.print("[!] Tekan Y, untuk terus melanjutkan. (y/t)");
         while (( lagi = baca.next()).equalsIgnoreCase("y")) {
             System.out.print("ID Yang Ingin Dihapus   = "); String delete_id = baca.next();
             sql = "DELETE FROM Users WHERE id = ?";
             try (Connection conn = new DBConnection().connect();
                  PreparedStatement pstmt = conn.prepareStatement(sql)) {                          
                  // set the corresponding param
                  pstmt.setString(1, delete_id);
                  // execute the delete statement
                  pstmt.executeUpdate();                   
             } catch (SQLException e) {System.out.println(e.getMessage());}
             System.out.println("Data User ID (" + delete_id +") Berhasil Dihapus !");  
             System.out.print("Ingin Hapus Data Lagi ? ");  
         }
         Users(Nama,Level); 
       }
   

   
}
