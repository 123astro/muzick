package com.cohort15adv.muzick.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "listener_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"age"})
    private Listener listener;

//    public Listener getCreator() {
//        return creator;
//    }


    public Note() {
    }

    public Note(Long id, String title, String body, Listener listener) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.listener = listener;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Listener getCreator() {
        return listener;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreator(Listener listener) {
        this.listener = listener;
    }

}
