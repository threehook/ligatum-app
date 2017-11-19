package nl.tvdven.ligatum.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Cli {

    //    private final String LIGATUM_RUN_PATH = Ligatum.class.getCanonicalName();
    private static final Logger logger = LoggerFactory.getLogger(Cli.class);
    //    private Options options;
    private Options agentOptions;

    public Cli() {
//        options = new Options()
//                .addOption("v", "version", false, "Ligatum's version")
//                .addOption("h", "help", true, "High level help message or help for a specific command and optional arguments");
//        mainOptionGroup.addOption(Option.builder("v").longOpt("version").desc("Version").build());
//        mainOptionGroup.addOption(Option.builder("h").longOpt("help").desc("Help").build());

        agentOptions = new Options()
                .addRequiredOption("bind", null,true, "Address to bind network listeners to, e.g. 0.0.0.0:7946. To use an IPv6 address, specify [::1] or [::1]:7946.")
                .addOption("iface", false, "Network interface to bind to. Can be used instead of -bind if the interface is known but not the address. If both are provided, then Ligatum verifies that the interface has the bind address that is provided. This flag also sets the multicast device used for -discover.")
                .addOption("advertise", true, "Address to advertise to the other cluster members")
                .addOption("configFile", true, "Path to a JSON file to read configuration from. This can be specified multiple times.")
                .addOption("configDir", true, "Path to a directory to read configuration files from. This will read every file ending in \".json\" as configuration in this directory in alphabetical order.")
                .addOption("discover", true, "A cluster name used to discovery peers. On networks that support multicast, this can be used to have peers join each other without an explicit join.")
                .addOption("encrypt", true, "Key for encrypting network traffic within Ligatum. Must be a base64-encoded 16-byte key.")
                .addOption("keyringFile", false, "The keyring file is used to store encryption keys used by Ligatum. As encryption keys are changed, the content of this file is updated so that the same keys may be used during later agent starts.")
                .addOption("eventHandler", true, "Script to execute when events occur. This can be specified multiple times. See the event scripts section below for more info.")
                .addOption("join", false, "An initial agent to join with. This flag can be specified multiple times.")
                .addOption("logLevel", true, "Log level of the agent.")
                .addOption("node", true, "Name of this node. Must be unique in the cluster")
                .addOption("profile", true, "[lan|wan|local] Profile is used to control the timing profiles used in Ligatum. The default if not provided is lan.")
                .addOption("protocol", true, "Ligatum protocol version to use. This defaults to the latest version, but can be set back for upgrades.")
                .addOption("rejoin", false, "Ignores a previous leave and attempts to rejoin the cluster. Only works if provided along with a snapshot file.")
                .addOption("retryJoin", true, "An agent to join with. This flag be specified multiple times. Does not exit on failure like -join, used to retry until success.")
                .addOption("retryInterval", true, "Sets the interval on which a node will attempt to retry joining nodes provided by -retry-join. Defaults to 30s.")
                .addOption("retryMax", true, "Limits the number of retry events. Defaults to 0 for unlimited.")
                .addOption("role", true, "The role of this node, if any. This can be used by event scripts to differentiate different types of nodes that may be part of the same cluster.'-role' is deprecated in favor of '-tag role=foo'.")
                .addOption("rpcAddr", true, "Address to bind the RPC listener.")
                .addOption("snapshot", true, "The snapshot file is used to store alive nodes and event information so that Ligatum can rejoin a cluster and avoid event replay on restart.")
                .addOption("tag", true, "Tag can be specified multiple times to attach multiple key/value tag pairs to the given node.")
                .addOption("tags", true, "The tags file is used to persist tag data. As an agent's tags are changed, the tags file will be updated. Tags can be reloaded during later agent starts. This option is incompatible with the '-tag' option and requires there be no tags in the agent configuration file, if given.")
                .addOption("syslog", false, "When provided, logs will also be sent to syslog.")
                .addOption("broadcastYimeout", true, "Sets the broadcast timeout, which is the max time allowed for responses to events including leave and force remove messages. Defaults to 5s.");
    }

    public void parse(final String[] args) {


//        help(args);

//        CommandLineParser parser = new DefaultParser();

//        CommandLine cmd = null;
//        try {
//            cmd = parser.parse(options, args);

//            if (args.length == 0) help(args);
//            if (cmd.hasOption("h")) help(args);
//
//            if (cmd.hasOption("v")) {
//                logger.info("Using cli argument -v or --version"); // + cmd.getOptionValue("v"));
        // Whatever you want to do with the setting goes here
//            } else {
//                logger.error("Missing -v or --version option");
//                help();
//            }

//        } catch (ParseException e) {
//            logger.error("Failed to parse command line properties", e);
//            help(args);
//        }
    }

    private void help(String[] args) {
        // This prints out some help
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(120);
        formatter.setLeftPadding(5);
        formatter.setDescPadding(5);
        if (args.length != 0 && args[0].equals("agent")) {

            String agentHelp = String.join("\n"
                    , "agent [options]"
                    , "       Starts the Ligatum agent and runs until an interrupt is received. The agent represents a single node in a cluster."
                    , ""
                    , "Options:"
                    , "");
            formatter.printHelp(agentHelp, agentOptions, true);
            String agentEventHandlerHelp = String.join("\n"
                    ,"Event handlers:"
                    ,""
                    ,"  For more information on what event handlers are, please read the"
                    ,"  Ligatum documentation. This section will document how to configure them"
                    ,"  on the command-line. There are three methods of specifying an event"
                    ,"  handler:"
                    ,""
                    ,"  - The value can be a plain script, such as \"event.sh\". In this case,"
                    ,"  Ligatum will send all events to this script, and you'll be responsible"
                    ,"  for differentiating between them based on the LIGATUM_EVENT."
                    ,""
                    ,"  - The value can be in the format of \"TYPE=SCRIPT\", such as"
                    ,"  \"member-join=join.sh\". With this format, Ligatum will only send events"
                    ,"  of that type to that script."
                    ,""
                    ,"  - The value can be in the format of \"user:EVENT=SCRIPT\", such as"
                    ,"  \"user:deploy=deploy.sh\". This means that Ligatum will only invoke this"
                    ,"  script in the case of user events named \"deploy\".");
            System.out.println(agentEventHandlerHelp);
        } else {
            String help = String.join("\n"
                    , "Usage:"
                    , "-v, --version"
                    , "-h, --help [<command>] [<args>]"
                    , "<command> [<args>]"
                    , ""
                    , "Available commands are:"
                    , "agent           Runs a Ligatum agent"
                    , "event           Send a custom event through the Ligatum cluster"
                    , "force-leave     Forces a member of the cluster to enter the \"left\" state"
                    , "info            Provides debugging information for operators"
                    , "join            Tell Ligatum agent to join cluster"
                    , "keygen          Generates a new encryption key"
                    , "keys            Manipulate the internal encryption keyring used by Ligatum"
                    , "leave           Gracefully leaves the Ligatum cluster and shuts down"
                    , "members         Lists the members of a Ligatum cluster"
                    , "monitor         Stream logs from a Ligatum agent"
                    , "query           Send a query to the Ligatum cluster"
                    , "reachability    Test network reachability"
                    , "rtt             Estimates network round trip time between nodes"
                    , "tags            Modify tags of a running Ligatum agent"
                    , "version         Prints the Ligatum version"
                    , "");

            System.out.println(help);
        }
        System.exit(0);
    }

}
