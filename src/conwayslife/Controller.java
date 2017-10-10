package conwayslife;

public class Controller extends Thread {

    LifeFrame theFrame;
    Board theBoard;
    boolean running = false;
    int speed = 20;

    public Controller(LifeFrame f) {
        this.theFrame = f;
    }
    
    public void setBoard(Board b) {
        this.theBoard = b;
    }

    @Override
    public void run() {
        for (;;) {
            if (running) {
                if (!theBoard.boardStuck()) {
                    theBoard.recordGrids();
                    theFrame.update();
                    theFrame.repaint();
                } else {
                    theFrame.reset();
                    running = false;
                }
            }
            delay();
        }
    }

    private void delay() {
        try {
            Thread.sleep(speed);
        } catch (Exception e) {
            System.out.println("Nope!" + e);
        }
    }

    void toggleRunning() {
        running = !running;
    }

    boolean getRunning() {
        return running;
    }

    void setSpeed(int n) {
        this.speed = n;
    }

}
