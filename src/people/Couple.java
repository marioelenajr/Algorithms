package people;


 class Couple{
	private Person male;
	private Person female;
	
	public Couple(Person male, Person female){
		this.male=male;
		this.female=female;
	}
	
	public Person getMaleCouple(){
		return male;
	}
	public Person getFemaleCouple(){
		return female;
	}
}
