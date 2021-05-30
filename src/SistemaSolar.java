/* Reproduzir o sistema solar e seus movimentos */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.*;

public class SistemaSolar extends JPanel implements Runnable
{
    private BufferedImage spaceImg, sunImg, mercImg, venusImg, earthPlanet, marsImg, jupiterImg, saturnImg;
    double mercRotVel = 0.0;
    double venusRotVel = 0.0;
    double earthRotVel = 0.0;
    double marsRotVel = 0.0;
    double jupiterRotVel = 0.0;
    double saturnRotVel = 0.0;
    double luaRotVel = 0.0;
    double i=0.0;
    
    public SistemaSolar()
    {
        Thread gameLoop = new Thread(this);
        gameLoop.start();
        
        try {
            spaceImg = ImageIO.read(new File("imagens/space.jpg"));
            sunImg = ImageIO.read(new File("imagens/sun_1.jpeg"));
            mercImg = ImageIO.read(new File("imagens/merc.jpeg"));
            venusImg = ImageIO.read(new File("imagens/venus.jpg"));
            earthPlanet = ImageIO.read(new File("imagens/earth.jpeg"));
            marsImg = ImageIO.read(new File("imagens/mars.jpeg"));
            jupiterImg = ImageIO.read(new File("imagens/jupiter.jpg"));
            saturnImg = ImageIO.read(new File("imagens/saturn.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    
        Graphics2D g2d = (Graphics2D) g;
        
        AffineTransform at = AffineTransform.getTranslateInstance(-50, 30);
        AffineTransform at2 = AffineTransform.getTranslateInstance(870, 300);
        AffineTransform at3 = AffineTransform.getRotateInstance(0);
        AffineTransform at4 = AffineTransform.getTranslateInstance(545, 390);
        AffineTransform at5 = AffineTransform.getTranslateInstance(550, 395);
        AffineTransform at6 = AffineTransform.getTranslateInstance(550, 395);
        AffineTransform at7 = AffineTransform.getTranslateInstance(550, 395);
        AffineTransform at8 = AffineTransform.getTranslateInstance(550, 395);
        AffineTransform at9 = AffineTransform.getTranslateInstance(550, 395);
        AffineTransform at10 = AffineTransform.getTranslateInstance(-90, 155);
        
        /* Space (BackGroung Image) */
        g2d.drawImage(spaceImg, 0, 0, 0+1100, 0+900, 0,0, spaceImg.getWidth(), spaceImg.getHeight(), null);
        
        /* Nome e Nro */
        g2d.setPaint(new GradientPaint(5, 30, Color.yellow, 35, 100, Color.white, true));
        g2d.setFont(new Font("Serif", Font.BOLD, 32));
        //g2d.drawString("Crisbelo Neto", 5, 40);
        //g2d.drawString("- 16862", 70, 80);
        
        /* Sol */
        g2d.setPaint(new GradientPaint(10, 30, Color.red, 35, 100, Color.yellow, true));
        //g2d.fillOval(500, 350, 95, 95);
        g2d.drawImage(sunImg, 490, 350, 490+110, 350+95, 0,0, sunImg.getWidth(), sunImg.getHeight(), null);
        
        /* Órbitas */
        g2d.setPaint(new GradientPaint(10, 30, Color.white, 35, 100, Color.DARK_GRAY, true));
        g2d.translate(495, 345);
        g2d.drawOval(-30, -30, 165, 165);
        g2d.translate(52, 52);
        g2d.drawOval(-135, -135, 265, 265);
        g2d.drawOval(-185, -185, 370, 370);
        g2d.drawOval(-255, -255, 510, 510);
        g2d.drawOval(-285, -285, 570, 570);
        g2d.drawOval(-365, -365, 730, 730);
        g2d.drawOval(-435, -435, 865, 865);
        
        /* Mercúrio */
        g2d.rotate(Math.toRadians(-mercRotVel));
        g2d.setPaint(new GradientPaint(20, 10, Color.orange, 50, 10, Color.DARK_GRAY, true));
        //g2d.fillOval(50, -75, 30, 30);
        g2d.drawImage(mercImg, 50, -75, 50+30, -75+30, 0,0, mercImg.getWidth(), mercImg.getHeight(), null);
        
        /* Vénus */
        g2d.rotate(Math.toRadians(-venusRotVel +mercRotVel));
        g2d.setPaint(new GradientPaint(0, 10, Color.orange, 10, 10, Color.yellow, true));
        g2d.translate(-120, -120);
        g2d.fillOval(0, 0, 45, 45);
        g2d.drawImage(venusImg, 0, 0, 0+45, 0+45, 0,0, venusImg.getWidth(), venusImg.getHeight(), null);
        
        /* Terra */
        g2d.setTransform(at3);
        g2d.setPaint(new GradientPaint(0, 10, Color.blue, 40, 5, Color.white, true));
        g2d.setTransform(at4);
        g2d.rotate(Math.toRadians(-earthRotVel));
        //g2d.fillOval(-90, 155, 47, 47);
        //g2d.drawImage(earthPlanet, -90, 155, -90+47, 155+47, 0,0, earthPlanet.getWidth(), earthPlanet.getHeight(), null);
        at10.rotate(Math.toRadians(-i), resize(earthPlanet, 47, 47).getWidth()/2, resize(earthPlanet, 47, 47).getHeight()/2);
        g2d.drawImage(resize(earthPlanet, 47, 47), at10, null);
        
        /* Lua da Terra */
        g2d.setPaint(new GradientPaint(10, 0, Color.white, 0, 47, Color.gray, true));
        g2d.translate(-167, 43);
        g2d.rotate(-luaRotVel/22, 100, 137);
        g2d.fillOval(110, 160, 15, 15);
        
        /* Marte */
        g2d.setTransform(at3);
        g2d.setPaint(new GradientPaint(10, 30, Color.red, 5, 100, Color.yellow, true));
        g2d.setTransform(at5);
        g2d.rotate(Math.toRadians(-marsRotVel));
        //g2d.fillOval(225, 20, 40, 40);
        g2d.drawImage(marsImg, 225, 20, 225+45, 20+45, 0,0, marsImg.getWidth(), marsImg.getHeight(), null);
        
        /* Luas de Marte */
        g2d.setPaint(new GradientPaint(10, 0, Color.white, 0, 47, Color.gray, true));
        g2d.translate(145, -95);
        g2d.rotate(-luaRotVel/22, 100, 137);
        g2d.fillOval(100, 160, 3, 3);
        g2d.translate(23, -25);
        g2d.fillOval(100, 160, 5, 5);
        
        /* Júpiter */
        g2d.setTransform(at3);
        g2d.setPaint(new GradientPaint(0, 0, Color.GRAY, 10, 4, Color.CYAN, true));
        g2d.setTransform(at6);
        g2d.rotate(Math.toRadians(-jupiterRotVel));
        //g2d.fillOval(265, -85, 60, 60);
        g2d.drawImage(jupiterImg, 265, -85, 265+60, -85+60, 0,0, jupiterImg.getWidth(), jupiterImg.getHeight(), null);
        
        /* Luas de Júpiter */
        g2d.setPaint(new GradientPaint(10, 0, Color.white, 0, 47, Color.gray, true));
        g2d.translate(195, -190);
        g2d.rotate(-luaRotVel/25, 100, 137);
        g2d.fillOval(120, 160, 15, 15);
        g2d.translate(13, -20);
        g2d.fillOval(120, 160, 10, 10);
        g2d.translate(-35, -57);
        g2d.fillOval(120, 160, 20, 20);
        g2d.translate(-35, 13);
        g2d.fillOval(120, 160, 17, 17);
        
        /* Saturno */
        g2d.setTransform(at3);
        g2d.setPaint(new GradientPaint(0, 40, Color.cyan, 10, 50, Color.yellow, true));
        g2d.setTransform(at7);
        g2d.rotate(Math.toRadians(-saturnRotVel));
        //g2d.fillOval(305, 155, 45, 45);
        g2d.drawImage(saturnImg, 305, 155, 305+45, 155+45, 0,0, saturnImg.getWidth(), saturnImg.getHeight(), null);
        
        /* Luas de Saturno */
        g2d.setPaint(new GradientPaint(10, 0, Color.white, 0, 47, Color.gray, true));
        g2d.translate(229, 40);
        g2d.rotate(-luaRotVel/25, 100, 137);
        g2d.fillOval(113, 160, 6, 6);
        g2d.translate(6, -10);
        g2d.fillOval(113, 160, 7, 7);
        g2d.translate(-10, -42);
        g2d.fillOval(113, 160, 8, 8);
        g2d.translate(-32, 50);
        g2d.fillOval(113, 160, 9, 9);
        g2d.translate(15, 5);
        g2d.fillOval(113, 160, 11, 11);
        g2d.translate(32, -43);
        g2d.fillOval(113, 160, 16, 16);
        g2d.translate(-32, -14);
        g2d.fillOval(113, 160, 7, 7);
        g2d.translate(-17, 5);
        g2d.fillOval(113, 160, 9, 9);
        g2d.translate(-6, 15);
        g2d.fillOval(113, 160, 5, 5);
    }
    
    private void update()
    {    
        mercRotVel += 2.4;
        venusRotVel += 0.7;
        earthRotVel += 1.1;
        marsRotVel += 0.8;
        jupiterRotVel += 0.4;
        saturnRotVel += 0.2;
        luaRotVel += 1.1;
        
        i += 2.0;
    }
    
    public BufferedImage resize(BufferedImage img, int newW, int newH)
    {
        int w = img.getWidth();  
        int h = img.getHeight();  
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
        g.dispose();  
        return dimg;  
    }
    
    private void sleep()
    {
        try {
            Thread.sleep(16);
        } catch (InterruptedException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            repaint(); // pinta
            sleep(); // dorme
            update(); // atualiza
        }
    }
}