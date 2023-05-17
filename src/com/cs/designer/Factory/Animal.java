package com.cs.designer.Factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈帅
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Animal {
    private String name;
    public void behavior(){
        System.out.println("my name is " + name);
    }
}
