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
        setSize(640, 715);
        theBoard = new Board(this);
        this.add(theBoard); 
        theController = new Controller(this);
        theController.setBoard(theBoard);
        controls = new ControlFrame(theController, this);
        theController.start();
    }
    
    public void init() {
        theBoard.reset();
        theBoard.checkNeighbors();
        repaint();
    }
    
    public void reset() {
        controls.reset();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        resetMenu = new javax.swing.JMenuItem();
        saveMenu = new javax.swing.JMenuItem();
        loadMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");

        resetMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        resetMenu.setText("Reset");
        resetMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMenuActionPerformed(evt);
            }
        });
        jMenu1.add(resetMenu);

        saveMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenu.setText("Save Board");
        saveMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenu);

        loadMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        loadMenu.setText("Load Board");
        loadMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuActionPerformed(evt);
            }
        });
        jMenu1.add(loadMenu);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMenuActionPerformed
        theBoard.reset();
    }//GEN-LAST:event_resetMenuActionPerformed

    private void saveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuActionPerformed
        MyWriter mw = new MyWriter();
        mw.print(theBoard.saveString());
        mw.close();
    }//GEN-LAST:event_saveMenuActionPerformed

    private void loadMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuActionPerformed
        theBoard.reset();
        theBoard.count = 0;
        MyReader mr = new MyReader();
        while (mr.hasMoreData()) {
            theBoard.add(mr);
        }
        mr.close();
        repaint();
    }//GEN-LAST:event_loadMenuActionPerformed

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem loadMenu;
    private javax.swing.JMenuItem resetMenu;
    private javax.swing.JMenuItem saveMenu;
    // End of variables declaration//GEN-END:variables

    public void setN(int n) {
        theBoard.setN(n);
        theBoard.reset();
    }

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
