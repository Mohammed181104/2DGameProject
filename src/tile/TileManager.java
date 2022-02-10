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
                tileNum = mapNum[pX][pY];
            }
            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
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
}
