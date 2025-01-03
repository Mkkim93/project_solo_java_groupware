package com.group.domain.todo.repository;


import com.group.domain.todo.entity.Todo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TodoRepository extends JpaRepository<Todo, Integer> {

    Todo getTodoById(Integer id);

    @Modifying
    @Query("update Todo set todoDeleted = 'Y' where id = :todoId")
    Integer deleteTodo(@Param("todoId") Integer todoId);
}
