/** Yuanli Zhong
  * BU ID: U79526308
  * ComplexityLaboratory.java.
  **/

import java.util.Random;
import java.util.Comparator;

public class ComplexityLaboratory{
  public static void main (String []args) { 
    resetCounter(); 
    selectionSort(GRA(42));
    
    Grapher G = new Grapher("Comparison of Average Case Sort for Six Algorithms");
    int [] c = new int [42];
    
    for (int i = 0; i <= 20; i++) {
      resetCounter();
      selectionSort(GRA(i*5));
      c[i*2 + 1] = counter;
    }
    for (int i = 0; i <= 20 ; i++) {
      c[i*2] = i*5;
    }
    G.drawCurve(c, "Selection Sort");
    //
    for (int i = 0; i <= 20; i++) {
      resetCounter();
      heapSort(GRA(i*5));
      c[i*2 + 1] = counter;
    }
    for (int i = 0; i <= 20 ; i++) {
      c[i*2] = i*5;
    }
    G.drawCurve(c, "Heap Sort");
    //
    for (int i = 0; i <= 20; i++) {
      resetCounter();
      insertionSort(GRA(i*5));
      c[i*2 + 1] = counter;
    }
    for (int i = 0; i <= 20 ; i++){
      c[i*2] = i*5;
    }
    G.drawCurve(c, "Insertion Sort");
    //
    for (int i = 0; i <= 20; i++) {
      resetCounter();
      shellSort(GRA(i*5));
      c[i*2 + 1] = counter;
    }
    for (int i = 0; i <= 20 ; i++) {
      c[i*2] = i*5;
    }
    G.drawCurve(c, "Shell Sort");
    //
    for (int i = 0; i <= 20; i++) {
      resetCounter();
      mergeSort(GRA(i*5));
      c[i*2 + 1] = counter;
    }
    for (int i = 0; i <= 20 ; i++) {
      c[i*2] = i*5;
    }
    G.drawCurve(c, "Merge Sort");
    
    for (int i = 0; i <= 20; i++) {
      resetCounter();
      quickSort(GRA(i*5));
      c[i*2 + 1] = counter;
    }
    for (int i = 0; i <= 20 ; i++) {
      c[i*2] = i*5;
    }
    G.drawCurve(c, "Quick Sort"); 
  }
  
  private static Comparable [] GRA(int size) {       // create an array and fill it with random four-digit numbers
    Random Rand = new Random();
    Comparable [] temp = new Comparable[size];
    for (int i = 0; i < size; i++) { 
      temp[i]= Rand.nextInt(10000); 
    }
    return temp;
  }
  
  private static int counter = 0;
  private static void resetCounter() {
    counter = 0;
  }
  private static boolean less(Comparable m, Comparable n) {
    counter++;
    return m.compareTo(n) < 0;
  }
  /***********************************************************/
  private static void selectionSort(Comparable[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
      int min = i;
      for (int j = i+1; j < N; j++) {
        if (selectionless(a[j], a[min])) min = j;
      }
      selectionexch(a, i, min);
    }
  }
  
  private static boolean selectionless(Comparable m, Comparable n) {
    counter++;
    return (m.compareTo(n) < 0);
  }
  
  private static void selectionexch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  } 
  /***********************************************************/
  private static void heapSort(Comparable[] a) {
    int N = a.length;
    for (int k = N/2; k >= 1; k--)
      heapsink(a, k, N);
    while (N > 1) {
      heapexch(a, 1, N--);
      heapsink(a, 1, N);
    }
  }
  
  private static void heapsink(Comparable[] a, int k, int N) {
    while (k*2 <= N) {
      int j = k*2;
      if (j < N && heapless(a, j, j+1))
        j++;
      if (!heapless(a, k, j)) 
        break;
      heapexch(a, k, j);
      k = j;
    }
  }
  
  private static boolean heapless(Comparable[] a, int i, int j) {
    counter++;
    return a[i-1].compareTo(a[j-1]) < 0;
  }
  
  private static void heapexch(Object[] a, int i, int j) {
    Object swap = a[i-1];
    a[i-1] = a[j-1];
    a[j-1] = swap;
  }
  /***********************************************************/
  private static void insertionSort(Comparable[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
      for (int j = i; j > 0 && insertionless(a[j], a[j-1]); j--) {
        insertionexch(a, j, j-1);
      }
    }
  }
  
  private static boolean insertionless(Comparable m, Comparable n) {
    counter++;
    return (m.compareTo(n) < 0);
  }
  
  private static void insertionexch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
  /***********************************************************/  
  private static void shellSort(Comparable[] a) {
    int N = a.length;
    
    int H = 1;
    while (H < N/3)
      H = 3*H + 1; 
    while (H >= 1) {  // h-sort the array
      for (int i = H; i < N; i++) {
        for (int j = i; j >= H && shellless(a[j], a[j-H]); j -= H) {
          shellexch(a, j, j-H);
        }
      }
      H /= 3;
    }
  }
  
  private static boolean shellless(Comparable m, Comparable n) {
    counter++;
    return (m.compareTo(n) < 0);
  }
  
  // exchange a[i] and a[j]
  private static void shellexch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
  /***********************************************************/ 
  private static void mergeSort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    mergesort2(a, aux, 0, a.length-1);
  }
  
  private static void mergesort2(Comparable[] a, Comparable[] aux, int low, int high) {
    if (high <= low) return;
    int mid = low + (high - low) / 2;
    mergesort2(a, aux, low, mid);
    mergesort2(a, aux, mid + 1, high);
    merge(a, aux, low, mid, high);
  }
  private static boolean mergeless(Comparable m, Comparable n) {
    counter++;
    return (m.compareTo(n) < 0);
  }
  
  private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
    for (int k = low; k <= high; k++) {
      aux[k] = a[k]; 
    }
    int i = low, j = mid+1;
    for (int k = low; k <= high; k++) {
      if      (i > mid)              
        a[k] = aux[j++];   
      else if (j > high)               
        a[k] = aux[i++];
      else if (mergeless(aux[j], aux[i])) 
        a[k] = aux[j++];
      else                           
        a[k] = aux[i++];
    }
  }
  /***********************************************************/  
  private static void quickSort(Comparable[] a) {
    quicksort(a, 0, a.length - 1);
  }
  
  private static void quicksort(Comparable[] a, int low, int high) { 
    if (high <= low) 
      return;
    int j = partition(a, low, high);
    quicksort(a, low, j-1);
    quicksort(a, j+1, high);
  }
  
  private static boolean quickless(Comparable m, Comparable n) {
    counter++;
    return (m.compareTo(n) < 0);
  }
  
  private static void quickexch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
  
  private static int partition(Comparable[] a, int low, int high) {
    int i = low;
    int j = high + 1;
    Comparable m = a[low];
    while (true) { 
      while (quickless(a[i++], m))
        if (i == high) 
        break;
      while (quickless(m, a[j--]))
        if (j == low) 
        break;     
      
      if (i >= j) 
        break; // check if pointers cross
      quickexch(a, i, j);
    }
    quickexch(a, low, j);
    return j; // now, a[low .. j-1] <= a[j] <= a[j+1 .. high]
  }
  
}