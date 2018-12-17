package Model;
//This is a factory thats only job is creating Candy
 //By encapsulating ship creation, we only have one
//place to make modifications

public class CandyFactory {

	
	
  public static Candy makeCandy(String newCandyType,int x,int y){
 
	  if (newCandyType.equals("PoisonCandy")) {
		  
		  
		  return new PoisonCandy(x,y);
	  }
	  else if (newCandyType.equals("QuestionCandy")) {
		  
		 
		  return  new QuestionCandy(x,y);
	  } 
	  else if (newCandyType.equals("Silver")||newCandyType.equals("Gold")||newCandyType.equals("Yellow")) {
		  
		  
		  return  new ScoreCandy(x,y, newCandyType) ;
	  	}
	  else {
		  return null;
	  }
	  
	  }
}
