import java.sql.*;
import java.util.Scanner;
public class wizytaCRUD {

    private int id_wizyty;
    private String pesel;
    private int id_pracownika;
    private String data;
    private String godzina;


    public int getId_wizyty() {
        return id_wizyty;
    }

    public void setId_wizyty(int id_wizyty) {
        this.id_wizyty = id_wizyty;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }
    static wizytaCRUD wizyta = new wizytaCRUD();



    public static void createWizyta() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS WIZYTA " +
                    "(ID_WIZYTY INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " PESEL INTEGER, " +
                    " ID_PRACOWNIKA  INTEGER, " +
                    " DATA DATE, " +
                    " FOREIGN KEY (PESEL) REFERENCES PACJENCI(PESEL), " +
                    " FOREIGN KEY (ID_PRACOWNIKA) REFERENCES PERSONEL(ID_PRACOWNIKA)) ";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tabela WIZYTA utworzona pomyslnie");
    }

    public static void insertWizyta(wizytaCRUD wizyta) {

        try {
            Connection c = null;
            Statement stmt = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO WIZYTA (PESEL,ID_PRACOWNIKA,DATA) " +
                    "VALUES (" + wizyta.getPesel() + "," + wizyta.getId_pracownika() + "," + wizyta.getData() + ");";
//            System.out.println(sql);
            int zmiena = stmt.executeUpdate(sql);
//          System.out.println(zmiena);
            stmt.close();
            // tu commit wywalał
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Wizyta dodana pomyslnie");

    }

    public static void deleteWizyta() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DROP TABLE WIZYTA;";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    public static void addWizytyLosowe() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO WIZYTA (ID_PRACOWNIKA,PESEL,DATA) " +
                    "VALUES (2,98765432175,'2023-02-02 13:00:00');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO WIZYTA (ID_PRACOWNIKA,PESEL,DATA) " +
                    "VALUES (3,98765432175,'2023-02-02 14:00:00');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO WIZYTA (ID_PRACOWNIKA,PESEL,DATA) " +
                    "VALUES (1,1240803919,'2023-02-02 10:00:00');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO WIZYTA (ID_PRACOWNIKA,PESEL,DATA) " +
                    "VALUES (3,98766432120,'2023-02-03 11:00:00');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO WIZYTA (ID_PRACOWNIKA,PESEL,DATA) " +
                    "VALUES (2,1240803919,'2023-02-03 12:00:00');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO WIZYTA (ID_PRACOWNIKA,PESEL,DATA) " +
                    "VALUES (1,98766432120,'2023-02-03 12:30:00');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO WIZYTA (ID_PRACOWNIKA,PESEL,DATA) " +
                    "VALUES (3,1240803919,'2023-02-03 13:30:00');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void wypiszWizyty() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM WIZYTA;");
            while (rs.next()) {
                int id_wizyty = rs.getInt("ID_WIZYTY");
                int id_pracownika = rs.getInt("ID_PRACOWNIKA");
                double pesel = rs.getInt("PESEL");
                String data = rs.getString("DATA");
                System.out.println("ID WIZYTY = " + id_wizyty);
                System.out.println("ID PRACOWNIKA = " + id_pracownika);
                System.out.println("PESEL = " + pesel);
                System.out.println("DATA = " + data);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static boolean czyWizytaIstnieje(String datawizyty, int id_pracownika) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM WIZYTA WHERE DATA = " + datawizyty + " and id_pracownika = " + id_pracownika + ";");
            if (rs.next()) {
                rs.close();
                stmt.close();
                c.close();
                return true;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }


    public static void addNewWizyta() {
        Connection c = null;
        Statement stmt = null;
        String calaData = null;
        int lekarz = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj pesel pacjenta: ");
            String peselPacjenta = scanner.nextLine();
            if (pacjenciCRUD.checkPatient(peselPacjenta) == 1) {
                System.out.println("Podaj lekarza, do którego chcesz się zapisać: ");
                System.out.println("1 - Lekarz rodzinny");
                System.out.println("2 - Dermatolog");
                System.out.println("3 - Stomatolog");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        lekarz = 1;
                        break;
                    case 2:
                        lekarz = 2;
                        break;
                    case 3:
                        lekarz = 3;
                        break;
                    default:
                        System.out.println("Nie ma takiej opcji");
                        break;
                }
                ResultSet rs = null;
                scanner.nextLine();
                do {
                    System.out.println("Podaj date wizyty: (yyyy-mm-dd)");
                    String dataWizyty = scanner.nextLine();
                    System.out.println("Podaj godzine wizyty: (hh:mm:ss)");
                    String godzinaWizyty = scanner.nextLine();
                    calaData = "'" + dataWizyty + " " + godzinaWizyty + "'" ;
                    stmt = c.createStatement();
                    String sql = "SELECT count(ID_WIZYTY) FROM WIZYTA WHERE DATA = " + calaData + " and id_pracownika = " + lekarz + ";";
                    stmt.executeUpdate(sql);
                    rs = stmt.executeQuery(sql);
                } while (rs.getInt(1) > 0);
                wizyta.setPesel(peselPacjenta);
                wizyta.setData(calaData);
                wizyta.setId_pracownika(lekarz);
                c.close();
                wizytaCRUD.insertWizyta(wizyta);
            } else {
                System.out.println("Nie ma takiego pacjenta w bazie");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }


}







