package com.aim.service.Impl;

import com.aim.entity.UplodFile;
import com.aim.service.FindService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.List;
import java.util.Map;

@Service
public class FindServiceImpl implements FindService {
    @Autowired
    private SolrTemplate solrTemplate;
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
        map.putAll(search1(searchMap));
        return map;
    }

    private Map<String ,Object> search1(Map searchMap) {
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        SimpleHighlightQuery query = new SimpleHighlightQuery(criteria);
        Object pageNo = searchMap.get("pageNo");
        Object pageSize = searchMap.get("pageSize");
        query.setOffset((Integer.parseInt(String.valueOf(pageNo)) - 1) * Integer.parseInt(String.valueOf(pageSize)));
        query.setRows(Integer.parseInt(String.valueOf(pageSize)));
        HighlightOptions item_title = new HighlightOptions().addField("item_title");
        item_title.setSimplePrefix("<em style='color:red'>");
        item_title.setSimplePostfix("</em>");
        query.setHighlightOptions(item_title);
        HighlightPage<UplodFile> items = solrTemplate.queryForHighlightPage(query, UplodFile.class);
        List<HighlightEntry<UplodFile>> highlighted = items.getHighlighted();
        for (HighlightEntry<UplodFile> entry : highlighted) {
            UplodFile entity = entry.getEntity();
            if (null != entry.getHighlights() && entry.getHighlights().size() > 0) {
                String s = entry.getHighlights().get(0).getSnipplets().get(0);
                entity.setTitle(s);

            }
        }

        HashMap<String, Object> map = new HashMap<>();

        map.put("rows", items.getContent());
        map.put("total", items.getTotalElements());
        map.put("totalPages", items.getTotalPages());
        return map;

    }
}
