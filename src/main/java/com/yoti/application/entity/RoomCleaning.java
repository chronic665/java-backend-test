package com.yoti.application.entity;

import com.google.common.base.MoreObjects;
import com.yoti.application.dto.ResultPage;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class RoomCleaning {
    @Id
    private String _id;
    private RoomInput input;
    private ResultPage output;
    private long timestamp;

    public RoomCleaning() {
    }

    public RoomCleaning(RoomInput input, ResultPage output, long timestamp) {
        this._id = UUID.randomUUID().toString();
        this.input = input;
        this.output = output;
        this.timestamp = timestamp;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public RoomInput getInput() {
        return input;
    }

    public void setInput(RoomInput input) {
        this.input = input;
    }

    public ResultPage getOutput() {
        return output;
    }

    public void setOutput(ResultPage output) {
        this.output = output;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCleaning that = (RoomCleaning) o;
        return Objects.equals(_id, that._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("input", input)
                .add("output", output)
                .add("finishedAt", timestamp)
                .toString();
    }
}
