import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NightmareSnake extends JPanel implements ActionListener {

    private final int boardWidth = 600;
    private final int boardHeight = 600;
    private final int dotSize = 10;
    private final int allDots = 3600;
    private final int randomKey = 50;

    private int delay = 200;

    private int wallWidth = 10;

    private final int x[] = new int[allDots];
    private final int y[] = new int[allDots];

    private int dots;
    private int appleX;
    private int appleY;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private boolean pause = false;

    private boolean inGame = true;

    private Timer timer;

    private int counter = 0;


    public NightmareSnake(){

        startSnake();

    }

    private void startSnake(){

        addKeyListener(new NightmareSnake.DirectionChanger());
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
        g.drawString(message,(boardWidth - placement.stringWidth(message))/2, boardHeight/2-20);

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
            r += (dotSize + wallWidth);
        }
        if (r >= boardWidth-wallWidth){
            r = r - (wallWidth + dotSize);
        }
        appleX = r;

        int rr = (int)(Math.random() * randomKey);
        rr = rr * dotSize;
        if(rr == 0){
            rr += (dotSize + wallWidth);
        }
        if (rr >= boardHeight-wallWidth){
            rr = rr - (wallWidth + dotSize);
        }
        appleY = rr;

        appleWallWups();
    }

    private void appleWallWups(){

        if (appleX >= 0 && appleX <= wallWidth) {
            placeApple();
        }

        if(appleY >= 0 && appleY <= wallWidth){
            placeApple();
        }

        if (appleX >= (boardHeight - wallWidth) && appleX <= boardHeight) {
            placeApple();
        }

        if( appleY >= (boardWidth - wallWidth) && appleY <= boardWidth){
            placeApple();
        }
    }

    private void checkApple(){

        if((x[0] == appleX)&& (y[0] == appleY)){
            dots++;
            counter++;
            wallWidth += 10;

            if(delay != 25) {
                delay = delay - 25;
                timer.stop();
                timer.setDelay(delay);
                timer.start();
            }
            revalidate();
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

        if (y[0] >= boardHeight - (wallWidth + dotSize)) {
            inGame = false;
        }

        if (y[0] <= wallWidth) {
            inGame = false;
        }

        if (x[0] >= boardWidth - (wallWidth + dotSize)) {
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
        }

        repaint();

    }

    public class DirectionChanger extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)&& (!pause)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)&& (!pause)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)&& (!pause)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)&& (!pause)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if((key == KeyEvent.VK_SPACE)&& (!pause)) {
                timer.stop();
                pause = true;
            } else if((key == KeyEvent.VK_SPACE) && (pause)){
                timer.start();
                pause = false;
            }

        }
    }
}
