package mergesort;

public class MultithreadedMergeSort extends Thread{
	
	public MultithreadedMergeSort(){
		
	}
	
	
	public void run(){
		System.out.print("hello");
	}
	
	public static void main(String args[]) throws InterruptedException{
		(new MultithreadedMergeSort())
		.start();
		

	}
	
	

}
