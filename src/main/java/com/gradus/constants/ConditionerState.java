package com.gradus.constants;

import lombok.Getter;

@Getter
public enum ConditionerState {
    On(0), Off(12);
    private Integer value;

    ConditionerState(Integer value) {
        this.value = value;
    }
}
