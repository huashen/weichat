package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.Todo;
import com.lhs.weichat.dao.TodoDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TodoDao
 *
 * @author longhuashen
 * @since 15/10/1
 */
@Repository
public class TodoDaoImpl extends GenericDaoImpl implements TodoDao {

    @Override
    public Todo saveTodo(Todo todo) {
        return null;
    }

    @Override
    public void updateTodo(Todo todo) {

    }

    @Override
    public Todo getTodoById(int todoId) {
        return null;
    }

    @Override
    public List<Todo> getAllTodoByToId(int userId, int fid) {
        return null;
    }
}
