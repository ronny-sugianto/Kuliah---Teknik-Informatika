import java.util.Scanner;
import java.sql.*;
public class Payments
{
    // instance variables - replace the example below with your own
    private Layout display = new Layout();
    private Scanner baca = new Scanner(System.in);
    private Statement stmt;
    private ResultSet rs;
    private String sql,lagi,id_brg;
    
    private int qty;
    private int jml_brng;
    private int jumlah_harga;
    private int jumlah_total;
    public void Payments(String Nama,String Level,Connection conn) throws Exception
    {
      System.out.print("============================================================================================\n[*] Tekan y untuk melanjutkan !");             
        while ((lagi = baca.next()).equalsIgnoreCase("y")) {
            System.out.print("\nKode Barang   = "); id_brg = baca.next();
            if (CheckIsExist(id_brg,conn) != false) {
                System.out.print("Jumlah Barang       = "); int update_qty = Integer.parseInt(baca.next());    
                int qtyOri = rs.getInt("qty") - update_qty;
                sql = "UPDATE Inventory SET qty = ? WHERE id = ?";
                int success;
                try (PreparedStatement psPay = conn.prepareStatement(sql)){
                     psPay.setInt(1, qty);
                     psPay.setString(2, id_brg);
                     success = psPay.executeUpdate();
                     System.out.println(success);
                }
                if (success == 0) {
                    System.out.println("Data Barang ID (" + id_brg +") Berhasil Diubah !");  
                    System.out.print("Ingin Update Data Lagi ? "); 
                }//catch (Exception e) {System.out.println(e.getMessage());}    

            }
            
       }
    }
    private boolean CheckIsExist(String kode_barang,Connection conn) throws Exception {
       String sql = "Select * from Inventory where id=?";
         try (PreparedStatement pstmt  = conn.prepareStatement(sql)){        
              pstmt.setString(1,id_brg);
              ResultSet rs  = pstmt.executeQuery();
              if (rs.next()) {
                 return true;    
              }
              return false;
         }

    }
    
}
