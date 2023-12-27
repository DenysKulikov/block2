package com.solvd.laba.domain;

public class BuildingApproval {
    private Long id;
    private String time_needed;
    private String approved_by;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime_needed() {
        return time_needed;
    }

    public void setTime_needed(String time_needed) {
        this.time_needed = time_needed;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }
}
