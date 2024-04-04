package sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{
                3, 5, 15, 26, 27, 2, 36
        };
        // 冒泡排序：从左到右（由小到大）
        int[] res = BubbleSort.bubbleSort(arr);
        for (int r : res) {
            System.out.print(r+"-");
        }
    }

    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        // 从左到右依次检查，如果左边大于右边则交换
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换 arr[j+1] 和 arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
