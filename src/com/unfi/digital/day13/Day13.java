package com.unfi.digital.day13;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day13 {

  public static void main(String[] args) throws IOException {
    var in = Day13.class.getResourceAsStream("/com/unfi/digital/day13/input.txt");
    var pairs = new String(in.readAllBytes()).split("\r\n");
    var sorted = Arrays.stream(pairs).filter(s -> !s.isBlank()).map(Packet::new).sorted().map(packet -> String.valueOf(packet.chars)).collect(
        Collectors.toList());
    System.out.println((sorted.indexOf("[[2]]") + 1 ) * (sorted.indexOf("[[6]]") + 1));

  }

}
