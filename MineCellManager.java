import java.awt.Graphics;


/**
 * Manages the MineCell board by being able to count the number of bombs one
 * space away from a given MineCell, keeping track of the amount of bombs left
 * and how many spaces that are not bombs have not been clicked, and opens up
 * the board when there is a cell that is not a bomb and contains no bombs in
 * the vicinity of it. Also draws the board, guaranteeing that the first click
 * will not be a bomb and 3 other spots one cell away from it will have the
 * property that none of the cells one space away from it have a bomb
 * 
 * @author Shreyas Rai, Daniel Pu
 * @version May 21, 2018
 * @author Period: 4
 * @author Assignment: Minesweeper
 * @author Sources: Nishanth Karthikeyan Swarna
 */
public class MineCellManager
{
    public MineCell[][] minecells;

    public int numberOfBombs;

    public int actualNumberOfBombs;

    public int numberOfValidCells;

    public int difficulty;

    // no bomb zone
    public int clickedRow = 0, clickedColumn = 0;

    public int randomRow1 = 0, randomCol1 = 0;

    public int randomRow2 = 0, randomCol2 = 0;

    public int randomRow3 = 0, randomCol3 = 0;


    /**
     * Constructor for MineCellManager
     */
    /**
     * @param difficult difficulty setting
     */
    public MineCellManager( int difficult )

    {
        if ( difficult == 1 )
        {
            minecells = new MineCell[6][6];
            numberOfBombs = 9;
            actualNumberOfBombs = 9;
            numberOfValidCells = 27;
        }
        if ( difficult == 2 )
        {
            minecells = new MineCell[10][20];
            numberOfBombs = 40;
            actualNumberOfBombs = 40;
            numberOfValidCells = 160;
        }
        else if ( difficult == 3 )
        {
            minecells = new MineCell[16][30];
            numberOfBombs = 99;
            actualNumberOfBombs = 99;
            numberOfValidCells = 381;
        }
        else if ( difficult == 4 )
        {
            minecells = new MineCell[30][44];
            numberOfBombs = 264;
            actualNumberOfBombs = 264;
            numberOfValidCells = 1056;
        }
        else if ( difficult == 5 )
        {
            minecells = new MineCell[40][72];
            numberOfBombs = 576;
            actualNumberOfBombs = 576;
            numberOfValidCells = 1890;
        }
        else if ( difficult == 6 )
        {
            minecells = new MineCell[49][84];
            numberOfBombs = 823;
            actualNumberOfBombs = 823;
            numberOfValidCells = 3293;

        }

        createMineCells();

        difficulty = difficult;
    }


    /**
     * returns the difficulty setting
     * 
     * @return difficulty
     */
    public int getDifficulty()
    {
        return difficulty;
    }


    /**
     * sets the difficulty setting for the manager
     * 
     * @param d
     *            difficulty
     */
    public void setDifficulty( int d )
    {
        difficulty = d;
    }


    /**
     * returns the mineCell array
     * 
     * @return mineCells
     */
    public MineCell[][] getMineCells()
    {
        return minecells;
    }


