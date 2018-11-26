package core;

public class TodoItem {
    private String text;
    public TodoItem(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TodoItem(String text) {
        this.text = text;
    }
}
