import java.util.Scanner;
import java.sql.*;
public class Apps
{
    // instance variables - replace the example below with your own
    static Admin adm = new Admin();
    static Public pbc = new Public();
    static Connection conn = new DBConnection().connect();
    static Statement stmt;
    static Scanner baca = new Scanner(System.in);
    static Layout display = new Layout();
    // instance variable for looping & query
    static String sql,lagi;
    public static void main(String[] Args) throws Exception{
    
           Auth();
   }
   public static void Auth() throws Exception{
   
                display.Header();
                System.out.println("[*] Login Dibutuhkan ! ");
                System.out.format("%65s","= = = = = = = = = = = = = = = = \n");
                System.out.format("%49s","|-| User ID    = "); String uid  = baca.next();
                System.out.format("%49s","|-| Kode Akses = "); String pass = baca.next();
                System.out.format("%65s","= = = = = = = = = = = = = = = = \n");
                
                String sql = "Select * from Users where id=? and key=?";
        
                try {
                    Connection conn = new DBConnection().connect();
                    PreparedStatement pstmt  = conn.prepareStatement(sql);
                    // set the value
                    pstmt.setString(1,uid);
                    pstmt.setString(2,pass);
                    
                    ResultSet rs  = pstmt.executeQuery();
                    if (rs.next()) {
                            String Nama = rs.getString("name");
                            String Level = rs.getString("level");
                            if (Level.equalsIgnoreCase("administrator")) {
                                rs.close();
                                conn.close();
                                adm.Admin(Nama,Level);
                            
                            } else {
                                pbc.Public(Nama,Level);
                            }
                        } else {
                            System.out.format("%60s","Maaf Anda Tidak Terdaftar !");
                            System.out.println();
                            System.out.print("Ingin coba lagi ? (y/t)");
                            while ((lagi = baca.next()).equalsIgnoreCase("y")) {
                                Auth();
                            }
                           
                            display.Footer();
                            System.exit(0);
                        }
                } catch (SQLException e) {System.out.println(e.getMessage());}
   }
}
