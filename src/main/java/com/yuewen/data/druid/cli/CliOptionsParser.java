package com.yuewen.data.druid.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.yuewen.data.druid.exceptions.LoadPropertiesException;

public class CliOptionsParser {
	public static final Option OPTION_HELP = Option
            .builder("h")
            .required(false)
            .longOpt("help")
            .desc("Show the help message with descriptions of all options.")
            .build();
    public static final Option PATHS = Option
            .builder("paths")
            .required(true)
            .longOpt("hadoop-paths")
            .numberOfArgs(1)
            .argName("hadoop paths")
            .desc("The data paths in hadoop file system.")
            .build();

    public static final Option TEMPLATE = Option
            .builder("template")
            .required(true)
            .longOpt("json-template")
            .numberOfArgs(1)
            .argName("json-template")
            .desc("The json template used in Druid.io query or ingestion task")
            .build();

    public static final Option INTERVALS = Option
            .builder("intervals")
            .required(true)
            .longOpt("data-intervals")
            .numberOfArgs(1)
            .argName("data intervals")
            .desc("The data intervals that will calc")
            .build();
    public static final Option HOST = Option
            .builder("host")
            .required(true)
            .longOpt("host-path")
            .numberOfArgs(1)
            .argName("host path")
            .desc("The host path of Overlord(for index task) or MiddleManager(for query)")
            .build();

    public static void printHelpClient() {
        System.out.println("./DruidJobSubmit [OPTIONS]");
        System.out.println();
        System.out.println("The following options are available:");

        printHelpEmbeddedModeClient();

        System.out.println();
    }
    public static void printHelpEmbeddedModeClient() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setLeftPadding(5);
        formatter.setWidth(80);
        System.out.println("\n  Syntax: DruidJobSubmit [OPTIONS]");
        formatter.setSyntaxPrefix("  \"DruidJobSubmit\" options:");
        formatter.printHelp(" ", getEmbeddedModeClientOptions(new Options()));

        System.out.println();
    }
    
    private static void buildGeneralOptions(Options options) {
        options.addOption(OPTION_HELP);
    }
    
    public static Options getEmbeddedModeClientOptions(Options options) {
        buildGeneralOptions(options);
        options.addOption(PATHS);
        options.addOption(TEMPLATE);
        options.addOption(INTERVALS);
        options.addOption(HOST);
        return options;
    }
    
    public static CliOptions parseEmbeddedModeClient(String[] args) {
        try {
            DefaultParser parser = new DefaultParser();
            CommandLine line = parser.parse(getEmbeddedModeClientOptions(new Options()), args, true);
            return new CliOptions(
                    line.hasOption(CliOptionsParser.OPTION_HELP.getOpt()),
                    checkPaths(line),
                    checkDs(line),
                    checkIntervals(line),
                    checkHost(line)
                    );
        }
        catch (ParseException e) {
            throw new LoadPropertiesException(e.getMessage());
        }
    }
    public static String checkPaths(CommandLine line){
        final String paths = line.getOptionValue(CliOptionsParser.PATHS.getOpt());
        if(null == paths || paths.isEmpty()){
            throw new LoadPropertiesException("a data path in hadoop file system must enter");
        }
        return paths;
    }
    
    public static String checkDs(CommandLine line){
    	final String ds = line.getOptionValue(CliOptionsParser.TEMPLATE.getOpt());
    	if(null == ds || ds.isEmpty()){
    		throw new LoadPropertiesException("a ds of data must enter");
    	}
    	return ds;
    }
    
    public static String checkIntervals(CommandLine line){
    	final String intervals = line.getOptionValue(CliOptionsParser.INTERVALS.getOpt());
    	if(null == intervals || intervals.isEmpty()){
    		throw new LoadPropertiesException("a intervals of data must enter");
    	}
    	return intervals;
    }
    
    public static String checkHost(CommandLine line){
    	final String host = line.getOptionValue(CliOptionsParser.HOST.getOpt());
    	if(null == host || host.isEmpty()){
    		throw new LoadPropertiesException("a host must enter");
    	}
    	return host;
    }
    
}
