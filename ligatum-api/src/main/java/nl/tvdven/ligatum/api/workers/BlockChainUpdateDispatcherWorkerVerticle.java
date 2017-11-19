package nl.tvdven.ligatum.api.workers;

import io.scalecube.cluster.Cluster;
import io.scalecube.cluster.ClusterConfig;
import io.scalecube.transport.Address;
import io.scalecube.transport.Message;
import io.scalecube.transport.TransportConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.scalecube.cluster.ClusterConfig.builder;

public class BlockChainUpdateDispatcherWorkerVerticle {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

//    @Override
    public void start() throws Exception {
        System.out.println("[Worker] Starting in " + Thread.currentThread().getName());

        ClusterConfig clusterConfig = builder().seedMembers(Address.from("2001:985:aa1:1:c060:e11c:725e:b50e:" + TransportConfig.DEFAULT_PORT)/*, Address.from( "169.254.157.193:1234")*/).build();

        // Start cluster nodes and subscribe on listening gossips
        // Alice init cluster
//        Cluster cluster = Cluster.joinAwait(ImmutableMap.of("name", "Alice"));
//        System.out.println(now() + " Alice join members: " + cluster.members());
//        cluster.listenMembership().subscribe(event -> System.out.println(now() + " Alice received: " + event));
//        cluster.listenGossips().subscribe(gossip -> System.out.println("Alice heard: " + gossip.data()));


        System.out.println(now() + " Eve joins");
        Cluster eve = Cluster.joinAwait(clusterConfig);
        eve.spreadGossip(Message.fromData("Gossip from Eve"));
        System.out.println(now() + " Eve gossiped: Gossip from Eve");
    }

    private static String now() {
        return sdf.format(new Date());
    }
}

