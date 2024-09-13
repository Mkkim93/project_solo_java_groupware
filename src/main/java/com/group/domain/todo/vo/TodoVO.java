package com.group.domain.todo.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoVO {

    private Integer id;
    private String todoType;
    private String todoTitle;
    private String todoContent;
    private LocalDateTime todoDueDate;
    private String todoStatus;
    private LocalDateTime todoCreate;
    private LocalDateTime todoUpdate;
    private Integer empId;
}
