package com.example.TODO.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="todoitems")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date deadline ;

    private String description;
    @NotNull
    private String name;
    private long dependentTo;

    private Status status =Status.PROGRESS;

    public TodoItem(){}

    public TodoItem(Date deadline, String name,String description) {
        this.deadline = deadline;
        this.description = description;
    }

    public TodoItem(Date deadline, String name,String description,long dependentTo) {
        this.deadline = deadline;
        this.description = description;
        this.dependentTo=dependentTo;
    }

    public long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public long getDependentTo() {
        return dependentTo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDependentTo(long dependentTo) {
        this.dependentTo = dependentTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
