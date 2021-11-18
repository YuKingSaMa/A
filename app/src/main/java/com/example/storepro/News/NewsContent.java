package com.example.storepro.News;

public class NewsContent {
    private String title;
    private String img;
    private String content;
    private String date;
    private String writer;

    public NewsContent(String title, String img, String content, String date, String writer) {
        this.title = title;
        this.img = img;
        this.content = content;
        this.date = date;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
