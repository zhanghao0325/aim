package com.aim.service.Impl;

import com.aim.dao.FileDao;
import com.aim.entity.File;
import com.aim.entity.UplodFile;
import com.aim.service.FindService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class FindServiceImpl implements FindService {
    @Autowired
    private SolrTemplate solrTemplate;
    @Autowired
    private FileDao  fileDao;
    @Override
    public List<UplodFile> find(String desc) {
        Criteria criteria =new Criteria("item_desc").is(desc);
        SimpleQuery simpleQuery = new SimpleQuery(criteria);
        simpleQuery.setOffset(0);
        simpleQuery.setRows(5);
        ScoredPage<UplodFile> files = solrTemplate.queryForPage(simpleQuery, UplodFile.class);
        System.out.println("总条数:"+files.getTotalElements());
        System.out.println("总页数:"+files.getTotalPages());
        List<UplodFile> content = files.getContent();
        return content;
    }

    @Override
    public PageInfo<UplodFile> find1(Integer pageSize, Integer pageNum, String desc) {
        PageHelper.startPage(pageNum,pageSize);
        Criteria criteria =new Criteria("item_desc").is(desc);
        SimpleQuery simpleQuery = new SimpleQuery(criteria);
        ScoredPage<UplodFile> files = solrTemplate.queryForPage(simpleQuery, UplodFile.class);
        List<UplodFile> content = files.getContent();
        PageInfo<UplodFile> productPageInfo = new PageInfo<>(content);
//        System.out.println(productPageInfo.getNavigateLastPage());
//        System.out.println(productPageInfo.getNavigateFirstPage());
        return productPageInfo;
    }

    @Override
    public Map<String, Object> search(Map searchMap) {
        String keywords = (String) searchMap.get("keywords");
        searchMap.put("keywords",keywords.replace(" ",""));
        HashMap<String, Object> map = new HashMap<>();
        try {
            map.putAll(search1(searchMap));
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return map;
    }

    private Map<String ,Object> search1(Map searchMap) throws SolrServerException {
        String url=new String("http://192.168.0.100:8983/solr/newcore/");
        HttpSolrServer httpSolrServer = new HttpSolrServer(url);
        SolrQuery solrQuery = new SolrQuery();
        String s2 = "\""+searchMap.get("keywords")+"\"~10";
        solrQuery.setQuery("item_desc:"+s2 );
        Object pageNo = searchMap.get("pageNo");
        Object pageSize = searchMap.get("pageSize");
        solrQuery.setStart((Integer.parseInt(String.valueOf(pageNo)) - 1) * Integer.parseInt(String.valueOf(pageSize)));
        solrQuery.setRows(Integer.parseInt(String.valueOf(pageSize)));
        boolean isHighlighting = !StringUtils.equals("*", s2) && StringUtils.isNotEmpty(s2);

        if (isHighlighting) {
            // 设置高亮
            solrQuery.setHighlight(true); // 开启高亮组件
            solrQuery.addHighlightField("item_desc");// 高亮字段
            solrQuery.setHighlightSimplePre("<em style='color:red'>");// 标记，高亮关键字前缀
            solrQuery.setHighlightSimplePost("</em>");// 后缀
        }
        solrQuery.setHighlightFragsize(1000000);
        QueryResponse queryResponse = httpSolrServer.query(solrQuery);
        List<UplodFile> foos = queryResponse.getBeans(UplodFile.class);
        if (isHighlighting) {
            // 将高亮的标题数据写回到数据对象中
            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
                for (UplodFile foo : foos) {
                    if (!highlighting.getKey().equals(foo.getId().toString())) {
                        continue;
                    }
                    foo.setDsc(StringUtils.join(highlighting.getValue().get("item_desc"), ""));

                    break;
                }
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", foos);
        map.put("total", foos.size());
        map.put("totalPages", foos.size()/Integer.parseInt(String.valueOf(pageSize)));
        return map;
    }

    private Map<String ,Object> search2(Map searchMap) {
//        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords")+"~0");
//        String str = searchMap.get("keywords")+"~10";
        String s1 = "\\"+"\""+ searchMap.get("keywords")+"\\"+"\""+"~10";
        String s2 = "\\"+"\""+ searchMap.get("keywords")+"\\"+"\""+"~10";
        String s3 = "\""+searchMap.get("keywords")+"\"~10";
        Criteria criteria = new Criteria("item_keywords").is(s3);
        SimpleHighlightQuery query = new SimpleHighlightQuery(criteria);
        Object pageNo = searchMap.get("pageNo");
        Object pageSize = searchMap.get("pageSize");
        query.setOffset((Integer.parseInt(String.valueOf(pageNo)) - 1) * Integer.parseInt(String.valueOf(pageSize)));
        query.setRows(Integer.parseInt(String.valueOf(pageSize)));
        HighlightOptions item_desc = new HighlightOptions().addField("item_desc");
        item_desc.setSimplePrefix("<em style='color:red'>");
        item_desc.setSimplePostfix("</em>");
        query.setHighlightOptions(item_desc);
        HighlightPage<UplodFile> items = solrTemplate.queryForHighlightPage(query, UplodFile.class);
        List<HighlightEntry<UplodFile>> highlighted = items.getHighlighted();
        for (HighlightEntry<UplodFile> entry : highlighted) {
            UplodFile entity = entry.getEntity();
            if (null != entry.getHighlights() && entry.getHighlights().size() > 0) {
                String s = entry.getHighlights().get(0).getSnipplets().get(0);
                entity.setDsc(s);

            }
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", items.getContent());
        map.put("total", items.getTotalElements());
        map.put("totalPages", items.getTotalPages());
        return map;

    }
}
