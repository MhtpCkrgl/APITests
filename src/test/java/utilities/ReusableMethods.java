package utilities;

import java.util.Random;

public class ReusableMethods {
    public static int generateRandomNumber() {
        Random random = new Random();
        int min = 1;
        int max = 10;

        // nextInt metodunu kullanarak min (dahil) ile max (hariç) arasında rastgele bir sayı elde ediyoruz
        int randomNumber = random.nextInt(max - min) + min;
        return randomNumber;

    }
}
