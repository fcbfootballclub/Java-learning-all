package org.example.Collections;

import java.util.*;
import java.util.stream.Collectors;

public class Bai8 {
    public static void main(String[] args) {
        // Creae a list and add some colors to the list
//        List<String> list_Strings = new ArrayList<String>();
//        list_Strings.add("Red");
//        list_Strings.add("Green");
//        list_Strings.add("Orange");
//        list_Strings.add("White");
//        list_Strings.add("Black");
//        System.out.println("List before sort: "+list_Strings);
//        list_Strings.sort((o1, o2) -> o2.compareTo(o1));
//        System.out.println("List after sort: "+list_Strings);
//
//        System.out.println("-----------------");
//        List<List<Object>> list = new ArrayList<>();
//        List<Object> list1 = new ArrayList<>(Arrays.asList(1, "Dao", "dinh"));
//        List<Object> list2 = new ArrayList<>(Arrays.asList(2, "Minh", "Phjuong"));
//
//        list1.forEach(System.out::println);
//        list2.forEach(System.out::println);
//        list.add(list1);
//        list.add(list2);
//        list.forEach(System.out::println);
        getListString("[1,{\"id\":\"Z^ft\",\"arg\":\"sin(pi)\"},{\"id\":\"Ryft\",\"arg\":\"pi\"},1,{\"id\":\"Rzft\",\"arg\":\"pi\"},\"Y\",1,{\"id\":\"Rxft\",\"arg\":\"pi\"}]");
    }

    public static List<List<Objects>> getJsonList(String json) {
        String json2 = "{\"cols\":[[1,{\"id\":\"Z^ft\",\"arg\":\"sin(pi)\"}]]}";
        String json3 = json2.replace("\"", "");
        String json4 = json3.substring(7, json3.length() - 2);

        List<String> list = List.of(json4.split("],|]"));
        List<String> list2 = list.stream().map(item -> item.replace("[", "")).collect(Collectors.toList());
        List<List<String>> demo = new ArrayList<>();
        for(String str : list2) {
            if(!str.contains("arg")) {
                List<String> col = List.of(str.split(","));
                demo.add(col);
            } else {
                demo.add(Collections.singletonList(str));
            }
        }
        System.out.println("demo request: " + demo.get(0));
        getJsonRequest(demo);
        return null;
    }

    public static void getJsonRequest(List<List<String>> list) {
        String prefix = "{\"cols\":[";
        String endfix = "]";

        for (List<String> strings : list) {
            StringBuilder url = new StringBuilder("[");
            for (int j = 0; j < strings.size(); j++) {
                if(!strings.get(j).equals("1")) {
                    url.append("\"");
                    url.append(strings.get(j));
                    url.append("\"");
                }
                else {
                    url.append(strings.get(j));
                }
                if(j < strings.size() - 1) {
                    url.append(", ");
                } else {
                    url.append("]");
                }
            }
            System.out.println(prefix + url + endfix);
        }

    }

    public static List<String> getListString(String input) {
        List<String> tokens = new ArrayList<>();
        int startPosition = 0;
        boolean isInQuotes = false;
        for (int currentPosition = 0; currentPosition < input.length(); currentPosition++) {
            if (input.charAt(currentPosition) == '{' || input.charAt(currentPosition) == '}') {
                isInQuotes = !isInQuotes;
            }
            else if (input.charAt(currentPosition) == ',' && !isInQuotes) {
                tokens.add(input.substring(startPosition, currentPosition));
                startPosition = currentPosition + 1;
            }
        }

        String lastToken = input.substring(startPosition);
        if (lastToken.equals(",")) {
            tokens.add("");
        } else {
            tokens.add(lastToken);
        }
        System.out.println(tokens);
        System.out.println(tokens.size());
        tokens.forEach(System.out::println);
        return tokens;
    }
}
