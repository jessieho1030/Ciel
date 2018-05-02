package app.comps456f;

/**
 * Created by meiyuk on 1/5/2018.
 */


public class Comment {
    private String text;
    private String name;
    private String time;
    public Comment(){

    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Comment(String name, String text, String time){
        this.name = name;
        this.text = text;
        this.time = time;
    }

}