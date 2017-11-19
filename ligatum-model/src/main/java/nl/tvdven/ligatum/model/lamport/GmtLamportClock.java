package nl.tvdven.ligatum.model.lamport;

import com.google.common.primitives.UnsignedLong;

public class GmtLamportClock {


    private UnsignedLong d  = UnsignedLong.valueOf(1L);
    private UnsignedLong clock = UnsignedLong.valueOf(0L);

    public GmtLamportClock() {}

    public GmtLamportClock(UnsignedLong d) {
        this.d = d;
    }

    public synchronized void local_event() { clock = clock.plus(d); }

    public synchronized void msg_event(UnsignedLong msg_clock) {
        this.local_event();
        if (msg_clock.plus(d).compareTo(this.clock) >= 0) {
            clock = msg_clock.plus(d);
        }
    }
    public UnsignedLong increment() {return d;}
    public UnsignedLong peek() { return clock; }
}
