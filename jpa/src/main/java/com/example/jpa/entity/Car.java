package com.example.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "car")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "model")
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id")
    @JsonBackReference
    private Person person;


    public static void main(String[] args) {

        String a = "zadb fdsfmdsfmfdsafsdfjhytuklqa";
        char[] chars = a.toCharArray();
        Queue<Character> que1 = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o2 - o1;
            }
        });
        for(int i = 0; i < chars.length; i++) {
            que1.add(chars[i]);
        }
        System.out.println(que1);
        while (que1.size() > 0) {
            System.out.println(que1.poll());
        }

    }
}
