import java.sql.*;

public class pacjenciCRUD {
    private int id_pacjenta;
    private String imie;
    private String nazwisko;
    private String adres;
    private int telefon;
    private double pesel;

    public int getId_pacjenta() {
        return id_pacjenta;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
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

    public double getPesel() {
        return pesel;
    }

    public void setPesel(double pesel) {
        this.pesel = pesel;
    }

    public static void createPacjenci() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PACJENCI " +
                    "(ID_PACJENTA INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " IMIE VARCHAR(20), " +
                    " NAZWISKO VARCHAR(20), " +
                    " ADRES VARCHAR(20), " +
                    " TELEFON INTEGER, " +
                    " PESEL TEXT(11) )";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Tabela Pacjenci utworzona pomyslnie");

    }

public static void createPacjent(String imie,String nazwisko,String adres,int telefon,double pesel) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO PACJENCI (IMIE,NAZWISKO,ADRES,TELEFON,PESEL) " +
                    "VALUES ('" + imie+ "', '" + nazwisko + "','" + adres + "', " + telefon + ", " + pesel + ")";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Pacjent dodany pomyslnie");
    }

    public static void readPacjenci() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PACJENCI;" );
            while ( rs.next() ) {
                int id_pacjenta = rs.getInt("id_pacjenta");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String adres = rs.getString("adres");
                int telefon = rs.getInt("telefon");
                String pesel = rs.getString("pesel");
                System.out.println( "ID_PACJENTA = " + id_pacjenta );
                System.out.println( "IMIE = " + imie );
                System.out.println( "NAZWISKO = " + nazwisko );
                System.out.println( "ADRES = " + adres );
                System.out.println( "TELEFON = " + telefon );
                System.out.println( "PESEL = " + pesel );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println();
    }

    public static int checkPatient(String pesel) {
        int result = 0;
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT COUNT(*) FROM pacjenci WHERE pesel = '" + pesel + "'";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                result = rs.getInt(1);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        finally {
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
        return result;
    }

    public static void insertPacjenci() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO PACJENCI (IMIE,NAZWISKO,ADRES,TELEFON,PESEL) " +
                    "VALUES ('Jan', 'Kowalski', 'Warszawa', 605291293, 69129382109), " +
                   "('Anna', 'Kowalska', 'ul. Wiejska 1', 609120239, 78129341283), " +
            "('Jan', 'Nowak', 'ul. Miła 2', 987654321, 98765432120), " +
            "('Maria', 'Wiśniewska', 'ul. Kolejowa 3', 98765432, 98765432131), " +
            "('Tomasz', 'Mazur', 'ul. Lesna 4', 9876543, 98765432142), " +
            "('Agnieszka', 'Kaczmarek', 'ul. Parkowa 5', 9876554, 98765432153), " +
            "('Andrzej', 'Wojciechowski', 'ul. Brzozowa 6', 9876564, 98765432164), " +
            "('Joanna', 'Kwiatkowska', 'ul. Lipowa 7', 9876574, 98765432175), " +
            "('Piotr', 'Krawczyk', 'ul. Sosnowa 8', 9876584, 98765432186), " +
            "('Katarzyna', 'Zielińska', 'ul. Jagodowa 9', 9876594, 98765432197), " +
            "('Janusz', 'Wróbel', 'ul. Złota 10', 9876654, 98766432108), " +
            "('Marta', 'Jankowska', 'ul. Biała 11', 9876644, 98766432199), " +
            "('Grzegorz', 'Kaczor', 'ul. Cicha 12', 9876634, 98766432120), " +
            "('Ewa', 'Kozłowska', 'ul. Słoneczna 13', 9876314, 98766432131), " +
            "('Adam', 'Wieczorek', 'ul. Zielona 14', 9876314, 98766432142), " +
            "('Magdalena', 'Górska', 'ul. Długa 15', 9876344, 98766432153); ";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println();
        System.out.println("Pomyslnie dodano 15 pacjentów do bazy danych");
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

    public static void deletePacjenci() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DROP TABLE PACJENCI";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println();
        System.out.println("Pomyslnie usunięto pacjenta z bazy danych");
    }












}