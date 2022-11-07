package uoc.ds.pr.model;

import uoc.ds.pr.SportEvents4Club;

import java.time.LocalDate;

public class File {

    private String fileId;

    private String eventId;

    private String orgId;

    private String description;

    private SportEvents4Club.Type type;

    private SportEvents4Club.Status status;

    private byte resources;

    private double max;

    private LocalDate startDate;

    private LocalDate endDate;

    public File(String fileId, String eventId, String orgId, String description,
                SportEvents4Club.Type type, byte resources, double max, LocalDate startDate, LocalDate endDate) {
        this.fileId = fileId;
        this.eventId = eventId;
        this.orgId = orgId;
        this.description = description;
        this.type = type;
        this.resources = resources;
        this.max = max;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setType(SportEvents4Club.Type type) {
        this.type = type;
    }

    public SportEvents4Club.Type getType() {
        return type;
    }

    public void setResources(byte resources) {
        this.resources = resources;
    }

    public byte getResources() {
        return resources;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMax() {
        return max;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStatus(SportEvents4Club.Status status) {
        this.status = status;
    }

    public SportEvents4Club.Status getStatus() {
        return status;
    }
}