    /**
     * sets up an area in which no bombs can be created there
     * 
     * @param row
     *            row of the first clicked mineCell
     * @param column
     *            column of the first clicked mineCekk
     */
    public void setupNoBombZone( int row, int column )
    {

        int rowLength = minecells.length;
        int columnLength = minecells[0].length;

        clickedRow = row;
        clickedColumn = column;
        if ( clickedRow < 0 )
            clickedRow = 0;
        if ( clickedColumn < 0 )
            clickedColumn = 0;

        if ( clickedRow >= rowLength )
            clickedRow = rowLength - 1;
        if ( clickedColumn >= columnLength )
            clickedColumn = columnLength - 1;

        // set up three cells when click on the board cell
        int randomRow1 = 2, randomCol1 = 2, randomRow2 = 2, randomCol2 = 2, randomRow3 = 2,
                        randomCol3 = 2;
        boolean isHardCoded = false;
        if ( 0 == clickedRow )
        {
            isHardCoded = true;
            if ( 0 == clickedColumn )
            {
                randomRow1 = 2;
                randomCol1 = 3;
                randomRow2 = 3;
                randomCol2 = 3;
                randomRow3 = 3;
                randomCol3 = 2;
            }
            else if ( columnLength - 1 == clickedColumn )
            {
                randomRow1 = 2;
                randomCol1 = 2;
                randomRow2 = 3;
                randomCol2 = 1;
                randomRow3 = 3;
                randomCol3 = 2;
            }
            else
            {
                randomRow1 = 2;
                randomCol1 = 3;
                randomRow2 = 3;
                randomCol2 = 2;
                randomRow3 = 2;
                randomCol3 = 1;
            }
        }
        if ( rowLength - 1 == clickedRow )
        {
            isHardCoded = true;
            if ( 0 == clickedColumn )
            {
                randomRow1 = 1;
                randomCol1 = 2;
                randomRow2 = 3;
                randomCol2 = 3;
                randomRow3 = 2;
                randomCol3 = 3;
            }
            else if ( columnLength - 1 == clickedColumn )
            {
                randomRow1 = 1;
                randomCol1 = 2;
                randomRow2 = 1;
                randomCol2 = 1;
                randomRow3 = 2;
                randomCol3 = 2;
            }
            else
            {
                randomRow1 = 1;
                randomCol1 = 2;
                randomRow2 = 2;
                randomCol2 = 3;
                randomRow3 = 1;
                randomCol3 = 2;
            }
        }
        if ( 0 != clickedRow && rowLength - 1 != clickedRow )
        {
            if ( 0 == clickedColumn )
            {
                isHardCoded = true;
                randomRow1 = 1;
                randomCol1 = 2;
                randomRow2 = 2;
                randomCol2 = 3;
                randomRow3 = 3;
                randomCol3 = 2;
            }
            if ( columnLength - 1 == clickedColumn )
            {
                isHardCoded = true;
                randomRow1 = 1;
                randomCol1 = 2;
                randomRow2 = 2;
                randomCol2 = 1;
                randomRow3 = 3;
                randomCol3 = 2;
            }
        }

        // set up three cells randomly
        if ( !isHardCoded )
        {
            randomRow1 = (int)Math.ceil( Math.random() * 1 );
            randomCol1 = (int)Math.ceil( Math.random() * 3 );
            if ( randomRow1 == 2 && randomCol1 == 2 )
                randomCol1 = 1; // move left

            randomRow2 = (int)Math.ceil( Math.random() * 2 );
            randomCol2 = (int)Math.ceil( Math.random() * 3 );
            if ( randomRow2 == randomRow1 && randomCol2 == randomCol1 )
            {
                randomRow2 = 2; // row 2
            }
            if ( randomRow2 == 2 && randomCol2 == 2 )
                randomCol2 = 3; // move right

            randomRow3 = (int)Math.ceil( Math.random() * 3 );
            randomCol3 = (int)Math.ceil( Math.random() * 3 );
            if ( randomRow3 == randomRow1 && randomCol3 == randomCol1 )
            {
                randomRow3 = 3; // row 3
            }
            if ( randomRow3 == randomRow2 && randomCol3 == randomCol2 )
            {
                randomRow3 = 3; // row 3
            }
            if ( randomRow3 == 2 && randomCol3 == 2 )
                randomRow3 = 3; // move down
        }
        randomRow1 = clickedRow + randomRow1 - 2;
        randomCol1 = clickedColumn + randomCol1 - 2;
        randomRow2 = clickedRow + randomRow2 - 2;
        randomCol2 = clickedColumn + randomCol2 - 2;
        randomRow3 = clickedRow + randomRow3 - 2;
        randomCol3 = clickedColumn + randomCol3 - 2;

    }


    /**
     * checks if the mineCell at the given row and column can have a bomb
     * generated there
     * 
     * @param row
     *            row of given mineCell
     * @param column
     *            column of given mineCell
     * @return true if the mineCell cannot have a bomb generated here, false
     *         otherwise
     */
    public boolean isNoBombZone( int row, int column )
    {
        int rowLength = minecells.length;
        int columnLength = minecells[0].length;
        if ( row < 0 || column < 0 )
            return true;
        if ( row >= rowLength )
            return true;
        if ( column >= columnLength )
            return true;

        if ( isGivenCellInBombZone( row, column, clickedRow, clickedColumn )
            || isGivenCellInBombZone( row, column, randomRow1, randomCol1 )
            || isGivenCellInBombZone( row, column, randomRow2, randomCol2 )
            || isGivenCellInBombZone( row, column, randomRow3, randomCol3 ) )
            return true;

        return false;
    }


