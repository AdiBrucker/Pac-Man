package Controller;

import Model.InterModel;
import Model.ModelLogic;
import Model.SysData;

public class PacmanController {
	
	private static PacmanController instance;///singleton
	/** ModelLogic reference pointer */
	private static InterModel model;
	//Constructor
	private PacmanController() {
		model = new ModelLogic();

	}
	/**
	 * instance of PacmanController that also loading the questions and the 
	 * last scores from the  last games 
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
 
	
	public static void SetInstance() {
    	instance=null;
    }
	
	public static PacmanController getInstance() {
		return instance;
	}
	public boolean addPacman(String Name, int score){
		return model.AddPacman(score, Name);
	} 

}
