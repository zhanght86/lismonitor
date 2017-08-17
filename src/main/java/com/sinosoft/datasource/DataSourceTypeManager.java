package com.sinosoft.datasource;

public class DataSourceTypeManager {

    private static final ThreadLocal<DataSources> DATA_SOURCES_THREAD_LOCAL = new ThreadLocal<DataSources>(){
        @Override
        protected DataSources initialValue(){
            return DataSources.LOCAL;
        }
    };

    public static DataSources get() {
        return DATA_SOURCES_THREAD_LOCAL.get();
    }

    public static void set(DataSources dataSources) {
        DATA_SOURCES_THREAD_LOCAL.set(dataSources);
    }

    public static void reset() {
        DATA_SOURCES_THREAD_LOCAL.set(DataSources.LOCAL);
    }
}
