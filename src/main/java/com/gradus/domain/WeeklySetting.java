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
@TypeAlias("weekly_setting")
@Document(collection="weekly_setting")
public class WeeklySetting {

    @Id
    @Field
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId id;

    @Field
    private Boolean isMonday = true;

    @Field
    private Boolean isTuesday = true;

    @Field
    private Boolean isWednesday = true;

    @Field
    private Boolean isThursday = true;

    @Field
    private Boolean isFriday = true;

    @Field
    private Boolean isSaturday = true;

    @Field
    private Boolean isSunday = true;

}
