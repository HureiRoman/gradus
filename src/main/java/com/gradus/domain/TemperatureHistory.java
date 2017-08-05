package com.gradus.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gradus.deserializer.ObjectIdJsonSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@TypeAlias("temperature_history")
@Document(collection="temperature_history")
public class TemperatureHistory {
    @Id
    @Field
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId id;

    @Field
    private Double temperature;

    @Field
    private Double humidity;

    @Field
    private Date date = new Date();
}
