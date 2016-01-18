package com.lhs.weichat.service.impl;

import com.lhs.weichat.bean.Todo;
import com.lhs.weichat.dao.ITodoDao;
import com.lhs.weichat.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ToService
 *
 * @author longhuashen
 * @since 15/10/1
 */
//@Service
public class ToServiceImpl implements ITodoService {

    @Autowired
    private ITodoDao todoDao;

    @Override
    public Todo saveTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }

    @Override
    public Todo getTodoById(int todoId) {
        return todoDao.getTodoById(todoId);
    }

    @Override
    public void finishTodo(int todoId, String message, boolean argeeOrNot) {
        Todo todo = getTodoById(todoId);
        if (todo != null) {
            todo.setComplete(true);
            todo.setHandleDate(new Date());
            todo.setAgree(argeeOrNot);
            todoDao.updateTodo(todo);
        }
    }

    @Override
    public List<Todo> getAllTodoByToId(int userId, int fid) {
        return todoDao.getAllTodoByToId(userId, fid);
    }
}
