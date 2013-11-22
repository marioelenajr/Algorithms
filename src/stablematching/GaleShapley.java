package stablematching;


import java.util.HashMap;
import java.util.LinkedList;



public class GaleShapley {

	public static void main(String[] args){


		GaleShapley p = new GaleShapley();

		//		Person fab = p.generatePeople("fab", "mel", 3, "bel",2,  "kel", 1, "female");
		//		Person fred = p.generatePeople("fred", "mel", 3, "bel",1,  "kel", 2, "female");
		//		Person gel = p.generatePeople("gel", "mel", 1, "bel",3,  "kel", 2, "female");

		Person fab = p.generatePeople("fab", "mel", 1, "bel",2,  "kel", 3, "female", "male");
		Person fred = p.generatePeople("fred", "mel", 1, "bel",3,  "kel", 2, "female","male");
		Person gel = p.generatePeople("gel", "mel", 3, "bel",1,  "kel", 2, "female","male");

		//		Person mel = p.generatePeople("mel", "fab", 2, "fred",3,  "gel", 1, "male");
		//		Person bel = p.generatePeople("bel", "fab", 3, "fred",2,  "gel", 1, "male");
		//		Person kel = p.generatePeople("kel", "fab", 1, "fred",2,  "gel", 3, "male");

		Person mel = p.generatePeople("mel", "fab", 2, "fred",1,  "gel", 3, "male", "female");
		Person bel = p.generatePeople("bel", "fab", 1, "fred",2,  "gel", 3, "male", "female");
		Person kel = p.generatePeople("kel", "fab", 3, "fred",2,  "gel", 1, "male", "female");


		LinkedList<Person> femaleMatches = new LinkedList<Person>();
		femaleMatches.add(mel);
		femaleMatches.add(bel);
		femaleMatches.add(kel);

		LinkedList<Person> maleMatches = new LinkedList<Person>();
		maleMatches.add(fab);
		maleMatches.add(fred);
		maleMatches.add(gel);

		HashMap<String, Couple> matching = p.sort(femaleMatches, maleMatches);

		matching.toString();

	}


	public HashMap<String, Couple> run(){
		Person fab = generatePeople("fab", "mel", 1, "bel",2,  "kel", 3, "female", "male");
		Person fred = generatePeople("fred", "mel", 1, "bel",3,  "kel", 2, "female","male");
		Person gel = generatePeople("gel", "mel", 3, "bel",1,  "kel", 2, "female","male");


		Person mel = generatePeople("mel", "fab", 2, "fred",1,  "gel", 3, "male", "female");
		Person bel = generatePeople("bel", "fab", 1, "fred",2,  "gel", 3, "male", "female");
		Person kel = generatePeople("kel", "fab", 3, "fred",2,  "gel", 1, "male", "female");


		LinkedList<Person> femaleMatches = new LinkedList<Person>();
		femaleMatches.add(mel);
		femaleMatches.add(bel);
		femaleMatches.add(kel);

		LinkedList<Person> maleMatches = new LinkedList<Person>();
		maleMatches.add(fab);
		maleMatches.add(fred);
		maleMatches.add(gel);
		HashMap<String, Couple> matching = sort(femaleMatches, maleMatches);

		return matching;
	}

	private Person generatePeople(String yourName, String n1, int r1, String n2, int r2, String n3, int r3, final String theirSex, String yourSex ){


		Person p1 = new Person(theirSex,null,n1);
		p1.setRank(r1);

		Person p2 = new Person(theirSex,null,n2);
		p2.setRank(r2);

		Person p3 = new Person(theirSex,null,n3);
		p3.setRank(r3);




		final LinkedList<Person> desiredList = new LinkedList<Person>();
		desiredList.add(p1);
		desiredList.add(p2);
		desiredList.add(p3);

		return new Person(yourSex, desiredList, yourName );

	}

	private HashMap<String , Couple > sort(LinkedList<Person> femaleMatches, LinkedList<Person> maleSeekers){


		HashMap<String , Couple > menAndWomenMatches = new HashMap<String , Couple >();
		MergeSort ms = new MergeSort();

		HashMap<String, Person> females = new HashMap<String, Person>();
		HashMap<String, Person> males = new HashMap<String, Person>();

		for(Person desiredFemale : femaleMatches){
			females.put(desiredFemale.getName(), desiredFemale);
		}
		for(Person desiredMale : maleSeekers){
			males.put(desiredMale.getName(), desiredMale);
		}
		
		


		while(!maleSeekers.isEmpty()){


			Person male = maleSeekers.removeFirst();
			
			male.setDesiredList(ms.mergeSort(male.getDesiredList()));

			Person desiredFemale = male.getDesiredList().remove();
			Person matchedFemale = females.remove((desiredFemale.getName()));

			if(male.canPropose(matchedFemale)){



				matchedFemale.becomesMatched();
				male.becomesMatched();
				
//				HashMap<String, Person> list = new HashMap<String, Person>();
//				
//				for(Person searchingMale : matchedFemale.getDesiredList()){
//					list.put(searchingMale.getName(), searchingMale);
//				}
				
				menAndWomenMatches.put(matchedFemale.getName(), new Couple(male, matchedFemale));



				females.put(matchedFemale.getName(), matchedFemale);


			}else{
				Couple herCouple = menAndWomenMatches.get(matchedFemale.getName());
				Person herMalePartner = males.get(herCouple.getMaleCouple().getName());
				
				
				
				
				HashMap<String, Person> femaleDesiredList = new HashMap<String, Person>();
				//rename mpartner to something else
				for(Person desiredMaleNames : matchedFemale.getDesiredList()){
					femaleDesiredList.put(desiredMaleNames.getName(), desiredMaleNames);
				}
				
				Person askingMale = femaleDesiredList.get(male.getName());
				
				

				//1 is highest. so if current male has lower rank, swap
				if( askingMale.getRank() < femaleDesiredList.get(herMalePartner.getName()).getRank()){

					//add the newly decoupled male
					//Person decoupledMale = herCouple.getMaleCouple();
					//decoupledMale.becomesUnMatched();
					herMalePartner.becomesUnMatched();
					maleSeekers.add(herMalePartner);
					matchedFemale.becomesUnMatched();

					menAndWomenMatches.remove(matchedFemale.getName());




					male.becomesMatched();
					matchedFemale.becomesMatched();

					females.put(matchedFemale.getName(), matchedFemale);
					menAndWomenMatches.put(matchedFemale.getName(), new Couple(male, matchedFemale));

				}else{
					//try again
					maleSeekers.add(male);
					females.put(matchedFemale.getName(), matchedFemale);
				}
			}
			//}
		}
		return menAndWomenMatches;
	}
	//}//else throw some exception? Name isn't valid

	//}			
	//}
	
	public class Couple{
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
}











