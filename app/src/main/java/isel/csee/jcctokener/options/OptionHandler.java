package isel.csee.jcctokener.options;

import org.apache.commons.cli.*;

public class OptionHandler {
    private boolean helpRequested;

    public Options createOptions() {
        Options options = new Options();

        options.addOption(Option.builder("h").longOpt("help").desc("Show the help page").build());
        options.addOption(Option.builder("i").longOpt("input").desc("A java file to read").build());
        options.addOption(Option.builder("s").longOpt("step").desc("Steps to using program").build());
        options.addOption(Option.builder("p").longOpt("percent").desc("The percentage to detect clones").build());

        return options;
    }

    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("h")) {
                helpRequested = true;
            } else {
                helpRequested = false;
            }








        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
