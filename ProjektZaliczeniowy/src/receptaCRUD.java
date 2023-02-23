import java.sql.*;
public class receptaCRUD {
    private int id_recepty;
    private int id_pacjenta;
    private int id_leku;

    public int getId_recepty() {
        return id_recepty;
    }

    public void setId_recepty(int id_recepty) {
        this.id_recepty = id_recepty;
    }

    public int getId_pacjenta() {
        return id_pacjenta;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
    }

    public int getId_leku() {
        return id_leku;
    }

    public void setId_leku(int id_leku) {
        this.id_leku = id_leku;
    }

    public static void createRecepta() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS RECEPTA " +
                    "(ID_RECEPTY INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " ID_PACJENTA INTEGER, " +
                    " ID_LEKU INTEGER, " +
                    "FOREIGN KEY (ID_PACJENTA) REFERENCES PACJENCI(ID_PACJENTA)," +
                    "FOREIGN KEY (ID_LEKU) REFERENCES LEKI(ID_LEKU))";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Tabela recepta utworzona pomyslnie");
    }



}
