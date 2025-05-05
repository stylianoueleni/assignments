
/**
 * Class Utilities provide methods for turning letters of the chess-board to numbers and vice-versa.
 *
 * Eleni Stylianou ge21708
 * 14/01/2022
 */
public class Utilities extends java.lang.Object
{  
    /**
     * Constructor for class Utilities.
     */
    public Utilities()
    {
    }
    
    /**
     * Changes a char in ['A'..'H'] to a number in [1..8].
     * xCharPos - the character.
     */
    public static int char2Int(char xCharPos){
       int i=xCharPos-'A'+1;
       return i;
    }
    
    /**
     * Changes a number in [1..8] to a char in ['A'..'H'].
     * xIntPos - the number
     */
    public static char int2Char(int xIntPos){
       char j='A';
       for(int i=1;i<xIntPos;i++){
           j++;
       }
       return j;
    }
}
