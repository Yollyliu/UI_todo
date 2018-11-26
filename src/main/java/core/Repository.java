package core;

public class Repository {

    public static TodoList todoList=new TodoList();

    public static void buildItems() {
        todoList.setTitle("Todo");
        todoList.add(new TodoItem("HTML"));
        todoList.add(new TodoItem("Git"));
        todoList.add(new TodoItem("ZZ"));

    }
}
