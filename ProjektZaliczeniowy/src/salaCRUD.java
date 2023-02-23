import java.sql.*;

public class salaCRUD {
    private int id_sali;
    private int nr_sali;

    public int getId_sali() {
        return id_sali;
    }

    public void setId_sali(int id_sali) {
        this.id_sali = id_sali;
    }

    public int getNr_sali() {
        return nr_sali;
    }

    public void setNr_sali(int nr_sali) {
        this.nr_sali = nr_sali;
    }

    public static void createSala() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SALA " +
                    "(ID_SALI INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NR_SALI INTEGER)";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tabela sala utworzona pomyslnie");
    }

    public void insertSala() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO SALA (NR_SALI) " +
                    "VALUES (101),(102),(103)";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


}
