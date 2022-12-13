package com.unfi.digital.day11;

import com.unfi.digital.day6.Day6;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import javax.script.ScriptException;

public class Day11 {

  private static Map<String, PriorityQueue<Long>> monks = new HashMap<>();
  private static Map<String, String> monksMath = new HashMap<>();
  private static Map<String, Integer> counter = new HashMap<>();

  public static void main(String[] args)
      throws IOException, ScriptException, NoSuchMethodException {
    var in = Day6.class.getResourceAsStream("/com/unfi/digital/day11/input.txt");
    var monkeys = new String(in.readAllBytes()).split("\r\n\r\n");

    for (String monkey : monkeys) {
      var parts = monkey.split("\r\n");
      PriorityQueue<Long> queue = new PriorityQueue<>();
      var startingItems = parts[1].split(" ");
      for (int i = 4; i < startingItems.length; i++) {
        Long num = Long.parseLong(startingItems[i].substring(0, 2));
        queue.add(num);
      }
      var math = parts[2].split("= ");
      monks.put(parts[0].substring(7, 8), queue);
      monksMath.put(parts[0].substring(7, 8), math[1]);
      counter.put(parts[0].substring(7, 8), 0);
    }
    for (int i = 0; i < 10000; i++) {
      for (String monkey : monkeys) {
        var parts = monkey.split("\r\n");
        var id = parts[0].substring(7, 8);
        var stuff = monks.get(id);
        while (!stuff.isEmpty()) {
            var item = stuff.poll();
            var divisor = Integer.parseInt(parts[3].split(" ")[5]);
            var newVal = math(item, monksMath.get(id), 9699690);
            counter.put(id, counter.get(id) + 1);
            if (monkeyTest(newVal, divisor)){
              monks.get(parts[4].split("monkey ")[1]).add(newVal);
            } else {
              monks.get(parts[5].split("monkey ")[1]).add(newVal);
            }
        }
      }
      System.out.println(i);
      System.out.println(counter);
    }
    System.out.println(counter);
  }

  private static boolean monkeyTest(Long number, Integer divisor) {
    return number % divisor == 0;
  }

  private static Long math(Long number, String math, Integer divisor) {
    var m = math.split(" ");
    Long val;
    if (Objects.equals(m[2], "old")) {
      val = number;
    } else {
      val = Long.parseLong(m[2]);
    }
    if (Objects.equals(m[1], "+")) {
      return ((number + val) % divisor);
    } else {
      return ((number * val) % divisor);
    }
  }
}
