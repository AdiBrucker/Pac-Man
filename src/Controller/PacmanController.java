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
	/// need to check this one    
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
