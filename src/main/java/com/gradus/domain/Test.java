package com.gradus.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gradus.deserializer.ObjectIdJsonSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@TypeAlias("test")
@Document(collection="test")
public class Test {

    @Id
    @Field
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId id;

    @Field
    private String name;
}
