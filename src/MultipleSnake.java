import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MultipleSnake extends JPanel implements ActionListener {

    private final int boardWidth = 600;
    private final int boardHeight = 600;
    private final int dotSize = 20;
    private final int allDots = 3600;
    private final int randomKey = 25;
    private final int delay = 200;

    private final int wallWidth = 10;

    private final int x[] = new int[allDots];
    private final int y[] = new int[allDots];

    private final int xx[] = new int[allDots];
    private final int yy[] = new int[allDots];

    private int dots;
    private int dots2;
    private int appleX;
    private int appleY;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private boolean leftDirection2 = false;
    private boolean rightDirection2 = true;
    private boolean upDirection2 = false;
    private boolean downDirection2 = false;

    private boolean pause = false;

    private boolean inGame = true;

    private Timer timer;

    private int counter = 0;
    private int counter2 = 0;


    public MultipleSnake(){

        startSnake();

    }

    private void startSnake(){

        addKeyListener(new MultipleSnake.DirectionChanger());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(boardWidth,boardHeight));
        initiateGame();

    }


    private void initiateGame(){
        dots = 3;
        dots2 = 3;

        for (int z = 0 ; z < dots ; z++){
            x[z] = 60 - z * 10;
            y[z] = boardHeight/2 ;
        }

        for (int a = 0 ; a < dots2 ; a++){
            xx[a] = 60 - a * 10;
            yy[a] = boardHeight/2 ;
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

            for( int z = 0 ; z < dots2 ; z++ ){
                if(z == 0){
                    g.setColor(Color.pink);
                    g.fillOval(xx[z],yy[z],dotSize,dotSize);
                } else {
                    g.setColor(Color.green);
                    g.fillOval(xx[z],yy[z],dotSize,dotSize);
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

        String score = "Gray Final Score:  " + counter;
        Font fontScore = new Font(Font.MONOSPACED,Font.BOLD,25);
        FontMetrics scorePlacement = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(fontScore);
        g.drawString(score, (boardWidth - scorePlacement.stringWidth(score))/2, boardHeight/2+30);

        String score2 = "Pink Final Score:  " + counter2;
        Font fontScore2 = new Font(Font.MONOSPACED,Font.BOLD,25);
        FontMetrics scorePlacement2 = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(fontScore2);
        g.drawString(score2, (boardWidth - scorePlacement2.stringWidth(score2))/2, boardHeight/2+60);

    }


    private void placeApple(){

        int r = (int)(Math.random()*randomKey);
        r = r * dotSize;
        if(r == 0){
            r += (dotSize + wallWidth);
        }
        if (r >= boardWidth-wallWidth){
            r -= (wallWidth + dotSize);
        }
        appleX = r;

        int rr = (int)(Math.random() * randomKey);
        rr = rr * dotSize;
        if(rr == 0){
            rr += (dotSize + wallWidth);
        }
        if (rr >= boardHeight-wallWidth){
            rr -= (wallWidth + dotSize);
        }
        appleY = rr;
    }

    private void checkApple(){

        if((x[0] == appleX)&& (y[0] == appleY)){
            dots++;
            counter++;
            placeApple();
        }

        if((xx[0] == appleX)&& (yy[0] == appleY)){
            dots2++;
            counter2++;
            placeApple();
        }

    }

    private void moveSnake(){

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        for (int z = dots2; z > 0; z--) {
            xx[z] = xx[(z - 1)];
            yy[z] = yy[(z - 1)];
        }

        if (leftDirection && y[0] != 1000) {
            x[0] -= dotSize;
        }

        if (rightDirection && y[0] != 1000) {
            x[0] += dotSize;
        }

        if (upDirection && y[0] != 1000) {
            y[0] -= dotSize;
        }

        if (downDirection && y[0] != 1000) {
            y[0] += dotSize;
        }


        if (leftDirection2 && yy[0] != 1000) {
            xx[0] -= dotSize;
        }

        if (rightDirection2 && yy[0] != 1000) {
            xx[0] += dotSize;
        }

        if (upDirection2 && yy[0] != 1000) {
            yy[0] -= dotSize;
        }

        if (downDirection2 && yy[0] != 1000) {
            yy[0] += dotSize;
        }

    }

    private void checkCollision(){

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        for (int z = dots2; z > 0; z--) {

            if ((z > 4) && (xx[0] == xx[z]) && (yy[0] == yy[z])) {
                inGame = false;
            }
        }

        if (y[0] >= boardHeight - (wallWidth +dotSize)) {
            y[0] = 1000;
        }

        if (y[0] < wallWidth) {
            y[0] = 1000;
        }

        if (x[0] >= boardWidth - (wallWidth +dotSize)) {
            y[0] = 1000;
        }

        if (x[0] < wallWidth) {
            y[0] = 1000;
        }

        if (yy[0] >= boardHeight - (wallWidth +dotSize)) {
            yy[0] = 1000;
        }

        if (yy[0] < wallWidth) {
            yy[0] = 1000;
        }

        if (xx[0] >= boardWidth - (wallWidth +dotSize)) {
            yy[0] = 1000;
        }

        if (xx[0] < wallWidth) {
            yy[0] = 1000;
        }

        if( y[0]== 1000 && yy[0]== 1000){
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


            if ((key == KeyEvent.VK_A) && (!rightDirection2)&& (!pause)) {
                leftDirection2 = true;
                upDirection2 = false;
                downDirection2 = false;
            }

            if ((key == KeyEvent.VK_D) && (!leftDirection2)&& (!pause)) {
                rightDirection2 = true;
                upDirection2 = false;
                downDirection2 = false;
            }

            if ((key == KeyEvent.VK_W) && (!downDirection2)&& (!pause)) {
                upDirection2 = true;
                rightDirection2 = false;
                leftDirection2 = false;
            }

            if ((key == KeyEvent.VK_S) && (!upDirection2)&& (!pause)) {
                downDirection2 = true;
                rightDirection2 = false;
                leftDirection2 = false;
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
