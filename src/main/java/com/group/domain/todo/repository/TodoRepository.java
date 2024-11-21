package com.group.domain.todo.repository;


import com.group.application.todo.dto.TodoDTO;
import com.group.domain.todo.entity.Todo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    Todo getTodoById(Integer id);
}
