package frc.robot.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Logger {
    private PrintWriter fileWriter;

    public Logger(String name) throws IOException {
        // File file = new File("frc/robot/util/log.txt");
        fileWriter = new PrintWriter(name+".txt", "UTF-8");
        log("test");
        fileWriter.close();
    }

    public <T> void log(T... values) {
        try {
            for (T v : values) {
                System.out.println(v);
                fileWriter.println(v);
            }
        } catch (Exception e) {

        }
    }

    public <T> void log(Exception e, T v) {
        try {
            if (v != null) {
                fileWriter.println(v + ": " + e);
                return;
            }
            fileWriter.println(e);
        } catch (Exception ex) {

        }
    }

    public void breakLine() {
        try {
            fileWriter.println();
        } catch (Exception e) {

        }
    }

    public void breakLine(int i) {
        try {
            for (int k = 0; k < i; k++)
                fileWriter.println();
        } catch (Exception e) {

        }
    }

    // public static void testError() throws IOException {
    // try {
    // throw(new IllegalArgumentException("no poopy"));
    // } catch(Exception e) {
    // log(e, "lol");
    // }
    // }
}