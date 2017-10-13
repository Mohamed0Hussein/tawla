package tawla;

import java.awt.*;

public class Positions {
    public static Point [][] Pos;
    public static boolean [] availablePos;
    public Positions(){
        Pos = new Point[24][16];
        for(int i=0;i<24;i++)
            for(int j=0;j<16;j++)
                Pos[i][j] = new Point();
        setupPositions();
        availablePos = new boolean[24];
        for(int i=0;i<24;i++)
            availablePos[i] = false;
    }
        public void setupPositions(){
    int n=902;
    for(int j=0;j<6;j+=2){
            Pos[j][0].x=(int) (n*Dim.H_ratio);
            Pos[j][0].y=(int) (395*Dim.V_ratio);
            for(int i=1;i<16;i++){
                Pos[j][i].x=(int) (n*Dim.H_ratio);
                Pos[j][i].y=(int) (Pos[0][i-1].y+15*Dim.V_ratio);
        }
            n-=149;
    }
    n=406;
    for(int j=6;j<12;j+=2){
            Pos[j][0].x=(int) (n*Dim.H_ratio);
            Pos[j][0].y=(int) (395*Dim.V_ratio);
            for(int i=1;i<16;i++){
                Pos[j][i].x=(int) (n*Dim.H_ratio);
                Pos[j][i].y=(int) (Pos[6][i-1].y+15*Dim.V_ratio);
        }
            n-=149;
    }
    n=33;
    for(int j=12;j<18;j+=2){
            Pos[j][0].x=(int) (n*Dim.H_ratio);
            Pos[j][0].y=(int) (234*Dim.V_ratio);
            for(int i=1;i<16;i++){
                Pos[j][i].x=(int) (n*Dim.H_ratio);
                Pos[j][i].y=(int) (Pos[12][i-1].y-15*Dim.V_ratio);
        }
            n+=149;
    }
    n=530;
    for(int j=18;j<24;j+=2){
            Pos[j][0].x=(int) (n*Dim.H_ratio);
            Pos[j][0].y=(int) (234*Dim.V_ratio);
            for(int i=1;i<16;i++){
                Pos[j][i].x=(int) (n*Dim.H_ratio);
                Pos[j][i].y=(int) (Pos[18][i-1].y-15*Dim.V_ratio);
        }
            n+=149;
    }
    n=22;
    for(int j=1;j<24;j+=2){
        for(int i=0;i<16;i++){
            Pos[j][i].x=Pos[n][i].x;
            Pos[j][i].y=Pos[j-1][i].y;
        }
        n-=2;
    }
}

}
