package nl.tvdven.ligatum.model;

import nl.tvdven.ligatum.model.lamport.LamportClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.List;

@Component
public class Node {

    @Value("${node.name}")
    private String name;

    private InetAddress ip;

    private int port;

    @Value("#{'${node.seed.addresses}'.split(',')}")
    private List<String> seedAddresses;


    public String getName() {
        return name;
    }


    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<String> getSeedAddresses() {
        return seedAddresses;
    }


    //    @Autowired
//    private LamportClock localclock;

//    @Autowired
//    private LamportMutex mutex;

//    public Node(String name, InetAddress ip, int port) {
//
//        this.name = name;
//        this.ip = ip;
//        this.port = port;
//    }

//    /**
//     * Receiving msgs from other Nodes (I would call it receiveMessage)
//     * @param msg
//     */
//    public synchronized void deliver_message(Message msg) {
//        //System.out.println(msg+"  "+msg.getType()+".from..."+msg.getSender()+"   local clock: "+localclock.peek());
//        this.localclock.msg_event(msg.getClock());
//        //System.out.println("new local clock: "+localclock.peek());
//        if      (msg.getType().equals("request")) {
//            System.out.println(msg.getType()+".from..."+msg);
//            mutex.queue_request(msg);
//            /*
//            in version 1, send reply to all requests whatsoever
//             */
//            send_message(msg.getSender(),"reply");
//        }
//        else if (msg.getType().equals("release")) {
//            System.out.println(msg.getType()+".from..."+msg);
//            //this.total_protocol_msgs += 1;
//            mutex.release_request(msg);
//        }
//        else if (msg.getType().equals("reply")) {
//            System.out.println(msg.getType()+".from..."+msg);
//            this.total_protocol_msgs += 1;
//            mutex.reply_request(msg);
//        }
//    }

//    public synchronized void send_message(int receiver, String type) {
//        //System.out.println("sending message from:"+ pid+" to "+receiver);
//
//        /*
//        reply messages are never multicasts so
//        we have to increase the local clock in send_message method
//         */
//        if(type.equals("reply")) {
//            this.localclock.local_event();
//        }
//
//        //Data collection
//        if (type.equals("application")) {
//            this.total_application_msgs += 1;
//        } else {
//            if(receiver != this.pid) {
//                /*
//                the replies you send are not for YOUR
//                crit execution requests.
//                 */
//                if (!type.equals("reply"))
//                    this.total_protocol_msgs += 1;
//            }
//        }
//        Message msg = new Message.MessageBuilder()
//                .to(receiver)
//                .from(this.pid)
//                .clock(this.localclock.peek())
//                .type(type).build();
//        /*
//        If sending message to self?
//        type should better be "request"
//         */
//        if(receiver == this.pid && type.equals("request")) {
//            this.mutex.queue_request(msg);
//        }
//        else {
//            String receiver_ip = lookup.getIP(receiver);
//            int receiver_port = lookup.getPort(receiver);
//
//            try (Socket sock = new Socket(receiver_ip, receiver_port)) {
//                OutputStream out = sock.getOutputStream();
//                ObjectOutputStream outstream = new ObjectOutputStream(out);
//                outstream.writeObject(msg);
//                outstream.close();
//                out.close();
//                sock.close();
//
//            } catch (IOException ex) {
//                System.err.println("can't send message" + ex);
//            }
//        }
//    }

//    public synchronized void multicast(String type) {
//        this.localclock.local_event();
//        if(type.equals("request")) {
//            System.out.println("sending request at "+localclock.peek());
//            send_message(this.pid, type);
//        }
//        for(String pid_str: lookup.table.keySet()) {
//            int pid_int = Integer.parseInt(pid_str);
//            if (pid_int == this.pid) {
//                continue;
//            }
//            else {
//                send_message(pid_int,type);
//            }
//        }
//    }

//    private synchronized void execute_crit() {
//        write_sharedlog("Entering");
//        System.out.println("executing crit");
//        this.crit_executions += 1;
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
//        write_sharedlog("Leaving");
//    }

//    @Override
//    public void run() {
//        run_listener();
//        try {
//            Thread.sleep(8000);//till I start other processes;
//        } catch (InterruptedException ex) {}
//
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            public void run() {
//                cleanup();
//            }
//        });
//        while(true) {
//            Random rand = new Random();
//            int sleeptime = rand.nextInt(101 - 10) + 10;
//
//            try {
//                Thread.sleep(sleeptime*10);
//            } catch (InterruptedException ex) {
//                System.err.println(ex);
//            }
//
//            int decider = rand.nextInt(101 - 1) + 1;
//            if (this.crit_executions >= 20) {
//                System.out.println("I'm done");
//            }
//            else if (decider >= 1 && decider <= 90) {
//                multicast("application");
//            }
//            else {
//                long startTime = System.currentTimeMillis();
//                int proto_messages_before = this.total_protocol_msgs;
//
//                //multicast("request"); -- do that inside LamportMutex
//                while(!mutex.request_crit_section()) {
//                    //keep checking if we can enter crit or not.
//                }
//                long endTime = System.currentTimeMillis();
//                long duration = (endTime - startTime);
//                execute_crit();
//                mutex.release_request();
//                multicast("release");
//                int proto_messages_after = this.total_protocol_msgs;
//                this.delay_per_crit = duration;
//                this.count_per_crit = proto_messages_after - proto_messages_before;
//                log_and_reset();
//            }
//        }
//    }

//    public synchronized void log_and_reset() {
//        log_writer.println(String.format("%-12s %-12s",this.count_per_crit,this.delay_per_crit));
//        this.delay_per_crit = 0;
//        this.count_per_crit = 0;
//    }
//
//    public synchronized void write_sharedlog(String action) {
//        try {
//            Writer sharedlog_writer = new PrintWriter(new FileWriter("shared.log",true));
//            sharedlog_writer.append(
//                    String.format("%-5s %-10s %-6s\n",this.pid,action,localclock.peek()));
//            sharedlog_writer.close();
//        } catch(IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public void cleanup() {
//        log_writer.println("total application messages sent: "+this.total_application_msgs);
//        this.log_writer.close();
//    }

//    public static void main(String[] args) {
//        //System.out.println(args[1]);
//        Node n = new Node(Integer.parseInt(args[0]),args[1]);
//        Thread t = new Thread(n);
//        t.start();
//    }

}
