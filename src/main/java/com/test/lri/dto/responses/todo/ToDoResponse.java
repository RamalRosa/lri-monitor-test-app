package com.test.lri.dto.responses.todo;

import lombok.Data;

@Data
public class ToDoResponse {
    private int UserId;
    private int id;
    private String title;
    private boolean completed;
}
