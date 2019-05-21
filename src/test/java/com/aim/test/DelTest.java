package com.aim.test;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;

import java.io.IOException;

public class DelTest {
    @Autowired
    private SolrTemplate solrTemplate;
    @Test
    public void del() throws IOException, SolrServerException {
        String url=new String("http://192.168.0.100:8983/solr/newcore/");
        HttpSolrServer httpSolrServer = new HttpSolrServer(url);
        UpdateResponse updateResponse = httpSolrServer.deleteByQuery("*:*");
        httpSolrServer.commit();
    }
}
