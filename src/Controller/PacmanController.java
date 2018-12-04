package Controller;

import Model.InterModel;
import Model.ModelLogic;
import Model.SysData;

public class PacmanController {
	
	public static PacmanController instance;///singleton
	/** ModelLogic reference pointer */
	public static InterModel model;
	//Constructor
	public PacmanController() {
		model = new ModelLogic();

	}
	public static PacmanController CreateInstance() {
		try {
			if (instance == null) {
				model = ModelLogic.CreateInstance();
			  	model.inputSerialize();
				instance = new PacmanController();
				 
				return instance;
			}  
		} catch (Exception e) { //instead of IOException as before

			e.printStackTrace(); 
		}
		return instance;

 	}
	
	
	public static PacmanController getInstance() {
		return instance;
	}
	

	public boolean addPacman(String Name, int score){
		return model.AddPacman(score, Name);
	} 
	
}
