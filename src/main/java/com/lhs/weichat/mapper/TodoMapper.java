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

    int deleteByPrimaryKey(Integer id);

    int insert(Todo record);

    int insertSelective(Todo record);

    Todo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Todo record);

    int updateByPrimaryKey(Todo record);

    Todo saveTodo(Todo todo);

    void updateTodo(Todo todo);

    Todo getTodoById(int todoId);

    List<Todo> getAllTodoByToId(int userId, int fid);

    List<Todo> getUnCompleteTodoByUserId(int userId);
}
