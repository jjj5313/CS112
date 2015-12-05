public class AF {
  public static void insertion(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      for (int j = i; j > 0; j--) {
        if (arr[j] < arr[j - 1])
          swap(arr, j, j - 1);
        // show array here
      }
    }
  }
}