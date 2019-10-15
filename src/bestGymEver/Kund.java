package bestGymEver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Kund {
    private String personNummer;
    private String namn;
    private LocalDate senastBetalning;

    Kund(String namn, String betalning){
        this.personNummer = namn.substring(0,namn.indexOf(','));
        this.namn = namn.substring(namn.indexOf(" "));
        this.senastBetalning = LocalDate.parse(betalning);
    }
    boolean getBetalandeKund(){
        LocalDate idag = LocalDate.now();
        idag.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));

        return 366 > Math.abs(ChronoUnit.DAYS.between(idag, this.senastBetalning));
    }
    String getNamn() {
        return this.namn;
    }
    String getPersonNummer() {
        return this.personNummer;
    }
}