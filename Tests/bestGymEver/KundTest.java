package bestGymEver;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class KundTest {

    @Test
    void getBetalandeKund() {
        LocalDate idag = LocalDate.now();
        idag.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
        Kund k1 = new Kund("111111, person", idag + "");
        Kund k2 = new Kund("222222, person", "1970-01-01");
        assertTrue(k1.getBetalandeKund(idag));
        assertFalse(k2.getBetalandeKund(idag));
    }
}