import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private Connection koneksi;
    public Statement stmt;
    public Connection connect(){
       //untuk koneksi ke driver
       try{
           Class.forName("org.sqlite.JDBC");
       }catch(ClassNotFoundException ex){
           System.err.println("Tidak ada Driver!\n" + ex);
       }
       //untuk koneksi ke database
       try{
           //String url="jdbc:sqlite:db/litedb.om4gus";
           String url="jdbc:sqlite:apps.db";
           koneksi=DriverManager.getConnection(url);
           stmt = koneksi.createStatement();
       }catch(SQLException se){
           System.out.println("Gagal koneksi "+se);
       }
       return koneksi;
    }
}