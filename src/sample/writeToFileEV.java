package sample;

import java.io.*;

import static sample.Main.E_V_FILE_PATH;

public class writeToFileEV {

    public writeToFileEV(String s1, String s2) throws FileNotFoundException {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileOutputStream(E_V_FILE_PATH, true));
            printWriter.write(s1+"<html><i>"+s1+"</i><br/><ul><li><font color='#cc0000'><b>"+s2+"</b></font></li></ul></html>"+"\n");
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        if(printWriter != null) {
            printWriter.flush();
            printWriter.close();
        }
    }
}
