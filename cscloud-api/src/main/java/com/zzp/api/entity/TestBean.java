package com.zzp.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @ClassName TestBean
 * @Description
 * @Date
 * @Author Administrator
 **/
@Document(indexName = "testdoct", type = "testbean")
@Data
public class TestBean implements Serializable {

    // 必须指定一个id，
    @Id
    private long id;
    // 这里配置了分词器，字段类型，可以不配置，默认也可
    @Field(analyzer = "ik_smart")
    private String name;
    private Integer age;
    @Field(analyzer = "ik_smart")
    private String sex;
    @Field(analyzer = "ik_smart")
    private String desc;

}
