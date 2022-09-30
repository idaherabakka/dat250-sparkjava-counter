package no.hvl.dat250.rest.todos;

import java.util.ArrayList;
import java.util.Collection;

public class TodoServiceImpl implements TodoService {
    private Collection<Todo> todoMap;

    public TodoServiceImpl() {
        todoMap = new ArrayList<>();
    }

    @Override
    public void addTodo(Todo todo) {
        todoMap.add(todo);
    }

    @Override
    public Collection<Todo> getTodos() {
        return todoMap;
    }
    @Override
    public Todo getTodo(String id) {
        for(Todo item: todoMap){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }

    @Override
    public Todo editTodo(Todo forEdit) {
        try {
            if (forEdit.getId() == null)
                throw new Exception("ID cannot be blank");

            Todo toEdit = forEdit;

            if (toEdit == null)
                throw new Exception("Todo not found");

            //FIX skal ikkje kunna endras
            if (forEdit.getId() != null) {
                toEdit.setId(forEdit.getId());
            }
            if (forEdit.getDescription() != null) {
                toEdit.setDescription(forEdit.getDescription());
            }
            if (forEdit.getSummary() != null) {
                toEdit.setSummary(forEdit.getSummary());
            }

            return toEdit;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void deleteTodo(String id) {
        todoMap.remove(id);
    }

    @Override
    public boolean todoExist(String id) {
        if(getTodo(id)!= null){
            return true;
        }
        return false;
    }
}
