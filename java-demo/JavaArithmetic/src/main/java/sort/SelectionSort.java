package sort;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{
                3, 5, 15, 26, 27, 2, 36
        };
        // 选择排序
        int[] res = SelectionSort.selectionSort(arr);
        for (int r : res) {
            System.out.print(r+"-");
        }
    }

    public static int[] selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 找到从i到n-1中的最小元素
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // 将找到的最小元素与i位置的元素交换
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
