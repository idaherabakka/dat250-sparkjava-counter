package no.hvl.dat250.rest.todos;

import java.util.Collection;

public interface TodoService {
    public void addTodo(Todo employee);
    public Collection<Todo> getTodos();
    public Todo getTodo(String id);
    //suppose to have?
    public Todo editTodo(Todo employee);
    public void deleteTodo(String id);
    public boolean todoExist(String id);



}
