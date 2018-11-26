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

            JsonObject listJson = new JsonObject();  //the one to store all
            listJson.addProperty("title", todoList.getTitle());

            JsonArray itemsJson = new JsonArray();
            listJson.add("items", itemsJson);

            List<TodoItem> items = todoList.getItems();
            for (TodoItem item : items) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("text", item.getText());
                itemsJson.add(jsonObject);
            }

            String jsonString = new Gson().toJson(listJson);
            // apache write to file...

        }

        @Override
        public TodoList read() {


            //第一行跟后面的所有，二选一就可以啦
           // TodoList todoList = new Gson().fromJson(data, TodoList.class);

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

            //string->object
            JsonObject jsonObject=new Gson().fromJson(data, JsonObject.class);
            TodoList todoList=new TodoList();
            todoList.setTitle(jsonObject.get("title").getAsString());



            JsonArray jsonArray=jsonObject.getAsJsonArray("items");

            for(JsonElement itemJson:jsonArray){
                TodoItem todoItem=new TodoItem();
                String text=itemJson.getAsJsonObject().get("text").getAsString();
                todoItem.setText(text);
                todoList.add(todoItem);
            }

            return todoList;

        }
    }


