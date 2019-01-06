package Model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Manages the connection between the questions and the game.
 */
public class ModelLogic implements InterModel, Serializable {

    //for Serialization
    private static final long serialVersionUID = 1L;
    private static ModelLogic instance;
    public static SysData data = SysData.createInstance();


    /**
     * Gets the sysData data
     * @return
     */
    public static SysData getsData() {
        return data;
    }

    /**
     * Creates the instance of the Model Logic
     * @return
     * @throws IOException
     */
    public static ModelLogic CreateInstance() throws IOException {
        if (instance == null) {
            instance = new ModelLogic();
            data = SysData.createInstance();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Responsible to add a question.
     * @param question
     * @param level
     * @param team
     * @param answers
     * @param correct_ans
     * @return
     */
    @Override
    public boolean addQuestion(String question, int level, String team, List<String> answers, String correct_ans) {
        data.addQuestion(question, level, team, answers, correct_ans);
        return false;
    }

    /**
     * Removes a question from the data
     * @param index
     * @return
     */
    @Override
    public boolean removeQuestion(Integer index) {
        data.removeQuestion(index);
        return false;
    }

    /**
     * Writes a question to the JSON file
     */
    @Override
    public void writeQuestionsToJsonFile() {
        data.writeQuestionsToJsonFile();
    }

    /**
     * Loads the questions from the JSON file
     */
    @Override
    public void loadQuestionsFromJsonFile() {
        data.loadQuestionsFromJsonFile();
    }

    /**
     * Adds a pacmans score the the data
     * @param score
     * @param name
     * @return
     */

    @Override
    public boolean AddPacman(int score, String name) {
        return data.AddPacman(score, name);
    }

    /**
     * Data Serializable
     */
    public void inputSerialize() {
        data = data.inputSerialize();
        SysData.createInstance().SetPacman(data.getPacman());
    }
}
