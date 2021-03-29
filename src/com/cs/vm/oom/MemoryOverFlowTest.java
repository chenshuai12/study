package com.cs.vm.oom;

import java.util.ArrayList;
import java.util.List;

public class MemoryOverFlowTest {
    public static void main(String[] args) {
       MemoryOverFlowTest test = new MemoryOverFlowTest();
       test.oom();
    }
    private void oom(){
        List list = new ArrayList();
        while (true){
            list.add(new MemoryOverflow());
        }
    }
}
