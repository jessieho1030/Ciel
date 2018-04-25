package app.comps456f;

/**
 * Created by meiyuk on 25/4/2018.
 */

public class Post {
    private String author;
    private String subject;
    private String text;
    private String time;
    private String name;
    private String readBy;
    private String recomment;


    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    /*public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }*/

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecomment() {
        return recomment;
    }

    public void setRecomment(String recomment) {
        this.recomment = recomment;
    }

    public String getReadBy() {
        return readBy;
    }

    public void setReadBy(String readBy) {
        this.readBy = readBy;
    }

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    //public Post(String author, String category, String subject, String text, String time){
    public Post(String author, String subject, String text, String time, String name){
        this.author = author;
        this.subject = subject;
        this.text = text;
        this.time = time;
        this.name = name;
        recomment = "null";
        readBy = "null";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
