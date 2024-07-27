package com.noob.base.model.entity.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Father {
    private String name;
    private int age;
    private String fatherDesc;
}
