package Controller;

import Model.InterModel;
import Model.ModelLogic;

/**
 * Controller class responsible to connect between the model and the view
 */
public class PacmanController {

    //singleton
    private static PacmanController instance;

    //ModelLogic reference pointer
    private static InterModel model;

    /**
     * PacmanController Constructor
     */
    private PacmanController() {
        model = new ModelLogic();
    }

    /**
     * creates instance of PacmanController that also loading the questions and the
     * last scores from the  last games
     *
     * @return
     */
    public static PacmanController CreateInstance() {
        try {
            if (instance == null) {
                model = ModelLogic.CreateInstance();
                model.loadQuestionsFromJsonFile();
                model.inputSerialize();
                instance = new PacmanController();

                return instance;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return instance;
    }
}
