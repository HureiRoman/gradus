package com.gradus.constants;

import lombok.Getter;

@Getter
public enum FanState {
    Min(0), Middle(2), Max(4), Chaos(5);
    private Integer value;

    FanState(Integer value) {
        this.value = value;
    }
}
