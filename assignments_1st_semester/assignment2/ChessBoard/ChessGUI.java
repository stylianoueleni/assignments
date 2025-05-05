/**
 * Write a description of class ChessGUI here.
 *
 * Eleni Stylianou ge21708
 * 14/01/2022
 */
import java.awt.*;
import javax.swing.*;
import java.util.Arrays;


public class ChessGUI extends JFrame{

    private JPanel contentPane;
    private ChessEngine game;
    ChessGUI() {
        initializeGui();
        game=new ChessEngine();
        game.playChess();
        drawChess();
        show();
    }
    
    public void drawChess(){
        for(int i=0; i<8;i++)
            for(int j=0; j<8;j++){
                Pioni p=game.b.board[i][j];
                if(p!=null){
                    JLabel piece=null;
                    char type=p.getType();
                    if(type=='N'){
                        piece=new JLabel(new ImageIcon("alogo_white.png"));
                    }
                    else if(type=='n'){
                        piece=new JLabel(new ImageIcon("alogo_black.png"));
                    }
                    else if(type=='P'){
                        piece=new JLabel(new ImageIcon("stratiotis_white.png"));
                    }
                    else if(type=='p'){
                        piece=new JLabel(new ImageIcon("stratiotis_black.png"));
                    }
                    else if(type=='K'){
                        piece=new JLabel(new ImageIcon("king_white.png"));
                    }
                    else if(type=='k'){
                        piece=new JLabel(new ImageIcon("king_black.png"));
                    }
                    else if(type=='Q'){
                        piece=new JLabel(new ImageIcon("queen_white.png"));
                    }
                    else if(type=='q'){
                        piece=new JLabel(new ImageIcon("queen_black.png"));
                    }
                    else if(type=='B'){
                        piece=new JLabel(new ImageIcon("stratigos_white.png"));
                    }
                    else if(type=='b'){
                        piece=new JLabel(new ImageIcon("stratigos_black.png"));
                    }
                    else if(type=='R'){
                        piece=new JLabel(new ImageIcon("pirgos_white.png"));
                    }
                    else if(type=='r'){
                        piece=new JLabel(new ImageIcon("pirgos_black.png"));
                    }
                    JPanel panel = (JPanel)contentPane.getComponent(8*i+j);
                    panel.add(piece);
                }
            }
    }
    
    public void clearChess(){
        for(int i=0; i<64;i++){
           JPanel panel = (JPanel)contentPane.getComponent(i);
           Component[] components=panel.getComponents();
           for(Component c : components){
               if(c instanceof JLabel){
                   panel.remove(c);
               }
           }
        }
    }
    
    public void nextMove(char xOrig, int yOrig,char xDest, int yDest){
        game.nextMove(xOrig, yOrig, xDest, yDest);
        clearChess();
        drawChess();
        revalidate();
        repaint();
    }

    public void initializeGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 735, 520);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(8, 8, 0, 0));
        for (int i = 0; i < 64; i++){
            JPanel panel = new JPanel();
            int row = (i / 8) % 2;
            if (row == 0){
                panel.setBackground( i % 2 == 0 ? Color.WHITE : Color.DARK_GRAY );
            }     
            else{
                panel.setBackground( i % 2 == 0 ? Color.DARK_GRAY: Color.WHITE );
            }
            contentPane.add(panel); 
        }
    }
}
