import java.sql.*;
import java.util.Scanner;
public class specjalizacjaCRUD {
    private int id_specjalizacji;
    private String nazwa_specjalizacji;

    public int getId_specjalizacji() {
        return id_specjalizacji;
    }

    public void setId_specjalizacji(int id_specjalizacji) {
        this.id_specjalizacji = id_specjalizacji;
    }

    public String getNazwa_specjalizacji() {
        return nazwa_specjalizacji;
    }

    public void setNazwa_specjalizacji(String nazwa_specjalizacji) {
        this.nazwa_specjalizacji = nazwa_specjalizacji;
    }

    public static void createSpecjalizacja() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SPECJALIZACJA " +
                    "(ID_SPECJALIZACJI INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NAZWA_SPECJALIZACJI TEXT)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Tabela SPECJALIZACJA utworzona pomyslnie");
    }

    public static void insertSpecjalizacja() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO SPECJALIZACJA (NAZWA_SPECJALIZACJI) " +
                    "VALUES ('Lekarz rodzinny'), ('Dermatolog'),('Stomatolog') ";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
