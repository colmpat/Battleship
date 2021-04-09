import java.util.Scanner;
public class Board
{
  public static String[][] boardMatrix = new String[10][10];
  public static String[][] hitOrMiss = new String[10][10];
  public static Ship[] shipsAry;
  public Board(Ship[] ships)
  {
    shipsAry = ships;
    for(int row = 0; row < boardMatrix.length; row++)
      for(int colm = 0; colm < boardMatrix[0].length; colm++)
        boardMatrix[row][colm] = "O";
    for(int row = 0; row < hitOrMiss.length; row++)
      for(int colm = 0; colm < hitOrMiss[0].length; colm++)
        hitOrMiss[row][colm] = "[ ]";
  }
  private void printBoard(String[][] board)
  {
    System.out.println("X   A   B   C   D   E   F   G   H   I   J");
    for(int row = 0; row < board.length; row++)
    {
      System.out.print(row + "  ");
      for(int colm = 0; colm < board[0].length; colm++)
        System.out.print(board[row][colm] + " ");
      System.out.println("");
    }
  }
  private void randomizeOneShip(Ship test)
  {
    test.setVert(Math.random() > 0.5);
    if(test.getVert())
    {
      test.setPos((int)(Math.random() * (10 - test.getLength())) , (int)(Math.random() * (10)));
    }
    else
    {
      test.setPos((int)(Math.random() * (10)) , (int)(Math.random() * (10 - test.getLength())));
    }
  }
  private void placeShip(Ship test)
  {
    if(test.getVert())
    {
      for(int i = test.getRow(); i < test.getRow() + test.getLength(); i++)
        boardMatrix[i][test.getColm()] = test.getCell();
    }
    else
    {
      for(int i = test.getColm(); i < test.getColm() + test.getLength(); i++)
        boardMatrix[test.getRow()][i] = test.getCell();
    }
  }
  private boolean isBlocked(Ship test)
  {
    if(test.getVert())
    {
      for(int i = test.getRow(); i < test.getRow() + test.getLength(); i++)
        if(!boardMatrix[i][test.getColm()].equals("O"))
          return true;
    }
    else
    {
      for(int i = test.getColm(); i < test.getColm() + test.getLength(); i++)
        if(!boardMatrix[test.getRow()][i].equals("O"))
          return true;
    }
    return false;
  }
  private boolean isSunk(Ship test)
  {
    if(test.getVert())
    {
      for(int i = test.getRow(); i < test.getRow() + test.getLength(); i++)
        if(!boardMatrix[i][test.getColm()].equals("O"))
          return false;
    }
    else
    {
      for(int i = test.getColm(); i < test.getColm() + test.getLength(); i++)
        if(!boardMatrix[test.getRow()][i].equals("O"))
          return false;
    }
    return true;
  }
  private void checkHit(String guess)
  {
    int row = Integer.parseInt(guess.substring(1,2));
    int colm = guess.charAt(0) - 65;
    if(!boardMatrix[row][colm].equals("O"))
    {
      String cell = boardMatrix[row][colm];
      boardMatrix[row][colm] = "O";
      hitOrMiss[row][colm] = "[!]";
      System.out.println("Hit!");
      if(isSunk(whichShip(cell)))
        System.out.println("You sunk my " + whichShip(cell).getName() + "!");
    }
    else
    {
      hitOrMiss[row][colm] = "[o]";
      System.out.println("Miss!");
    }
  }
  private Ship whichShip(String cell)
  {
    int cellNum = Integer.parseInt(cell);
    for(int i = 0; i < shipsAry.length; i++)
      if(cellNum == i)
        return shipsAry[i];
    return shipsAry[0];
  }
  private void setBoard()
  {
    for(Ship ship : shipsAry)
    {
      do
      {
        randomizeOneShip(ship);
      }while(isBlocked(ship));
      placeShip(ship);
    }
  }
  private boolean gameOver()
  {
    for(Ship ship : shipsAry)
      if(!isSunk(ship))
        return false;
    return true;
  }
  public void playGame()
  {
    String guess = "";
    System.out.println("welcome to battleship mr joo\nguesses are to be made in the following format: B4 (letter THEN number)");
    Scanner keyboard = new Scanner(System.in);
    setBoard();
    while(!gameOver())
    {
      printBoard(hitOrMiss);
      do
      {
        System.out.println("please enter a valid guess");
        guess = keyboard.nextLine().toUpperCase();
      }while(guess.charAt(0) < 65 || guess.charAt(0) > 74 || guess.charAt(1) < 48 || guess.charAt(1) > 57);
      checkHit(guess);
    }
    printBoard(hitOrMiss);
    System.out.println("good job, mr joo. game over");
  }
}
