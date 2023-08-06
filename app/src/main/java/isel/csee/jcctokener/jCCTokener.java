package isel.csee.jcctokener;

import isel.csee.jcctokener.files.RelatedClassSelector;
import isel.csee.jcctokener.parser.jCCParser;
import org.eclipse.jdt.core.dom.ASTParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class jCCTokener {

    public static void main(String[] args) throws IOException {
        jCCTokener jCCTokener = new jCCTokener();

        jCCTokener.run(args);

        File file = new File("/Users/kimdong-gyu/Desktop/JChecker/JCCTokener/2023-1-java/SourceCodes");
        File[] files = file.listFiles();

        for(int i = 0; i < files.length; i++) {
            RelatedClassSelector relatedClassSelector = new RelatedClassSelector(files[i].getPath(),
                    "/Users/kimdong-gyu/Desktop/JChecker/JCCTokener/2023-1-java/SelectedCodes");

            relatedClassSelector.selectRelatedClass(files[i].getPath());
        }

    }

    public void run(String[] args) throws IOException {
        String temp;
        String filePath = args[0];
        String source = new String(Files.readAllBytes(Paths.get(filePath)));

//        jCCTokener.fileListGetter("/Users/kimdong-gyu/Desktop/JChecker/JCCTokener/app/src/main/java");


        jCCParser jCCParser = new jCCParser(source);
        jCCParser.parserCodes();
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
