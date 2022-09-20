
import java.util.Scanner;
import java.util.*;

public  class Simulation
{

  private String nameGood1;
  private String nameGood2;

  private int c1g1;
  private int c1g2;
  private int c2g1;
  private int c2g2;

  private String comparative1; 
  private String comparative2;

  private Board[][] board;

  
  private String country1;
  private String country2; 
  private String trade1;
  private String trade2;

  Scanner sc = new Scanner(System.in);
  
  public Simulation()
  {
    country1 = "U.S.";
    country2 = "Brazil";
    nameGood1 = "coffee";
    nameGood2 = "wheat";
    c1g1 = 30;
    c1g2 = 30;
    c2g1 = 20;
    c2g2 = 10;
    board = new Board[3][3];  


    for(int r = 0; r < 3; r++)
    {
      for(int c = 0; c < 3; c++)
      {
        board[r][c] = new Board();
      }
    }
  }

  public void play()
  {
    intro();
    getComparative();
    System.out.print("Type 1 to see the answers.");
    if(sc.nextInt()==1){
      calculateTrade();
    }
    
  }
  
  

  public void intro()
  {
    print();
    System.out.println("Enter the name of Country 1:");
    country1 = sc.next();
    board[1][0].setMarker(country1);
    print();

    System.out.println("Enter the name of Country 2:");
    country2 = sc.next();
    board[2][0].setMarker(country2);
    print();

    System.out.println("Enter the name of Good 1:");
    nameGood1 = sc.next();
    board[0][1].setMarker(nameGood1);
    print();

    System.out.println("Enter the name of Good 2:");
    nameGood2 = sc.next();
    board[0][2].setMarker(nameGood2);
    print();

    System.out.println("Enter the number of Good 1 " + country1 + " can produce: ");
    c1g1 = validate(0, 500);
    board[1][1].setMarker("" + c1g1);
    print();

    System.out.println("Enter the number of Good 1 " + country2 + " can produce: ");
    c2g1 = validate(0, 500);
    board[2][1].setMarker("" + (int)c2g1);
    print();

    System.out.println("Enter the number of Good 2 " + country1 + " can produce: ");
    c1g2 = validate(0, 500);
    board[1][2].setMarker("" + (int)c1g2);
    print();

    System.out.println("Enter the number of Good 2 " + country2 + " can produce: ");
    c2g2 = validate(0, 500);
    board[2][2].setMarker("" + (int)c2g2);
    print();

    }
  


  public void print(){
    System.out.println("- - - - - - - - - - - - -");
    int rCount = 0;
    int cCount = 0;
    for(int i = 0; i < 3; i++){
      if(rCount == 1){
        rCount = 0;
        System.out.println("- - - - - - - - - - - - -");
      }

      if(i == 0){
          System.out.print("|     ");
      }
      for(int j = 0; j < 3; j++){
        if(cCount == 1){
          cCount = 0;
          if(board[i][j].isTaken())
            System.out.print("| ");
          else
            System.out.print("|     ");
        }
        if(board[i][j].isTaken()){

          if(board[i][j].toString().length() == 5)
            System.out.print(board[i][j] + " ");
          else if(board[i][j].toString().length() == 4)
            System.out.print(" " + board[i][j] + " ");
          else if(board[i][j].toString().length()==3)
            System.out.print(" " + board[i][j] + "  ");
          else if(board[i][j].toString().length()==2)
            System.out.print("  " + board[i][j] + "  ");

        }
        else
          System.out.print(board[i][j] + " ");
        cCount++;
      }
      System.out.println("|");
      rCount++;
    }
    System.out.println("- - - - - - - - - - - - -");
  }

  public void getComparative(){
    if(c1g1*c2g2 > c1g2*c2g1){
      comparative1 = country1 + " " + nameGood1;
      comparative2 = country2 + " " + nameGood2;
    }
    else if(c1g1*c2g2 < c1g2*c2g1){
      comparative1 = country1 + " " + nameGood2;
      comparative2 = country2 + " " + nameGood1;
    }
    else{
      System.out.println("There isn't a comparative advantage, try the simulation again by typing 1. Type 0 to exit the code.");
      if(sc.nextInt()==1){
        clearBoard();
        play();
      }
      else{
        System.exit(0);
      }
    }
  }
  
  public void calculateTrade(){
    if(comparative1.contains(nameGood1)){
      double r1 = (double)c1g1/c1g2;
      double r2 = (double)c1g2/c1g1;
      double r3 = (double)c2g1/c2g2;
      double r4 = (double)c2g2/c2g1;
      trade1 = r2 + " " + nameGood2 + " < " + r1 + " " + nameGood1 + " < " + r4 + " " + nameGood2;
      trade2 = r3 + " " + nameGood1 + " < " + r2 + " " + nameGood2 + " < " + r1 + " " + nameGood1;
    }
    else if(comparative1.contains(nameGood2)){
      double ratio1 = (double)c1g1/c1g2;
      double ratio2 = (double)c1g2/c1g1;
      double ratio3 = (double)c2g1/c2g2;
      double ratio4 = (double)c2g2/c2g1;
      trade1 = ratio1 + " " + nameGood1 + " < " + ratio2 + " " + nameGood2 + " < " + ratio3 + " " + nameGood1;
      trade2 = ratio4 + " " + nameGood2 + " < " + ratio1 + " " + nameGood1 + " < " + ratio2 + " " + nameGood2;
    }

    
    System.out.println(country1 + " would want to trade: " + trade1);
    System.out.println(country2 + " would want to trade: " + trade2);
  }

  public int validate(int min, int max)
  {
    boolean doLoop = true;
    while(doLoop)
    {
      String filler = sc.next();
      try{
        if(Integer.parseInt(filler) > max || Integer.parseInt(filler) < min)
        {
          System.out.print("Enter an integer (" + min + "-" + max + "): ");
        }
        else{
          doLoop = false;
          return Integer.parseInt(filler);
        }
      }
      catch(Exception e){
          System.out.print("Enter an integer (" + min + "-" + max + "): ");
      }  
    }
    return -1;
  }

  public void clearBoard()
  {
    board[0][1].setMarker("  ");
    board[0][2].setMarker("  ");
    board[1][1].setMarker("  ");
    board[1][2].setMarker("  ");
    board[2][1].setMarker("  ");
    board[2][2].setMarker("  ");
    board[1][0].setMarker("  ");
    board[2][0].setMarker("  ");
  }


}