package editor.utility;

import editor.models.GameElement;
import editor.models.Labyrinth;
import editor.models.SpawnPoint;

import java.util.ArrayList;

public class PathValidator {

    public static boolean validatePath(Labyrinth labyrinth) {

        ArrayList<Integer> spawnX = new ArrayList<Integer>();
        ArrayList<Integer> spawnY = new ArrayList<Integer>();



        ArrayList<ArrayList<GameElement>> grid = labyrinth.getData();
        boolean[][] checkGrid = new boolean[labyrinth.getWidth()][labyrinth.getHeight()];

        // Find the spawn points
        for(int x = 0; x < labyrinth.getWidth(); x++) {
            for(int y = 0;  y < labyrinth.getHeight() ; y++) {
                //System.out.println("Processing x=" + x + ";y=" + y);
                if(grid.get(x).get(y) instanceof SpawnPoint) {
                    spawnX.add(x);
                    spawnY.add(y);
                    System.out.println("Found spawn point at x=" + x + ";y=" + y);
                }
            }
        }

        // Check if each spawn point can find each other
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.println("Checking spawn point " + (i + 1) + " against spawn point " + (j + 1));

                // Implement path finding algorithm


                boolean valid = false;


                return false;
            }
        }


        return false;
    }
}
