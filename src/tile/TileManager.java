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
                mapNum[pX][pY] = 3;
                mapNum[pX+1][pY+1] = 2;
                mapNum[pX+1][pY-1] = 2;
                mapNum[pX-1][pY+1] = 2;
                mapNum[pX-1][pY-1] = 2;
                mapNum[pX-1][pY] = 2;
                mapNum[pX+1][pY] = 2;
                mapNum[pX][pY-1] = 2;
                mapNum[pX][pY+1] = 2;
                g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);

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
}
