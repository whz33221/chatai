package com.example.chatai.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/4/9 9:31
 * @description
 */
@Getter
@Setter
@Accessors(chain = true)
public class SplitUtil {


    public static <T> List<List<T>> doSplitTasks(List<T> objs,int batchCount) {
        if(objs==null||objs.size()<=0){
            return new ArrayList<>(0);
        }
        if (batchCount == 0) {
            throw new IllegalArgumentException("batchCount cannot be zero");
        }
        return IntStream.range(0, objs.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / batchCount))
                .values()
                .stream()
                .map(group -> group.stream().map(objs::get).toList())
                .toList();
    }
}
