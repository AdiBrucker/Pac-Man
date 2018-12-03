package Model;

import java.util.List;

public interface InterModel {
	
	
	
	public boolean addQuestion(String question, int level,   String team, List<String> answers,String correct_ans ) ;
	public boolean removeQuestion(Integer index);
	public void writeQuestionsToJsonFile() ;
	public void loadQuestionsFromJsonFile () ;
	public boolean AddPacman(int score ,String name);
	public void inputSerialize();

}
