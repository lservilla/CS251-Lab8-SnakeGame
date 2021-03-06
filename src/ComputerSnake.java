import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ComputerSnake extends JPanel implements ActionListener {

    private final int boardWidth = 600;
    private final int boardHeight = 600;
    private final int dotSize = 20;
    private final int allDots = 3600;
    private final int randomKey = 25;
    private final int delay = 200;

    private final int wallWidth = 10;

    private final int x[] = new int[allDots];
    private final int y[] = new int[allDots];

    private int dots;
    private int appleX;
    private int appleY;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private boolean inGame = true;

    private Timer timer;

    private int counter = 0;


    public ComputerSnake(){

        startSnake();

    }

    private void startSnake(){

        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(boardWidth,boardHeight));
        initiateGame();

    }


    private void initiateGame(){
        dots = 3;

        for (int z = 0 ; z < dots ; z++){
            x[z] = 60 - z * 10;
            y[z] = boardHeight/2;
        }

        placeApple();

        timer = new Timer(delay, this);
        timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        drawWalls(g);


    }

    private void drawWalls(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(0,0,wallWidth,boardHeight);
        g.fillRect(0,0,boardWidth,wallWidth);
        g.fillRect(0,boardHeight-wallWidth,boardWidth,wallWidth);
        g.fillRect(boardWidth-wallWidth,0,wallWidth,boardHeight);
    }

    private void doDrawing(Graphics g){

        if(inGame){

            g.setColor(Color.red);
            g.fillOval(appleX,appleY, dotSize,dotSize);

            for( int z = 0 ; z < dots ; z++ ){
                if(z == 0){
                    g.setColor(Color.lightGray);
                    g.fillOval(x[z],y[z],dotSize,dotSize);
                } else {
                    g.setColor(Color.green);
                    g.fillOval(x[z],y[z],dotSize,dotSize);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }

    }


    private void gameOver(Graphics g){

        String message = "GAME OVER";
        Font font = new Font(Font.MONOSPACED,Font.BOLD,25);
        FontMetrics placement = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message,(boardWidth - placement.stringWidth(message))/2, boardHeight/2);

        String score = "Final Score:  " + counter;
        Font fontScore = new Font(Font.MONOSPACED,Font.BOLD,25);
        FontMetrics scorePlacement = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(fontScore);
        g.drawString(score, (boardWidth - scorePlacement.stringWidth(score))/2, boardHeight/2+20);


    }


    private void placeApple(){

        int r = (int)(Math.random()*randomKey);
        r = r * dotSize;
        if(r == 0){
            r = dotSize;
        }
        if (r >= boardWidth-wallWidth){
            r = r - (wallWidth + dotSize);
        }
        appleX = r;

        int rr = (int)(Math.random() * randomKey);
        rr = rr * dotSize;
        if(rr == 0){
            rr = dotSize;
        }
        if (rr >= boardHeight-wallWidth){
            rr = rr - (wallWidth + dotSize);
        }
        appleY = rr;
    }

    private void checkApple(){

        if((x[0] == appleX)&& (y[0] == appleY)){
            dots++;
            counter++;
            placeApple();
        }

    }

    private void moveSnake(){

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= dotSize;
        }

        if (rightDirection) {
            x[0] += dotSize;
        }

        if (upDirection) {
            y[0] -= dotSize;
        }

        if (downDirection) {
            y[0] += dotSize;
        }


    }

    private void checkCollision(){

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= boardHeight - (wallWidth+dotSize)) {
            inGame = false;
        }

        if (y[0] <= wallWidth) {
            inGame = false;
        }

        if (x[0] >= boardWidth - (wallWidth+dotSize)) {
            inGame = false;
        }

        if (x[0] <= wallWidth) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(inGame){
            checkApple();
            checkCollision();
            moveSnake();

            if(upDirection){
                upChanger();
            }else if(downDirection){
                downChanger();
            }else if(rightDirection){
                rightChanger();
            }else if(leftDirection){
                leftChanger();
            }
        }

        repaint();

    }

    public void upChanger() {
        if (x[0] < appleX) {
            upDirection = false;
            downDirection = false;
            rightDirection = false;
            leftDirection = true;
        }
        if (x[0] > appleX) {
            upDirection = false;
            downDirection = false;
            rightDirection = true;
            leftDirection = false;
        }
        if (x[0] == appleX) {
            upDirection = true;
            downDirection = false;
            rightDirection = false;
            leftDirection = false;

        }
    }

    public void downChanger(){
        if(x[0] > appleX){
            downDirection = false;
            leftDirection = true;
            rightDirection = false;
            upDirection = false;
        }
        if(x[0] < appleX){
            downDirection = false;
            upDirection = false;
            rightDirection = true;
            leftDirection = false;
        }
        if(x[0] == appleX){
            downDirection = true;
            upDirection = false;
            rightDirection = false;
            leftDirection = false;
        }
    }

    public void rightChanger() {
        if (y[0] > appleY) {
            rightDirection = false;
            leftDirection = false;
            upDirection = true;
            downDirection = false;
        }
        if (y[0] < appleY) {
            rightDirection = false;
            leftDirection = false;
            upDirection = false;
            downDirection = true;
        }
        if (y[0] == appleY) {
            rightDirection = true;
            leftDirection = false;
            upDirection = false;
            downDirection = false;

        }
    }

    public void leftChanger(){
        if(y[0] > appleY){
            leftDirection = false;
            rightDirection = false;
            upDirection = true;
            downDirection = false;
        }
        if(y[0] < appleY) {
            leftDirection = false;
            rightDirection = false;
            downDirection = true;
            upDirection = false;
        }
        if(y[0] == appleY){
            leftDirection = true;
            rightDirection = false;
            downDirection = false;
            upDirection = false;
        }

    }

}


