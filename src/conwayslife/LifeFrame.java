package conwayslife;

public class LifeFrame extends javax.swing.JFrame {

    Board theBoard;
    boolean viralClick = false;
    Controller theController;
    ControlFrame controls;

    public LifeFrame() {
        initComponents();
        setVisible(true);
        setTitle("Life Finds A Way");
        setSize(532, 555);
        theBoard = new Board(this);
        this.add(theBoard); 
        theController = new Controller(this);
        theController.setBoard(theBoard);
        controls = new ControlFrame(theController, this);
        theController.start();
        init();
    }
    
    public void init() {
        theBoard.reset();
        theBoard.checkNeighbors();
        theBoard.checkVirus();
        repaint();
    }
    
    public void reset() {
        controls.reset();
    }
    
    public void toggleColorful() {
        theBoard.toggleColorful();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LifeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    public void update() {
        theBoard.checkNeighbors();
        theBoard.checkVirus();
        theBoard.update();
    }
    
    public void setRunning(boolean b) {
        theController.running = b;
    }

    public void setDensity(int value) {
        theBoard.setDensity(value);
        theBoard.reset();
    }

    public void toggleVirus() {
        theBoard.toggleVirus();
    }
    
}
