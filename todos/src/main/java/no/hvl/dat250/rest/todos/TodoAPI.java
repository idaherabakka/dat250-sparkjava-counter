package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {


    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    static Todo todo = null;


    public static void main(String[] args) {

        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        after((req, res) -> res.type("application/json"));

        final TodoService todoService = new TodoServiceImpl();


        get("/todos", (request, response) -> {
            Gson gson = new Gson();
            return gson.toJson(todoService.getTodos());
        });

        get("/todos/:id", (request, response) -> {
            String paramId = request.params(":id");
            if (isNumeric(paramId)) {
                Long id = Long.parseLong(paramId);
                if (todoService.getTodo(id) != null) {
                    return new Gson().toJson(new Gson().toJsonTree(todoService.getTodo(id)));
                } else {
                    return String.format("Todo with the id  \"%s\" not found!", paramId);
                }
            } else {
                return String.format("The id \"%s\" is not a number!", paramId);
            }
        });


        post("/todos", (request, response) -> {
            Todo newTodo = new Gson().fromJson(request.body(), Todo.class);
            todoService.addTodo(newTodo);
            return new Gson().toJson(newTodo);
        });

        put("/todos/:id",((request, response) -> {
            String paramId = request.params(":id");
            if(!(isNumeric(paramId))){
                return String.format("The id \"%s\" is not a number!", paramId);
            }
            Todo originalTodo = new Gson().fromJson(request.body(), Todo.class);
            Todo updatedTodo = todoService.editTodo(originalTodo);
            if(updatedTodo !=null){
                return new Gson().toJson(updatedTodo);
            }else{
                return String.format("Todo with the id  \"%s\" not found!", paramId);
            }

        }));

        delete("/todos/:id",((request, response) -> {
            String paramId = request.params(":id");
            if(!(isNumeric(paramId))){
                return String.format("The id \"%s\" is not a number!", paramId);
            }
            Long id = Long.parseLong(paramId);
            if(!(todoService.todoExist(id))){
                return String.format("Todo with the id  \"%s\" not found!", paramId);
            }else{
                todoService.deleteTodo(id);
                return ("deleted");
            }}
        ));

    }
}
