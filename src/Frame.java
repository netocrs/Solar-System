/*  */

import java.awt.Color;
import javax.swing.JFrame;

public class Frame extends JFrame
{
    final int WIDTH=1100, HEIGHT=900;
    SistemaSolar sisSol = new SistemaSolar();
    
    public Frame()
    {
        sisSol.setBackground(Color.black);
        
        add(sisSol);
        
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    } 
}
