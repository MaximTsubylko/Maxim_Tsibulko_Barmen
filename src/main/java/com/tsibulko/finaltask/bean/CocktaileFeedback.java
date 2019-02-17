package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

public class CocktaileFeedback implements Identified<Integer> {
    private Integer id;
    private int fromUserId;
    private int toCocktileId;
    private int mark;
    private String title;
    private String comment;

    public CocktaileFeedback(int id , int fromUserId, int toCocktileId, int mark, String title, String comment) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toCocktileId = toCocktileId;
        this.mark = mark;
        this.title = title;
        this.comment = comment;
    }

    public CocktaileFeedback() {
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

    public int getToCocktileId() {
        return toCocktileId;
    }

    public void setToCocktileId(int toCocktileId) {
        this.toCocktileId = toCocktileId;
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
        return "CocktaileFeedback{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toCocktileId=" + toCocktileId +
                ", mark=" + mark +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocktaileFeedback that = (CocktaileFeedback) o;

        if (fromUserId != that.fromUserId) return false;
        if (toCocktileId != that.toCocktileId) return false;
        if (mark != that.mark) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + fromUserId;
        result = 31 * result + toCocktileId;
        result = 31 * result + mark;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
