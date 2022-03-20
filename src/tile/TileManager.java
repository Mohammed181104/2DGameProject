package tile;

import com.company.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.company.GameBoard.gridSize;

public class TileManager {
    GamePanel gp;
    public Tiles[] tile;
    int mapNum[][];
    private int numMines;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tiles[10];
        mapNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();



    }

    //LOADS TILE IMAGES
    public void getTileImage(){
        try{
            tile[0] = new Tiles(false,0,ImageIO.read(getClass().getResourceAsStream("/Tiles/Tile1.png")));
            tile[1] = new Tiles(false,1,ImageIO.read(getClass().getResourceAsStream("/Tiles/Tile2.png")));
            tile[2] = new Tiles(true,0,ImageIO.read(getClass().getResourceAsStream("/Tiles/Tile3.png")));
            tile[3] = new Tiles(true,5,ImageIO.read(getClass().getResourceAsStream("/Tiles/Tile4.png")));
            tile[4] = new Tiles(true,5,ImageIO.read(getClass().getResourceAsStream("/Tiles/Mine.png")));

        }catch(Exception e){
            System.out.println(e);
        }
    }

    //LOADS ORIGINAL STARTING MAP
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

    //DRAWS BLOCK IN LOCATION
    public void draw(Graphics2D g2, int pX,int pY){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapNum[col][row];
            if(gp.keyH.onePressed){
                drawLight(g2, pX, pY);
            }
            else if(gp.keyH.twoPressed){
                drawMine(g2,pX,pY);
                numMines++;
            }
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            System.out.println(numMines);
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

    private void drawLight(Graphics2D g2, int pX, int pY) {
        if(mapNum[pX][pY]!=1) {
            Spiral(5,5, pX, pY, g2, 2);
            mapNum[pX][pY] = 3;
        }
    }

    private void drawMine(Graphics2D g2, int pX, int pY) {
        if(mapNum[pX][pY]!=1) {
            Spiral(2,2, pX, pY, g2, 1);
            mapNum[pX+1][pY+1] = 4;

        }
    }

    //Creates a loop around the block placed - NO IDEA HOW IT WORKS
    private void Spiral(int X, int Y,int placeX, int placeY, Graphics2D g3, int tileNum){
        int x,y,dx,dy;
        x=y=dx=0;
        dy=-1;
        int t = Math.max(X,Y);
        int maxI = t*t;
        for (int i = 0; i < maxI; i++) {
            if((-1*X/2 <= x)&&(x<=X/2)&&(-1*Y/2 <= y)&&(y<= Y/2)){
                if(mapNum[placeX+x][placeY+y] == 0||mapNum[placeX+x][placeY+y] == 2) {
                    mapNum[placeX+x][placeY+y] = tileNum;
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
    public void generateStartingZone(Graphics2D g2,int xCoord,int yCoord){ // creates an 8 x 8 area of light
        Spiral(8,8,xCoord,yCoord,g2,2);

    }
    public void delay(){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
