//8ms
class ZeroEvenOdd {
    private int n;
    private final Semaphore s1= new Semaphore(1); //信号量1
    private final Semaphore s2= new Semaphore(0); //信号量2
    private final Semaphore s3= new Semaphore(0); //信号量3

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i++) {
            s1.acquire();
            printNumber.accept(0);
            if(i % 2 == 0) {
                s2.release();
            }else {
                s3.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2) {
            s2.acquire();
            printNumber.accept(i);
            s1.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2) {
            s3.acquire();
            printNumber.accept(i);
            s1.release();
        }
    }
}
