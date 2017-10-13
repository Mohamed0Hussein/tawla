package tawla;

import java.awt.*;

public class Oshat extends ImagePanel{
    public String oshatType;
    public Point currentPosition;
    public int ID;
    public boolean activated;
    public Oshat(int x, int y, int w, int h, String path, String oshatType){
        super(x, y, w, h, path);
        this.oshatType = oshatType;
        this.ID = -1;
        this.activated = false;
        
        currentPosition = new Point();
        if(oshatType=="B"){
            currentPosition.x = 0;
            currentPosition.y = ID;
        }
        else if(oshatType=="W")
            currentPosition.x = 23;
            currentPosition.y = ID;
    }
}
