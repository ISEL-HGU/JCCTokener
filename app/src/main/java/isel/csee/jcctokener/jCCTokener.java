package isel.csee.jcctokener;

import isel.csee.jcctokener.parser.ASTMaker;
import org.eclipse.jdt.core.dom.ASTParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.eclipse.osgi.storage.StorageUtil.readFile;

public class jCCTokener {


    public static void main(String[] args) throws IOException {
        jCCTokener jCCTokener = new jCCTokener();

        jCCTokener.run(args);

    }

    public void run(String[] args) throws IOException {
        String temp;
        String filePath = args[0];

        File file = new File(filePath);
        String source = new String(Files.readAllBytes(Paths.get(filePath)));



        ASTMaker astMaker = new ASTMaker(source);

        astMaker.parserCodes();
    }
}