    /**
     * checks if the given cell is in an area in which bombs cannot be created
     * 
     * @param row
     *            row of given cell
     * @param column
     *            column of given cell
     * @param noBombRow
     *            specific row of a cell in an area where bombs cannot be
     *            created
     * @param noBombColumn
     *            specific column of a cell in an area where bombs cannot be
     *            created
     * @return true if the given cell is in an area where bombs cannot be
     *         created, false otherwise
     */
    public boolean isGivenCellInBombZone( int row, int column, int noBombRow, int noBombColumn )
    {
        if ( row >= noBombRow - 1 && row <= noBombRow + 1 && column >= noBombColumn - 1
            && column <= noBombColumn + 1 )
            return true;

        return false;
    }


    /**
     * draws all the bombs when you lose
     * 
     * @param g
     *            Graphics
     */
    public void drawBombs( Graphics g )
    {

        for ( int i = 0; i < minecells.length; i++ )
        {
            ;
            for ( int j = 0; j < minecells[i].length; j++ )
            {
                if ( minecells[i][j].getBomb() )
                {
                    minecells[i][j].setClicked( true );
                    minecells[i][j].drawCell( g );
                }

            }
        }
    }


    /**
     * creates the grid of MineCells
     */
    public void createMineCells()
    {
        for ( int i = 0; i < minecells.length; i++ )
        {
            for ( int j = 0; j < minecells[i].length; j++ )
            {
                minecells[i][j] = new MineCell( j * 15, i * 15 );
            }
        }
    }


    /**
     * Randomly generates bombs in different positions on the board
     */
    public void makeBombs()
    {
        int bombsPlaced = 0;
        while ( bombsPlaced < numberOfBombs )
        {

            int x = (int)( Math.random() * minecells.length );
            int y = (int)( Math.random() * minecells[0].length );
            if ( minecells[x][y].getBomb() == false )
            {
                if ( isNoBombZone( x, y ) )
                    continue;

                minecells[x][y].setBomb( true );
                bombsPlaced++;
            }
        }
    }


    /**
     * creates the cells on the board
     * 
     * @param g
     *            graphics
     */
    public void drawGrid( Graphics g )
    {
        for ( int i = 0; i < minecells.length; i++ )
        {
            for ( int j = 0; j < minecells[i].length; j++ )
            {
                minecells[i][j].drawCell( g );
            }
        }
    }


    /**
     * counts the amount of bombs on board in the vicinity of the clicked cell
     * 
     * @param row
     *            board's row number
     * @param column
     *            board's column number
     * @return bombBounter number of bombs in vicinity
     */
    public int countBombAroundCells( int row, int column )
    {
        int rowLength = minecells.length;
        int columnLength = minecells[0].length;
        int bombCounter = 0;
        if ( row != 0 )
        { // You can check above the row.
            if ( column != 0 )
            { // Check the top left corner.
                if ( minecells[row - 1][column - 1].getBomb() )
                {
                    bombCounter++;
                }
            }
            if ( minecells[row - 1][column].getBomb() )
            {// check top middle
                bombCounter++;
            }
            if ( column != columnLength - 1 )
            {// check top right
                if ( minecells[row - 1][column + 1].getBomb() )
                {
                    bombCounter++;
                }
            }
        }
        if ( column != 0 )
        {// check middle left
            if ( minecells[row][column - 1].getBomb() )
            {
                bombCounter++;
            }
        }
        if ( column != columnLength - 1 )
        {// check middle right
            if ( minecells[row][column + 1].getBomb() )
            {
                bombCounter++;
            }
        }
        if ( row != rowLength - 1 )
        {
            if ( column != 0 )
            { // Check the bottom left corner.
                if ( minecells[row + 1][column - 1].getBomb() )
                {
                    bombCounter++;
                }
            }
            if ( minecells[row + 1][column].getBomb() )
            {// check bottom middle
                bombCounter++;
            }
            if ( column != columnLength - 1 )
            {// check bottom right
                if ( minecells[row + 1][column + 1].getBomb() )
                {
                    bombCounter++;
                }
            }
        }
        return bombCounter;
    }


