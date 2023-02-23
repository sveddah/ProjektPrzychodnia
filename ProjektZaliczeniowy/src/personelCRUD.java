import java.sql.*;


public class personelCRUD {

    private int id_pracownika;
    private String imie;
    private String nazwisko;
    private String adres;
    private int telefon;
    private int id_specjalizacji;

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public int getId_specjalizacji() {
        return id_specjalizacji;
    }

    public void setId_specjalizacji(int id_specjalizacji) {
        this.id_specjalizacji = id_specjalizacji;
    }

    public static void createPersonel() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PERSONEL " +
                    "(ID_PRACOWNIKA INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ID_SPECJALIZACJI INTEGER, " +
                    " IMIE VARCHAR(20), " +
                    " NAZWISKO VARCHAR(20), " +
                    " ADRES VARCHAR(20), " +
                    " TELEFON INTEGER, " +
                    " FOREIGN KEY (ID_SPECJALIZACJI) REFERENCES SPECJALIZACJA(ID_SPECJALIZACJI))";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);

        }
        System.out.println("Tabela Personel utworzona pomyślnie");
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

    public static void dropPersonelTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DROP TABLE PERSONEL";
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
