package bestGymEver;

import org.junit.jupiter.api.Test;

import java.io.*;


import static org.junit.jupiter.api.Assertions.*;

class BestGymEverTest {

    @Test
    void getAnvändarInput() {
        BestGymEver test = new BestGymEver();
        assertNotNull(test.getAnvändarInput());

    }

    @Test
    void getKundLista() throws IOException {

        File path = new File("C:\\intelliJ\\inlämning_2_gym\\test.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path.toString())));
        out.println("7603021234, Alhambra Aromes");
        out.println("2018-07-01");
        out.close();

        String[] testArray = {"7603021234, Alhambra Aromes", "2018-07-01"};
        BestGymEver test = new BestGymEver();
        assertNotNull(test.getKundLista(path.toString()));
        assertArrayEquals(testArray, test.getKundLista(path.toString()));

        path.delete();

    }
    @Test
    void getKund() throws IOException {
        BestGymEver test = new BestGymEver();
        Kund kund1 = test.getKund("Alhambra Aromes");
        assertEquals("Alhambra Aromes", kund1.getNamn());
        assertNotNull(kund1);
    }
}