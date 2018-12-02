package UI;

import com.zzpublic.zwing.Button;
import com.zzpublic.zwing.Label;
import com.zzpublic.zwing.TextField;
import com.zzpublic.zwing.View;
import core.Repository;
import core.TodoItem;
import core.TodoList;
import persistence.Persistence;
import persistence.PersistenceJson;
import persistence.PersistenceText;
import service.TodoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainView extends View {


    private TodoList list;

    private Persistence persistence = new PersistenceJson();
    private TodoService service;
    private final static int paddingNormal = 10;
    private final static int cellHeight = 30;
    private final static int panelHeight = 40;
    private final static int paddingSmall = 5;


    private Label titleLable;
    private LinkedList <Label> itemLabels;
    private View containerView;

    private Button addButton;

    private TextField textField;


    @Override
    protected void init() {
        super.init();
        list = Repository.todoList;
        itemLabels = new LinkedList <>();
        service=new TodoService();
        list=service.get();
    }

    @Override
    protected void initSubviews() {
        super.initSubviews();

        titleLable = new Label();
        titleLable.setLocation(paddingNormal, paddingNormal);
        titleLable.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);

        titleLable.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLable);

////        View inputView=new View();
//        containerView =new View();
//        containerView.setLocation(0,titleLable.getY()+titleLable.getHeight()+paddingNormal);
//        containerView.setSize(this.getWidth(),this.getHeight()-containerView.getY()-paddingNormal-panelHeight);
//        this.add(containerView);
//
//
//        int y = 0;
//        for (TodoItem todoItem : list.getItems()) {
//            Label label = new Label();
//            label.setText(todoItem.getText());
//            label.setLocation(paddingNormal, y);
//            label.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);
//            containerView.add(label);
//            //this.add(label);
//            //itemLabels.add(label);
//            y += label.getHeight() + paddingNormal;
//        }


        View inputView = new View();
        inputView.setSize(this.getWidth(), panelHeight);
        inputView.setLocation(0, this.getHeight() - inputView.getHeight() - 100);
        this.add(inputView);

        addButton = new Button("add");
        addButton.setSize(100, cellHeight);
        addButton.setLocation(this.getWidth() - paddingSmall - addButton.getWidth(), paddingSmall);
        inputView.add(addButton);

//        deleteButton = new Button("delete");
//        deleteButton.setSize(100, cellHeight);
//        deleteButton.setLocation(this.getWidth() - paddingSmall - addButton.getWidth()- addButton.getWidth(), paddingSmall);
//        inputView.add(deleteButton);

        textField = new TextField();
        textField.setSize(addButton.getX() - 2 * paddingSmall, cellHeight);
        textField.setLocation(paddingSmall, paddingSmall);
        inputView.add(textField);
    }

    protected void initEvents() {
        super.initEvents();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //update core
                String text = textField.getText();
                TodoItem todoItem = new TodoItem();
                todoItem.setText(text);
             //   list.add(todoItem);

                service.add(todoItem);


                MainView.this.remove(containerView);
                containerView = new View();
                containerView.setLocation(0, titleLable.getY() + titleLable.getHeight() + paddingNormal);
                containerView.setSize(MainView.this.getWidth(), MainView.this.getHeight() - containerView.getY() - paddingNormal - panelHeight);
                MainView.this.add(containerView);


                int y = 0;
                for (TodoItem item : list.getItems()) {
                    Label label = new Label();
                    label.setText(item.getText());
                    label.setLocation(paddingNormal, y);
                    label.setSize(MainView.this.getWidth() - 2 * paddingNormal, cellHeight);
                    containerView.add(label);
                    //this.add(label);
                    //itemLabels.add(label);
                    y += label.getHeight() + paddingNormal;
                }


//                for(Label label:itemLabels){
//                    MainView.this.remove(label);
//                }

                textField.setText("");
                persistence.save(list);


            }
        });

//
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                String text = textField.getText();
//                TodoItem todoItem = new TodoItem();
//                todoItem.setText(text);
//                list.delete(todoItem);
//                MainView.this.remove(containerView);
//
//
//                containerView = new View();
//                containerView.setLocation(0, titleLable.getY() + titleLable.getHeight() + paddingNormal);
//                containerView.setSize(MainView.this.getWidth(), MainView.this.getHeight() - containerView.getY() - paddingNormal - panelHeight);
//                MainView.this.add(containerView);
//
//                int y = 0;
//                for (TodoItem item : list.getItems()) {
//                    Label label = new Label();
//                    label.setText(item.getText());
//                    label.setLocation(paddingNormal, y);
//                    label.setSize(MainView.this.getWidth() - 2 * paddingNormal, cellHeight);
//                    containerView.add(label);
//                    //this.add(label);
//                    //itemLabels.add(label);
//                    y += label.getHeight() + paddingNormal;
//                }
//
//                textField.setText("");
//                persistence.save(list);
//
//
//            }
//        });
    }

    protected void viewDidDisplay(){
        super.viewDidDisplay();
        dataToView();
    }

    public void dataToView() {
        titleLable.setText(list.getTitle());
        if (containerView != null) {
            this.remove(containerView);
        }
        View inputView = new View();
        containerView = new View();
        containerView.setLocation(0, titleLable.getY() + titleLable.getHeight() + paddingNormal);
        containerView.setSize(this.getWidth(), this.getHeight() - containerView.getY() - paddingNormal - panelHeight);
        this.add(containerView);


        int y = 0;
        for (TodoItem todoItem : list.getItems()) {
            Label label = new Label();
            label.setText(todoItem.getText());
            label.setLocation(paddingNormal, y);
            label.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);
            containerView.add(label);
            //this.add(label);
            //itemLabels.add(label);
            y += label.getHeight() + paddingNormal;
        }


    }


}


//delete:
//删除
//
//        1. 删除 core 里的数据
//        2. 删除 界面上 所有 todo item 的 label
//        3. 重新 创建 todo item 的labe