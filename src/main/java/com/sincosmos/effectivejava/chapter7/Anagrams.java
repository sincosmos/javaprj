package com.sincosmos.effectivejava.chapter7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {
    public static void main(String[] args){
        String fileName = "test.txt";
        int min = 2;
        char a = 'a';

        try(Stream<String> lines =
                    Files.lines(Paths.get(Thread.currentThread()
                            .getContextClassLoader().getResource(fileName).toURI()))){
            lines.map(str -> str.split("\\s+"))
                    .flatMap(Arrays::stream)
                    //.flatMap(line -> Stream.of(line.split(("\\s+")))
                    .collect(Collectors.groupingBy(word -> alphabetize(word)))
                    .values().stream().filter(g -> g.size() >= min)
                    .forEach(System.out::println);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static String alphabetize(String word){
        char[] a = word.toCharArray();
        Arrays.sort(a);
        return String.valueOf(a);
    }
}
