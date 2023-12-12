package main.java.com.lolz404.threadder;

public enum CliArgs {
    ERROR("error", ""),
    RUN("run", "-r"),
    THREADED("thread", "-t"),
    GENERATE("gen", "-g");

    public final String flag;
    public final String tack;

    CliArgs(String flag, String tack) {
        this.flag = flag;
        this.tack = tack;

    }

    public static CliArgs fromString(String flag) throws IllegalArgumentException {
        for (CliArgs ca : CliArgs.values()) {
            if (ca.flag.equals(flag) || ca.tack.equals(flag)) {
                return ca;
            }
        }
        throw new IllegalArgumentException();
    }

}
