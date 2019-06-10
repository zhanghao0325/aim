package com.aim.test;

import com.aim.entity.File;
import com.aim.entity.UplodFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class find {
        // 定义http的solr服务

    @Test
    public void find() throws SolrServerException {
        String url=new String("http://192.168.0.100:8983/solr/newcore/");
        HttpSolrServer httpSolrServer = new HttpSolrServer(url);
        SolrQuery solrQuery = new SolrQuery(); //构造搜索条件
        String s1 = "\\"+"\""+ "电气火灾"+"\\"+"\""+"~10";
        String s3 = "电气火灾";

        String s2 = "\""+s3+"\"~10";
        //solrQuery.setQuery("item_keywords:" +s1); //搜索关键词
        solrQuery.setQuery("item_desc:"+s2 ); //搜索关键词
        // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
//        solrQuery.setStart((Math.max(2, 1) - 1) * 5);
//        solrQuery.setRows(5);
//
//        //是否需要高亮
//        boolean isHighlighting = !StringUtils.equals("*", s1) && StringUtils.isNotEmpty(s1);
//
//        if (isHighlighting) {
//            // 设置高亮
//            solrQuery.setHighlight(true); // 开启高亮组件
//            solrQuery.addHighlightField("title");// 高亮字段
//            solrQuery.setHighlightSimplePre("<em>");// 标记，高亮关键字前缀
//            solrQuery.setHighlightSimplePost("</em>");// 后缀
//        }

        // 执行查询
        QueryResponse queryResponse = httpSolrServer.query(solrQuery);
        List<UplodFile> foos = queryResponse.getBeans(UplodFile.class);
        System.out.println(foos.size());
//        if (isHighlighting) {
//            // 将高亮的标题数据写回到数据对象中
//            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
//            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
//                for (UplodFile foo : foos) {
//                    if (!highlighting.getKey().equals(foo.getId().toString())) {
//                        continue;
//                    }
//                    foo.setTitle(StringUtils.join(highlighting.getValue().get("title"), ""));
//
//                    break;
//                }
//            }
//        }
        for (UplodFile foo : foos) {
            System.out.println(foo.getDsc());
        }

    }
}
