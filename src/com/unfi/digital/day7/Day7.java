package com.unfi.digital.day7;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Day7 {

  public static class Node {

    String name;
    Long size;
    Node parent;
    Map<String, Node> children;

    public Node(String name, Long size, Node parent) {
      this.name = name;
      this.size = size;
      this.children = new HashMap<>();
      this.parent = parent;
    }

    public void addChild(String name, Long size) {
      this.children.put(name, new Node(name, size, this));
    }

    public Long getSize() {
      if (this.children.size() == 0) {
        return this.size;
      } else {
        return this.children.values().stream().map(Node::getSize).reduce(Long::sum).get();
      }
    }
  }

  public static void main(String[] args) throws IOException {
    var in = Day7.class.getResourceAsStream("/com/unfi/digital/day7/input.txt");
    var input = new String(in.readAllBytes());
    var commands = input.split("\r\n");
    Node start = new Node(null, null, null);
    start.addChild("/", 0L);
    Node current = start;
    for (String command : commands) {
      if (command.startsWith("$ cd ..")) {
        current = current.parent;
      } else if (command.startsWith("$ cd")) {
        var name = command.split(" ")[2];
        current = current.children.get(name);
      } else if (command.startsWith("dir")) {
        current.addChild(command.split(" ")[1], 0L);
      } else if (!command.startsWith("$")) {
        var split = command.split(" ");
        current.addChild(split[1], Long.parseLong(split[0]));
      }
    }
    var total = start.children.get("/").getSize();
    var currentFree = 70000000 - total;
    var target = 30000000 - currentFree;
    //System.out.println(collectValues(start));
    System.out.println(getFolderToDelete(start, target));
  }

  private static Long getFolderToDelete(Node start, Long target) {
    var c = start.children.values();
    for (Node curr : c) {
      var currSize = curr.getSize();
      if (currSize > target) {
        return Math.min(currSize, getFolderToDelete(curr, target));
      }
    }
    return Long.MAX_VALUE;
  }

  //part 1
  private static Long collectValues(Node start) {
    var c = start.children.values();
    var total = 0L;
    for (Node curr : c) {
      var currSize = curr.getSize();
      if (currSize < 100000 && curr.size == 0) {
        total += currSize;
      }
      total += collectValues(curr);
    }
    return total;
  }
}
