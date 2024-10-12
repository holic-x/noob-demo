package com.noob.design.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 适配器模式
 * 【案例】例如检索场景需要接入不同的数据源进行检索
 *  假设有知识库文章来源、锦囊文章来源
 */
public class AdapterDemo1 {

    public static void main(String[] args) {
        // 定义适配器
        DataSourceAdapter adapter = new DataSourceAdapter();

        // 通过适配器，传入指定参数限定调用规则
        Map<String,String> sp1 = new HashMap<>();
        sp1.put("keyword","hahah");
        adapter.doSearch("ZSQ",sp1);

        Map<String,String> sp2 = new HashMap<>();
        sp2.put("keyword","hahah");
        sp2.put("otherParam","xxxxx");
        adapter.doSearch("JinNang",sp2);
    }

}

// 适配目标：即要进行适配的接口参考
// 检索来源：知识库检索
class KnowledgeDataSource{
    public Map<String,Object> search(String keyword){
        System.out.println("知识库文章检索ING" + keyword);
        return null;
    }
}
// 检索来源：锦囊
class JinNangDataSource{
    public Map<String,Object> search(String keyword,String otherParam){
        System.out.println("锦囊文章检索ING" +   keyword + "- 辅助参数" + otherParam);
        return null;
    }
}

// 适配器
class DataSourceAdapter{
    // 定义统一的接口适配，然后调用相应的检索来源
    public  Map<String,Object> doSearch(String searchType,Map<String,String> searchParam){
        // 根据检索类型选定检索来源，此处定义了一个Map类型的searchParam(是参数适配的一种体现，统一了入参规范)
        if("ZSQ".equals(searchType)){
            return new KnowledgeDataSource().search(searchParam.get("keyword"));
        }else  if("JinNang".equals(searchType)){
            return new JinNangDataSource().search(searchParam.get("keyword"),searchParam.get("otherParam"));
        }else{
            return null;
        }
    }
}
