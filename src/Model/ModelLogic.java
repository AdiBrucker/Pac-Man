package Model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;



public class ModelLogic  implements InterModel, Serializable{
	
		
		//for Serialization
	private static final long serialVersionUID = 1L;
	private static ModelLogic instance;
 	public static SysData data = SysData.createInstance(); 
 	
 	
 	public static SysData getsData() {
	 	return data;
	}
	
	public static ModelLogic CreateInstance() throws IOException {
		if(instance == null){
			instance  = new ModelLogic();
			data = SysData.createInstance();
			return instance;
		}
		else{
			return instance;
		}
	}

	@Override
	public boolean addQuestion(String question, int level, String team, List<String> answers, String correct_ans) {

		data.addQuestion(question, level, team, answers, correct_ans);
		return false;
	}

	@Override
	public boolean removeQuestion(Integer index) {

		data.removeQuestion(index);
		return false;
	}

	@Override
	public void writeQuestionsToJsonFile() {

		data.writeQuestionsToJsonFile();
	}

	@Override
	public void loadQuestionsFromJsonFile() {
		data.loadQuestionsFromJsonFile();
 	}

	@Override
	public boolean AddPacman(int score, String name) {
		return data.AddPacman( score, name);
	}
	
	public  void inputSerialize(){
		 data=data.inputSerialize();
		 SysData.createInstance().SetPacman(data.getPacman());
		System.err.println(SysData.createInstance().getPacman());
		 
	}
}
