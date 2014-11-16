package andrienkoaleksandr.com.github.testreaction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrienko Alexander on 15.11.2014.
 *
 */
public class Result implements Serializable {
    private int id;
    private float result;
    private String date;
    private int sizeTable;
    private int speed;

    public Result(int id, float result, String date, int sizeTable, int speed ) {
        this.id = id;
        this.result = result;
        this.date = date;
        this.sizeTable = sizeTable;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSizeTable() {
        return sizeTable;
    }

    public void setSizeTable(int sizeTable) {
        this.sizeTable = sizeTable;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String toJSON() {
        return "{\"id\": " + id + ", \"result\": " + result + ", \"date\": \"" + date + "\", " +
                "\"sizeTable\": " + sizeTable + ", \"speed\": " + speed + "}";
}
}
