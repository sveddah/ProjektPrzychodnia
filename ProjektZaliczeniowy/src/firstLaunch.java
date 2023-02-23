public class firstLaunch {

    public static void firstInsert() {
        pacjenciCRUD.insertPacjenci();
        personelCRUD.insertPersonel();
        lekiCRUD.insertLeki();
        specjalizacjaCRUD.insertSpecjalizacja();
        wizytaCRUD.addWizytyLosowe();
        wizytaCRUD.wypiszWizyty();
    }
}
