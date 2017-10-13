package tawla;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
enum Player{black,white};
public class TawlaBoard extends ImagePanel{
    public int d1,d2,d3,d4;
    public static int activeOshat;
    private ImagePanel rollBtn,dice1,dice2,dice1Gif,dice2Gif,exit;
    private ImagePanel [][] activatedOshat;
    private JLabel player1,player2,score1,score2;
    private Font f1;
    private Random rand;
    private javax.swing.Timer t;
    private Oshat [] blackOshat ,whiteOshat;
    private Positions ps;
    private static Player p;
    private boolean isEqual=false;
public TawlaBoard(int x , int y,int w, int h, String path){
    super(x,y,w,h,path);
    this.setLayout(null);
    p=Player.white;
    ps = new Positions();
    d1=0;
    d2=0;
    d3=0;
    d4=0;
    exit = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/Exit.png");
    rollBtn = new ImagePanel(0,0, (int) (90*Dim.H_ratio), (int) (90*Dim.V_ratio),"images/roll.png");
    dice1 = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/dice1.png");
    dice2 = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/dice1.png");
    dice1Gif = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/dice.gif");
    dice2Gif = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/dice.gif");
    activatedOshat = new ImagePanel[24][16];
    for(int i=0;i<24;i++)
        for(int j=0;j<16;j++)
            activatedOshat[i][j] = new ImagePanel(0,0,(int)(63.0*Dim.H_ratio),(int)(64.0*Dim.V_ratio),"images/activatedOshat.png");
    for(int i=0;i<24;i++)
        for(int j=0;j<16;j++)
            activatedOshat[i][j].setBounds(Positions.Pos[i][j].x,Positions.Pos[i][j].y,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio));
    for(int i=0;i<24;i++)
        for(int j=0;j<16;j++)
            activatedOshat[i][j].setVisible(false);
    blackOshat = new Oshat[15];
    whiteOshat = new Oshat[15];
    for(int i=0;i<15;i++)
        blackOshat[i] = new Oshat(0,0,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio),"images/2oshat_eswd.png","B");
    for(int i=0;i<15;i++)
        whiteOshat[i] = new Oshat(0,0,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio),"images/2oshat_abyd.png","W");
    player1 = new JLabel("PLAYER 1");
    player2 = new JLabel("PLAYER 2");
    score1 = new JLabel("0");
    score2 = new JLabel("0");
    rand=new Random();
    f1 = new Font(player1.getText(),Font.BOLD,40);
    player1.setFont(f1);
    player2.setFont(f1);
    score1.setFont(f1);
    score2.setFont(f1);
    player1.setBounds((int)(1025.0*Dim.H_ratio),(int)(240.0*Dim.V_ratio),(int)(174.0*Dim.H_ratio),(int)(44.0*Dim.V_ratio));
    score1.setBounds((int)(1078.0*Dim.H_ratio),(int)(285.0*Dim.V_ratio),(int)(174.0*Dim.H_ratio),(int)(44.0*Dim.V_ratio));
    player2.setBounds((int)(1025.0*Dim.H_ratio),(int)(340.0*Dim.V_ratio),(int)(174.0*Dim.H_ratio),(int)(44.0*Dim.V_ratio));
    score2.setBounds((int)(1078.0*Dim.H_ratio),(int)(385.0*Dim.V_ratio),(int)(174.0*Dim.H_ratio),(int)(44.0*Dim.V_ratio));
    player1.setForeground(new Color(225,178,124));
    score1.setForeground(new Color(225,178,124));
    player2.setForeground(new Color(78,49,7));
    score2.setForeground(new Color(78,49,7));
    rollBtn.setBounds((int)(1047.0*Dim.H_ratio), (int) (120.0*Dim.V_ratio), (int) (90*Dim.H_ratio), (int) (90*Dim.V_ratio));
    dice1.setBounds((int)(1000*Dim.H_ratio),(int)(25*Dim.V_ratio),(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio));
    dice2.setBounds((int)(1095*Dim.H_ratio),(int)(25*Dim.V_ratio),(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio));
    exit.setBounds((int)(1045*Dim.H_ratio),(int)(582.0*Dim.V_ratio),(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio));
    for(int i=0;i<15;i++)
        blackOshat[i].setBounds(Positions.Pos[0][i].x,Positions.Pos[0][i].y,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio));
    for(int i=0;i<15;i++)
        whiteOshat[i].setBounds(Positions.Pos[23][i].x,Positions.Pos[23][i].y,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio));
    exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me) {
            repaint(exit,"images/Exit2.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(exit,"images/Exit22.png");
            System.exit(0);
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            repaint(exit,"images/Exit3.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            repaint(exit,"images/Exit.png");
        }
    });    
    rollBtn.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseEntered(MouseEvent me){
            repaint(rollBtn,"images/roll2.png");
        }
        @Override
        public void mouseExited(MouseEvent me){
            repaint(rollBtn,"images/roll.png");
        }
        @Override
        public void mousePressed(MouseEvent me){
            repaint(rollBtn,"images/roll1.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(rollBtn,"images/roll.png");
            if(d1==0&&d2==0&&d3==0&&d4==0){
            if(isEqual)
                isEqual=false;
            for(int i=0;i<24;i++)
                Positions.availablePos[i]=false;
            for(int i=0;i<24;i++)
                for(int j=0;j<16;j++)
                    activatedOshat[i][j].setVisible(false);
            if(p==Player.black){
                player2.setForeground(new Color(225,178,124));
                score2.setForeground(new Color(225,178,124));
                player1.setForeground(new Color(78,49,7));
                score1.setForeground(new Color(78,49,7));
                p=Player.white;
            }
            else if(p==Player.white){
                player1.setForeground(new Color(225,178,124));
                score1.setForeground(new Color(225,178,124));
                player2.setForeground(new Color(78,49,7));
                score2.setForeground(new Color(78,49,7));
                p=Player.black;
            }
            repaint(dice1,"images/dice.gif");
            repaint(dice2,"images/dice.gif");
            if(t==null)
            t = new javax.swing.Timer(1000,new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    d1=rand.nextInt(6)+1;
                    d2=rand.nextInt(6)+1;
                    t.stop();
                    repaint(dice1,"images/dice"+d1+".png");
                    repaint(dice2,"images/dice"+d2+".png");
                    
                    if(d1==d2){
                        isEqual=true;
                        d3=d1;
                        d4=d1;
                    }
                    if(p==Player.white){
                        d1*=-1;
                        d2*=-1;
                        d3*=-1;
                        d4*=-1;
                    }
                    }
            
            });
            t.start();
            }
        }
    });
    
    for(int i=0;i<15;i++){
        final int n = i;
        blackOshat[i].addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
                if(p==Player.black)
                    configureOshat(blackOshat,n);
            }
        });
    }
        for(int i=0;i<15;i++){
            final int n = i;
        whiteOshat[i].addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
                if(p==Player.white)
                    configureOshat(whiteOshat,n);
            }
        });
        }
    this.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent me) {
            deactivateOshat();
        }
    });
    for(int i=0;i<24;i++){
        for(int j=0;j<16;j++){
        final int n = i, m = j;
        activatedOshat[i][j].addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
                
                if(activatedOshat[n][m].isVisible()){
                    if(p==Player.black)
                        limitMovement(blackOshat[activeOshat],n,m);
                    else if(p==Player.white)
                        limitMovement(whiteOshat[activeOshat],n,m);                       
                }
        }
        });
        }
    }
    this.add(rollBtn);
    this.add(dice1);
    this.add(dice2);
    this.add(exit);
    this.add(player1);
    this.add(player2);
    this.add(score1);
    this.add(score2);
    for(int i=0;i<24;i++)
        for(int j=0;j<16;j++)
            this.add(activatedOshat[i][j]);
    for(int i=0;i<15;i++){
        this.add(blackOshat[i]);
        this.add(whiteOshat[i]);
    }
} 
public void repaint(ImagePanel pnl ,String path){
    pnl.setImage(path);
    pnl.repaint();
}
public void activateOshat(int n){
    this.activeOshat=n;
}
public void deactivateOshat(){
    for(int i=0;i<24;i++)
                for(int j=0;j<16;j++)
                    activatedOshat[i][j].setVisible(false);
}
public void limitMovement(Oshat o,int n,int m){
    int bx = o.currentPosition.x;
    if(isEqual){
        if(n==o.currentPosition.x+d1){
            if(d4==0&&d3==0&&d2==0)
                d1=0;
            if(d4==0&&d3==0)
                d2=0;
            if(d4==0)
                d3=0;
            d4=0;
        }
    else if(n==o.currentPosition.x+(d1+d2)){
        if(d3==0&&d4==0){
            d2=0;
            d1=0;
        }
        if(d4==0){
            d3=0;
            d2=0;
        }
        d3=0;
        d4=0;
    }
    else if(n==o.currentPosition.x+(d1+d2+d3)){
        if(d4==0){
            d1=0;
            d2=0;
            d3=0;
        }
        d4=0;
        d3=0;
        d2=0;
    }
    else if(n==o.currentPosition.x+(d1+d2+d3+d4)){
            d1=0;
            d2=0;
            d3=0;
            d4=0;
    }
    }
    else if(!isEqual){
        if(n==o.currentPosition.x+d1){
            d1=0;
        }
        if(n==o.currentPosition.x+d2)
            d2=0;
        if(n==o.currentPosition.x+(d1+d2)){
            d1=0;
            d2=0;
        }
    }
    int temp = 0;
    if(p==Player.black)
    for(int i=0;i<15;i++){
        if(blackOshat[i].currentPosition.x==n){
            if(blackOshat[i].currentPosition.y>temp)
                temp=blackOshat[i].currentPosition.y;
        }
    }
    else if(p==Player.white)
        for(int i=0;i<15;i++){
            if(whiteOshat[i].currentPosition.x==n){
                if(whiteOshat[i].currentPosition.y>temp)
                    temp=whiteOshat[i].currentPosition.y;
            }   
        }
    o.currentPosition.y=temp+1;                    
    o.setLocation(Positions.Pos[n][m].x,Positions.Pos[n][m].y);
    o.currentPosition.x+=n-o.currentPosition.x;
    deactivateOshat();
    for(int i=0;i<24;i++)
        Positions.availablePos[i]=false;
    Positions.availablePos[n]=false;
}
public boolean checkLocation(int a,int b){
        if(a+b>23||a+b<0)
            return false;
        return true;
}
public boolean check(int n){
    if(p==Player.black){
        for(int i=0;i<15;i++)
            if(whiteOshat[i].currentPosition.x==blackOshat[activeOshat].currentPosition.x+n)
            return false;
        return true;
    }
    else if(p==Player.white){
        for(int i=0;i<15;i++)
            if(blackOshat[i].currentPosition.x==whiteOshat[activeOshat].currentPosition.x+n)
            return false;
        return true;
    }
    return true;
}
public void configureOshat(Oshat [] o,int n){
    System.out.println("d1: "+d1+" "+"d2: "+d2+" "+"d3: "+d3+" "+"d4: "+d4);
                deactivateOshat();
                activateOshat(n);
                int a=0,b=0,c=0;
                int a1=0,b1=0,c1=0,e1=0;
                int bx = o[activeOshat].currentPosition.x;
                if(d1!=0||d2!=0||d3!=0||d4!=0){
                    if(checkLocation(bx,d1)&&d1!=0)
                        Positions.availablePos[bx+d1]=check(d1);
                    if(checkLocation(bx,d2)&&d2!=0)
                        Positions.availablePos[bx+d2]=check(d2);
                    if(checkLocation(bx,d2+d1)&&d1!=0&&d2!=0&&check(d2)&&check(d1))
                        Positions.availablePos[bx+d1+d2]=check(d1+d2);
                    
                    for(int i=0;i<15;i++){
                        if(i==activeOshat)
                            continue;
                        if(o[i].currentPosition.x==bx+d1){
                            if(o[i].currentPosition.y>a)
                                a=o[i].currentPosition.y;
                        }
                    }
                    for(int i=0;i<15;i++){
                        if(i==activeOshat)
                            continue;
                        if(o[i].currentPosition.x==bx+d2){
                            if(o[i].currentPosition.y>b)
                                b=o[i].currentPosition.y;
                        }
                    }
                    for(int i=0;i<15;i++){
                        if(i==activeOshat)
                            continue;
                        if(o[i].currentPosition.x==bx+d1+d2){
                            if(o[i].currentPosition.y>c)
                                c=o[i].currentPosition.y;
                        }
                    }
                    if(isEqual){
                        if(checkLocation(bx,d1)&&d1!=0)
                            Positions.availablePos[bx+d1]=check(d1);
                        if(checkLocation(bx,d1+d2)&&d2!=0&&check(d1))
                            Positions.availablePos[bx+d1+d2]=check(d1+d2);
                        if(checkLocation(bx,d1+d2+d3)&&d3!=0&&check(d1)&&check(d2))
                            Positions.availablePos[bx+d1+d2+d3]=check(d1+d2+d3);
                        if(checkLocation(bx,d1+d2+d3+d4)&&d4!=0&&check(d1)&&check(d2)&&check(d3))
                            Positions.availablePos[bx+d1+d2+d3+d4]=check(d1+d2+d3+d4);
                        
                        for(int i=0;i<15;i++){
                        if(i==activeOshat)
                            continue;
                        if(o[i].currentPosition.x==bx+d1){
                            if(o[i].currentPosition.y>a1)
                                a1=o[i].currentPosition.y;
                        }
                    }
                        for(int i=0;i<15;i++){
                        if(i==activeOshat)
                            continue;
                        if(o[i].currentPosition.x==bx+d1+d2){
                            if(o[i].currentPosition.y>b1)
                                b1=o[i].currentPosition.y;
                        }
                    }
                        for(int i=0;i<15;i++){
                        if(i==activeOshat)
                            continue;
                        if(o[i].currentPosition.x==bx+d1+d2+d3){
                            if(o[i].currentPosition.y>c1)
                                c1=o[i].currentPosition.y;
                        }
                    }
                        for(int i=0;i<15;i++){
                        if(i==activeOshat)
                            continue;
                        if(o[i].currentPosition.x==bx+d1+d2+d3+d4){
                            if(o[i].currentPosition.y>e1)
                                e1=o[i].currentPosition.y;
                        }
                    }
                        if(check(d1)&&check(d1+d2)&&check(d1+d2+d3)&&check(d1+d2+d3+d4)&&checkLocation(bx,d1+d2+d3+d4)&&Positions.availablePos[bx+d1+d2+d3+d4])
                            activatedOshat[bx+d1+d2+d3+d4][14-e1].setVisible(true); 
                        if(check(d1)&&check(d1+d2)&&check(d1+d2+d3)&&checkLocation(bx,d1+d2+d3)&&Positions.availablePos[bx+d1+d2+d3])
                            activatedOshat[bx+d1+d2+d3][14-c1].setVisible(true); 
                        if(check(d1)&&check(d1+d2)&&checkLocation(bx,d1+d2)&&Positions.availablePos[bx+d1+d2])
                            activatedOshat[bx+d1+d2][14-b1].setVisible(true); 
                        if(checkLocation(bx,d1)&&Positions.availablePos[bx+d1])
                            activatedOshat[bx+d1][14-a1].setVisible(true);    
                    }
                    else if(!isEqual){
                    if(check(d1)&&checkLocation(bx,d1)&&Positions.availablePos[bx+d1]){
                        activatedOshat[bx+d1][14-a].setVisible(true);        
                    }
                    if(check(d2)&&checkLocation(bx,d2)&&Positions.availablePos[bx+d2]){
                        activatedOshat[bx+d2][14-b].setVisible(true);        
                    }
                    if(check(d1+d2)&&checkLocation(bx,d1+d2)&&Positions.availablePos[bx+d1+d2]){
                        activatedOshat[bx+d1+d2][14-c].setVisible(true);        
                    }
                }
                }
                for(int i=0;i<24;i++){
                    System.out.println(i+": "+Positions.availablePos[i]);
                }
}
}