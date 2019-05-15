
/**
 * @version date (CS_251_004, 2019-04-15)
 * @author Lasair Servilla
 */


import javax.swing.*;

/**
 * This program reads in a text file with a level configuration and then
 * creates a game of snake played in specified wall set up.
 */

public class GameManager {

public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new Board();
        }
    });


}


}
