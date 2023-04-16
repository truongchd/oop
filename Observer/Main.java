public class Main {
    public static void main (String[] args) {
        BallFrame k = new BallFrame();
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
