package com.exadel.amc.poc;

public interface Connector<D extends MetricsData> {

    D getMetricsData(String id) throws ConnectorException;

}
