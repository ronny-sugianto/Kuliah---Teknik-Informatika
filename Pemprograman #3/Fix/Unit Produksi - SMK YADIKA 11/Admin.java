import java.util.Scanner;
public class Admin
{
    private Apps panggil = new Apps();
    private Users userManage = new Users();
    private Inventory inventoryManage = new Inventory();
    private Layout display = new Layout();
    private Scanner baca = new Scanner(System.in);
    private String lagi;
    public void Admin (String Nama,String Level) throws Exception
    {
         display.Header();
                System.out.println("[+] Hai, " + Nama + " (" + Level + ")");
                System.out.println(" ========== MENU ==========\n" +
                                   " [1] Users Management\n" + 
                                   " [2] Inventory Management\n" +
                                   " [3] Logout Account\n" +
                                   " ==========================");
                System.out.print(  " Pilihan Anda (Hanya Angka) : ");                 
                try {
                    int menu = baca.nextInt();
                    switch (menu) {
                            case 1: userManage.Users(Nama,Level); return;            
                            case 2: inventoryManage.Inventory(Nama,Level); return;
                            case 3: panggil.Auth(); return;
                            default: System.out.print("\nPilihan anda salah !\nIngin coba lagi ? (y/t)");
                                     while ((lagi = baca.next()).equalsIgnoreCase("y")) {
                                       Admin(Nama,Level);
                                     }
                                     return;
                        }
                } catch(Exception err) {System.err.println("Terjadi Kesalahan : " + err.getMessage());}
                
    }
}
