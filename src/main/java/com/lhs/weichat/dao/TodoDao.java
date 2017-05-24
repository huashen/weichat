package com.lhs.weichat.dao;

import com.lhs.weichat.bean.Todo;

import java.util.List;

/**
 * TodoDao
 *
 * @author longhuashen
 * @since 15/10/1
 */
public interface TodoDao {


    Todo saveTodo(Todo todo);

    void updateTodo(Todo todo);

    Todo getTodoById(int todoId);

    List<Todo> getAllTodoByToId(int userId, int fid);
}
