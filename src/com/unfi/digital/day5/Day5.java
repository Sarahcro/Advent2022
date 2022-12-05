package com.unfi.digital.day5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Day5 {


  public static void part1(String[] args) throws IOException {
    var init = Day5.class.getResourceAsStream("/com/unfi/digital/day5/init.txt");
    var inputInit = new String(init.readAllBytes());
    var layers = inputInit.split("\r\n");
    var stacks = initializeStacks();
    populateStacks(stacks, layers);
    var in = Day5.class.getResourceAsStream("/com/unfi/digital/day5/input.txt");
    var input = new String(in.readAllBytes());
    var movements = input.split("\r\n");
    for (String movement : movements) {
      var words = movement.split(" ");
      var quantity = Integer.parseInt(words[1]);
      var fromStack = stacks.get(Integer.parseInt(words[3]) - 1);
      var toStack = stacks.get(Integer.parseInt(words[5]) - 1);
      for (int i = 0; i < quantity; i++) {
        if (!fromStack.empty()) {
          toStack.push(fromStack.pop());
        }
      }
    }
    for (Stack<String> stack : stacks) {
      System.out.println(stack.pop());
    }
  }

  //part2
  public static void main(String[] args) throws IOException {
    var init = Day5.class.getResourceAsStream("/com/unfi/digital/day5/init.txt");
    var inputInit = new String(init.readAllBytes());
    var layers = inputInit.split("\r\n");
    var stacks = initializeStacks();
    populateStacks(stacks, layers);
    var in = Day5.class.getResourceAsStream("/com/unfi/digital/day5/input.txt");
    var input = new String(in.readAllBytes());
    var movements = input.split("\r\n");
    for (String movement : movements) {
      var words = movement.split(" ");
      var quantity = Integer.parseInt(words[1]);
      var fromStack = stacks.get(Integer.parseInt(words[3]) - 1);
      var toStack = stacks.get(Integer.parseInt(words[5]) - 1);
      var extraStack = new Stack<String>();
      for (int i = 0; i < quantity; i++) {
        if (!fromStack.empty()) {
          extraStack.push(fromStack.pop());
        }
      }
      while (!extraStack.isEmpty()){
        toStack.push(extraStack.pop());
      }
    }
    for (Stack<String> stack : stacks) {
      System.out.println(stack.pop());
    }
  }

  private static List<Stack<String>> initializeStacks() {
    List<Stack<String>> list = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      list.add(new Stack<String>());
    }
    return list;
  }

  private static void populateStacks(List<Stack<String>> stacks, String[] layers) {
    for (String layer : layers) {
      var crates = layer.split(" ");
      for (int i = 0; i < 9; i++) {
        if (!Objects.equals(crates[i], "[*]")) {
          stacks.get(i).add(0, crates[i]);
        }
      }
    }
  }

}
