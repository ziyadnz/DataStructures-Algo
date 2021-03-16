
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		Graph G = new Graph();
		Scanner screen = new Scanner(System.in);
		Scanner sc = new Scanner(new File("FinalExamGraphFile.txt"));

		while (sc.hasNext()) {
			int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
			G.addEdge(a, b, c);

		}
		sc.close();

		System.out.println("Question 1: Please enter the value");
		int a = screen.nextInt();
		String s = G.G1.get(a).toString();
		s = s.substring(1, s.length() - 1);
		String[] ss = s.split(",");
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i].trim());
		}

		screen.close();

		System.out.println();
		System.out.println("***************** Question 2 *********************");

		G.getPositives();
		System.out.println();
		System.out.println("***************** Question 2 END (Positive.txt loaded) *********************");

		System.out.println();
		System.out.println("***************** Question 3 *********************");
		G.NegativePairs(); // printing not NegativePairs users
		System.out.println();
		System.out.println("***************** Question 3 END (NegativePairs.txt created) *********************");

		System.out.println();
		System.out.println("***************** Question 4 *********************");
		int[] comarr = G.FindCommonFriends();
		String[] quick = new String[11184];
		String[] heap = new String[11184];
		String[] merge = new String[11184];
		String[] insert = new String[11184];
		String[] unsorted = new String[11184];
		int top = 0;
		try {
			Scanner scancom = new Scanner(new File("CommonFriends.txt"));
			int count = 0;
			String fr = "";
			while (scancom.hasNext()) {

				fr = scancom.nextLine();
				quick[count] = fr;
				heap[count] = fr;
				merge[count] = fr;
				insert[count] = fr;
				unsorted[count] = fr;
				count++;

			}
			scancom.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println();
		System.out.println("***************** Question 4 END (CommonFriends.txt created) *********************");

		System.out.println();
		System.out.println("***************** Question 5 *********************");
		System.out.println();


		System.out.println("****************QuickSort**************");
		long startTime = System.currentTimeMillis();

		G.quickSort(quick, 0, quick.length-1);
		long finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + "Milisecond");
		System.out.println();
		System.out.println();
		System.out.println("*-*-*-*-*- ACCURACY of QuickSort *-*-*-*-*-*-");
		
		int accuracy=0;
		for (int i = 0; i < unsorted.length/2; i++) {
			String strs=unsorted[i];
			for (int j = 0; j < quick.length/2; j++) {
				if (quick[j].equals(strs)) {
					accuracy++;
					break;
				}
				
			}
		}
		System.out.println("Accuracy of QuickSort is: " + accuracy*2+"/"+unsorted.length);

		System.out.println("****************HeapSort**************");
		startTime = System.currentTimeMillis();

		G.heapSort(heap);
		finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + "Milisecond");
		System.out.println();
		System.out.println();
		System.out.println("*-*-*-*-*- ACCURACY of HEAP *-*-*-*-*-*-");
		
		accuracy=0;
		for (int i = 0; i < unsorted.length/2; i++) {
			String strs=unsorted[i];
			for (int j = 0; j < heap.length/2; j++) {
				if (heap[j].equals(strs)) {
					accuracy++;
					break;
				}
				
			}
		}
		System.out.println("Accuracy of HeapSort is: " + accuracy*2+"/"+unsorted.length);

		System.out.println("****************MergeSort**************");
		startTime = System.currentTimeMillis();

		G.MergeSort(merge, 0, merge.length - 1);
		// G.MergeSort(merge, 0, merge.length-1);
		finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + "Milisecond");
		System.out.println();
		System.out.println();
		System.out.println("*-*-*-*-*- ACCURACY of MergeSort *-*-*-*-*-*-");
		
		accuracy=0;
		for (int i = 0; i < unsorted.length/2; i++) {
			String strs=unsorted[i];
			for (int j = 0; j < merge.length/2; j++) {
				if (merge[j].equals(strs)) {
					accuracy++;
					break;
				}
				
			}
		}
		System.out.println("Accuracy of MergeSort is: " + accuracy*2+"/"+unsorted.length);

		System.out.println("****************InsertionSort**************");
		startTime = System.currentTimeMillis();
		G.insertionSort(insert);
		finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + "Milisecond");

		System.out.println();
		System.out.println();
		System.out.println("*-*-*-*-*- ACCURACY of InsertionSort*-*-*-*-*-*-");
		
		accuracy=0;
		for (int i = 0; i < unsorted.length/2; i++) {
			String strs=unsorted[i];
			for (int j = 0; j < insert.length/2; j++) {
				if (heap[j].equals(strs)) {
					accuracy++;
					break;
				}
				
			}
		}
		System.out.println("Accuracy of InsertionSort is: " + accuracy*2+"/"+unsorted.length);
		
		
		
		
		
		System.out.println("***************** Question 5 END *********************");
		


	}

}
