package com.exadel.amc.youtube.data;

public abstract class AbstractItem<S extends AbstractStatus, T extends AbstractStatistics, P extends AbstractSnippet> {

    private String kind;
    private String etag;
    private String id;

    private S status;
    private T statistics;
    private P snippet;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public S getStatus() {
        return status;
    }

    public void setStatus(S status) {
        this.status = status;
    }

    public T getStatistics() {
        return statistics;
    }

    public void setStatistics(T statistics) {
        this.statistics = statistics;
    }

    public P getSnippet() {
        return snippet;
    }

    public void setSnippet(P snippet) {
        this.snippet = snippet;
    }

}
