package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {

    static Todo todo = null;

    public static void main(String[] args) {

        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        after((req, res) -> res.type("application/json"));

        final TodoService todoService = new TodoServiceImpl();

       /* post("/todo", (request, response) -> {
            response.type("application/json");

            Todo todo = new Gson().fromJson
                    (request.body(), Todo.class);
            todoService.addTodo(todo);

        });*/


        // TODO: Implement API, such that the testcases succeed.

        //FIX: parameters!
        /*todo = new Todo(4L, "tt", "ksdm");

        get("/todo", (req, res) -> "Hello World!");

        get("/todo", (req, res) -> todo.toJson());

        get("/todo/red", (req, res) -> todo.getId());
*/
        //get("/counters/green", (req, res) -> todo.getDescription());

        //get("/counters/green", (req, res) -> todo.getSummary());

        // TODO: put for green/red and in JSON
        // variant that returns link/references to red and green counter
        /*put("/todo", (req,res) -> {

            Gson gson = new Gson();

            todo = gson.fromJson(req.body(), Todo.class);

            return todo.toJson();

        });*/

        //Create
        // read
        // update
        //delete

        get("/todo", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new Gson().toJsonTree(todoService.getTodos()));
        });

        get("/todo/:id", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(
                            new Gson().toJsonTree(todoService.getTodo
                                    (request.params(":id"))));
        });

        put("/todo/:id", (request, response) -> {
            response.type("application/json");

            Todo toEdit = new Gson().fromJson(request.body(),
                    Todo.class);
            Todo editedTodo = todoService.editTodo(toEdit);

            if (editedTodo != null) {
                return new Gson()
                        .toJson( new Gson().
                                        toJsonTree(editedTodo));
            } else {
                return new Gson().toJson(
                                new Gson().toJson
                                        ("Todo not found or error in edit"));
            }
        });

        delete("/todo/:id", (request, response) -> {
            response.type("application/json");

            todoService.deleteTodo(request.params(":id"));
            return new Gson().toJson( "todo deleted successfully");
        });
    }
}
