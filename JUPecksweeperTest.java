import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import org.junit.BeforeClass;


public class JUPecksweeperTest
{
    private static MineCell cell;

    private static MineCellManager manager;

    private static MineCell[][] mines;


    @Test
    public void testPecksweeperJFrame()
    {
        assertNotNull( new PecksweeperJFrame( "Pecksweeper", 3, 3, 452, 452, 3 ) );

    }


    /**
     * sets up part of the board to test
     * 
     * @throws Exception
     *             if there is an exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        manager = new MineCellManager( 2 );
        manager.setupNoBombZone( 2, 2 );
        manager.makeBombs();
        manager.bombsAroundCell();

        mines = manager.getMineCells();
        cell = mines[5][5];
    }


    /**
     * tests if the MineCell constructed is not null
     */
    @Test
    public void testMineCellConstructor()
    {
        MineCell cell = new MineCell( 1, 2 );
        assertNotNull( cell );
    }


    /**
     * tests if setting the cell to have a bomb returns true for getBomb()
     */
    @Test
    public void testMineCellSetBomb()
    {
        MineCellManager mcm = new MineCellManager( 3 );
        MineCell mc = new MineCell( 3, 3 );
        mc.setBomb( true );
        assertTrue( mc.getBomb() );

    }


    /**
     * tests if setting the bomb to false returns false for getBomb()
     */
    @Test
    public void testMineCellSetClicked()
    {
        MineCellManager mcm = new MineCellManager( 3 );
        MineCell mc = new MineCell( 3, 3 );
        mc.setClicked( true );
        assertTrue( mc.getClicked() );

    }


    /**
     * checks if bombs around cell of a cell with no bombs around it should be 0
     */
    @Test
    public void testMineCellBombAroundCell()
    {
        MineCellManager mcm = new MineCellManager( 3 );

        assertEquals( 0, mcm.countBombAroundCells( 2, 2 ) );

    }


    /**
     * tests if a flagged cell is false for being not flagged
     */
    @Test
    public void testGetBombAndNotFlagged()
    {
        while ( !cell.getFlag() )
            cell.toggleFlag();

        assertTrue( !cell.getBombAndNotFlagged() );

    }


    /**
     * tests if getting if the MineCell is a bomb or not works
     */
    @Test
    public void testMineCellGetBomb()
    {
        cell.setBomb( true );
        assertTrue( cell.getBomb() );
    }


    /**
     * tests if a MineCell returns true if it is set to be clicked
     */
    @Test
    public void testMineCellgetClicked()
    {
        cell.setClicked( true );
        assertTrue( cell.getClicked() || cell.getFlag() );
    }


    /**
     * tests if getting if the MineCell is clicked will yield the correct result
     */
    @Test
    public void testMinceCellgetClicked()
    {
        cell.setClicked( false );
        assertTrue( !cell.getClicked() );
    }


    /**
     * tests if the MineCell correctly sets the number of surrounding bombs
     */
    @Test
    public void testMineCellsetNumberOfSurroundingBombs()
    {
        MineCell[][] mines = manager.getMineCells();
        mines[5][5].setNumberOfSurroundingBombs( 2 );
        cell = mines[5][5];
        assertEquals( cell.getNumberOfSurroundingBombs(), 2 );
    }


    /**
     * tests if getting if the MineCell is flagged will yield the correct
     * results
     */
    @Test
    public void testMineCellgetFlag()
    {
        cell.setClicked( false );
        boolean oldFlag = cell.getFlag();
        cell.toggleFlag();
        assertTrue( cell.getFlag() == !oldFlag );
    }


    /**
     * tests if toggling the flag sets the MineCell's property of being flagged
     * to not being flagged
     */
    @Test
    public void testMineCelltoggleFlag()
    {
        cell.setClicked( true );
        cell.toggleFlag();
        assertTrue( !cell.getFlag() );
    }


    /**
     * tests if setting the amount of surrounding bombs around a cell returns
     * the correct amount
     */
    @Test
    public void testMineCellgetNumberOfSurroundingBombs()
    {
        cell.setNumberOfSurroundingBombs( 1 );
        assertEquals( cell.getNumberOfSurroundingBombs(), 1 );
    }


    /**
     * tests if the MineCellManager constructs a MineCellManager
     */
    @Test
    public void testMineCellManagerConstructor()
    {
        assertNotNull( manager );
    }


    /**
     * tests if difficulty level 2 has 40 bombs
     */
    @Test
    public void testDifficulty2BombCount40()
    {
        MineCellManager mcm = new MineCellManager( 2 );
        assertEquals( mcm.returnNumberOfBombs(), 40 );

    }


    /**
     * tests if difficulty level 3 has 99 bombs
     */
    @Test
    public void testDifficulty3BombCount99()
    {
        MineCellManager mcm = new MineCellManager( 3 );
        assertEquals( mcm.returnNumberOfBombs(), 99 );

    }


    /**
     * tests if difficulty level 4 has 264 bombs
     */
    @Test
    public void testDifficulty4BombCount264()
    {
        MineCellManager mcm = new MineCellManager( 4 );
        assertEquals( mcm.returnNumberOfBombs(), 264 );

    }


    /**
     * tests if difficulty level 5 has 576 bombs
     */
    @Test
    public void testDifficulty5BombCount576()
    {
        MineCellManager mcm = new MineCellManager( 5 );
        assertEquals( mcm.returnNumberOfBombs(), 576 );

    }


