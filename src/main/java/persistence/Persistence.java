package persistence;

import core.TodoList;

public interface Persistence {

    public void save(TodoList todoList);
    public TodoList read();

}