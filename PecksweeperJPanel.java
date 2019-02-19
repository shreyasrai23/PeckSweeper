import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Has a board that will be displayed in JFrame, where the board is displayed in
 * another window. It can determine if the user left clicked or right clicked a
 * cell, clicking the cell or flagging it appropriately. It is also able to show
 * if the user won or lost by finding out if there are no bombs left to flag and
 * no more non-bomb spaces to click for the user to win or if the
 * user clicked on a bomb for them to lose
 *
 * @author Daniel Pu
 * @version May 22, 2018
 * @author Period: 4
 * @author Assignment: Pecksweeper
 *
 * @author Sources: Nishanth Karthikeyan Swarna, Shreyas Rai
 */
public class PecksweeperJPanel extends JPanel implements MouseListener
{
    public MineCellManager minecells;

    public boolean hasWon;

    public boolean gameHasEnded;

    public boolean firstClick;


    /**
     * constructor for JPanel
     */
    public PecksweeperJPanel( int difficulty )
    {
        gameHasEnded = false;
        hasWon = false;
        minecells = new MineCellManager( difficulty );
        addMouseListener( this );
        firstClick = false;

    }


    public void paintComponent( Graphics g )
    {

        /// super.paintComponent( g );
        drawGameArea( g );
        minecells.drawGrid( g );
        displayWinnerLoser( g );
        drawNumberofBombs( g );
    }


    /**
     * Fills the board with a grey blackground
     * 
     * @param g
     *            Graphics
     */
    public void drawGameArea( Graphics g )
    {
        g.setColor( Color.LIGHT_GRAY );
        g.fillRect( 0, 0, 2000, 1000 );

    }


    /**
     * Displays the number of bombs at the bottom corner
     * 
     * @param g
     *            Graphics
     */
    public void drawNumberofBombs( Graphics g )
    {
        g.setColor( Color.BLACK );
        int j = minecells.returnNumberOfBombs();
        g.setFont( new Font( "Serif", 0, 20 ) );

        // g.drawString( "Number of Bombs: " + j + "", 150, 300 );
        g.drawString( "Number of Bombs: " + j + "", this.getWidth()/3, this.getHeight() - 5 * 2 );
    }


    /**
     * Displays whether the user won or lose with a message
     * 
     * @param g
     */
    public void displayWinnerLoser( Graphics g )
    {
        g.setColor( Color.BLACK );
        g.setFont( new Font( "Serif", 0, 40 ) );
        if ( gameHasEnded == true && hasWon == false )
        {
            ImageIcon image2 = new ImageIcon( getClass().getResource( "deadpeck.jpg" ) );
            JLabel label2 = new JLabel( image2 );
            add( label2 );
            label2.setBounds( this.getWidth()/4 , this.getHeight() - 130 * 2, 200, 280 );
            // g.drawString( "You Lose", 120, 200 );
            minecells.drawBombs( g );
        }
        else if ( gameHasEnded == true && hasWon == true )
        {
            ImageIcon image3 = new ImageIcon( getClass().getResource( "alivepeck.jpg" ) );
            JLabel label3 = new JLabel( image3 );
            add( label3 );
            label3.setBounds( this.getWidth() - 180 * 2, this.getHeight() - 130 * 2, 200, 280 );
            // g.drawString( "You Win", 100, 100 );
        }
    }


    @Override
    public void mouseClicked( MouseEvent mouseEvent )
    {

        if ( gameHasEnded )
        {
            
            return;
        }

        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        int column = x / 15;
        int row = y / 15;

        if ( mouseEvent.getButton() == MouseEvent.BUTTON1 )
        {
            if ( firstClick == false )
            {
                firstClick = true;
                minecells.setupNoBombZone( row, column );
                minecells.makeBombs();
                minecells.bombsAroundCell();
            }

            minecells.clickCell( row, column );
            checkIfWin();
            if ( minecells.clickBomb( row, column ) == true )
            {

                youLose();

            }

        }
        else if ( mouseEvent.getButton() == MouseEvent.BUTTON3 )
        {
            minecells.flagCell( row, column );
            checkIfWin();

        }

        repaint();
    }


    @Override
    public void mousePressed( MouseEvent mouseEvent )
    {

    }


    @Override
    public void mouseReleased( MouseEvent mouseEvent )
    {

    }


    @Override
    public void mouseEntered( MouseEvent mouseEvent )
    {

    }


    @Override
    public void mouseExited( MouseEvent mouseEvent )
    {

    }


    /**
     * returns the MineCellManager object
     * 
     * @return MineCellManager object
     */
    public MineCellManager getCells()
    {
        return minecells;
    }


    /**
     * returns true if the game has ended, false otherwise
     * 
     * @return if the game has ended
     */
    public boolean gameEnded()
    {
        return gameHasEnded;
    }


    /**
     * sets the game to be over and the user to have lost. If so, the user
     * cannot interact with the board anymore
     */
    public void youLose()
    {

        gameHasEnded = true;
        hasWon = false;

    }


    /**
     * checks if user won
     */
    public void checkIfWin()
    {
        if ( minecells.noBombsLeft() && minecells.allValidCellsClicked() )
        {
            youWin();
        }
    }


    /**
     * sets the game to be over and the user to have won. If so, the user cannot
     * interact with the board anymore
     */
    public void youWin()
    {
        gameHasEnded = true;
        hasWon = true;

    }

}
