public class createTablesIfNotExist {
    // TWORZENIE TABELI JESLI NIE ISTNIEJA
    public static void utworzTabele() {
        pacjenciCRUD.createPacjenci();
        specjalizacjaCRUD.createSpecjalizacja();
        personelCRUD.createPersonel();
        wizytaCRUD.createWizyta();
        lekiCRUD.createLeki();
        receptaCRUD.createRecepta();
        salaCRUD.createSala();
    }
}
