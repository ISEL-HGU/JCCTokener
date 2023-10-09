package isel.codes;

public class FileWriter {
    public String createFile() { // 세금에 따라서 과일 가격을 sout 해줌
        String filename = "example.txt";

        try {
            writeFile(filename, "Hello, World!");
            String content = readFile(filename);

            if (!content.isEmpty()) {
                for (String line : content.split("\n")) {
                    switch (line) {
                        case "Hello, World!":
                            System.out.println("Found greeting line!");
                            break;
                        default:
                            System.out.println("Unknown line: " + line);
                            break;
                    }
                }
            } else {
                System.out.println("File is empty!");
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return filename;
    }
}
