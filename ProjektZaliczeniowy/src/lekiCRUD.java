import java.sql.*;
public class lekiCRUD {
    private int id_leku;
    private String nazwa_leku;

    public int getId_leku() {
        return id_leku;
    }

    public void setId_leku(int id_leku) {
        this.id_leku = id_leku;
    }

    public String getNazwa_leku() {
        return nazwa_leku;
    }

    public void setNazwa_leku(String nazwa_leku) {
        this.nazwa_leku = nazwa_leku;
    }

    public static void createLeki() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS LEKI" +
                    "(ID_LEKU INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NAZWA_LEKU VARCHAR(25))";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Tabela leki utworzona pomyslnie");
    }

    public static void insertLeki() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO LEKI (NAZWA_LEKU) VALUES ('Paracetamol'), ('APAP'),('Neosine'),('Gripex');";
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
