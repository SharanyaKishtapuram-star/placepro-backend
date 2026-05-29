package com.placepro.dto;

public class AnalyticsResponse {

    private Long totalStudents;

    private Long totalRecruiters;

    private Long totalJobs;

    private Long totalApplications;

    private Long selectedStudents;

    private Double placementPercentage;

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(
            Long totalStudents) {

        this.totalStudents =
                totalStudents;
    }

    public Long getTotalRecruiters() {
        return totalRecruiters;
    }

    public void setTotalRecruiters(
            Long totalRecruiters) {

        this.totalRecruiters =
                totalRecruiters;
    }

    public Long getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(
            Long totalJobs) {

        this.totalJobs =
                totalJobs;
    }

    public Long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(
            Long totalApplications) {

        this.totalApplications =
                totalApplications;
    }

    public Long getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(
            Long selectedStudents) {

        this.selectedStudents =
                selectedStudents;
    }

    public Double getPlacementPercentage() {
        return placementPercentage;
    }

    public void setPlacementPercentage(
            Double placementPercentage) {

        this.placementPercentage =
                placementPercentage;
    }
}