package isel.csee.jcctokener;

import isel.csee.jcctokener.parser.ASTMaker;
import org.eclipse.jdt.core.dom.ASTParser;

import java.io.*;

public class jCCTokener {


    public static void main(String[] args) throws IOException {
        jCCTokener jCCTokener = new jCCTokener();

        jCCTokener.run(args);

    }

    public void run(String[] args) throws IOException {
        String temp;
        String filePath = "";

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while ((temp = reader.readLine()) != null) {
            filePath += temp;
        }

        ASTMaker astMaker = new ASTMaker(filePath);

        astMaker.parserCodes();
    }
}
