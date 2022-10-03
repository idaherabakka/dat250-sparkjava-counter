package no.hvl.dat250.rest.todos;

import java.util.ArrayList;
import java.util.Collection;

public class TodoServiceImpl implements TodoService{

    Collection<Todo> todoMap = new ArrayList<>();

    @Override
    public void addTodo(Todo todo) {
        todoMap.add(todo);
    }

    @Override
    public Collection<Todo> getTodos() {
        return todoMap;
    }

    @Override
    public Todo getTodo(Long id) {
        for(Todo item: todoMap){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }

    @Override
    public Todo editTodo(Todo todo) {
        Todo updated = getTodo(todo.getId());
        deleteTodo(todo.getId());
        updated.setDescription(todo.getDescription());
        updated.setSummary(todo.getSummary());
        addTodo(updated);

        return updated;
    }

    @Override
    public void deleteTodo(Long id) {
        todoMap.remove(getTodo(id));

    }

    @Override
    public boolean todoExist(Long id) {
        if(getTodo(id)!= null){
            return true;
        }
        return false;
    }
}
