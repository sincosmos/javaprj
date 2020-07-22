package com.sincosmos.effectivejava.chapter7;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SubLists {
    public Stream<List<Integer>> of(List<Integer> list){
        return Stream.concat(Stream.of(Collections.emptyList()),
                prefixes(list).flatMap(x-> suffixes(x)));
    }

    private Stream<List<Integer>> prefixes(List<Integer> list) {
        return IntStream.rangeClosed(1, list.size())
                .mapToObj(end -> list.subList(0, end));
    }

    private Stream<List<Integer>> suffixes(List<Integer> pre) {
        return IntStream.range(0, pre.size())
                .mapToObj(start -> pre.subList(start, pre.size()));
    }

    public static void main(String[] args){
        List<Integer> a = Arrays.asList(1,2,3,4);
        SubLists subLists = new SubLists();
        subLists.of(a).forEach(System.out::println);
    }
}
