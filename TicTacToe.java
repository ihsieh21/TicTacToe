import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JPanel
{
    JButton buttons[][] = new JButton[3][3]; 
    private int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O
    private int status = 0;   // 0 = continue, 1 = X wins, 2 = O wins, 3 = tie

    public TicTacToe()
    {
      setLayout(new GridLayout(3,3));
      initializebuttons(); 
    }

    public void initializebuttons()
    {
        for(int row = 0; row <= 2; row++)
        {
          for (int col = 0; col <= 2; col++)
          {
            buttons[row][col] = new JButton();
            buttons[row][col].setText("");
            buttons[row][col].addActionListener(new buttonListener());

            add(buttons[row][col]); //adds this button to JPanel (note: no need for JPanel.add(...)
                                //because this whole class is a JPanel already          
          }
        }
    }
    
    public void resetButtons()
    {
        for(int row = 0; row <= 2; row++)
        {
          for (int col = 0; col <= 2; col++)
          {
            buttons[row][col].setText("");
          }  
        }
        status = 0;
    }

    // when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {

            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if(alternate%2 == 0)
                buttonClicked.setText("X");
            else
                buttonClicked.setText("O");

            if(checkStatus() > 0)     // if game is over
            {
                JOptionPane.showConfirmDialog(null, "Game Over.");
                resetButtons();
            }

            alternate++;

        }

        public int checkStatus()
        {
            String result = "";
            
            //horizontal win check
            for (int row = 0; row <= 2; row++)
            {
                result = checkRow (row);
                if (result.compareTo("") > 0)
                {
                    setStatus(result);
                    return status;
                }
            }
 
            // vertical win check
            for (int col = 0; col <= 2; col++)
            {
                result = checkCol (col);
                if (result.compareTo("") > 0)
                {
                    setStatus(result);
                    return status;
                }
            }
 
            //diagonal win check
            if (! buttons[0][0].getText().equals("") &&
                 buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                 buttons[0][0].getText().equals(buttons[2][2].getText()) )
            {
                result = buttons[0][0].getText();
                setStatus(result);
                return status;
            }

            if (! buttons[0][2].getText().equals("") &&
                 buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                 buttons[0][2].getText().equals(buttons[2][0].getText()) )
            {
                result = buttons[0][0].getText();
                setStatus(result);
                return status;
            }
            
            return status;
        }

        public String checkRow(int row)
        {
            if ( ! buttons[row][0].getText().equals("") &&
                 buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                 buttons[row][0].getText().equals(buttons[row][2].getText()) )
                return buttons[row][0].getText();
            else
                return "";
        }

        public String checkCol(int col)
        {
            if ( ! buttons[0][col].getText().equals("") &&
                 buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                 buttons[0][col].getText().equals(buttons[2][col].getText()) )
                return buttons[0][col].getText();
            else
                return "";
        }
        
        public int setStatus (String str)
        {
            if (str.equals("X"))
              status = 1;
            else if (str.equals("O"))
              status = 2;
            return status;
        }
    }

    public static void main(String[] args) 
    {
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}