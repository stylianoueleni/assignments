
/**
 * Class Pyrgos implements the "rook" pioni.
 *
 * Eleni Stylianou ge21708
 * 14/01/2022
 */
public class Pyrgos extends Pioni
{
    /**
     * Constructor for class Pyrgos.
     * isWhite - true if it is white, false if it is black
     */
    Pyrgos(boolean isWhite)
    {
         super(isWhite,(isWhite)?'R':'r');
    }
    
     /**
     * Checks to see if the specified location is a legal destination for the this pioni. 
     * The specified destination must be different from the current location. 
     * The destination follow the format: (char, int).
     * Takes into account the chessboard and its contents.
     * x - the X-coordinate of the destination.
     * y - the Y-coordinate of the destination.
     * b - the ChessBoard on which the chess game is played.
     */
    public boolean isLegalMove(char x, int y, ChessBoard b){
       int x1=Utilities.char2Int(xCoord);
       int x2=Utilities.char2Int(x);
       if(x1==x2 && y==yCoord)
           return false;
        
       boolean xValid=false;
       boolean yValid=false;
       for(int i=1;i<=8;i++){
            if(y==i)
                yValid=true;
            if(x==Utilities.int2Char(i))
                xValid=true;
       }
       if(!xValid || !yValid)
           return false;
           
       if(x1==x2 && y!=yCoord){
          if(yCoord<y){
              for(int i=yCoord+1;i<y;i++){
                   if(b.board[8-i][x1-1]!=null)
                      return false;
              }
          }
          else{
              for(int i=yCoord-1;i>y;i--){
                   if(b.board[8-i][x1-1]!=null)
                      return false;
              }
          }
          if(b.board[8-y][x1-1] != null && b.board[8-y][x1-1].isWhite()==white)
              return false;
          return true;
       }
       else if(x1!=x2 && y==yCoord){
               if(x1<x2){
                   for(int i=x1+1;i<x2;i++){
                        if(b.board[8-y][i-1]!=null)
                            return false;
                   }
               }
               else{
                   for(int i=x1-1;i>x2;i--){
                        if(b.board[8-y][i-1]!=null)
                            return false;
                   }
               }
               if(b.board[8-y][x2-1] != null && b.board[8-y][x2-1].isWhite()==white)
                   return false;
               return true;
            }
       return false;
    }
}
