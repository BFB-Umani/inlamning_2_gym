package bestGymEver;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BestGymEver {

    public static void main(String[] args) throws IOException {
        Kund kund1 = getKund(getAnvändarInput());
        printToFile(kund1);
    }

    static String getAnvändarInput() {
        Scanner input = new Scanner(JOptionPane.showInputDialog("Skriv in personnummer eller namn på kunden"));
        if (input.useDelimiter("\n").hasNext()) {
            return input.next();
        }
        return "";
    }

    static Kund getKund(String input) throws IOException {
        String[] allt2 = getKundLista();
        input = "((" + input + ", [a-zA-Z ]+)|(\\d+, " + input + "))";
        for (int i = 0; i < allt2.length; i += 2) {
            if (allt2[i].matches(input)) {
                return new Kund(allt2[i], allt2[i + 1]);
            }
        }
        JOptionPane.showMessageDialog(null, "Personen finns inte med i kund listan\nprogrammet avslutas.");
        System.exit(0);
        return new Kund("unreachable return", "behövs för att kompilera, fight me");
    }

    static String[] getKundLista() throws IOException {
        String allt = "";
        String line = "";
        try (BufferedReader buffer = new BufferedReader(new FileReader("customers.txt"))) {
            while (line != null) {
                allt += "\t" + line;
                line = buffer.readLine();
            }

        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
        return allt.trim().split("\t");
    }

    static void printToFile(Kund kund1) throws IOException {
        boolean betalandeKund = kund1.getBetalandeKund();

        if (betalandeKund) {
            JOptionPane.showMessageDialog(null, "Kunden är medlem.");

            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("betalande_kund.txt", true)))) {

                LocalDate idag = LocalDate.now();
                idag.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
                out.println(kund1.getPersonNummer() + ", " + kund1.getNamn() + "\n" + idag);
            } catch (FileNotFoundException e) {
                e.getStackTrace();
            }
            printToName(kund1);
        } else {
            JOptionPane.showMessageDialog(null, "kunden har varit medlem, men har inte betalat årsavgiften.");
        }
    }

    static void printToName(Kund kund1) throws IOException {
        boolean betalandeKund = kund1.getBetalandeKund();

        if (betalandeKund) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(kund1.getNamn() + ".txt")))) {

                LocalDate idag = LocalDate.now();
                idag.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
                out.println(kund1.getPersonNummer() + ", " + kund1.getNamn() + "\n" + idag);
            } catch (FileNotFoundException e) {
                e.getStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "kunden har varit medlem, men har inte betalat årsavgiften.");
        }
    }
}
