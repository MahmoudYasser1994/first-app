
package com.example.to7fademo.Models.ConfirmationModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmationModel {

    @SerializedName("status")
    @Expose
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
