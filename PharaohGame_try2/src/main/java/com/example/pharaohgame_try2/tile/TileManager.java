package com.example.pharaohgame_try2.tile;
import com.example.pharaohgame_try2.DisplayedObject;
import com.example.pharaohgame_try2.ScreenMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends DisplayedObject {
    Tile[] tile;
    GraphicsContext bgGc;
    ScreenMap bgScreen;
    int[][] mapTileNumber;
    public TileManager(GraphicsContext bgGc, ScreenMap bgScreen) {
        this.bgGc = bgGc;
        this.bgScreen = bgScreen;
        tile = new Tile[10];
        mapTileNumber = new int[bgScreen.getMaxScreenCol()][bgScreen.getMaxScreenRow()];
        getTileImage();
        loadMap("/com/example/pharaohgame_try2/maps/map01.txt");
    }

    public void getTileImage() {
        String imgPath = "/com/example/pharaohgame_try2/tiles/";
        //System.out.println("Bildpfad: " + getClass().toString() + " , " + getClass().getResource(imgPath).toString());

        tile[0] = new Tile();
        tile[0].image = new Image(getClass().getResource(imgPath+"grass.png").toString()); //

        tile[1] = new Tile();
        tile[1].image = new Image(getClass().getResource(imgPath+"concrete.png").toString()); //
        //  tile[1].image = new Image(String.valueOf(HelloApplication.class.getResource("tiles"+File.separator+"sandstone.png")));

        tile[2] = new Tile();
        tile[2].image = new Image(getClass().getResource(imgPath+"water.png").toString()); //
        // tile[2].image = new Image(String.valueOf(HelloApplication.class.getResource("tiles"+File.separator+"concrete.png")));
        tile[3] = new Tile();
        tile[3].image = new Image(getClass().getResource(imgPath+"earth.png").toString()); //

        tile[4] = new Tile();
        tile[4].image = new Image(getClass().getResource(imgPath+"tree.png").toString()); //

        tile[5] = new Tile();
        tile[5].image = new Image(getClass().getResource(imgPath+"sand.png").toString()); //
    }
    public void loadMap(String mapURI) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapURI); //get the map
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //read the map
            System.out.println("inputStream:" + inputStream.toString());

            int col = 0;
            int row = 0;

            while (col < bgScreen.getMaxScreenCol() && row < bgScreen.getMaxScreenRow()) { //get to the first cell
                String line = bufferedReader.readLine(); //gives a String of the first line i suppose
                System.out.println(line);

                while (col < bgScreen.getMaxScreenCol()) {
                    //fills out line by line
                    String[] numbers = line.split(" "); //gives a String
                    int num = Integer.parseInt(numbers[col]); //str -> int
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == bgScreen.getMaxScreenCol()) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Probleme mit loadMap: " + e.getMessage());
        }

    }
    public void draw() {
        //bgGc.drawImage(tile[0].image,0,0, bgScreen.getTileSize(), bgScreen.getTileSize());
        //bgGc.drawImage(tile[1].image,48,0, bgScreen.getTileSize(), bgScreen.getTileSize());
        //bgGc.drawImage(tile[2].image,96,0, bgScreen.getTileSize(), bgScreen.getTileSize());

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < bgScreen.getMaxScreenCol() && row < bgScreen.getMaxScreenRow()) {
            int tileNum = mapTileNumber[col][row];

            bgGc.drawImage(tile[tileNum].image,x,y, bgScreen.getTileSize(), bgScreen.getTileSize());

            col++;
            x += bgScreen.getTileSize();

            if(col == bgScreen.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += bgScreen.getTileSize();
            }
        }
    }
}
