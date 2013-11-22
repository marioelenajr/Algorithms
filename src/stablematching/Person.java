package stablematching;

import java.util.LinkedList;



class Person{
	 
	final private String sex;
	private int rank;
	final String name;
	private boolean engaged = false;
	//private Person proposal;
	private LinkedList<Person> desiredList;
	
	Person(String sex, LinkedList<Person> desiredList, String name){
		 this.sex = sex;
		 this.name=name;
		 this.desiredList = desiredList;
		 
		 }
	

	public void setRank(int rank){
		this.rank = rank;
	}
	
	public int getRank(){
		return rank;
	}
	
	public boolean canPropose(Person female){

		if( female.isMatched()){
			return false;
		}else{
			return true;
		}
		
	}
	
	public void setDesiredList(LinkedList<Person> desiredList){
		this.desiredList=desiredList;
	}
	
	public void becomesMatched(){
		this.engaged = true;
	}
	public void becomesUnMatched(){
		this.engaged=false;
	}
	
	private boolean isMatched(){
		return engaged;
	}
	
	public String getsex(){
		return sex;
	}
	public String getName(){
		return name;
	}
	
	public LinkedList<Person> getDesiredList(){
		return desiredList;
	}
}