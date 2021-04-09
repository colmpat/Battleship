public class Tester
{
  public static void main(String[] args)
  {
    Ship carrier = new Ship(5, "Carrier", "0");
    Ship battleship = new Ship(4, "Battleship", "1");
    Ship destroyer = new Ship(4, "Destroyer", "2");
    Ship submarine = new Ship(3, "Submarine", "3");
    Ship patrolBoat = new Ship(2, "Patrol Boat", "4");
    Ship[] ships = {carrier, battleship, destroyer, submarine, patrolBoat};

    Board gameBoard = new Board(ships);
    gameBoard.playGame();
    
  }
}
