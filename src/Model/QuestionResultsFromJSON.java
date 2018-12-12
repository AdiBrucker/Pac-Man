package Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionResultsFromJSON implements Serializable{
 
	
	private static final long serialVersionUID = 1L;


	@SerializedName("questions")
	@Expose
	private List<Question> questions = null;



	public List<Question> getQuestions() {
		System.err.println(questions);
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
