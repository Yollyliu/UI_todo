package persistence;

import core.TodoItem;
import core.TodoList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PersistenceText implements Persistence {
    @Override
    public void save(TodoList todoList) {

        //sequence; making object to linear structure
        StringBuilder builder = new StringBuilder();
        builder.append(todoList.getTitle() + "\n");

        List<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            builder.append(item.getText() + "\n");
        }

//        System.out.println(builder.toString());

        //store: 把函数写到磁盘上！！！通用
        File file=new File("data/todo.txt");

        try {
            FileUtils.writeStringToFile(file, builder.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }




//            JsonObject listJson = new JsonObject();
//        listJson.addProperty("title", todoList.getTitle());
//
//        JsonArray itemsJson = new JsonArray();
//        listJson.add("items", itemsJson);
//
//        List<TodoItem> items = todoList.getItems();
//        for (TodoItem item : items) {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("text", item.getText());
//            itemsJson.add(jsonObject);
//        }
//
//        String jsonString = new Gson().toJson(listJson);
//        // apache write to file...




    }

    @Override
    public TodoList read() {
//        return null;

        //先指向文件
        File file = new File("data/todo.txt");// new Path
        if(!file.exists()){
            return null;
        }

        String data = "";
        try {
            data = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 然乎在读取文件里面的string
        String[] parts = data.split("\n");
        String title = parts[0];
        TodoList todoList = new TodoList();
        todoList.setTitle(title);

        for (int i = 1; i < parts.length; i++) {
            String todoText = parts[i];
            TodoItem todoItem = new TodoItem();
            todoItem.setText(todoText);
            todoList.add(todoItem);
        }

        return todoList;
    }

}


