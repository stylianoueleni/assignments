
/**
 * This class creates and manages the chessBoard placing and moving chess pieces
 *
 * Eleni Stylianou ge21708
 * 14/01/2022
 */
public class ChessBoard extends java.lang.Object
{ 
   Pioni board[][];
   
   /**
    * Constructor for class ChessBoard.
    */
    public ChessBoard(){
       board= new Pioni[8][8];
    }
   
   /**
    * Places all pieces on the board for a new game to begin.
    */
    public void loadBoard(){
     for(int i=0;i<8;i++){
         board[6][i]=new Stratiotis(true);
         board[6][i].setPosition(Utilities.int2Char(i+1),2);         
         board[1][i]=new Stratiotis(false);
         board[1][i].setPosition(Utilities.int2Char(i+1),7);  
     }   
     board[0][0]=new Pyrgos(false);
     board[0][0].setPosition('A',8);    
     board[0][1]=new Alogo(false);
     board[0][1].setPosition('B',8);
     board[0][2]=new Stratigos(false);
     board[0][2].setPosition('C',8);
     board[0][3]=new Vasilissa(false);
     board[0][3].setPosition('D',8);
     board[0][4]=new Vasilias(false);
     board[0][4].setPosition('E',8);
     board[0][5]=new Stratigos(false);
     board[0][5].setPosition('F',8);
     board[0][6]=new Alogo(false);
     board[0][6].setPosition('G',8);
     board[0][7]=new Pyrgos(false);
     board[0][7].setPosition('H',8);
     board[7][0]=new Pyrgos(true);
     board[7][0].setPosition('A',1);
     board[7][1]=new Alogo(true);
     board[7][1].setPosition('B',1);
     board[7][2]=new Stratigos(true);
     board[7][2].setPosition('C',1);
     board[7][3]=new Vasilissa(true);
     board[7][3].setPosition('D',1);
     board[7][4]=new Vasilias(true);
     board[7][4].setPosition('E',1);
     board[7][5]=new Stratigos(true);
     board[7][5].setPosition('F',1);
     board[7][6]=new Alogo(true);
     board[7][6].setPosition('G',1);
     board[7][7]=new Pyrgos(true);
     board[7][7].setPosition('H',1);
    }
   
   /**
    * Prints the chessboard with all the pieces on it.
    */
    public void printBoard(){
        System.out.println("   A B C D E F G H   ");
        System.out.println("---------------------");
        for(int i=0;i<8;i++){
           System.out.print(8-i+"  ");
           for(int j=0;j<8;j++){               
               if(board[i][j]==null)
                System.out.print("  ");
               else
                System.out.print(board[i][j].getType()+" ");
           }
           System.out.print(" ");
           System.out.print(8-i);
           System.out.println();
        }
        System.out.println("---------------------");
        System.out.println("   A B C D E F G H   ");
   }
   
   /**
    * Returns the pioni at a given position.
    * xPos - The X-coordinate of the position.
    * yPos - The Y-coordinate of the position.    
    */
   public Pioni getPioniAt(char xPos,int yPos)
   {
       return board[(8-yPos)][(Utilities.char2Int(xPos)-1)];    
   }
   
   /**
    * Places a pioni at a location on the board.
    * p - the pioni.
    * xPos - the X-coordinate of the location.
    * yPos - the Y-coordinate of the location.
    */
   public void placePioniAt(Pioni p,char xPos,int yPos)
   {
       board[8-yPos][Utilities.char2Int(xPos)-1]=p;
       p.setPosition(xPos,yPos);
   }
   
   /**
    * Moves a pioni.
    * xOrig - The X-coordinate of the origin of pioni.
    * yOrig - The Y-coordinate of the origin of pioni.
    * xDest - The X-coordinate of the destination of pioni.
    * yDest - The Y-coordinate of the destination of pioni.
    */
   public void move(char xOrig,int yOrig,char xDest, int yDest)
   {
       placePioniAt(getPioniAt(xOrig,yOrig),xDest,yDest);
       board[8-yOrig][Utilities.char2Int(xOrig)-1]=null;
   }
  
}
