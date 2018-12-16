package Model;

import java.awt.geom.IllegalPathStateException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;

/**
 * holds the data of the played games
 */

public class SysData implements Serializable{
	
	//for Serialization
	private static final long serialVersionUID = 1L;
	///Singleton  
	public static SysData instance;
	
	
	///Stores all questions from the class that imports the JSON 
	public static List<Question> questions;
	
	public static List<Question> UndoRedo;/// list of Question  to Memento design-pattern 

	//all the pacman results after the game finish;
	/// can be a hash map right now i cant see why 
	// i think that at the table scores same player can be appear more then one time ;
	public  ArrayList< Pacman>Pacman;
	/**The path to which the data will be stored*/
	public static String route="Serializable.ser";
	
 	/**
	 * Full constructor 
	 */
	private SysData(){		
 		
 		questions = new ArrayList<Question>();
 		Pacman=new ArrayList<Pacman>();
 		UndoRedo= new ArrayList<Question>();
	}
	
  
	/**
	 *(singleton).
	 * @return reference to this class's only instance (singleton).
	 */
	public static SysData createInstance() {

		if(instance == null){
			instance = new SysData();
			return instance;
		}
			return instance;
	}
	
	public void SetPacman(ArrayList< Pacman> A) {
		Pacman=A;
		}
	/**
	 * @return the pacman DB.
	 */
	public ArrayList< Pacman> getPacman() {
		return Pacman;
	}

	public  List<Question> getQuestions() {
		return questions;
	}
	
	public  List<Question> getUndoRedo() {
		return UndoRedo;
	}
	/**
	 * this method loads the questions data from the JSON file
	 */
	public void loadQuestionsFromJsonFile () {
		
		Gson gson = new Gson();
		BufferedReader br = null;
		
		try {///src\\res\\questions.json
				br = new BufferedReader(new FileReader(new File("res/questions.json").getAbsolutePath()));
				QuestionResultsFromJSON questionsResults = gson.fromJson(br, QuestionResultsFromJSON.class);
				questions = questionsResults.getQuestions();
				
		} catch (FileNotFoundException e) {
 				e.printStackTrace();

		} finally {
			
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
	}
	
	/**
	 * this method writes the questions data to the JSON file
	 */
	public void writeQuestionsToJsonFile() {
 
 		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String strJson = "{\n    \"questions\":"+gson.toJson(getQuestions())+"}";
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter ("res/questions.json");
			writer.write(strJson);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * adding Question to list 
	 * at the end of the game we will update json file 
	 * we will call writeQuestionsToJsonFile(); to update
	 * @param question
	 * @param level
	 * @param team
	 * @param answers
	 * @param correct_ans
	 * @return
	 */
	public boolean addQuestion(String question, int level,   String team, List<String> answers,String correct_ans ) {
		
		int id = getQuestions().size()+1;
		boolean hasntBeenWritenYet = true;
		for(Question q: getQuestions()) {
			if (q.getquestion().equals(question)) {
				hasntBeenWritenYet=false;
			}
		}
		for(Question q1: getUndoRedo()) {////////////////ask shai 
			if (q1.getquestion().equals(question)) {
				hasntBeenWritenYet=false;
			}
		}
		if( question!=null&&!question.isEmpty()  && level>=0 && level<=2 && team!=null&&!team.isEmpty() && answers.size()>=2 && hasntBeenWritenYet==true) {
			Question newQ = new Question ();
 			newQ.setquestion(question);
			newQ.setlevel(level);
			newQ.setTeam(team);
			newQ.setAnswers(answers);
			newQ.setCorrect_ans(correct_ans);
			getUndoRedo().add(newQ);
			return true;
		}
		return false;
	}
	
	/**
	 * marge UndoRedo array with Questions() array
	 * @param number
	 */
	public void UpdateQuestionArray (int number ) {
		for(int i=0; i<number;i++) {
			getQuestions().add(getUndoRedo().get(i));
		}
		
 		UndoRedo= new ArrayList<Question>();

	}
	/**
	 * remove Question from json files
	 * we update the list and when we finished the game and we close the screen 
	 * we will call the mathode  	writeQuestionsToJsonFile();
	 * @param index
	 * @return
	 */
	public boolean removeQuestion(Integer index) {
		if(index!=null) {
			Question questionToRemove = getQuestions().get(index);
			if (questionToRemove != null) {
				getQuestions().remove(questionToRemove);
 				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * adding  pacman results  to the PacmanResults list and sort by scores
	 * 
	 * @param score
	 * @param name
	 * @return
	 */
	public boolean AddPacman(int score, String name) {
 		if (name!=null&& score>0) {
		 	Pacman.add(new Pacman(score, name));
 			 	Collections.sort(getPacman());
 				System.out.println(getPacman()+"after adding ");

 				return true; 
 
		}
		return false; 
	}
	
	/**
	 * this method loads game objects that has already been
	 * created and saved into the system. 
	 */
	public static SysData inputSerialize(){
		try{
 			FileInputStream inputFile= new FileInputStream(route);
			ObjectInputStream inputStream = new ObjectInputStream(inputFile);
			SysData input = (SysData)inputStream.readObject();
 			inputStream.close();
 			inputFile.close();
 			
 			return input;
		}
		catch (Exception e){
			e.getMessage();
 			e.printStackTrace();
 		 	return new SysData();
		}
	}
	
	/**
	 * this method writes new data   to the serialized DB
	 * so that everything new that inserted to the system will be saved.
	 */
	public static void Serialize(Object Obj) throws IllegalPathStateException{
		try{
 			FileOutputStream OutPutFile= new FileOutputStream(route);
			ObjectOutputStream OutPutStream = new ObjectOutputStream(OutPutFile);
			OutPutStream.writeObject(Obj);
 			OutPutStream.close();
			OutPutFile.close();
 
		}
		catch (IOException e){
 			e.printStackTrace();
		}
	}

}

