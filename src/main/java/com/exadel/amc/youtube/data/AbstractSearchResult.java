package com.exadel.amc.youtube.data;

import java.util.List;

public abstract class AbstractSearchResult<T extends AbstractItem<?, ?, ?>> {

    private String kind;
    private String etag;
    private PageInfo pageInfo;

    public abstract List<T> getItems();

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
