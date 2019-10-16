package bestGymEver;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BestGymEver gym = new BestGymEver();
        Kund kund1 = gym.getKund(gym.getAnvändarInput());
        gym.printToFile(kund1);
    }
}
