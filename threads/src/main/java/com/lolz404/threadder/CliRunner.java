package main.java.com.lolz404.threadder;

class CliRunner {

    public static void main(String[] args) {
        final var PATH_OF_FILES = "C:\\Users\\Coury\\Documents\\Code\\experiments\\threads\\generated";

        if (args.length > 0) {
            var cliArg = CliArgs.ERROR;
            try {
                cliArg = CliArgs.fromString(args[0]);
            } catch (IllegalArgumentException e) {
                System.out.println("help text");
                System.exit(1);
            }

            switch (cliArg) {
                case GENERATE:
                    System.out.println("GENERATE");
                    var genny = new GennyFromTheBlock(PATH_OF_FILES);
                    genny.make(Integer.parseInt(args[1]));
                    System.exit(0);
                    break;

                case RUN:
                    var listFilePath = args[1];
                    var run = new RunAdder(listFilePath);
                    run.run();
                    System.exit(0);
                    break;

                case THREADED:
                    System.out.println("THREADED");
                    // TODO: Added thread sub flag

                    System.exit(0);
                    break;

                case ERROR:
                default:
                    System.out.println("help text");
                    System.exit(1);
                    break;
            }
        }
        System.out.println("help text");
        System.exit(1);

    }
}