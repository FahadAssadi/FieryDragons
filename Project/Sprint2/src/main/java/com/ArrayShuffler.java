package main.java.com;
import java.util.Random;

public class ArrayShuffler  {
    static void shuffleArray(float[][] array) {
        int index;
        float[] temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