    /**
     * tests if difficulty level 6 has 823 bombs
     */
    @Test
    public void testDifficulty6BombCount823()
    {
        MineCellManager mcm = new MineCellManager( 6 );
        assertEquals( mcm.returnNumberOfBombs(), 823 );

    }


    /**
     * tests if the MineCellManager is initialized to the right difficulty
     */
    @Test
    public void testMineCellManagerGetDifficulty()
    {
        assertEquals( manager.getDifficulty(), 2 );
    }


    /**
     * tests if the MineCellManager is set to the right difficulty
     */
    public void testMineCellManagerSetDifficulty()
    {
        manager.setDifficulty( 3 );
        assertEquals( manager.getDifficulty(), 3 );
    }


    /**
     * tests if the constructor returns the MineCells
     */
    public void testMineCellManagerGetMineCells()
    {

        assertNotNull( manager.getMineCells() );

    }


    /**
     * tests if the bombZone works as intended
     */
    @Test
    public void testMineCellManagerSetupNoBombZone()
    {
        manager.setupNoBombZone( 2, 2 );
        assertTrue( manager.isNoBombZone( 2, 2 ) );

    }


    /**
     * tests if the given row and column are in the noBombZone
     */
    @Test
    public void testMineCellNoBombZone()
    {
        assertTrue( manager.isNoBombZone( 1, 1 ) );
        assertTrue( manager.isNoBombZone( 1, 2 ) );
        assertTrue( manager.isNoBombZone( 2, 3 ) );
        assertTrue( manager.isNoBombZone( 3, 3 ) );
    }


    /**
     * tests if the given cell is with the given row and column of the
     * noBombZone
     */
    @Test
    public void testMineCellManagerIsGivenCellInBombZone()
    {
        assertTrue( manager.isGivenCellInBombZone( 1, 1, 1, 1 ) );
        assertTrue( manager.isGivenCellInBombZone( 1, 2, 1, 2 ) );
        assertTrue( manager.isGivenCellInBombZone( 2, 3, 2, 3 ) );
        assertTrue( manager.isGivenCellInBombZone( 3, 3, 3, 3 ) );
    }


    /**
     * tests if MineCells are created and not null
     */
    @Test
    public void testMineCellManagerCreateMineCells()
    {

        manager.createMineCells();

        assertNotNull( mines[4][4] );
    }


    /**
     * tests if bombs are made
     */
    @Test
    public void testMineCellManagerMakeBombs()
    {
        manager.makeBombs();

        for ( int i = 0; i < mines.length; i++ )
        {
            for ( int j = 0; j < mines[0].length; j++ )
            {
                if ( mines[i][j].getBomb() )
                    assertTrue( mines[i][j].getBomb() );
            }
        }
    }


    /**
     * tests if a cell with no bombs around the cell returns that it has no
     * bombs around it
     */
    @Test
    public void testMineCellManagerCountbombsAroundCell()
    {
        assertEquals( manager.countBombAroundCells( 2, 2 ), 0 );
    }


    /**
     * tests if the bombsAroundCell() method successfully returns that the cell
     * with no bombs around it has no bombs around it
     */
    @Test
    public void testMineCellManagerBombsAroundCell()
    {
        manager.bombsAroundCell();
        assertEquals( manager.countBombAroundCells( 2, 2 ), 0 );
    }


    /**
     * tests if the row and column of a MineCell is within the board constraints
     * and if its not
     */
    @Test
    public void testMineCellManagerIsValidRowColumn()
    {
        assertTrue( manager.isValidRowColumn( 2, 2 ) );
        assertTrue( !manager.isValidRowColumn( 100000, 100000 ) );
    }


    /**
     * tests if the cell is successfully clicked
     */
    @Test
    public void testClickCell()
    {

        manager.clickCell( 2, 2 );
        assertTrue( mines[2][2].getClicked() );

    }


    /**
     * tests if flagging the cell reduces the amountOfBombs by one
     */
    @Test
    public void testMineCellManagerFlagCell()
    {

        manager.flagCell( 8, 8 );
        assertEquals( manager.returnNumberOfBombs(), 39 );
    }


    /**
     * tests if the given cell which should not be a bomb is not a bomb
     */
    @Test
    public void testMineCellManagerClickBomb()
    {
        assertTrue( !manager.clickBomb( 2, 2 ) );
    }


    /**
     * tests if clicking all the non-bomb cells makes all the valid cells
     * clicked
     */
    @Test
    public void allValidCellsClicked()
    {

        for ( int i = 0; i < mines.length; i++ )
        {
            for ( int j = 0; j < mines[0].length; j++ )
            {
                if ( !mines[i][j].getBomb() )
                {
                    manager.clickCell( i, j );
                }
            }
        }
        assertTrue( !manager.allValidCellsClicked() );
    }


    /**
     * checks if clicking a cell with a bomb is true for if a bomb was clicked
     * and clicking a cell without a bomb is not true
     */
    @Test
    public void testMineCellManagerclickBomb()
    {
        assertTrue( !manager.clickBomb( -1, -1 ) );
        assertEquals( manager.clickBomb( 5, 5 ), mines[5][5].getBombAndNotFlagged() );
    }


    /**
     * tests if setting the number of bombs to zero makes it so that there are
     * no actual bombs left
     */
    @Test
    public void testMineCellManagernoBombsLeft()
    {
        manager.actualNumberOfBombs = 0;
        assertTrue( manager.noBombsLeft() );
    }


    /**
     * tests if the number of bombs is correctly returned
     */
    @Test
    public void testMineCellManagerreturnNumberOfBombs()
    {
        assertEquals( manager.returnNumberOfBombs(), 40 );
    }
}
