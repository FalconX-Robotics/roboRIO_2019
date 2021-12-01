package app;

import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        double[] data = { -361, 12.3, -12.3, 361.1 };
        System.out.println(Arrays.toString(moduloGyroInfo(data, 360)));
    }

    private static double[] moduloGyroInfo(double[] data, int modulo) {
        modulo = Math.abs(modulo);
        for (int i = 0; i < data.length; i++) {
            double value = data[i];
            double sign = Math.signum(value);

            if (modulo < Math.abs(value)) {
                data[i] = (Math.abs(value) % modulo) * -sign;
            }
        }

        return data;
    }
}