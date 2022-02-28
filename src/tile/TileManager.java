package tile;

import com.company.GamePanel;
import com.company.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();

    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Tile1.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Tile2.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Tile3.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Tile4.png"));

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col<gp.maxScreenCol && row<gp.maxScreenRow){
                String line = br.readLine();
                while(col<gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row ++;
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void draw(Graphics2D g2, int pX,int pY){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapNum[col][row];
            if(gp.keyH.spacePressed){
                mapNum[pX][pY] = 1;
                g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
            }
            else if(gp.keyH.onePressed){
                if(mapNum[pX][pY]!=1) {
                    Spiral(5,5,pX,pY,g2,tileNum);
                    mapNum[pX][pY] = 3;
                    g2.drawImage(tile[tileNum].image, pX, pY, gp.tileSize, gp.tileSize, null);

                }

            }else {
                g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            }
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
    public void Spiral(int X, int Y,int placeX, int placeY, Graphics2D g3, int tileNum){
        int x,y,dx,dy;
        x=y=dx=0;
        dy=-1;
        int t = Math.max(X,Y);
        int maxI = t*t;
        for (int i = 0; i < maxI; i++) {
            if((-1*X/2 <= x)&&(x<=X/2)&&(-1*Y/2 <= y)&&(y<= Y/2)){

                if(mapNum[placeX+x][placeY+y] == 0) {
                    mapNum[placeX+x][placeY+y] = 2;
                    g3.drawImage(tile[tileNum].image, placeX, placeY, gp.tileSize, gp.tileSize, null);
                }
            }
            if((x==y)||((x<0)&&(x==(-1*y)))||((x>0)&&(x==1-y))){
                t = dx;
                dx= -1*dy;
                dy = t;
            }
            x += dx;
            y += dy;
        }

    }
}