    /**
     * displays the number of bombs around the clicked cell
     */
    public void bombsAroundCell()
    {
        for ( int i = 0; i < minecells.length; i++ )
        {
            for ( int j = 0; j < minecells[i].length; j++ )
            {
                if ( !minecells[i][j].getBomb() )
                {
                    minecells[i][j].setNumberOfSurroundingBombs( countBombAroundCells( i, j ) );
                }
            }
        }
    }


    /**
     * checks if the row/column is within bounds of the board
     * 
     * @param row
     *            row
     * @param column
     *            column
     * @return true if the row/col is within bound, false if not
     */
    public boolean isValidRowColumn( int row, int column )
    {
        int rowLength = minecells.length;
        int columnLength = minecells[0].length;
        if ( row < 0 || column < 0 )
            return false;
        if ( row >= rowLength )
            return false;
        if ( column >= columnLength )
            return false;

        return true;
    }


    /**
     * if the user clicks on the space on the board at row, column and that
     * space contains no bombs in the cells one space away from it, it opens the
     * board up and also reveals the other spaces one space away from it that
     * also contain no bombs, and this continue for that direction until a space
     * with bombs next to it is revealed.
     * 
     * @param row
     *            board's row number
     * @param column
     *            board's column number
     */
    public void clickCell( int row, int column )
    {
        if ( !isValidRowColumn( row, column ) )
            return;

        if ( minecells[row][column].getClicked() == false
            && minecells[row][column].getFlag() == false )
        {
            minecells[row][column].setClicked( true );

            if ( !minecells[row][column].getBomb() )
            {
                numberOfValidCells--;

            }
            if ( minecells[row][column].getNumberOfSurroundingBombs() == 0
                && !minecells[row][column].getBombAndNotFlagged() )
            {
                clickCell( row - 1, column - 1 );
                clickCell( row - 1, column );
                clickCell( row - 1, column + 1 );
                clickCell( row, column + 1 );
                clickCell( row + 1, column + 1 );
                clickCell( row + 1, column );
                clickCell( row + 1, column - 1 );
                clickCell( row, column - 1 );
            }
        }
    }


    /**
     * Creates flag on board and reduces the actual amount of bombs counter if
     * bomb is flagged (this is not displayed to the user). Also increases the
     * amount of bombs if a flagged bomb is unflagged (also not displayed to the
     * user). Number of bombs displayed for the user is determined only by the
     * user flagging or unflagging a cell, which increases the bomb counter if
     * the user unflags and decreases it if the user flags a cell.
     * 
     * 
     * @param row
     *            board's row number
     * @param column
     *            board's column number
     */
    public void flagCell( int row, int column )
    {

        if ( !isValidRowColumn( row, column ) )
            return;

        if ( minecells[row][column].getClicked() )
        {
            return;
        }
        if ( !minecells[row][column].getFlag() )
        {

            if ( minecells[row][column].getBomb() )
            {
                actualNumberOfBombs--;

            }
        }
        if ( minecells[row][column].getFlag() )
        {

            numberOfBombs += 2;
            if ( minecells[row][column].getBomb() )
            {
                actualNumberOfBombs++;

            }
        }

        minecells[row][column].toggleFlag();
        numberOfBombs--;

    }


    /**
     * Checks if the minecell at row, column on the board is a bomb or not
     * 
     * @param row
     *            board's row number
     * @param column
     *            board's column number
     * @return if the minecell at row, column on the board that the user clicked
     *         is a bomb or not
     */
    public boolean clickBomb( int row, int column )
    {
        if ( !isValidRowColumn( row, column ) )
            return false;

        return minecells[row][column].getBombAndNotFlagged();
    }


    /**
     * checks if all cells that are not bombs are clicked
     * 
     * @return true if all cells that are not bombs are clicked, false otherwise
     */
    public boolean allValidCellsClicked()
    {
        if ( numberOfValidCells == 0 )
        {
            return true;
        }
        return false;
    }


    /**
     * Checks if there are any bombs left on board
     * 
     * @return true if no bombs left
     * @return false if bombs still present
     */
    public boolean noBombsLeft()
    {
        if ( actualNumberOfBombs == 0 )
        {
            return true;
        }
        return false;
    }


    /**
     * returns the hypothetical amount of bombs on board based on how many times
     * the user flagged/unflagged a mineCell
     * 
     * @return numberOfBombs the number of bombs left
     */
    public int returnNumberOfBombs()
    {
        return numberOfBombs;
    }

}