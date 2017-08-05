package com.gradus.constants;

import lombok.Getter;

@Getter
public enum Mode {
    Cooling(8), Degidration(9), Auto(11), Heating(12);
    private Integer value;

    Mode(Integer value) {
        this.value = value;
    }
}
