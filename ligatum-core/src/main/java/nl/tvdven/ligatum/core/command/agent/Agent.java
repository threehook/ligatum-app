package nl.tvdven.ligatum.core.command.agent;

import nl.tvdven.ligatum.core.config.Config;

/**
 * Agent starts and manages a Ligatum instance, adding some niceties
 * on top of Ligatum such as storing logs that you can later retrieve,
 * and invoking EventHandlers when events occur.
 */
public class Agent {

    // Stores the serf configuration
    Config config;

    // Stores the agent configuration
    AgentConfig agentConf;

}


