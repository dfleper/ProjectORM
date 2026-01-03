package aed.proyectorm;

import aed.ui.InitUI;

/**
 * Entry point de la aplicación Swing.
 */
public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InitUI init = new InitUI();
                init.setLocationRelativeTo(null);
                init.setVisible(true);
            }
        });
    }
}
