package search;

/**
 * 二分法搜索
 */
public class BinarySearch {

    public static void main(String[] args) {
        // 二分搜索需对数组进行排序，随后调用二分搜索进行检索，返回数组下标
        int[] arr = new int[]{
                1, 6, 15, 17, 29
        };
        System.out.println(BinarySearch.binarySearch(arr, 15));
    }

    public static int binarySearch(int[] arr, int x) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 检查x是否在中间
            if (arr[mid] == x)
                return mid;
            // 如果x大于中位数，忽略左半边
            if (arr[mid] < x)
                left = mid + 1;
                // 如果x小于中位数，忽略右半边
            else
                right = mid - 1;
        }
        // 如果没有找到，返回-1
        return -1;
    }
}
