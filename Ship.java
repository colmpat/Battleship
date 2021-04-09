public class Ship
{
  private int row, colm;
  private int shipLength;
  private boolean vert;
  private String shipName;
  private String boardCell;
  public Ship(int length, String name, String cell)
  {
    shipLength = length;
    shipName = name;
    row = -1; //default value out of array
    colm = -1; //default value out of array
    vert = true;
    boardCell = cell;
  }
  public String getCell()
  {
    return boardCell;
  }
  public void setPos(int r, int c)
  {
    row = r;
    colm = c;
  }
  public boolean getVert()
  {
    return vert;
  }
  public void setVert(boolean bool)
  {
    vert = bool;
  }
  public String getName()
  {
    return shipName;
  }
  public void setName(String name)
  {
    shipName = name;
  }
  public int getLength()
  {
    return shipLength;
  }
  public int getRow()
  {
    return row;
  }
  public int getColm()
  {
    return colm;
  }
  public void setLength(int len)
  {
    shipLength = len;
  }
}
