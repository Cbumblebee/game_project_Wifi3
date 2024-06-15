package com.example.pharaohgame_try2.tile;
import com.example.pharaohgame_try2.DisplayedObject;
import com.example.pharaohgame_try2.PlayerCharacter;
import com.example.pharaohgame_try2.ScreenMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends DisplayedObject {
    public Tile[] tile;
    GraphicsContext bgGc;
    ScreenMap bgScreen;
    public int[][] mapTileNumber;
    PlayerCharacter playerCharacter;
    public TileManager(GraphicsContext bgGc, ScreenMap bgScreen, PlayerCharacter playerCharacter) {

        this.bgGc = bgGc;
        this.bgScreen = bgScreen;
        this.playerCharacter = playerCharacter;

        tile = new Tile[10];
        mapTileNumber = new int[bgScreen.getMaxWorldCol()][bgScreen.getMaxWorldRow()];

        getTileImage();

        loadMap("/com/example/pharaohgame_try2/maps/world01.txt");
    }

    public void getTileImage() {
        String imgPath = "/com/example/pharaohgame_try2/tiles/";
        //System.out.println("Bildpfad: " + getClass().toString() + " , " + getClass().getResource(imgPath).toString());

        tile[0] = new Tile();
        tile[0].image = new Image(getClass().getResource(imgPath+"grass.png").toString()); //

        tile[1] = new Tile();
        tile[1].image = new Image(getClass().getResource(imgPath+"concrete.png").toString()); //
        tile[1].collision = true;

        tile[2] = new Tile();
        tile[2].image = new Image(getClass().getResource(imgPath+"water.png").toString()); //
        tile[2].collision = true;

        tile[3] = new Tile();
        tile[3].image = new Image(getClass().getResource(imgPath+"earth.png").toString()); //

        tile[4] = new Tile();
        tile[4].image = new Image(getClass().getResource(imgPath+"tree.png").toString()); //
        tile[4].collision = true;

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

            while (col < bgScreen.getMaxWorldCol() && row < bgScreen.getMaxWorldRow()) { //get to the first cell
                String line = bufferedReader.readLine(); //gives a String of the first line i suppose
                System.out.println(line);

                while (col < bgScreen.getMaxWorldCol()) {
                    //fills out line by line
                    String[] numbers = line.split(" "); //gives a String
                    int num = Integer.parseInt(numbers[col]); //str -> int
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == bgScreen.getMaxWorldCol()) {
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
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < bgScreen.getMaxWorldCol() && worldRow < bgScreen.getMaxWorldRow()) {

            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * bgScreen.getTileSize();
            int worldY = worldRow * bgScreen.getTileSize();
            int screenX = worldX - playerCharacter.getWorldXCoordinate() + playerCharacter.getScreenXCoordinate();
            int screenY = worldY - playerCharacter.getWorldYCoordinate() + playerCharacter.getScreenYCoordinate();


            if (worldX + bgScreen.getTileSize() * 2 > playerCharacter.getWorldXCoordinate() - playerCharacter.getScreenXCoordinate() &&
                worldX - bgScreen.getTileSize() * 2 < playerCharacter.getWorldXCoordinate() + playerCharacter.getScreenXCoordinate() &&
                worldY + bgScreen.getTileSize() * 2 > playerCharacter.getWorldYCoordinate() - playerCharacter.getScreenYCoordinate() &&
                worldY - bgScreen.getTileSize() * 2 < playerCharacter.getWorldYCoordinate() + playerCharacter.getScreenYCoordinate()) {

                bgGc.drawImage(tile[tileNum].image,screenX,screenY, bgScreen.getTileSize(), bgScreen.getTileSize());
            }


            worldCol++;

            if(worldCol == bgScreen.getMaxWorldCol()) {
                worldCol = 0;
                //x = 0;
                worldRow++;
                //y += bgScreen.getTileSize();
            }
        }
    }

}
