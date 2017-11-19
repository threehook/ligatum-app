package nl.tvdven.ligatum.model;

import com.google.common.primitives.UnsignedLong;

import java.io.Serializable;

public class Message implements Serializable, Comparable<Message> {

    final int from, to;
    final UnsignedLong clock;
    final String type;
    final String payload;

    private Message(MessageBuilder builder) {
        this.from = builder.from; this.to = builder.to;
        this.clock = builder.clock;
        this.type = builder.type;
        this.payload = builder.payload;
    }
    @Override
    public int compareTo(Message other) {

        if (this.clock.compareTo(other.clock)>0) return +1;
        else if (this.clock.compareTo(other.clock)<0) return -1;
        else { //Break ties with pid;
            if(this.getSender() > other.getSender()) return +1;
            else return -1;
            //PID SHOULD NEVER BE EQUAL
        }
    }

    @Override
    public String toString() {
        return String.format(from+" "+clock);
    }

    public int getSender() {
        return from;
    }

    public int getReceiver() {
        return to;
    }

    public UnsignedLong getClock() {
        return clock;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public static class MessageBuilder {
        private int to, from;
        private UnsignedLong clock;
        private String type;
        private String payload = "";
//        public MessageBuilder() {
//
//        }

        public MessageBuilder from(int from) {
            this.from = from; return this;
        }

        public MessageBuilder to(int to) {
            this.from = to; return this;
        }

        public MessageBuilder clock(UnsignedLong clock) {
            this.clock = clock;
            return this;
        }

        public MessageBuilder type(String type) {
            this.type = type; return this;
        }

        public MessageBuilder payload(String payload) {
            this.payload = payload;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

//    public static void main(String[] args) {
//        Message msg = new Message.MessageBuilder()
//                .to(1)
//                .from(0)
//                .clock(11)
//                .type("application").build();
//        System.out.println(msg.getClock());
//    }
}
