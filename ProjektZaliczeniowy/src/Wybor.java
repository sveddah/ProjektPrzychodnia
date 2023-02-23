import jdk.jfr.StackTrace;

import java.util.Scanner;

public class Wybor {

    public static void wybor() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Wybierz akcje: 1 - Rejestracja nowego pacjenta, 2 - sprawdz czy tabela pacjenci jest pusta, 3 - wyswietl pacjentow, 4 - dodaj losowych pacjentów i wizyty, 5 - Zarejestruj sie na wizyte");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();
        switch (wybor) {
            case 1:
                System.out.println("Wybrales Rejestracje.");
                scanner.nextLine();
                System.out.println("Podaj imie: ");
                String imie = scanner.nextLine();
                System.out.println("Podaj nazwisko: ");
                String nazwisko = scanner.nextLine();
                System.out.println("Podaj adres: ");
                String adres = scanner.nextLine();
                System.out.println("Podaj telefon: ");
                int telefon = scanner.nextInt();
                System.out.println("Podaj PESEL: ");
                double pesel = scanner.nextDouble();
                pacjenciCRUD.createPacjent(imie, nazwisko, adres, telefon, pesel);

                break;
            case 2:
                System.out.println("Wybrales sprawdzanie czy tabela pacjenci jest pusta");
                // SPRAWDZANIE CZY TABELA JEST PUSTA
                pacjenciCRUD.checkIfAnyPatientsExist();
                break;
            case 3:
                System.out.println("Wybrales wyswietlanie pacjentow");
                // WYSWIETLANIE PACJENTOW
                pacjenciCRUD.readPacjenci();
                break;
            case 4:
                System.out.println("Wybrales dodawanie pacjentow do bazy (15 losowych rekordow) i wizyty");
                // ALTERNATYWNE DODAWANIE REKORDOW DO BAZY ABY NIE BYLA PUSTA
                pacjenciCRUD.insertPacjenci();
                // DODAWANIE WIZYT
                wizytaCRUD.addWizytyLosowe();
                break;
            case 5:
                System.out.println("Wybrales zarejestrowanie sie na wizyte");
                System.out.println();
                wizytaCRUD.addNewWizyta();
                break;
            default:
                System.out.println("Nie ma takiej opcji");
                break;
        }
    }





}
