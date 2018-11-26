package core;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.LinkedList;
import java.util.List;

public class TodoList {

    private String title;

    private List<TodoItem> items=new LinkedList <>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List <TodoItem> getItems() {
        return items;
    }
    public void add(TodoItem item){
        items.add(item);
    }

    public void delete(TodoItem item) {
        for(int i=0;i<items.size();i++){
            if(items.get(i)==item){
                items.remove(i);
            }
        }
    }
}


