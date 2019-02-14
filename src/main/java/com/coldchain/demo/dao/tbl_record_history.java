package com.coldchain.demo.dao;

import lombok.Data;

@Data
public class tbl_record_history {
    private String ownOrderId;
    private String numberPlate;
    private Long recordTime;
    private String recordTem;
    private String recordPath;
    private int isDeleted;

    public String toTemJson(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"recordTime\":").append("\"").append(this.recordTime).append("\"").append(",");
        sb.append("\"recordTem\":").append("\"").append(this.recordTem).append("\"").append(",");
        sb.append("}");
        return sb.toString();
    }
    public String toPathJson(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"recordTime\":").append("\"").append(this.recordTime).append("\"").append(",");
        sb.append("\"recordPath\":").append("\"").append(this.recordPath).append("\"").append(",");
        sb.append("}");
        return sb.toString();
    }
    @Override
    public String toString() {
        return "tbl_record_history{" +
                "ownOrderId='" + ownOrderId + '\'' +
                ", numberPlate='" + numberPlate + '\'' +
                ", recordTime=" + recordTime +
                ", recordTem=" + recordTem +
                ", recordPath='" + recordPath + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public tbl_record_history(String ownOrderId, String numberPlate, Long recordTime, String recordTem, String recordPath, int isDeleted) {
        this.ownOrderId = ownOrderId;
        this.numberPlate = numberPlate;
        this.recordTime = recordTime;
        this.recordTem = recordTem;
        this.recordPath = recordPath;
        this.isDeleted = isDeleted;
    }

    public tbl_record_history() {
    }
}
