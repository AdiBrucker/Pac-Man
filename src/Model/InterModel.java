package Model;

import java.util.List;

/**
 * Responsible to define the methods the should be used in the questions manager and the logic between the pacman
 * and the questions
 */
public interface InterModel {
	public boolean addQuestion(String question, int level,   String team, List<String> answers,String correct_ans ) ;
	public boolean removeQuestion(Integer index);
	public void writeQuestionsToJsonFile() ;
	public void loadQuestionsFromJsonFile () ;
	public boolean AddPacman(int score ,String name);
	public void inputSerialize();
}
