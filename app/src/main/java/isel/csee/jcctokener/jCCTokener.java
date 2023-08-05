package isel.csee.jcctokener;

import isel.csee.jcctokener.parser.jCCParser;
import org.eclipse.jdt.core.dom.ASTParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

//        jCCTokener.fileListGetter("/Users/kimdong-gyu/Desktop/JChecker/JCCTokener/app/src/main/java");


        jCCParser jCCParser = new jCCParser(source);

        ASTParser parser = jCCParser.parserCodes();
    }

    public static void fileListGetter(String directoryPath) {
        File path = new File(directoryPath);
        File[] fileList = path.listFiles();

        for(int i = 0; i < fileList.length; i++) {
            if(fileList[i].isDirectory()) {
                fileListGetter(fileList[i].getPath());
            } else {
                System.out.println(fileList[i]);
            }
        }

    }
}
