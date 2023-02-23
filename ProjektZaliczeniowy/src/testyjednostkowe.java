import java.sql.*;
import java.util.Scanner;

class testyjednostkowe {


    public static void utworzTabele() {
        pacjenciCRUD.createPacjenci();
        specjalizacjaCRUD.createSpecjalizacja();
        personelCRUD.createPersonel();
        wizytaCRUD.createWizyta();
        lekiCRUD.createLeki();
        receptaCRUD.createRecepta();
        salaCRUD.createSala();
    }



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
            int zmiena = stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Wizyta dodana pomyslnie");

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


    public static void checkIfAnyPatientsExist() {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT COUNT(*) FROM pacjenci";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                int result = rs.getInt(1);
                if(result == 0) {
                    System.out.println("Tabela jest pusta");;
                }
                else {
                    System.out.println("Tabela nie jest pusta");
                }
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void insertPersonel() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO PERSONEL (IMIE,NAZWISKO,ADRES,TELEFON,ID_SPECJALIZACJI) " +
                    "VALUES ('Jan', 'Kowalski', 'Warszawa', 123456789, 1), " +
                    "('Anna', 'Nowak', 'Kraków', 987654321, 2), " +
                    "('Tomasz', 'Wiśniewski', 'Gdańsk', 111111111, 3)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
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














    @org.junit.jupiter.api.Test
    void utworzTabeleTest() {
        utworzTabele();
    }

    @org.junit.jupiter.api.Test
    void createWizytaTest() {
        createWizyta();
    }

    @org.junit.jupiter.api.Test
    void insertWizytaTest() {
        wizytaCRUD wizyta = new wizytaCRUD();
        insertWizyta(wizyta);
    }

    @org.junit.jupiter.api.Test
    void wypiszWizytyTest() {
        wypiszWizyty();
    }

    @org.junit.jupiter.api.Test
    void czyWizytaIstniejeTest() {
        czyWizytaIstnieje("2020-12-12", 1);
    }

    @org.junit.jupiter.api.Test
    void createSpecjalizacjaTest() {
        createSpecjalizacja();
    }


    @org.junit.jupiter.api.Test
    void checkIfAnyPatientsExistTest() {
        checkIfAnyPatientsExist();
    }

    @org.junit.jupiter.api.Test
    void insertPersonelTest() {
        insertPersonel();
    }

    @org.junit.jupiter.api.Test
    void createReceptaTest() {
        createRecepta();
    }


















}