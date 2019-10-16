package bestGymEver;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class BestGymEver {
    static Path path = Paths.get("C:\\intelliJ\\inlämning_2_gym\\customers.txt");

    String getAnvändarInput() {
        try {
            Scanner input = new Scanner(JOptionPane.showInputDialog("Skriv in personnummer eller namn på kunden"));
            if (input.useDelimiter("\n").hasNext()) {
                return input.next();
            }
        }catch(NullPointerException e) {
            JOptionPane.showMessageDialog(null, "programmet avslutas.");
            System.exit(0);
        }
        return "";
    }

    Kund getKund(String input) throws IOException {
        if (input.contains("\\")) {
            JOptionPane.showMessageDialog(null,"du får ej skriva in \" \\\\ \"");
            System.exit(0);
        }
        String[] allt2 = getKundLista(path.toString());
        String regEx = "((" + input + ", [a-zA-Z ]+)|(\\d+, " + input + "))";
        for (int i = 0; i < allt2.length; i += 2) {
            if (allt2[i].matches(regEx)) {
                return new Kund(allt2[i].trim(), allt2[i + 1].trim());
            }
        }
        JOptionPane.showMessageDialog(null, "Personen finns inte med i kund listan\nprogrammet avslutas.");
        System.exit(0);
        //unreachable satement för att kunna kompilera.
        return new Kund();
    }

    String[] getKundLista(String path) throws IOException {
        String allt = "";
        String line = "";
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            while (line != null) {
                allt += "\t" + line;
                line = buffer.readLine();
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"kan inte hitta filen.");
            e.getStackTrace();
            System.exit(-1);
        }
        return allt.trim().split("\t");
    }

    void printToFile(Kund kund1) throws IOException {
        LocalDate idag = LocalDate.now();
        idag.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
        boolean betalandeKund = kund1.getBetalandeKund(idag);

        if (betalandeKund) {
            JOptionPane.showMessageDialog(null, "Kunden är medlem.");

            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("betalande_kund.txt", true)))) {
                out.println(kund1.getPersonNummer() + ", " + kund1.getNamn() + "\n" + idag);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,"kan inte hitta filen.");
                e.getStackTrace();
            }
            printToName(kund1, idag);
        } else {
            JOptionPane.showMessageDialog(null, "kunden har varit medlem, men har inte betalat årsavgiften.");
        }
    }

    void printToName(Kund kund1, LocalDate idag) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(kund1.getNamn() + ".txt")))) {
            out.println(kund1.getPersonNummer() + ", " + kund1.getNamn() + "\n" + idag);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"kan inte hitta filen.");
            e.getStackTrace();
        }
    }
}
