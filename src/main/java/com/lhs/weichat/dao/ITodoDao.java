package com.lhs.weichat.dao;

import com.lhs.weichat.bean.Todo;

import java.util.List;

/**
 * ITodoDao
 *
 * @author longhuashen
 * @since 15/10/1
 */
public interface ITodoDao {


    Todo saveTodo(Todo todo);

    void updateTodo(Todo todo);

    Todo getTodoById(int todoId);

    List<Todo> getAllTodoByToId(int userId, int fid);
}
