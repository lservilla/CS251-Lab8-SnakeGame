import javax.swing.*;

public class Player extends JFrame {

    public Player(int i) {

        if (i == 1) {
            add(new ImmortalSnake());

            setResizable(false);
            pack();

            setTitle("Snake Game");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

        if (i == 2) {

            add(new Snake());

            setResizable(false);
            pack();

            setTitle("Snake Game");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        if (i == 3) {

            add(new HardSnake());

            setResizable(false);
            pack();

            setTitle("Snake Game");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        }

        if (i == 4) {
            add(new NightmareSnake());

            setResizable(false);
            pack();

            setTitle("Snake Game");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

        if(i == 5){

            add(new PlayerCreatedBoard());


            setResizable(false);
            pack();

            setTitle("Snake Game");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

        if(i == 6){

            add(new MultipleSnake());

            setResizable(false);
            pack();

            setTitle("Snake Game");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

        if(i == 7){

            add(new ComputerSnake());

            setResizable(false);
            pack();

            setTitle("Snake Game");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }
    }
}
