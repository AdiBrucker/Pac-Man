package Model;

import java.awt.geom.IllegalPathStateException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Collections;


public class SysData implements Serializable{
	
	//for Serialization
	private static final long serialVersionUID = 1L;
	///Singleton  
	public static SysData instance;
	
	
	///Stores all questions from the class that imports the JSON 
	private static List<Question> questions;
	//all the pacman results after the game finish;
	/// can be a hash map right now i cant see why 
	// i think that at the table scores same player can be appear more then one time ;
	private  ArrayList< Pacman>Pacman;
	/**The path to which the data will be stored*/
	public static String route="src//Serializable.ser";
	
 	/**
	 * Full constructor 
	 */
	// need to change to private /// just for checking the q load
	public SysData(){
		questions = new ArrayList<Question>();
		Pacman=new ArrayList<Pacman>();
		///data for checking Serializable
// 		AddPacman(568, "shahar");
// 		AddPacman(323232, "neta");
// 		AddPacman(323232321, "guy");
 
		//question data is saved in JSON file and being transfered back 
	 	
	 
	}
	
  
	/**
	 *(singleton).
	 * @return reference to this class's only instance (singleton).
	 */
	public static SysData createInstance() {

		if(instance == null){
			instance = new SysData();
	 	 	loadQuestionsFromJsonFile();

			return instance;
		}
			return instance;
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
	/**
	 * this method loads the questions data from the JSON file
	 */
	public static void loadQuestionsFromJsonFile () {
		
		Gson gson = new Gson();
		BufferedReader br = null;
		
		try {///src\\res\\questions.json
				br = new BufferedReader(new FileReader(new File("src\\res\\questions.json").getAbsolutePath()));
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
		String strJson = "{\n    \"questions\":"+gson.toJson(this.questions)+"}";
		
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
	
	public boolean addQuestion(String question, int level,   String team, List<String> answers,String correct_ans ) {
		
		int id = getQuestions().size()+1;
		
		boolean hasntBeenWritenYet = true;
		
		for(Question q: getQuestions()) {
			if (q.getquestion().equals(question)) {
				hasntBeenWritenYet=false;
			}
		}
		
		if(question!=null && level>=0 && level<=2 && team!=null && answers!=null && hasntBeenWritenYet==true) {
			Question newQ = new Question ();
 			newQ.setquestion(question);
			newQ.setlevel(level);
			newQ.setTeam(team);
			newQ.setAnswers(answers);
			newQ.setCorrect_ans(correct_ans);
			getQuestions().add(newQ);
			writeQuestionsToJsonFile();
			return true;
		}
		return false;
	}
	
	public boolean removeQuestion(Integer index) {
		if(index!=null) {
			Question questionToRemove = getQuestions().get(index);
			if (questionToRemove != null) {
				getQuestions().remove(questionToRemove);
				writeQuestionsToJsonFile();
				return true;
			}
		}
		return false;
	}
	
	public boolean AddPacman(int score, String name) {
		
		if (name!=null&& score>0) {
		//	Pacman P = new Pacman(score, name);
		//	System.out.println(P);
		 	Pacman.add(new Pacman(score, name));
		//	if() {
			 	Collections.sort(getPacman());  
				Serialize(this);
				return true; 
	//		}
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

