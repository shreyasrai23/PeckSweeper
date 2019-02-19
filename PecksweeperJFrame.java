import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * JFrame that displays the JPanel, drawing the board. Contains a JButton that
 * allows the user to reset the game by pressing it. Also contains various
 * difficulty levels that contain different amounts of bombs and cells. This is
 * done through scrolling through a dropdown menu, selecting the difficulty you
 * want and selecting ok.
 *
 * @author Daniel Pu \
 * @version May 22, 2018
 * @author Period: 4
 * @author Assignment: Pecksweeper
 *
 * @author Sources: Nishanth Karthikeyan Swarna, Shreyas Rai
 */
public class PecksweeperJFrame extends JFrame
{
    private static int difficulty = 3; // set the default one first

    private JPanel frameContent;

    private static JFrame gui;

    private JPanel operationPanel;

    JLabel lbl = new JLabel( "Select Difficulty" );

    private JButton reset;


    /**
     * main method
     * 
     * @param args
     *            arguments
     */
    public static void main( String[] args )
    {
        difficulty = 3;
        gui = new PecksweeperJFrame( "Pecksweeper", 3, 3, 452, 452, difficulty );
    }


    /**
     * constructor for PecksweeperJFrame
     * 
     * @param name
     *            name of title
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @param w
     *            width
     * @param h
     *            height
     * @param difficulty
     *            difficulty setting
     */
    public PecksweeperJFrame( String name, int x, int y, int w, int h, int difficulty )
    {
        super();

        this.difficulty = difficulty;
        setTitle( name );
        setLocation( x, y );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        operationPanel = new JPanel();
        add( operationPanel, BorderLayout.SOUTH );
        operationPanel.setBounds( 0, h - 35 * 3, w, 30 * 5 );

        String[] choices = { "Braindead", "Easy", "Medium", "Hard", "Annihilation", "Peckmode" };
        lbl.setVisible( true );
        operationPanel.add( lbl );

        final JComboBox<String> cb = new JComboBox<String>( choices );
        cb.setVisible( true );
        operationPanel.add( cb );

        JButton btn = new JButton( "OK" );
        operationPanel.add( btn );

        reset = new JButton( "Reset" );

        operationPanel.add( reset );
        reset.addMouseListener( new ResetListener() );
        btn.addActionListener( new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent arg0 )
            {
                String choice = (String)cb.getSelectedItem();
                if ( choice == "Braindead" )
                {
                    gui.dispose();
                    // difficulty = 2;
                    gui = new PecksweeperJFrame( "Pecksweeper", 3, 3, 100, 302, 1 );

                }
                if ( choice == "Easy" )
                {
                    gui.dispose();
                    // difficulty = 2;
                    gui = new PecksweeperJFrame( "Pecksweeper", 3, 3, 452, 452, 2 );

                }
                if ( choice == "Medium" )
                {
                    gui.dispose();
                    // difficulty = 3;
                    gui = new PecksweeperJFrame( "Pecksweeper", 3, 3, 452, 452, 3 );

                }
                if ( choice == "Hard" )
                {
                    gui.dispose();
                    // difficulty = 4;
                    gui = new PecksweeperJFrame( "Pecksweeper", 3, 3, 700, 650, 4 );
                }
                if ( choice == "Annihilation" )
                {
                    gui.dispose();
                    gui = new PecksweeperJFrame( "Pecksweeper", 3, 3, 1120, 800, 5 );
                    frameContent = new PecksweeperJPanel( 5 );
                }
                if ( choice == "Peckmode" )
                {
                    gui.dispose();
                    gui = new PecksweeperJFrame( "Pecksweeper", 3, 3, 1280, 925, 6 );
                    frameContent = new PecksweeperJPanel( 6 );
                }
            }
        } );

        // Create an instance of the JPanel class, and set this to define the
        // content of the window
        frameContent = new PecksweeperJPanel( this.difficulty );

        // visibleArea.add( frameContent );
        add( frameContent );
        btn.setBounds( 0, 0, w, h - 30 * 5 );

        // Set the size of the content pane of the window, resize and validate
        // the window to suit, obtain keyboard focus, and then make the window
        // visible
        frameContent.setPreferredSize( new Dimension( w, h ) );
        pack();
        setResizable( false );
        frameContent.requestFocusInWindow();
        setVisible( true );

    }


    /**
     * Button that resets the game
     *
     * @author Daniel Pu
     * @version May 22, 2018
     * @author Period: 4
     * @author Assignment: Minesweeper
     *
     * @author Sources:
     */
    private class ResetListener implements MouseListener
    {

        public void mouseClicked( MouseEvent mouseEvent )
        {
            if ( mouseEvent.getButton() == MouseEvent.BUTTON1 )
            {
                gui.dispose();
                gui = new PecksweeperJFrame( "Pecksweeper",
                    3,
                    3,
                    450,
                    450,
                    PecksweeperJFrame.difficulty );

            }
        }


        @Override
        public void mouseEntered( MouseEvent arg0 )
        {

        }


        @Override
        public void mouseExited( MouseEvent arg0 )
        {

        }


        @Override
        public void mousePressed( MouseEvent arg0 )
        {

        }


        @Override
        public void mouseReleased( MouseEvent arg0 )
        {

        }
    }

}
