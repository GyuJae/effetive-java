package item8;

import java.lang.ref.Cleaner;

public class Room implements AutoCloseable {
    private static final Cleaner CLEANER = Cleaner.create();

    private final Cleaner.Cleanable cleanable;
    private boolean closed;

    Room() {
        this.cleanable = CLEANER.register(this, new State());
    }

    @Override
    public void close() throws Exception {
        if (!this.closed) {
            this.closed = true;
            this.cleanable.clean(); // 즉시 청소 실행(명시적 close)
        }
    }

    private static class State implements Runnable {
        @Override
        public void run() {
            // 자원 반납 로직
        }
    }
}
