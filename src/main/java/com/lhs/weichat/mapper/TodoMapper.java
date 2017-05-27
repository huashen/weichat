package com.lhs.weichat.mapper;

import com.lhs.weichat.bean.Todo;

import java.util.List;

/**
 * TodoMapper
 *
 * @author longhuashen
 * @since 15/10/1
 */
public interface TodoMapper {


    Todo saveTodo(Todo todo);

    void updateTodo(Todo todo);

    Todo getTodoById(int todoId);

    List<Todo> getAllTodoByToId(int userId, int fid);
}
