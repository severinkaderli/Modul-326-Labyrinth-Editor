package editor.utility;

/**
 * Created by marius on 23.11.16.
 */
public class InputValidator {
    public static int MINIMUM_LABYRINTH_DIMENSION = 6;
    public static int MAXIMUM_LABYRINTH_DIMENSION = Integer.MAX_VALUE;

    /**
     * takes a string containing a number and converts it to an integer, given it's within the valid range
     * @param dimensionString
     * @return
     */
    public static int validateDimensions(String dimensionString) throws IllegalArgumentException{
        int dimension = Integer.parseInt(dimensionString);

        if(MINIMUM_LABYRINTH_DIMENSION > dimension){
            throw new IllegalStateException("Dimension too small, must be > 6");
        }

        if(MAXIMUM_LABYRINTH_DIMENSION < dimension){
            throw new IllegalStateException("Dimension too large");
        }

        return dimension;
    }

    /**
     * takes a string and checks if it only contains the valid letters
     * @param name
     * @return
     */
    public static String validateName(String name) throws IllegalArgumentException{
        if(name == null || name.isEmpty()){
            throw new IllegalStateException("Name is empty");
        }
        if(!name.matches("[a-zA-Z0-9._-]+")){
            throw new IllegalStateException("Name contains invalid characters ([a-zA-Z0-9._-] only)");
        }

        return name;
    }
}