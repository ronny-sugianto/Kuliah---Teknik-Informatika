
import java.sql.*;
import java.util.Scanner;
public class Public
{
    // instance variables - replace the example below with your own
    private Apps buka = new Apps();
    private Payments panggil = new Payments();
    private Layout display = new Layout();
    private Connection conn = new DBConnection().connect();
    private Scanner baca = new Scanner(System.in);
    private Statement stmt;
    private ResultSet rs;
    private String sql,lagi;
    private int jumtot;
    public void Public(String Nama,String Level) throws Exception
    {
        // initialise instance variables
       display.Header();
       
       System.out.println("[+] Hai, " + Nama + " (" + Level + ")");
       display.Inventory_View();
       sql = "Select * from Inventory";
       try {
           stmt = conn.createStatement();
           rs = stmt.executeQuery(sql);
           while (rs.next()) {
                  System.out.format("|| %-5s || %-36s || %-14s || Rp.%-7s ||  %-4s  ||",rs.getString("id"), rs.getString("name"),rs.getString("vendor"),rs.getInt("price"),rs.getInt("qty"));
                  System.out.println();                               
           }  
       } catch(Exception err) {System.err.println("Terjadi Kesalahan [Public] : " + err.getMessage());}
       System.out.println("============================================================================================");        
       System.out.println(" ========== MENU ==========\n" +
                          " [1] Payment\n" + 
                          " [2] Logout Account\n" +
                          " ==========================");                          
       System.out.print(  " Pilihan Anda (Hanya Angka) : ");
       int menu = baca.nextInt();
       switch (menu) {
                           case 1: panggil.Payments(Nama,Level,conn); 
                           //System.out.println("Mohon Maaf Fitur Ini Masih Dalam Tahap Pengembangan !");
                           return;
                           case 2: buka.Auth(); return;
                            default: 
                                    System.out.print("\nPilihan anda salah !\nIngin coba lagi ? (y/t)");
                                    while ((lagi = baca.next()).equalsIgnoreCase("y")) {
                                        Public(Nama,Level);
                                    }
                                    return;
                                    
                            }    
                        
                
        
     }

    }
