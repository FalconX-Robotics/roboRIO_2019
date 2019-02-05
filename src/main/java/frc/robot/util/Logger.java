package frc.robot.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;

public class Logger { 
    private final String FILE_NAME, FILE_PATH;
    private File file;
    private BufferedWriter bw;
    private static String fileSeparator = System.getProperty("file.separator"); //Don't replace with '\'

    //CONFIG
    private final boolean UNIQUE_ERRORS_ONLY;
    private final boolean RELEVANT_ERRORS_ONLY;
    private List<Exception> uniqueErrorLog = new ArrayList<Exception>();
 
    public Logger(final String FILE_NAME, final String FILE_PATH, final boolean UNIQUE_ERRORS_ONLY, final boolean RELEVANT_ERRORS_ONLY) {
        //EXAMPLE: new Logger("log", "src" + fileSeparator + "util");

        this.FILE_NAME = FILE_NAME;
        this.FILE_PATH = FILE_PATH; // Relative
        this.UNIQUE_ERRORS_ONLY = UNIQUE_ERRORS_ONLY;
        this.RELEVANT_ERRORS_ONLY = RELEVANT_ERRORS_ONLY;

        try {
            file = new File(FILE_PATH + fileSeparator + FILE_NAME + ".txt");

            // System.out.println(file.getParentFile().mkdirs());
            if (!file.exists()) {
                file.createNewFile();
             }

            bw = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean put(String data) {
        try {
            bw.write(data);
            // System.out.println("Putting exception 5");
            bw.flush();
            return true;
        } catch (IOException e) {
            // System.out.println("error 5");
            e.printStackTrace();
            return false;
        }
    }


    private boolean put(String data, boolean newLine) {
        if (newLine) {
            // System.out.println("Putting exception 4");
            return put(data + System.lineSeparator());
        } else {
            return put(data);
        }
    }

    private String convertStackTraceToString(Exception e) { //Source: https://www.programiz.com/java-programming/examples/convert-stack-trace-string
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    private boolean filterRelevantErrors(StackTraceElement[] stackTraceElements) {
        // for (StackTraceElement e : stackTraceElements) {
            
        // }

        return false;
    }
    

    private synchronized boolean filterUniqueErrors(Exception e) {
        for (Exception uniqueError : uniqueErrorLog) {
            if (convertStackTraceToString(e).equals(convertStackTraceToString(uniqueError))) {
                return false;
            }
        }

        uniqueErrorLog.add(e);
        return true;
    }

    private boolean put(Exception e) {
        String data = null;
        
        //Filters
        if (UNIQUE_ERRORS_ONLY) {
            if (!filterUniqueErrors(e))
                return false;
        }
        if (RELEVANT_ERRORS_ONLY) {
            if (!filterRelevantErrors(e.getStackTrace()))
                return false;
        }

        // System.out.println("Putting exception 2");
        // if (e.getMessage() == null) {
        return log(convertStackTraceToString(e));
        // }
        // return log(e.getMessage() + ":", convertStackTraceToString(e));
    }

    public <T extends Exception> boolean log(T exception) {
        try {
            put(exception);
            // System.out.println("Putting exception 1");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            put(convertStackTraceToString(e), true);
        }
        return false;
    }
    
    //Can not create array with generics
    public boolean log(Object... array) {
        try {
            for (Object data : array) {
                put(data.toString(), true);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            put(convertStackTraceToString(e), true);
        }
        return false;
    }

    public boolean br() {
        return put("");
    }

    public String getFileName() {
        return FILE_NAME;
    }

    public String getFilePath() {
        return FILE_PATH;
    }
}