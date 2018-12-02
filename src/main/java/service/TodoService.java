package service;

import core.Repository;
import core.TodoItem;
import core.TodoList;
import persistence.Persistence;
import persistence.PersistenceJson;

public class TodoService {


    public TodoList get() {
        return Repository.todoList;
    }

    public void add(TodoItem item) {
        Repository.todoList.add(item);
        PersistenceJson persistenceJson = new PersistenceJson();
        persistenceJson.save(Repository.todoList);
    }


//    public TodoList get(){
//        return null;
//    }
//
//    public void add(TodoItem item){
//
//        Repository.todoList.add(item);
//        PersistenceJson persistenceJson=new PersistenceJson();
//        persistenceJson.save(Repository.todoList);
//    }
}
