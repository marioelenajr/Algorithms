package mergesort;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import people.Person;


public class MergeSortTest {

	@Test
	public void testMergeSort() {
		
		MergeSort mergeSort = new MergeSort();
		
		
		String[] names = new String[10]; 
		
		names[0] = "a";//1
		names[1] = "b";//2
		names[2] = "c";//3
		names[3] = "d";//4
		names[4] = "e";//5
		names[5] = "f";
		names[6] = "g";
		names[7] = "h";//8
		names[8] = "i";
		names[9] = "j";
		
		
		int rank[] = new int[10];
		rank[0] = 5;//1
		rank[1] = 8;//2
		rank[2] = 4;//3
		rank[3] = 7;//4
		rank[4] = 2;//5
		rank[5] = 1;//6
		rank[6] = 9;//7
		rank[7] = 3;//8
		rank[8] = 6;//9
		rank[9] = 10;//10
		
		String[] desiredNames = new String[10]; 
		
		desiredNames[0] = "f";
		desiredNames[1] = "e";
		desiredNames[2] = "h";
		desiredNames[3] = "c";
		desiredNames[4] = "a";
		desiredNames[5] = "i";
		desiredNames[6] = "d";
		desiredNames[7] = "b";
		desiredNames[8] = "g";
		desiredNames[9] = "j";
		
		int desiredRank[] = new int[10];
		for(int i = 0; i < desiredRank.length;i++){
			
			desiredRank[i] = i+1;
		}
		
		Person desiredList = generatePeople("fab", desiredNames, desiredRank, "female", "male");
		Person gel = generatePeople("gel", names, rank, "female","male");

		LinkedList<Person> test = mergeSort.mergeSort(gel.getDesiredList());
		
		for(int i =0; i<test.size(); i++){
			
			assertEquals(desiredList.getDesiredList().get(i).getName(),test.get(i).getName());
			assertEquals(desiredRank[i], test.get(i).getRank());
		}
		

	}
		
	
	
	public Person generatePeople(String yourName, String[] names, int[] ranks, final String theirSex, String yourSex ){
		
		final LinkedList<Person> desiredList = new LinkedList<Person>();
		for(int i = 0; i<names.length; i++){
			Person p = new Person(theirSex, null, names[i]);
			p.setRank(ranks[i]);
			desiredList.add(p);
			
		}
		
		return new Person(yourSex, desiredList, yourName );
		
	}

}
