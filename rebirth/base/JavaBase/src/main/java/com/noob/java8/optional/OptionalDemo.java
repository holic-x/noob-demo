package com.noob.java8.optional;

import java.util.Optional;

// 定义会员类
class Member {
    private String name;

    Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class WithoutOptionalDemo {
    // 定义一个方法模拟获取会员信息
    public static Member getMemberByIdFromDB() {
        // 当前 ID 的会员不存在
        return null;
    }

    public static void main(String[] args) {
        // 传统方式NPE判断
        Member mem = getMemberByIdFromDB();
        if (mem != null) {
            System.out.println(mem.getName());
        }
    }
}

class WithOptionalDemo {
    public static Optional<Member> getMemberByIdFromDB(boolean hasName) {
        if (hasName) {
            return Optional.of(new Member("noob"));
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        // 通过Optional处理NPE
        Optional<Member> member = getMemberByIdFromDB(false);
        member.ifPresent((m) -> {
            System.out.println("会员姓名：" + m.getName());
        });
    }
}


public class OptionalDemo {

    public static void main(String[] args) {
        // 1.使用静态方法 empty() 创建一个空的 Optional 对象
        Optional<String> emptyObj = Optional.empty();
        System.out.println(emptyObj);
        // 2.使用静态方法 of() 创建一个非空的 Optional 对象
        Optional<Member> member = Optional.of(new Member("noob"));
        System.out.println(member);
        // 如果使用of()方法，传递的参数必须非空（如果传递参数为null还是会抛出NPE）
        // Optional<Member> nullMember = Optional.of(null);// 错误-抛出NPE
        // 3.使用ofNullable() 创建一个即可空又可非空的 Optional 对象
        String name = null;
        Optional<String> optOrNull = Optional.ofNullable(name);
        System.out.println(optOrNull); // 输出：Optional.empty


        // 判断值是否存在
        Optional<String> opt = Optional.of("noob");
        System.out.println(opt.isPresent()); // 输出：true
        Optional<String> optOrNullObj = Optional.ofNullable(null);
        System.out.println(optOrNullObj.isPresent()); // 输出：false

    }

}
