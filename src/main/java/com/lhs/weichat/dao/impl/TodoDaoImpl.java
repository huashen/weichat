package com.lhs.weichat.dao.impl;

import com.lhs.weichat.bean.Todo;
import com.lhs.weichat.dao.BaseDao;
import com.lhs.weichat.dao.ITodoDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TodoDao
 *
 * @author longhuashen
 * @since 15/10/1
 */
@Repository
public class TodoDaoImpl extends BaseDao implements ITodoDao {

    @Override
    public Todo saveTodo(Todo todo) {
        Integer id = (Integer)this.save(todo);
        todo.setId(id);
        return todo;
    }

    @Override
    public void updateTodo(Todo todo) {
        this.update(todo);
    }

    @Override
    public Todo getTodoById(int todoId) {
        String hql = "from Todo where id =?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, todoId);
        return (Todo) query.uniqueResult();
    }

    @Override
    public List<Todo> getAllTodoByToId(int userId, int fid) {
        String hql = "from Todo where userId =? and complete =? and id=?";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, false);
        query.setParameter(2, fid);
        return query.list();
    }
}
