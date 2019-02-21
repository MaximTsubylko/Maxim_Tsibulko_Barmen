package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

public class BarmenFeedback implements Identified<Integer> {
    private Integer id;
    private int fromUserId;
    private int toUserId;
    private int mark;
    private String title;
    private String comment;

    public BarmenFeedback(Integer id, int fromUserId, int toUserId, int mark, String title, String comment) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.mark = mark;
        this.title = title;
        this.comment = comment;
    }

    public BarmenFeedback() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BarmenFeedback{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", mark=" + mark +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BarmenFeedback that = (BarmenFeedback) o;

        if (fromUserId != that.fromUserId) return false;
        if (toUserId != that.toUserId) return false;
        if (mark != that.mark) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + fromUserId;
        result = 31 * result + toUserId;
        result = 31 * result + mark;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
