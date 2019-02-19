import java.awt.Color;
import java.awt.Font;

import java.awt.*;


/**
 * A class that represents a MineCell object, which is an individual cell on the board
 * of a Minesweeper game. It has properties such as being able to be flagged and clicked,
 * and displays if it is a bomb or the number of bombs around it when it is clicked.
 *
 * @author Shreyas Rai
 * @version May 21, 2018
 * @author Period: 4
 * @author Assignment: Pecksweeper
 *
 * @author Sources: Daniel Pu, Nishanth Karthikeyan Swarna
 */
public class MineCell
{
    private int width;

    private int x;

    private int y;

    private boolean isBomb;

    private int numberOfSurroundingBombs;

    private boolean isClicked;

    private boolean isFlagged;


    /**
     * Constructor for MineCell
     * 
     * @param x
     *            x-position in a MineCell
     * @param y
     *            y-position in a MineCell
     */
    public MineCell( int x, int y )
    {
        isBomb = false;
        numberOfSurroundingBombs = 0;
        width = 15;
        this.x = x;
        this.y = y;
        isClicked = false;
        isFlagged = false;
    }


    /**
     * Draws a flag in the cell if the user flags the cell or a bomb if the user
     * clicks on a bomb or displays the number of bombs (if any) next the cell
     * 
     * @param g
     *            Graphics
     */
    public void drawCell( Graphics g )
    {
        g.setColor( Color.BLACK );
        g.drawRect( x, y, width, width );

        if ( isClicked == false && isFlagged == true )
        {
            g.fillRect( x + 10, y + 3, 2, 10 );
            g.setColor( Color.RED );
            g.fillRect( x + 6, y + 3, 4, 4 );

        }
        if ( isClicked == true )
        {
            g.setColor( Color.GRAY );
            g.fillRect( x + 1, y + 1, width - 1, width - 1 );
        }
        if ( isClicked == true && isBomb == true )
        {
            g.setColor( Color.BLACK );
            g.fillOval( x + 4, y + 4, 4, 4 );

        }

        else if ( isClicked == true && numberOfSurroundingBombs == 1 )
        {
            g.setColor( Color.BLUE );
            g.setFont( new Font( Font.SERIF, Font.PLAIN, 15 ) );
            g.drawString( "1", x + 5, y + 14 );
        }
        else if ( isClicked == true && numberOfSurroundingBombs == 2 )
        {
            g.setColor( Color.GREEN );
            g.setFont( new Font( Font.SERIF, Font.PLAIN, 15 ) );
            g.drawString( "2", x + 5, y + 14 );
        }
        else if ( isClicked == true && numberOfSurroundingBombs == 3 )
        {
            g.setColor( Color.RED );
            g.setFont( new Font( Font.SERIF, Font.PLAIN, 15 ) );
            g.drawString( "3", x + 5, y + 14 );
        }
        else if ( isClicked == true && numberOfSurroundingBombs == 4 )
        {
            g.setColor( new Color( 102, 0, 204 ) );
            g.setFont( new Font( Font.SERIF, Font.PLAIN, 15 ) );
            g.drawString( "4", x + 5, y + 14 );
        }
        else if ( isClicked == true && numberOfSurroundingBombs == 5 )
        {
            g.setColor( new Color( 153, 51, 51 ) );
            g.setFont( new Font( Font.SERIF, Font.PLAIN, 15 ) );
            g.drawString( "5", x + 5, y + 14 );
        }
        else if ( isClicked == true && numberOfSurroundingBombs == 6 )
        {
            g.setColor( Color.YELLOW );
            g.setFont( new Font( Font.SERIF, Font.PLAIN, 15 ) );
            g.drawString( "6", x + 5, y + 14 );
        }
        else if ( isClicked == true && numberOfSurroundingBombs == 7 )
        {
            g.setColor( Color.ORANGE );
            g.setFont( new Font( Font.SERIF, Font.PLAIN, 15 ) );
            g.drawString( "7", x + 5, y + 14 );
        }
        else if ( isClicked == true && numberOfSurroundingBombs == 8 )
        {
            g.setColor( Color.PINK );
            g.setFont( new Font( Font.SERIF, Font.PLAIN, 15 ) );
            g.drawString( "8", x + 5, y + 14 );
        }

    } 
    /**
     * returns true if the MineCell is a bomb, false otherwise
     * @return if the MineCell is a bomb
     */
    public boolean getBomb() {
        return isBomb;
    }


    /**
     * returns true if the MineCell is a bomb, false otherwise or if it is 
     * flagged
     * 
     * @return if the MineCell is a bomb and not flagged
     */
    public boolean getBombAndNotFlagged()
    {
        if ( isFlagged )
        {
            return false;
        }
        return isBomb;
    }


    /**
     * sets the MineCell to a bomb or not a bomb depending on the parameter
     * passed
     * 
     * @param shouldBeBomb
     *            condition that determines whether the MineCell should be set
     *            to a bomb or not a bomb
     */
    public void setBomb( boolean shouldBeBomb )
    {
        isBomb = shouldBeBomb;

    }


    /**
     * returns true if the MineCell is clicked already, false if otherwise or
     * it is flagged
     * 
     * @return if the MineCell is clicked
     */
    public boolean getClicked()

    {
        if ( isFlagged )
        {
            return false;
        }
        return isClicked;
    }


    /**
     * Sets the MineCell to be clicked or not clicked based on the parameter
     * passed. If it is flagged already, this method does nothing
     * 
     * @param isItClicked
     *            condition that determines whether the MineCell should be set
     *            to be clicked or not clicked
     */
    public void setClicked( boolean isItClicked )
    {
        if ( isFlagged == false )
        {
            isClicked = isItClicked;
        }
    }


    /**
     * sets the number of surrounding bombs of the MineCell to the parameter
     * passed
     * 
     * @param numberOfBombs
     *            number of surrounding bombs
     */
    public void setNumberOfSurroundingBombs( int numberOfBombs )
    {
        numberOfSurroundingBombs = numberOfBombs;
    }


    /**
     * returns true if the MineCell is flagged
     * 
     * @return if the MineCell is flagged
     */
    public boolean getFlag()
    {
        return isFlagged;
    }


    /**
     * Switches the boolean statement of whether or not the MineCell is flagged
     */
    public void toggleFlag()
    {

        if ( !isClicked )
        {

            isFlagged = !isFlagged;

        }
        else if ( isFlagged )
        {

            isFlagged = !isFlagged;

        }
    }


    /**
     * returns the number of bombs one cell away from the MineCell
     * @return the number of bombs one cell away from the MineCell
     */
    public int getNumberOfSurroundingBombs()
    {
        return numberOfSurroundingBombs;
    }
}
