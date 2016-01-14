package com.lhs.weichat.service;

import com.lhs.weichat.bean.Todo;

import java.util.List;

/**
 * ITodoService
 *
 * @author longhuashen
 * @since 15/10/1
 */
public interface ITodoService {

    /**
     * 保存代办事项
     *
     * @param todo
     * @return
     */
    Todo saveTodo(Todo todo);

    /**
     * 更新用户代办事项
     *
     * @param todo
     */
    void updateTodo(Todo todo);

    /**
     * 根据id获取代办事项
     *
     * @param todoId
     * @return
     */
    Todo getTodoById(int todoId);

    /**
     * 处理代办事项
     *
     * @param todoId
     * @param message
     * @param argeeOrNot
     */
    void finishTodo(int todoId, String message, boolean argeeOrNot);

    /**
     * 获取用户所有的代办事项
     *
     * @param userId
     * @param fid
     * @return
     */
    List<Todo> getAllTodoByToId(int userId, int fid);
}
