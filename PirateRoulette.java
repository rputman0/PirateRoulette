//Pirate Roulette Game
//Player who picks an empty cell containing a hidden X looses
//There are 26 Cells, Computer Player moves Second
import java.util.Scanner;

//TODO: Program prompts user to play game over again, only sometimes
//TODO: Separate into different classes: GUI, Computer, Player, Main
//TODO: Clean up code, rename variables


public class PirateRoulette{
  //TODO: Create Fewer Barrels? (15)?
  public static String[] barrel = {
    "[   ]","[   ]","[   ]","[   ]",
    "[   ]","[   ]","[   ]","[   ]",
    "[   ]","[   ]","[   ]","[   ]",
    "[   ]","[   ]","[   ]","[   ]",
    "[   ]","[   ]","[   ]","[   ]",
    "[   ]","[   ]","[   ]","[   ]",
    "[   ]","[   ]"};

  public static int randomXCell = (int)(Math.random()*26);
  
  public static void main(String[]args){
    firstSetup();
  }
  
  static void firstSetup(){
    System.out.println("Pirate Roulette");
    System.out.println("Rules: Whoever Chooses The Cell Containing X Loses");
    gui();
    play("Player 1 (A)",1);
  }
  
  static void play(String player, int playerNumber){
    Scanner input = new Scanner(System.in);
    int choice = 0;
    if(playerNumber == 1){
     System.out.println(player + ": Enter The Number of an Unmarked Cell");
     try{
      choice = input.nextInt();
      if(choice > 26 || choice < 1){
       System.out.println("Enter A Number From 1-26");
       play("Player 1 (A)",1);
      }
     }catch(Exception e){ 
       System.out.println("Enter A Number From 1-26");
       play("Player 1 (A)",1);
     }
     placeSword(choice,playerNumber);
     input.close();
    }
    else if(playerNumber == 2){
     int computerChoice = (int)(Math.random()*26)+1;
     System.out.println("Computer (B) - Computer Picks: " + computerChoice);
     placeSword(computerChoice,playerNumber);
     input.close();
    }
  }
  
  static void printCells(){
    for(int i=0;i<barrel.length;i++){
      System.out.print((i+1) + barrel[i] + " ");
       if(i == 14)
         System.out.println();
    }
    System.out.println();
  }
  
  static void placeSword(int position,int playerNumber){
   if(barrel[position-1].equals("[   ]") && playerNumber == 1)
    barrel[position-1] = playerChoice(1);
   else if(barrel[position-1].equals("[   ]") && playerNumber == 2)
    barrel[position-1] = playerChoice(2);
   else
    promptAgain(playerNumber);
     
    outcome(position,playerNumber);
  }
  
   static void promptAgain(int playerNumber){
    Scanner input = new Scanner(System.in);
    int choice = 0;
    
    if(playerNumber == 1){
      System.out.println("ERROR: You have to choose an empty cell"); 
      gui();
      playerMessage(1,'A');
      try{
      choice = input.nextInt();
      if(choice > 26 || choice < 1){
       System.out.println("Enter A Number From 1-26");
       promptAgain(1);
      }
     }catch(Exception e){ 
       System.out.println("Enter A Number From 1-26");
       promptAgain(1);
     }
    }
    else{
      int computerChoice = (int)(Math.random()*26)+1;
      System.out.println("Computer (B) - Computer Picks: " + computerChoice);
      placeSword(computerChoice,playerNumber);
      input.close();
     }
   }
  
  static void outcome(int position, int playerNumber){
   if(position == randomXCell && playerNumber == 1){
     gameOver("You");
     System.out.println("Program Terminated");
     System.out.println();
    }
   else if(position == randomXCell && playerNumber == 2){
     gameOver("Computer");
     System.out.println("Program Terminated");
     System.out.println();
   }
   else if(position != randomXCell){
     gui();
     changeTurns(playerNumber);
   }
   else
     gui();
  }
  
  static void gameOver(String player){
    System.out.println("GAME OVER - " + player + " LOSE");
    barrel[randomXCell-1] = "[X]";
    gameOverArrows();
    System.out.println();
    printCells();
  }
    
  static void changeTurns(int playerNumber){
    switch(playerNumber){
       case 1: play("Player 2 (B)",2); break;
       case 2: play("Player 1 (A)",1); break;
     }
  }
  
  static String playerChoice(int playerNumber){
    switch(playerNumber){
      case 1: return "[A]";
      case 2: return "[B]";
    }
    return "";
  }
  
  static void arrow(){
    System.out.println("->");
  }
  
  static void playerMessage(int playerNumber, char playerPiece){
   System.out.println("Player " + playerNumber + "(" + playerPiece + ")" + ": Enter The Number of an Unmarked Cell");
  }
  
  static void gui(){
    arrow();
    printCells();
  }
  
  static void gameOverArrows(){
    for(int i=0;i<25;i++){
      System.out.print("-> ");
      try{
        Thread.sleep(100);
      }
      catch(Exception e){
      }
    }
  }
  
}