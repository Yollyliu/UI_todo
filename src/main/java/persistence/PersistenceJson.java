package persistence;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import core.TodoItem;
import core.TodoList;
import org.apache.commons.io.FileUtils;
import persistence.Persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

    public class PersistenceJson implements Persistence {
        @Override
        public void save(TodoList todoList) {


            //第一行跟后面的所有，二选一就可以啦。

//            String toJson=new Gson().toJson(todoList);
            //下面为所有
//            JsonObject listJson = new JsonObject();  //the one to store all
//            listJson.addProperty("title", todoList.getTitle());
//
//            JsonArray itemsJson = new JsonArray();
//            listJson.add("items", itemsJson);
//
//            List<TodoItem> items = todoList.getItems();
//            for (TodoItem item : items) {
//                JsonObject jsonObject = new JsonObject();
//                jsonObject.addProperty("text", item.getText());
//                itemsJson.add(jsonObject);
//            }

            String jsonString = new Gson().toJson(todoList);
            // apache write to file...

            File file = new File("data/todo.json");
            try {
                FileUtils.writeStringToFile(file, jsonString, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public TodoList read() {
            // byte[] @ disk -> string @ memory
            File file = new File("data/todo.json");// new Path

            if (!file.exists()) {
                return null;
            }

            String data = "";
            try {
                data = FileUtils.readFileToString(file, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }


            // string -> object
//        JsonObject jsonObject = new Gson().fromJson(data, JsonObject.class);
//        TodoList todoList = new TodoList();
//        todoList.setTitle(jsonObject.get("title").getAsString());
//
//        JsonArray itemsJson = jsonObject.getAsJsonArray("items");
//
//        for (JsonElement itemJson : itemsJson) {
//            TodoItem item = new TodoItem();
//            String text = itemJson.getAsJsonObject().get("text").getAsString();
//            item.setText(text);
//            todoList.add(item);
//        }

            TodoList todoList = new Gson().fromJson(data, TodoList.class);

            return todoList;




        }
    }


