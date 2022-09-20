import java.util.Scanner;
import java.io.*;
class Main 
{
  public static void main(String[] args) throws FileNotFoundException 
  {
    
    Simulation s; 
    //Scanner sc = new Scanner(new File("winx.dat"));
    //Scanner sc = new Scanner(new File("winO.dat"));
    // Scanner sc = new Scanner(new File("tie.dat"));
    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome to Ricky, Brandon, and Owen's Economics simulator!\nBelow is the board that your input will be printed in.\n\n**The max length for a word inputted is 5 letters so please make sure to abbreviate if your country name, good, or number exceeds 5 letters.**\n");
    s = new Simulation();
    s.play();
    
  }


  
}