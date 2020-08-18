package br.com.codenation;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StatisticUtil {

    public static int average(int[] elements) {
        return (elements != null && elements.length > 0) ?
                (int) IntStream.of(elements).average().getAsDouble() : 0;
    }

    public static int mode(int[] elements) {
        if (elements != null && elements.length > 0) {
            boolean goToNextElement;
            int bigger = 0, smaller, quantity, line = 0, modeIndex = -1;
            int[][] matrizModeRepetition = new int[elements.length][2];

            Arrays.sort(elements);

            for (int i = 0; i < elements.length; i++) {

                goToNextElement = true;
                quantity = 0;

                do {
                    if ((i + 1) == elements.length) {
                        goToNextElement = false;
                        continue;
                    }

                    if (i < elements.length && (i + 1) != elements.length && elements[i] == elements[i + 1]) {
                        i++;
                    } else {
                        goToNextElement = false;
                    }

                } while (goToNextElement);

                for (int element : elements)
                    if (elements[i] == element) quantity++;

                if (quantity > 1) {
                    matrizModeRepetition[line][0] = elements[i];
                    matrizModeRepetition[line][1] = quantity;
                    line++;
                }
            }

            if (line == 1)
                return matrizModeRepetition[0][0];

            if (line > 0) {
                smaller = matrizModeRepetition[0][1];
                for (int i = 0; i < line; i++) {
                    if (matrizModeRepetition[i][1] > bigger) {
                        bigger = matrizModeRepetition[i][1];
                        modeIndex = i;
                    }
                    if (matrizModeRepetition[i][1] < smaller)
                        smaller = matrizModeRepetition[i][1];
                }
                if (modeIndex != -1 && bigger != smaller)
                    return matrizModeRepetition[modeIndex][0];
            }
        }
        return 0;
    }

    public static int median(int[] elements) {
        if (elements != null && elements.length > 1) {
            Arrays.sort(elements);
            int middle = elements.length / 2;
            return elements.length % 2 != 0 ? elements[middle] : (elements[middle - 1] + elements[middle]) / 2;
        }
        return 0;
    }

}
