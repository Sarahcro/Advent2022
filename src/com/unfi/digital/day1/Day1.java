package com.unfi.digital.day1;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

  public static void main(String[] args) throws IOException {
    var in = Day1.class.getResourceAsStream("/com/unfi/digital/day1/input.txt");
    var input = new String(in.readAllBytes());
    var elves = input.split("\r\n\r\n");
    List<Integer> calories = Arrays.stream(elves).map(e -> Arrays.stream(e.split("\r\n")).map(Integer::parseInt).reduce(0,
        Integer::sum)).collect(
        Collectors.toList());
    var sorted = calories.stream().sorted(Comparator.reverseOrder()).toList();
    System.out.println(sorted.get(0) + sorted.get(1) + sorted.get(2));
  }

}
