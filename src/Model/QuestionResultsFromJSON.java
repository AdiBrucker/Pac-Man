package Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Holds the results of the questions from the JSON file
 */
public class QuestionResultsFromJSON implements Serializable{
 
	
	private static final long serialVersionUID = 1L;


	@SerializedName("questions")
	@Expose
	private List<Question> questions = null;

	/**
	 * Gets a question
	 * @return
	 */
	public List<Question> getQuestions() {
		System.err.println(questions);
		return questions;
	}

	/**
	 * Sets a question
	 * @param questions
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
