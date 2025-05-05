
/**
 * Describes a "word" as an element of a crossword: the word its position its direction (horizontal or vertical).
 * Eleni Stylianou ge21708
 * 27/11/2021
 */
public class Word
{  
   public String word; // Contains the word
   public int x;//contains the row of the word
   int y;//contains the column of the word
   public char Direction;//the direction of the word in the cross (values 'H' for Horizontal 'V' for Vertical)   
   
   /**
    * Creates a word with "position" and "direction" info.
    * newWord - the word
    * newX - the row
    * newY - the column
    * newDirection - the direction (values 'H' for Horizontal 'V' for Vertical)
    */
   public Word(String newWord, int newX,int newY, char newDirection)
   {  
       word=newWord;
       x=newX;
       y=newY;
       Direction=newDirection;
   }
   
   /**
    * Returns the direction ('H' for horizontal 'V' for Vertical).
    */
   public char getDirection()
   {
       return Direction;
   }
   
   /**
    * Return the word.
    */
   public java.lang.String getWord()
   {
       return word;
   }
   
   /**
    * Returns the word and the position of word.
    */
   public String getWordInfo()
   {
       return "Word:"+word+" X="+x+" Y="+y+" Direction="+Direction;
   }
   
   /**
    * Returns the row.
    */
   public int getX()
   { 
       return x;
   }
   
   /**
    * Returns the column.
    */
   public int getY()
   {
       return y;
   }
}