package Model;

public interface LifeBonus {
	boolean life();
}

class isLife implements LifeBonus{

	@Override
	public boolean life() {
		// TODO Auto-generated method stub
		return true;
	}
	
}

class isntLife implements LifeBonus{

	@Override
	public boolean life() {
		// TODO Auto-generated method stub
		return false;
	}
	
}