package stablematching;

import java.util.LinkedList;

public class MergeSort {
	
	
	public LinkedList<Person> mergeSort(LinkedList<Person> list){
		

		if(list.size()<=1){
			return list;
		}
		
		LinkedList<Person> left = new LinkedList<Person>();
		LinkedList<Person> right = new LinkedList<Person>();
				
		int middle = list.size()/2;
		
		for(int i =0; i<middle; i++){
			
			left.add(list.get(i));			
		}
		for(int i = middle; i<list.size(); i++){
			right.add(list.get(i));
		}
		
		left = mergeSort(left);
		
		right = mergeSort(right);
		
		return merge(left, right);
		
	}
	

	private LinkedList<Person> merge(LinkedList<Person> left, LinkedList<Person> right){

		LinkedList<Person> result = new LinkedList<Person>();

		while(left.size()>0 || right.size()>0){

			if(left.size() >0 && right.size()>0){

				if(left.getFirst().getRank() <= right.getFirst().getRank()){

					result.add(left.remove());
				}else{
					result.add(right.remove());
				}
			}else if(left.size()>0){
				result.add(left.remove());
			}else if(right.size()>0){
				result.add(right.remove());
			}			
		}
		return result;
	}
	
	
	
	
//	private void mergeSort(int low, int high){
//		if(low<high){
//			int middle = low + (high-low)/2;
//			
//			mergeSort(low, middle);
//			
//			mergeSort(middle+1, high);
//			
//			merge(low, middle, high);
//			
//		}
//		
//	}
	
//	private void merge(int low, int middle, int high){
//		
//		for(int i = low; i<high; i++){
//			helper[i] = numbers[i];
//			helperList.add(list.get(i));
//		}
//		
//		int i = low;
//		int j = middle+1;
//		int k = low;	//pointer 
//		
//		while(i<=middle && j<=high){
//			if(helper[i] <= helper[j]){
//				numbers[k] = helper[i];	// low index value is smaller than middle index value
//				
//				Person copy = list.get(j);
//				list.add(j, copy);		//Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
//				copy = list.remove(j+1);
//				list.remove(i);
//				list.add(i, copy);
//				
//				
//				i++;
//			}else{
//				numbers[k] = helper[j];	// high index value is smaller than low index value
//				
//				
//				j++;
//			}
//			k++;
//		}
//		
//		while(i<= middle){
//			numbers[k] = helper[i];
//			k++;
//			i++;
//		}
//	}
//	
//	private void swap(int i , int j, LinkedList<Person> swappableList){
//		Person copy = list.get(j);
//		list.add(j, copy);		//Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
//		copy = list.remove(j+1);
//		list.remove(i);
//		list.add(i, copy);
//	}
}
