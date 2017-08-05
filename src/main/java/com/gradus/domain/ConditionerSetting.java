package com.gradus.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gradus.constants.FanState;
import com.gradus.constants.Mode;
import com.gradus.deserializer.ObjectIdJsonSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@TypeAlias("conditioner_setting")
@Document(collection="conditioner_setting")
public class ConditionerSetting {

    @Id
    @Field
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId id;

    @Field
    private Integer unknown1;

    @Field
    private Integer unknown2;

    @Field
    private Boolean isOn = false;

    @Field
    private Mode mode;

    @Field
    private Integer temperature;

    @Field
    private FanState fanState;

}
