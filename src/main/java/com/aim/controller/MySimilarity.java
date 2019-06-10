package com.aim.controller;

import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.Similarity;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
public class MySimilarity extends DefaultSimilarity {

    /**
     * freq 表示 term 在一个document的出现次数,这里设置为1.0f表示不考滤这个因素影响
     * */
    @Override
    public float tf(float freq) {
        return 1.0F;
    }

    /**
     * 这里表示匹配的docuemnt在全部document的影响因素,同理也不考滤
     * */
    @Override
    public float idf(long docFreq, long numDocs) {
        return 1.0F;
    }

    @Override
    public float sloppyFreq(int distance) {
        return 1.0F;
    }

    @Override
    public float queryNorm(float sumOfSquaredWeights) {
        return 1.0F;
    }


    /**
     * 这里表示每一个Document中所有匹配的关键字与当前关键字的匹配比例因素影响,同理也不考滤
     * */
    @Override
    public float coord(int overlap, int maxOverlap) {
        return 1.0F;
    }

    @Override
    public float lengthNorm(FieldInvertState state) {
        return 1.0F;
    }

    protected boolean discountOverlaps = false;

    public void setDiscountOverlaps(boolean v) {
        discountOverlaps = v;
    }

    public boolean getDiscountOverlaps() {
        return discountOverlaps;
    }

    public String toString(){
        return "MySimilarity";
    }

}

