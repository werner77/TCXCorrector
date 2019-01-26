package com.behindmedia.tcxcorrector;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.List;

public final class TCXCorrector {

    private static void usage(int exitCode) {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(120);
        formatter.printHelp("java " + TCXCorrector.class.getName() + " [OPTION]... [INPUT FILE] [OUTPUT FILE]", getOptions());
        System.exit(exitCode);
    }

    private static Options getOptions() {

        // create Options object
        Options options = new Options();
        options.addOption("h", "help", false, "Print this message");
        return options;
    }

    public static void main(String[] args) throws Exception {

        // create the parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(getOptions(), args);

            if (line.hasOption("h")) {
                usage(0);
            }

            List<String> argList = line.getArgList();

            if (argList.size() != 2) {
                usage(1);
            }

            final String inputFilePath = argList.get(0);
            final String outputFilePath = argList.get(1);

            Stylizer stylizer = new Stylizer();

            final InputStream styleSheet = TCXCorrector.class.getResourceAsStream("/reorder.xslt");

            stylizer.transform(styleSheet, new BufferedInputStream(new FileInputStream(inputFilePath)), new BufferedOutputStream(new FileOutputStream(outputFilePath)));

        } catch (ParseException exp) {
            usage(1);
        } catch (NumberFormatException exp) {
            usage(1);
        }
    }

}