 

public class Layout {
    
    public void Header() {
        clrscr();
         //System.out.println("********************************************************************************************");
         System.out.println("                                 UNIT PRODUKSI - SMK YADIKA 11                                  ");
         System.out.println("                                          v.0.8 - BETA                                          ");
         System.out.println("================================================================================================");

    }
    
    public void Footer() {
         System.out.println("================================================================================================");
         System.out.println("|                                         TERIMA KASIH                                         |");
         System.out.println("================================================================================================");
         System.out.println("|^| Credit By : \n- Ronny S (201843502340)\n- M.Hindardhi (201843502003)\n- Dicky A (201843502394)\n- Angga M (201843502394)");
         System.out.println("|*| Will be uploaded on my blog on December 25 2019");         
    }
      public void Inventory_View() {
         System.out.println("================================================================================================");
         System.out.println("||   ID   ||              NAMA BARANG             ||     VENDOR     ||     PRICE    ||   QTY  ||");
         System.out.println("================================================================================================");

    }
    
    public void Users_View() {
         System.out.println("================================================================================================");
         System.out.println("||       ID        ||        KEY        ||             NAME             ||       LEVEL        ||");
         System.out.println("================================================================================================");

    }
    public void StrukPay_View() {
         System.out.println("================================================================================");
         System.out.println("||   NO   ||             NAMA BARANG            ||     QTY     ||    TOTAL    ||");
         System.out.println("================================================================================");
    }
    
    public void clrscr(){
    //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception ex) {
            System.exit(0);
        }
    }
    
}
