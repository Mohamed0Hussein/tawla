package tawla;
import javax.swing.*;
import java.awt.*;
class Dim{
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double V_ratio = screenSize.height/686.0;
    public static double H_ratio = screenSize.width/1200.0;
}
class TawlaFrame extends JFrame{
    public TawlaBoard  MNPanel;
    public Dimension screenSize;
    public TawlaFrame(){
        init();
    }
    public void init(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Tawla");
        this.setBounds(0,0,screenSize.width,screenSize.height);
        this.setUndecorated(true);
        Container c=this.getContentPane();
        MNPanel = new TawlaBoard(0,0,screenSize.width,screenSize.height,"images/tawla.png");
        MNPanel.setBounds(0,0,screenSize.width,screenSize.height);
        MNPanel.setLayout(null);
        c.add(MNPanel);
        c.setLayout(null);            
    }
}
public class Tawla {
    public static void main(String[] args) {
        TawlaFrame f = new TawlaFrame();
        f.setVisible(true);
    }
    
}
