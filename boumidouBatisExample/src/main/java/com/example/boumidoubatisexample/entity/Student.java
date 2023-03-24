package com.example.boumidoubatisexample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer idStudent;
    private String name;
    private int age;
    private int sex;

}
