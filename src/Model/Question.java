package Model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * question's text.  
	 * 
	 */
	@SerializedName("question")
	@Expose
	private String question;

	/**
	 * list of possible answers for given question. 
	 * 
	 */
	@SerializedName("answers")
	@Expose
	public List<String> answers = null;
	/**
	 * correct_ans for a question.  
	 */
	@SerializedName("correct_ans")
	@Expose
	private String correct_ans;
	/**
	 * question's level. 0 - easy, 1 - normal, 2 - hard. (Required)
	 * 
	 */
	@SerializedName("level")
	@Expose
	private Integer level;
 
	/**
	 * team that created this question. 
	 * 
	 */
	@SerializedName("team")
	@Expose
	private String team;
	

	public String getCorrect_ans() {
		return correct_ans;
	}
 
	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}

 
	public String getTeam() {
		return team;
	}
 
	public void setTeam(String team) {
		this.team = team;
	}

	 
	public String getquestion() {
		return question;
	}
 
	public void setquestion(String question) {
		this.question = question;
	}

 
	public Integer getlevel() {
		return level;
	}

 
	public void setlevel(Integer level) {
		this.level = level;
	}

 
	public List<String> getAnswers() {
		return answers;
	}
 
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [correct_ans=" + correct_ans + ", question=" + question + ", level=" + level + ", team=" + team
				+ ", answers=" + answers + "]";
	}

 

}


