public class Main {
    public static void main (String[] args) {
        Observer observer = new Observer();
        BallFrame k = new BallFrame(observer);
        while (true) {
            k.moveBall();
            k.repaintBall();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
