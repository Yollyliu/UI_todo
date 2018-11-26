import UI.MainView;
import com.sun.org.apache.regexp.internal.RE;
import com.zzpublic.zwing.ViewFlow;
import com.zzpublic.zwing.Window;
import core.Repository;
import core.TodoList;
import persistence.Persistence;
import persistence.PersistenceJson;
import persistence.PersistenceText;

import javax.swing.text.View;

public class Driver {
    public static void main(String[] args) {
//        Repository.todoList.setTitle("Learning Plan");
//        Repository.buildItems();

        Persistence persistence=new PersistenceJson();
        TodoList todoList=persistence.read();
        if(todoList==null){
            todoList=new TodoList();
            todoList.setTitle("todo");
        }
        Repository.todoList=todoList;

        MainView view=new MainView();
        ViewFlow viewFlow=new ViewFlow();
        viewFlow.push(view);
        Window window=new Window(viewFlow);
        window.setVisible(true);
        window.setResizable(true);

    }
}
