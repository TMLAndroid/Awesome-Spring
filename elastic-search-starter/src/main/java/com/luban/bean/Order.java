package com.luban.bean;

import org.elasticsearch.index.VersionType;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "order",type = "_doc",shards = 1,replicas = 0,createIndex = false,useServerConfiguration = true,versionType = VersionType.EXTERNAL)
public class Order {
    public Order() {
    }

    @Id
    private long order_id;

    @Field(type = FieldType.Date)
    private String order_date;

    @Field(type = FieldType.Text)
    private String buyer;

    @Version
    private Long version;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_date='" + order_date + '\'' +
                ", buyer='" + buyer + '\'' +
                ", version=" + version +
                '}';
    }
}
