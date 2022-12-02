package com.unfi.digital.day2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Day2 {

  private static final Map<String, Integer> mapPart1 = Map.of("A X", 4, "A Y", 8, "A Z", 3,
      "B X", 1, "B Y", 5, "B Z", 9,
      "C X", 7, "C Y", 2, "C Z", 6);

  private static final Map<String, Integer> mapPart2 = Map.of("A X", 3, "A Y", 4, "A Z", 8,
      "B X", 1, "B Y", 5, "B Z", 9,
      "C X", 2, "C Y", 6, "C Z", 7);

  public static void main(String[] args) throws IOException {
    var in = Day2.class.getResourceAsStream("/com/unfi/digital/day2/input.txt");
    var input = new String(in.readAllBytes());
    var rounds = input.split("\r\n");
    var value = Arrays.stream(rounds).map(mapPart2::get).reduce(Integer::sum);
    System.out.println(value);
  }

}
