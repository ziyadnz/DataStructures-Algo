
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Graph {
	static File out;
	static FileWriter writer;

	Map<Integer, List<Node>> G1;

	public Graph() {
		G1 = new HashMap();
	}

	public void edgefind(int v1) {

		if (G1.containsKey(v1)) {
			for (Map.Entry<Integer, List<Node>> m : G1.entrySet()) {
				if (m.getKey().equals(v1)) {
					for (int i = 0; i < G1.get(v1).size(); i++) {
						System.out.println("(" + m.getKey() +" " + m.getValue().get(i).endNode + ") "
								+ m.getValue().get(i).weight);
					}

				}
			}

		}

	}

	public void addEdge(int v1, int v2, int weight) {
		if (!G1.containsKey(v1)) {
			G1.put(v1, new LinkedList<>());
		}

		G1.get(v1).add(new Node(v1, v2, weight));

	}

	// this is for user that not foolow back each other :)
	public void NotGT() throws IOException {

		int tmp, compare;
		String negatives = "";
		Integer[] keys = G1.keySet().toArray(new Integer[G1.size()]);
		for (int i = 0; i < keys.length - 1 / 2; i++) {
			System.out.println(1);

			tmp = keys[i]; // for taking first and last n/2 time
			compare = keys[keys.length - i];
			Integer[] tmparr = G1.get(tmp).toArray(new Integer[G1.get(tmp).size()]);
			Integer[] comparearr = G1.get(compare).toArray(new Integer[G1.get(compare).size()]);

			boolean a = Arrays.asList(tmparr).contains(comparearr[i]);
			boolean b = Arrays.asList(comparearr).contains(tmparr[i]);

			if (a == false && b == false) {
				negatives += "" + tmp + " " + compare + "\n";
				writer.write(negatives);

			}
		}
		try {
			FileWriter fw = new FileWriter(new File("NotGT.txt"));
			fw.write(negatives);

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// this is find users that they not friend each other (just one of them is
	// follows other)
	public void NegativePairs() throws IOException {

		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<Integer> endnodes1 = new ArrayList<Integer>();
		ArrayList<Integer> endnodes2 = new ArrayList<Integer>();
		String negatives = "";
		int counter = 0;

		// the problem is taking keys of G1
		for (int a : G1.keySet()) {

			values.add(a);
		}

		for (Map.Entry<Integer, List<Node>> m : G1.entrySet()) { // at here

			for (int i = 0; i < values.size() - 1 / 2; i++) { // search through all keys

				// no need one more if if G1.contains tmp because all values contains
				int firstKey = values.get(i);
				int secondKey = values.get(values.size() - 1 - i);
				endnodes1.clear();
				endnodes2.clear();

				for (int j = 0; j < G1.get(firstKey).size(); j++) {
					int tmp1 = G1.get(firstKey).get(j).endNode;
					endnodes1.add(tmp1);

				}
				//////////////////// KONTROL ET BURAYI//////////
				/*
				 * if (endnodes1.contains(secondKey)) { // firstkey nodes incluse second key
				 * reset firstkey and secondkey return; }
				 */
				for (int j = 0; j < G1.get(secondKey).size(); j++) {// add nodes of secondkey
					int tmp1 = G1.get(secondKey).get(j).endNode;
					endnodes2.add(tmp1);

				}
				if (!endnodes2.contains(firstKey) && !endnodes1.contains(secondKey) && counter < 5592) {
					negatives += firstKey + " " + secondKey + "\n";
					counter++;
					// System.out.println(counter);
					// System.out.println("nnnn");
					// System.out.print(negatives);

				}
				if (counter >= 5592) {
					break;
				}

			}

		}
		try {
			FileWriter fw = new FileWriter(new File("NegativePairs.txt"));
			fw.write(negatives);

			fw.close();
		} catch (Exception e) {
			
		}

	}

	public int[][] getNegatives() {
		int[][] arr = new int[5592][2];
		try {
			Scanner fr = new Scanner(new File("NegativePairs.txt"));
			for (int i = 0; i < arr.length; i++) {
				arr[i][0] = fr.nextInt();
				arr[i][1] = fr.nextInt();

			}
fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public int[][] getPositives() {
		int[][] arr = new int[5592][2];
		try {
			Scanner fr = new Scanner(new File("Positive.txt"));
			for (int i = 0; i < arr.length; i++) {
				arr[i][0] = fr.nextInt();
				arr[i][1] = fr.nextInt();

			}
fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public int[] FindCommonFriends() {
		String s = "";
		int[] comarr = new int[5592 * 2];
		/*
		 * take firstkey's nodes into a list take secondkey's nodes into a list each
		 * vontains increase +1 and print firstkey secondkey # C friends
		 */
		int[][] positiveArr = getPositives();
		int[][] negativeArr = getNegatives();

		for (int i = 0; i < positiveArr.length; i++) {
			comarr[i] = cmmnfriends(positiveArr[i][0], positiveArr[i][1]);
			s += "" + comarr[i] + " " + positiveArr[i][0] + " " + positiveArr[i][1] + " \n";
		}
		for (int i = 0; i < positiveArr.length; i++) {
			comarr[i + positiveArr.length] = cmmnfriends(negativeArr[i][0], negativeArr[i][1]);
			s += "" + comarr[i + positiveArr.length] + " " + negativeArr[i][0] + " " + negativeArr[i][1] + " \n";
		}
		try {
			FileWriter fw = new FileWriter(new File("CommonFriends.txt"));
			fw.write(s);

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comarr;

	}

	public int cmmnfriends(int i1, int i2) {
		int common = 0;
		List<Node> list1 = G1.get(i1);
		List<Node> list2 = G1.get(i2);
		ArrayList<Integer> friendl1 = new ArrayList<Integer>();

		for (int i = 0; i < list1.size(); i++) {
			friendl1.add(list1.get(i).endNode);
		}
		for (int i = 0; i < list2.size(); i++) {
			if (friendl1.contains(list2.get(i).endNode)) {
				common++;
			}
		}

		return common;

	}

	/*
	 * while sorting first two value is 1 string and third is int
	 * 
	 */
	/******************* QUICK SORT **********************/
	public static int partition(String a[], int beg, int end) {

		int left, right, loc, flag;
		String temp = "";
		loc = left = beg;
		right = end;
		flag = 0;
		while (flag != 1) {
			while ((Integer.parseInt(a[loc].split(" ")[0]) <= Integer.parseInt(a[right].split(" ")[0]))
					&& (loc != right)) {
				right--; 
				}
				if (loc == right)
					flag = 1;
				else if (Integer.parseInt(a[loc].split(" ")[0]) > Integer.parseInt(a[right].split(" ")[0])) {
					temp = a[loc];
					a[loc] = a[right];
					a[right] = temp;
					loc = right;
				}
				if (flag != 1) {
					while ((Integer.parseInt(a[loc].split(" ")[0]) >= Integer.parseInt(a[left].split(" ")[0]))
							&& (loc != left))
						left++;
					if (loc == left)
						flag = 1;
					else if (Integer.parseInt(a[loc].split(" ")[0]) < Integer.parseInt(a[left].split(" ")[0])) {
						temp = a[loc];
						a[loc] = a[left];
						a[left] = temp;
						loc = left;
					}
				}
			}


		return loc;
	}

	static void quickSort(String a[], int beg, int end) {

		int loc;
		if (beg < end) {
			loc = partition(a, beg, end);
			quickSort(a, beg, loc - 1);
			quickSort(a, loc + 1, end);
		}
	}

	/******************* QUICK SORT END **********************/

	/********************* HEAP SORT *******************/
	public void heapSort(String arr[])
    {
        int n = arr.length;
 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
 
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(String arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && Integer.parseInt(arr[l].split(" ")[0]) >Integer.parseInt(arr[largest].split(" ")[0]))
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && Integer.parseInt(arr[r].split(" ")[0]) >Integer.parseInt(arr[largest].split(" ")[0]))
            largest = r;
 
        // If largest is not root
        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

	/********************* HEAP SORT *******************/

	/********************* MERGE SORT ***********************************/
	public void sortM(String a[], int left, int mid, int right) {
		int a1 = mid - left + 1;
		int a2 = right - mid;

		/* Create temp arrays */ 
		String L[] = new String[a1];
		String R[] = new String[a2];

		/* Copy data to temp arrays */
		for (int i = 0; i < a1; ++i)
			L[i] = a[left + i];
		for (int j = 0; j < a2; ++j)
			R[j] = a[mid + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = left;
		while (i < a1 && j < a2) {

			if (Integer.parseInt(L[i].split(" ")[0]) <= Integer.parseInt(R[j].split(" ")[0])) {  
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < a1) {
			a[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < a2) {
			a[k] = R[j];
			j++;
			k++;
		}

	}

	public void MergeSort(String arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			MergeSort(arr, l, m);
			MergeSort(arr, m + 1, r);

			// Merge the sorted halves
			sortM(arr, l, m, r);
		}
	}

	/********************* MERGE SORT ***************************************/

	/********************* INSERTION SORT ***********************************/
	public void insertionSort(String b[]) {

		for (int i = 1; i < b.length; ++i) {
			String keys = b[i];
			int j = i - 1;

			while (j >= 0 && Integer.parseInt(b[j].split(" ")[0]) > Integer.parseInt(b[i].split(" ")[0])) {
				b[j + 1] = b[j];
				b[j + 1] = b[j];
				j = j - 1;
			}
			b[j + 1] = keys;

		}

	}

	/********************* INSERTION SORT ***********************************/

	/*
	 * boolean isConnected(int u, int v) { for (Edge i : G[u]) if (i.v == v) return
	 * true;
	 * 
	 * return false; }
	 */

	@Override
	public String toString() {
		String data = "";
		for (int a : G1.keySet()) {
			data += "" + G1.get(a)+ "";
		}
		return data;
	}


}
