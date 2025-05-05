
/**
 * Abstract class Pioni - implements a piece of chess
 *
 * Eleni Stylianou ge21708
 * 14/01/2022
 */
public abstract class Pioni
{
    boolean white;//the color of the pioni
    char typeOfPioni;// the type of the pioni   
    char xCoord;// the X coordinate of the pioni
    int yCoord;// the Y coordinate of the pioni
    
    /**
     * Constructor for class Pioni.
     * white - if the pioni is white or black.
     * type - the type of the pioni.
     */
    Pioni(boolean white, char type)
    {
        this.white=white;
        typeOfPioni=type;
    }
    
    /**
     * Changes the position of the pioni on the chessboard.
     * x - the new X-coordinate as a character.
     * y - the new Y-coordingate as a number.
     */
    public void setPosition(char x, int y)
    {
        xCoord=x;
        yCoord=y;
    }
    
    /**
     * Returns the type of the pioni.
     */
    public char getType()
    {
        return typeOfPioni;
    }
    
    /**
     * Returns the X-coordinate of the pioni on the chessboard as a character.
     */
    public char getXCoord()
    {
        return xCoord;
    }
    
    /**
     * Returns the Y-coordinate of the pioni on the chessboard as a number.
     */
    public int getYCoord()
    {
        return yCoord;
    }
    
    /**
     * Returns if the color of the pioni is white or black.
     */
    public boolean isWhite()
    {
        return white;
    }
    
    /**
     * Checks to see if the specified location is a legal destination for the this pioni. The specified destination must be different from the current location. The destination follows the format: (char, int). Takes into account the chessboard and its contents.
     * x - the X-coordinate of the destination.
     * y - the Y-coordinate of the destination.
     * b - the ChessBoard on which the chess game is played.
     */
    public abstract boolean isLegalMove(char x, int y, ChessBoard b);
    
    /**
     * Produces a single letter representation of the pioni depending on its type.
     */
    public String print()
    {
        return " "+typeOfPioni;
    }
    
    /**
     * Produces a description of the pioni as a String.
     */
    public String toString()
    {
        return typeOfPioni+":"+xCoord+yCoord;
    }
    
}