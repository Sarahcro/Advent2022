package com.unfi.digital.day4;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day4 {


  public static void part1(String[] args) throws IOException {
    var in = Day4.class.getResourceAsStream("/com/unfi/digital/day4/input.txt");
    var input = new String(in.readAllBytes());
    var teams = input.split("\r\n");
    var value = Arrays.stream(teams).filter(t -> {
      var elves = t.split(",");
      var elf1 = elves[0].split("-");
      var elf2 = elves[1].split("-");
      return (Integer.parseInt(elf1[0]) >= Integer.parseInt(elf2[0])
          && Integer.parseInt(elf1[1]) <= Integer.parseInt(elf2[1])) ||
          (Integer.parseInt(elf2[0]) >= Integer.parseInt(elf1[0])
              && Integer.parseInt(elf2[1]) <= Integer.parseInt(elf1[1]));
    }).count();
    System.out.println(value);
  }

  public static void main(String[] args) throws IOException {
    var in = Day4.class.getResourceAsStream("/com/unfi/digital/day4/input.txt");
    var input = new String(in.readAllBytes());
    var teams = input.split("\r\n");
    var value = Arrays.stream(teams).filter(t -> {
      var elves = t.split(",");
      var elf1 = Arrays.stream(elves[0].split("-")).map(Integer::parseInt)
          .collect(Collectors.toList());
      var elf2 = Arrays.stream(elves[1].split("-")).map(Integer::parseInt)
          .collect(Collectors.toList());
      return (elf1.get(0) >= elf2.get(0) && elf1.get(0) <= elf2.get(1)) ||
          (elf1.get(1) >= elf2.get(0) && elf1.get(1) <= elf2.get(1)) ||
          (elf2.get(0) >= elf1.get(0) && elf2.get(0) <= elf1.get(1)) ||
          (elf2.get(1) >= elf1.get(0) && elf2.get(1) <= elf1.get(1));
    }).count();
    System.out.println(value);
  }

}
