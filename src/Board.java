
/**
 * @version date (CS_251_004, 2019-04-15)
 * @author Lasair Servilla
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener{

   private int width = 800;
    private int height = 600;

    private static JFrame options;
    private JPanel panel;
    JPanel more;
    JPanel instruct;

    int numbers;
    static List<Integer> numList = new ArrayList<Integer>();


    public Board(){

        //Create and set up frame window
        options = new JFrame("Snake Game");
        options.setPreferredSize(new Dimension(width, height));
        options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creating new panel for buttons
        panel = new JPanel();


        //Set boxLayout to be top to bottom
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        //Set layout and other details for panel
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(75,100,100,100)));
        panel.setBackground(Color.black);

        //Creating label to direct user what to do
        JLabel label = new JLabel("Select level to play.");
        label.setFont(new Font(Font.MONOSPACED,Font.BOLD,15 ));
        label.setForeground(Color.white);

        JLabel label2 = new JLabel("Or click 'More Options' for other game play formats");
        label2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        label2.setForeground(Color.white);

        //Defining new buttons
        JButton jbImmortal = new JButton("Immortal");
        JButton jbEasy = new JButton("Easy");
        JButton jbHard = new JButton("Hard");
        JButton jbNightmare = new JButton("Nightmare");

        JButton jbOther = new JButton("More Options");

        //Adding action listener for each button
        jbImmortal.addActionListener(this);
        jbEasy.addActionListener(this);
        jbHard.addActionListener(this);
        jbNightmare.addActionListener(this);

        jbOther.addActionListener(this);


        //Add buttons and label to panel
        panel.add(Box.createRigidArea(new Dimension(2,5)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(2,5)));

        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label2);
        panel.add(Box.createRigidArea(new Dimension(2,30)));

        jbImmortal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(jbImmortal);
        panel.add(Box.createRigidArea(new Dimension(2,20)));
        jbEasy.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(jbEasy);
        panel.add(Box.createRigidArea(new Dimension(2,20)));
        jbHard.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(jbHard);
        panel.add(Box.createRigidArea(new Dimension(2,20)));
        jbNightmare.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(jbNightmare);
        panel.add(Box.createRigidArea(new Dimension(2,60)));
        jbOther.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(jbOther);


        //Adding panel to frame window
        options.add(panel);
        options.setResizable(false);
        options.pack();
        options.setVisible(true);

    }

    public void special(){

        more = new JPanel();
        more.setBackground(Color.black);
        BoxLayout boxThree = new BoxLayout(more,BoxLayout.Y_AXIS);

        more.setLayout(boxThree);

        JButton jbSmart = new JButton("Computer");
        JButton jbMultiple = new JButton("Two Players");
        JButton jbChoice = new JButton("Player Select Board");

        JButton back = new JButton("Back To Levels");

        jbSmart.addActionListener(this);
        jbMultiple.addActionListener(this);
        jbChoice.addActionListener(this);
        back.addActionListener(this);

        more.add(Box.createRigidArea(new Dimension(2,150)));
        jbSmart.setAlignmentX(Component.CENTER_ALIGNMENT);
        more.add(jbSmart);
        more.add(Box.createRigidArea(new Dimension(2,20)));
        jbMultiple.setAlignmentX(Component.CENTER_ALIGNMENT);
        more.add(jbMultiple);
        more.add(Box.createRigidArea(new Dimension(2,20)));
        jbChoice.setAlignmentX(Component.CENTER_ALIGNMENT);
        more.add(jbChoice);
        more.add(Box.createRigidArea(new Dimension(2,60)));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        more.add(back);


        options.remove(panel);
        options.add(more);
        options.revalidate();
        options.repaint();
    }

    JTextField text = new JTextField( );

    public void instructions(int i){

        instruct = new JPanel();
        instruct.setBackground(Color.black);
        BoxLayout boxTwo = new BoxLayout(instruct,BoxLayout.Y_AXIS);

        instruct.setLayout(boxTwo);


        if (i == 1){

            JLabel info = new JLabel("<html> Immortal : <br/>" +
                    "<br/> Use up, down, left, and right arrows to control the snake." +
                    "<br/> Try to eat as many apples as you can." +
                    "<br/> Play as long as you want, there is no way to die." +
                    "<br/> To kill the game and see your score hit the 'K' key <html/>");
            info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            info.setForeground(Color.white);

            instruct.add(Box.createRigidArea(new Dimension(2,100)));
            instruct.add(info);
            instruct.add(Box.createRigidArea(new Dimension(2,200)));

            options.remove(panel);
        }
        if (i == 2){

            JLabel info = new JLabel("<html> Easy: " +
                    "<br/> <br/> Use up, down, left, and right arrows to control the snake. " +
                    "<br/> Try to eat as many apples as you can. " +
                    "<br/> Don't run into the walls or yourself, that will end the game.");
            info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            info.setForeground(Color.white);

            instruct.add(Box.createRigidArea(new Dimension(2,100)));
            instruct.add(info);
            instruct.add(Box.createRigidArea(new Dimension(2,200)));

            options.remove(panel);
        }
       if(i == 3){

           JLabel info = new JLabel("<html> Hard: " +
                   "<br/> <br/> Use up, down, left, and right arrows to control the snake." +
                   "<br/> Try to eat as many apples as you can." +
                   "<br/> With each apple eaten the snakes speed will increase.Up to a point." +
                   "<br/> Don't run into the walls or yourself, that will end the game.");
           info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
           info.setForeground(Color.white);

           instruct.add(Box.createRigidArea(new Dimension(2,100)));
           instruct.add(info);
           instruct.add(Box.createRigidArea(new Dimension(2,200)));

           options.remove(panel);
        }
       if(i == 4){

           JLabel info = new JLabel("    Good Luck :)");
           info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
           info.setForeground(Color.white);

           instruct.add(Box.createRigidArea(new Dimension(2,100)));
           instruct.add(info);
           instruct.add(Box.createRigidArea(new Dimension(2,200)));

           options.remove(panel);
        }
       if(i == 5){

           JLabel create = new JLabel("<html> To create your own board:" +
                   " <br/> <br/> 1. Enter desired length and width " +
                   "<br/>   (for best results use multiples of 100) " +
                   "<br/> <br/> 2. Enter coordinate point for top left corner of desired wall, followed by length and width" +
                   "<br/> <br/> 3. Create as many walls as your heart desires " +
                   "<br/> <br/> <br/> Example: 25 7 0 0 25 1 5 6 15 1, will create the following " +
                   "<br/> <br/>  XXXXXXXXXXXXXXXXXXXXXXXXX" +
                   "<br/> OOOOOOOOOOOOOOOOOOOOOOOOOO " +
                   "<br/> OOOOOOOOOOOOOOOOOOOOOOOOOO" +
                   "<br/> OOOOOOOOOOOOOOOOOOOOOOOOOO" +
                   "<br/> OOOOOOOOOOOOOOOOOOOOOOOOOO" +
                   "<br/> OOOOOXXXXXXXXXXXXXXXOOOOO" +
                   "<br/> OOOOOOOOOOOOOOOOOOOOOOOOOO" +
                   " <br/> <br/> <br/> Rules are the same as easy level, with the addition of having to avoid any extra walls <html/>");
           create.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
           create.setForeground(Color.white);
           JButton enter = new JButton("Play");
            enter.addActionListener(this);
            enter.setAlignmentX(Component.CENTER_ALIGNMENT);
           text.addActionListener(this);
            text.setBackground(Color.white);

           instruct.add(Box.createRigidArea(new Dimension(2,20)));
            instruct.add(create);
           instruct.add(Box.createRigidArea(new Dimension(2,30)));
            instruct.add(text);
           instruct.add(Box.createRigidArea(new Dimension(2,30)));
            instruct.add(enter);

           options.remove(more);

        }
       if(i == 6){

           JLabel info = new JLabel("<html> Multiple Players: " +
                   "<br/> <br/> Use up, down, left, and right arrows to control the grey snake." +
                   "<br/> Use 'W', 'S', 'A', and 'D'keys to control the pink snake." +
                   "<br/> Try to eat as many apples as you can." +
                   "<br/> Don't run into the walls or yourself, that will end the game.");
           info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
           info.setForeground(Color.white);

           instruct.add(Box.createRigidArea(new Dimension(2,100)));
           instruct.add(info);
           instruct.add(Box.createRigidArea(new Dimension(2,200)));

           options.remove(more);
        }
        if(i == 7){

            JLabel info = new JLabel("<html>Sit back and relax, it's up to the computer to collect the apples." +
                    "<br/> <br/> Obviously the computer is having some issues right now, it's learning though.<html/>");
            info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            info.setForeground(Color.white);

            instruct.add(Box.createRigidArea(new Dimension(2,100)));
            instruct.add(info);
            instruct.add(Box.createRigidArea(new Dimension(2,200)));

            options.remove(more);
        }


        JButton back = new JButton("Back");

        back.addActionListener(this);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        instruct.add(Box.createRigidArea(new Dimension(2,30)));
        instruct.add(back);
        instruct.add(Box.createRigidArea(new Dimension(2,30)));


        options.add(instruct);
        options.revalidate();
        options.repaint();



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Immortal")){

            JFrame game = new Player(1);
            game.setVisible(true);

            instructions(1);

        }

        if (e.getActionCommand().equals("Easy")){

            JFrame game = new Player(2);
            game.setVisible(true);

            instructions(2);

        }

        if (e.getActionCommand().equals("Hard")){

            JFrame game = new Player(3);
            game.setVisible(true);

            instructions(3);

        }

        if (e.getActionCommand().equals("Nightmare")){

            JFrame game = new Player(4);
            game.setVisible(true);

            instructions(4);

        }

        if(e.getActionCommand().equals("More Options")){

            special();

        }

        if (e.getActionCommand().equals("Player Select Board")){

            instructions(5);

        }

        if (e.getActionCommand().equals("Two Players")){
            JFrame game = new Player(6);
            game.setVisible(true);

            instructions(6);

        }

        if(e.getActionCommand().equals("Computer")){

            JFrame game = new Player(7);
            game.setVisible(true);

            instructions(7);
        }


        if(e.getActionCommand().equals("Back")){

            options.remove(instruct);
            options.add(panel);
            options.revalidate();
            options.repaint();


        }

        if(e.getActionCommand().equals("Back To Levels")){

            options.remove(more);
            options.add(panel);
            options.revalidate();
            options.repaint();

        }

        if(e.getActionCommand().equals("Play")) {

            numList.clear();

            String lines = text.getText().replace(",", " ");
            String[] nums = lines.split(" ");

            for (int i = 0; i < nums.length; i++) {

                try {
                    numList.add(Integer.parseInt(nums[i]));
                } catch (NumberFormatException nfe) {

                }

            }

            System.out.println(numList);

            JFrame game = new Player(5);
            game.setVisible(true);

        }


    }
}


