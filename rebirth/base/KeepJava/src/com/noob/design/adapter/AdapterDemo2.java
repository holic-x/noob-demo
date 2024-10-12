package com.noob.design.adapter;

/**
 * 适配器模式：
 * 进阶版 通过泛型接口优化代码结构
 */
public class AdapterDemo2 {
    public static void main(String[] args) {
        MyDataSourceAdapter adapter = new MyDataSourceAdapter();
        adapter.doSearch("text","didi");
        adapter.doSearch("picture","hahah");
    }
}


// 1.定义泛型接口，统一数据源接入规范
interface DataSource<T> {
    // T 表示检索结果，S 表示检索参数
    public T search(String searchText);
}

// 2.定义文本检索、图片检索两种方式
class TextDataSource implements DataSource<String> {
    @Override
    public String search(String searchText) {
        return "检索到的内容为" + searchText;
    }
}

// 此处可灵活定义检索和响应的参数
class Picture {
    String name;

    Picture(String name) {
        this.name = name;
    }
}

class PictureDataSource implements DataSource<Picture> {
    @Override
    public Picture search(String searchText) {
        Picture picture = new Picture(searchText);
        return picture;
    }
}


// 3.定义适配器，接入不同的数据源
class MyDataSourceAdapter {
    public void doSearch(String searchType, String searchText) {
        switch (searchType) {
            case "text":
                System.out.println(new TextDataSource().search(searchText));
                break;
            case "picture":
                System.out.println(new PictureDataSource().search(searchText));
                break;
            default:
                System.out.println("nono");
        }
    }
}

// 扩展新的数据源
class Video{
    String videoName;
    int videoSize;
    Video(String videoName, int videoSize) {
        this.videoName = videoName;
        this.videoSize = videoSize;
    }
}
class VideoDataSource implements DataSource<Video> {
    @Override
    public Video search(String searchText) {
        return new Video(searchText, 10);
    }
}

// 然后在适配器处补充新的数据源即可