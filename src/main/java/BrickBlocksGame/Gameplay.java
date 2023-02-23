
package BrickBlocksGame;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Gameplay extends JPanel implements KeyListener, ActionListener {
    
    private int [] xwpolrzedne = {200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775};
    private int [] ywpolrzedne = {125, 150, 175, 200, 225, 250};    
    List<Integer> coordinates = new ArrayList<Integer>(Arrays.asList(200, 125, 225, 125, 250, 125, 275, 125, 300, 125, 325, 125, 350, 125, 375, 125, 400, 125, 425, 125, 450, 125, 475, 125, 500, 125, 525, 125, 550, 125, 575, 125, 600, 125, 625, 125, 650, 125, 675, 125,700, 125, 725, 125, 750, 125, 775, 125,
    200, 150, 225, 150, 250, 150, 275, 150, 300, 150, 325, 150, 350, 150, 375, 150, 400, 150, 425, 150, 450, 150, 475, 150, 500, 150, 525, 150, 550, 150, 575, 150, 600, 150, 625, 150, 650, 150, 675, 150,700, 150, 725, 150, 750, 150, 775, 150,
    200, 175, 225, 175, 250, 175, 275, 175, 300, 175, 325, 175, 350, 175, 375, 175, 400, 175, 425, 175, 450, 175, 475, 175, 500, 175, 525, 175, 550, 175, 575, 175, 600, 175, 625, 175, 650, 175, 675, 175,700, 175, 725, 175, 750, 175, 775, 175));
       
    private ImageIcon bridge;
    private ImageIcon brick;
    private ImageIcon ball;
    
    int bridgeX1Position=0;
    int bridgeX2Position=0;
    int bridgeX3Position=0;
    int bridgeX4Position=0;
    int bridgeX5Position=0;
    int bridgeYPosition=0;
    
    int ballXPosition = 500;
    int ballYPosition = 300;
    
    private boolean left = false;
    private boolean right = false;
    
    private boolean leftBall= false;
    private boolean rightBall = false;
    private boolean upBall = false;
    private boolean downBall = true;
    
    private int move = 0;
    private Timer timer;
    
    private int delay = 100;
    
    public Gameplay()
    {
      addKeyListener(this);
         setFocusable(true);
         setFocusTraversalKeysEnabled(false);
         timer = new Timer(delay, this);
         timer.start();  
        
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(99, 99, 801, 801);        
           


        ball = new ImageIcon("src/main/resources/Images/ball.png");
        brick = new ImageIcon("src/main/resources/Images/brick.png");
        bridge = new ImageIcon("src/main/resources/Images/bridge.png");
                
        if (move==0)
        {   
            bridgeX1Position=425;
            bridgeX2Position=450;
            bridgeX3Position=475;
            bridgeX4Position=500;
            bridgeX5Position=525;
            bridgeYPosition=800;
        }

        ball.paintIcon(this, g, ballXPosition, ballYPosition);
        bridge.paintIcon(this, g, bridgeX1Position, bridgeYPosition);
        bridge.paintIcon(this, g, bridgeX2Position, bridgeYPosition);
        bridge.paintIcon(this, g, bridgeX3Position, bridgeYPosition);
        bridge.paintIcon(this, g, bridgeX4Position, bridgeYPosition);
        bridge.paintIcon(this, g, bridgeX5Position, bridgeYPosition);
         
        for (int i=0; i<coordinates.size()-1; i=i+2)
        {
            if(i< coordinates.size()-1)
            {
            brick.paintIcon(this, g, coordinates.get(i), coordinates.get(i+1));
            }
        }
 
        if(ballYPosition>850)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.BOLD, 50));
            g.drawString("GAME OVER!", 370, 500);
            timer.stop();
        }          
         g.dispose();  
    }
     

    @Override
    public void keyTyped(KeyEvent e) {
        //a
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            left = true;
            right = false;
            move++;
            
            if (bridgeX1Position<125)
            {
                left=false;
            }
        }    
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            left = false;
            right = true;
            move++;  
            if (bridgeX1Position>750)
            {
                right=false;
            }
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //c
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
             
        //TYLKO DO DOLU
        if(downBall && ballYPosition<875 && rightBall==false && leftBall==false)
        {   
            downBall=true;
            ballXPosition=ballXPosition;
            ballYPosition=ballYPosition+25;
        }
        
        //TYLKO DO GORY
        if(upBall && ballYPosition>=100 && left!=true && right!=true && rightBall==false && leftBall==false)
        { 
            if(ballYPosition==100)
            {
                upBall=false;
                downBall=true;
            }
            else
            {
                ballXPosition=ballXPosition;
                ballYPosition=ballYPosition-25;
            }
        }
          
        //DO GORY I W PRAWO
        if(upBall==true && rightBall==true && ballYPosition>=100 && ballXPosition<=875)
        {
            if(ballYPosition==100)
            {
                upBall=false;
                downBall=true;
                rightBall=true;
                
            }
            else if(ballXPosition==875)
                    {
                      rightBall=false;
                      leftBall=true;
                    }
            else
            {
                ballXPosition=ballXPosition+25;
                ballYPosition=ballYPosition-25;
            }
        }
        
        //DO GORY I W LEWO
        if(upBall==true && leftBall==true && ballYPosition>=100 && ballXPosition>=100)
        {
            if(ballYPosition==100)
            {
                upBall=false;
                downBall=true;
                
            }
            else if (ballXPosition==100)
            {
                leftBall=false;
                rightBall=true;
            }
            else
            {
                ballXPosition=ballXPosition-25;
                ballYPosition=ballYPosition-25;
            }
        }
        
        //W DOL I PRAWO
        if(downBall==true && rightBall==true && ballXPosition<=875)
        {
            if(ballXPosition==875)
            {
             rightBall=false;
             leftBall=true;
            }
            else
            {
                ballXPosition=ballXPosition+25;
                ballYPosition=ballYPosition+25;
            }
        }
        
        //W DOL I W LEWO
        if(downBall==true && leftBall==true && ballXPosition>=100)
        {
            if(ballXPosition==100)
            {
             rightBall=true;
             leftBall=false;
            }
            else
            {
                ballXPosition=ballXPosition-25;
                ballYPosition=ballYPosition+25;
            }
        }
            
        if(ballYPosition==bridgeYPosition)
        {
            if(ballXPosition == bridgeX1Position
                    || ballXPosition == bridgeX2Position
                    || ballXPosition == bridgeX3Position
                    || ballXPosition == bridgeX4Position
                    || ballXPosition == bridgeX5Position)
            {
                if(left==false && right==false)
                {
                    downBall=false;
                    upBall=true;
                }
                
                if(left==true && right==false)
                {
                    downBall=false;
                    upBall=true;
                    leftBall=false;
                    rightBall=true;
                }
                
                if(left==false && right==true)
                {
                    downBall=false;
                    upBall=true;
                    leftBall=true;
                    rightBall=false;
                }
            }
            
        }
        
        for(int i=0; i<coordinates.size()-1; i=i+2)
        {
            if(i<=coordinates.size()-1)
            {
                if(coordinates.get(i)==ballXPosition && coordinates.get(i+1)==ballYPosition)
                {
                    
                    coordinates.remove(i);
                    coordinates.remove(i);
                    repaint();
                    
                    if(upBall==true && leftBall==false && rightBall==false)
                    {
                        upBall=false;
                        downBall=true;
                    }
                    else if (upBall==true && leftBall==true)
                    {
                        upBall=false;
                        downBall=true;
                        if(coordinates.equals(ballXPosition-25) && coordinates.equals(ballYPosition+25))
                        {
                            leftBall=false;
                            rightBall=true;
                        }
                    }
                    else if (upBall==true && rightBall==true)
                    {
                        rightBall=false;
                        downBall=true;
                    }
                }      
            }
        }
           
        repaint();
        if(left)
        {        
            bridgeX1Position=bridgeX1Position-25;
            bridgeX2Position=bridgeX2Position-25;
            bridgeX3Position=bridgeX3Position-25;
            bridgeX4Position=bridgeX4Position-25;
            bridgeX5Position=bridgeX5Position-25;
            left=false;
            repaint();
        }
        
         if(right)
        {        
            bridgeX1Position=bridgeX1Position+25;
            bridgeX2Position=bridgeX2Position+25;
            bridgeX3Position=bridgeX3Position+25;
            bridgeX4Position=bridgeX4Position+25;
            bridgeX5Position=bridgeX5Position+25;
            right=false;
            repaint();
        }        
    }       
}
