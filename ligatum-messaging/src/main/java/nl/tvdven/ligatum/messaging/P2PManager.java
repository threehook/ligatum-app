package nl.tvdven.ligatum.messaging;

import com.google.common.collect.ImmutableMap;
import io.scalecube.cluster.Cluster;
import io.scalecube.cluster.ClusterConfig;
import io.scalecube.transport.Address;
import io.scalecube.transport.TransportConfig;
import nl.tvdven.ligatum.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.scalecube.cluster.ClusterConfig.builder;

@Component
public class P2PManager {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    @Autowired
    private Node node;

    private Cluster cluster;

    @PostConstruct
    public void init() {
        TransportConfig transportConfig = TransportConfig.builder().listenAddress("2001:985:aa1:1:c060:e11c:725e:b50e").port(TransportConfig.DEFAULT_PORT).portAutoIncrement(false).build();
        ClusterConfig clusterConfig = builder().metadata(ImmutableMap.of("name", node.getName())).transportConfig(transportConfig).build();
        cluster = Cluster.joinAwait(clusterConfig);
        System.out.println(now() + " " + node.getName() + " join members: " + cluster.members());
        cluster.listenMembership().subscribe(event -> System.out.println(now() + " " + node.getName() + " received: " + event));
        cluster.listenGossips().subscribe(gossip -> System.out.println(node.getName() + " heard: " + gossip.data()));

    }

    private static String now() {
        return sdf.format(new Date());
    }
}
