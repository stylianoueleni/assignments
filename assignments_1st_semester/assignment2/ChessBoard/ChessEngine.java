
/**
 * Class ChessEngine coordinates a game of chess between two players.
 *
 * Eleni Stylianou ge21708
 * 14/01/2022
 */
public class ChessEngine
{
    ChessBoard b;// The chessboard of the game
    Pioni p;// The variable that is used later in the nextMove method
    int i;// a counter of the times the nextMove method was used to know whos(black or white) turn is to play
    /**
     * Constructor for objects of class ChessEngine
     */
    public ChessEngine()
    {
        
    }
    
    /**
     * Prints the standard notation used for the game.
     */
    public static void printNotation()
    {
        System.out.println("White pieces are presented as Upper case letters");
        System.out.println("Black pieces are presented as lower case letters");
        System.out.println("K/k = King - Vasilias");
        System.out.println("Q/q = Queen - Vasilissa");
        System.out.println("R/r = Rook - Pyrgos");
        System.out.println("N/n = Knight - Alogo");
        System.out.println("P/p = Pawn - Stratiotis");
    }

    
    /**
     * Initializes a game of chess.
     */
    public void playChess()
    {
        printNotation();
        i=1;
        b=new ChessBoard();
        b.loadBoard();
        b.printBoard();
        System.out.println("White execute method nextMove() to enter your move");
        i++;
    }
    
    /**
     * The player makes his move. If the move is valid, it is executed and the other player is prompted to play. Otherwise, no action is taken and the player is asked to make a valid move.
     * xOrig - the X-coordinate of the origin.
     * yOrig - the Y-coordinate of the origin.
     * xDest - the X-coordinate of the destination.
     * yDest - the Y-coordinate of the destination.
     */
    public void nextMove(char xOrig, int yOrig,char xDest, int yDest)
    {   
        boolean whitewon=true,blackwon=true;
        if(yOrig<1||yOrig>8||xOrig<'A'||xOrig>'H')
            System.out.println("Illegal move! Please try again using the nextMove method");
        else{
            p=b.getPioniAt(xOrig,yOrig);
            if(p.isLegalMove(xDest,yDest,b)){
                b.move(xOrig,yOrig,xDest,yDest);
                b.printBoard();
                for (int j=0;j<8;j++){
                    for (int k=0;k<8;k++){
                        if(b.board[j][k]!=null && b.board[j][k].getType()=='K')
                            blackwon=false;
                        if(b.board[j][k]!=null && b.board[j][k].getType()=='k')
                            whitewon=false;
                    }
                }
                if(whitewon){
                    System.out.println("White player won!!! You can start a new game using the playChess method");
                }
                else if(blackwon){
                    System.out.println("Black player won!!! You can start a new game using the playChess method");
                }
                else if(i%2==0)
                    System.out.println("Black execute method nextMove() to enter your move");
                else
                    System.out.println("White execute method nextMove() to enter your move");
                i++;
            }
            else
              System.out.println("Illegal move! Please try again using the nextMove method");
        }
    }
}
