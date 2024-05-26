package com.noob.collection;

import java.util.*;
import java.util.stream.Collectors;

class User{
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

// 集合转Map
class ListToMapDemo{
    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        users.add(new User("John", "123456@qq.com"));
        users.add(new User("Jane", null));
        // NPE
        users.stream().collect(Collectors.toMap(User::getName, User::getEmail));
    }
}

// 集合遍历（不要做增、删元素操作）
class IteratorDemo{
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("John");
        strList.add("Jane");
        strList.add("noob");

        // 错误示范：在for、foreach遍历集合的时候增删元素
        for (String str : strList) {
//            strList.remove(str); // ConcurrentModificationException
//            strList.add("new"); // ConcurrentModificationException
        }

        // 正确示例：使用迭代器操作
        Iterator<String> iterator = strList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if ("noob".equals(str)) {
                iterator.remove();
            }
        }
        System.out.println(strList);

        // Java8 可使用removeIf来删除满足特定条件的元素
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            list.add(i);
        }
        list.removeIf(filter -> filter % 2 == 0); /* 删除list中的所有偶数 */
        System.out.println(list); /* [1, 3, 5, 7, 9] */
    }
}

// 集合去重
/*
class RemoveDuplicateDemo{
    // Set 去重代码示例
    public static <T> Set<T> removeDuplicateBySet(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new HashSet<>();
        }
        return new HashSet<>(data);
    }

    // List 去重代码示例
    public static <T> List<T> removeDuplicateByList(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();

        }
        List<T> result = new ArrayList<>(data.size());
        for (T current : data) {
            if (!result.contains(current)) {
                result.add(current);
            }
        }
        return result;
    }
}
*/

// 集合转数组
class ListToArrayDemo{
    public static void main(String[] args) {
        String [] sArr = new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(sArr);
        Collections.reverse(list);
        // 没有指定类型的话会报错
        sArr = list.toArray(new String[0]);
        for (String s : sArr) {
            System.out.print(s + "-");
        }
    }
}

// 数组转集合
class ArrayToListDemo{
    public static void main(String[] args) {
        /*
        String[] myArray = {"Apple", "Banana", "Orange"};
        List<String> myList = Arrays.asList(myArray); // 等价于List<String> myList = Arrays.asList("Apple","Banana", "Orange");
        System.out.println(myList);
         */

        // 误区1：使用原生数据类型数组，将整个数组作为一个元素转为List
        int[] myArray = {1, 2, 3};
        // 传入原生数据类型数组，Arrays.asList得到的参数不是数组中的元素而是数组对象本身
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());// 1
        System.out.println(myList.get(0));// 数组地址值
//        System.out.println(myList.get(1));// 报错：ArrayIndexOutOfBoundsException
        // 即此时List中的唯一元素就是这个myArray数组（也就解释了上面的越界异常）
        int[] array = (int[]) myList.get(0);
        System.out.println(array[1]);// 2

        // 更正：调整为对应的包装类型，数组中的每个元素转为对应的List元素
        Integer[] arr = {1, 2, 3};
        List transferList = Arrays.asList(arr);
        System.out.println(transferList.size()); // 3


        // 误区2：使用集合的修改方法：add()、remove()、clear()会抛出异常
//        List list = Arrays.asList(1, 2, 3);
//        list.add(4);//运行时报错：UnsupportedOperationException
//        list.remove(1);//运行时报错：UnsupportedOperationException
//        list.clear();//运行时报错：UnsupportedOperationException

        // 更正
        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        list.add("6");
        list.remove("a");
        list.clear();

    }
}


/**
 * 一些集合使用注意事项案例分析
 */
public class CollectionDemo {


    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
    }

}
