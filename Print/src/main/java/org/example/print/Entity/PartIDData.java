package org.example.print.Entity;


import jakarta.persistence.*;

@Entity

@Table(name = "PartIDData", schema = "A01") // Define table & schema
public class PartIDData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Part_ID",length = 50)
    private String  partId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Sub_Title1")
    private String subTitle1;

    @Column(name = "Sub_Title2")
    private String subTitle2;

    @Column(name = "Sub_Title3")
    private String subTitle3;

    @Column(name = "Position")
    private String position;

    @Column(name = "Set_ID")
    private String setId;

    @Column(name = "CompletePosition")
    private String completePosition;

    // Default Constructor
    public PartIDData() {}

    // Getters and Setters
    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle1() {
        return subTitle1;
    }

    public void setSubTitle1(String subTitle1) {
        this.subTitle1 = subTitle1;
    }

    public String getSubTitle2() {
        return subTitle2;
    }

    public void setSubTitle2(String subTitle2) {
        this.subTitle2 = subTitle2;
    }

    public String getSubTitle3() {
        return subTitle3;
    }

    public void setSubTitle3(String subTitle3) {
        this.subTitle3 = subTitle3;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getCompletePosition() {
        return completePosition;
    }

    public void setCompletePosition(String completePosition) {
        this.completePosition = completePosition;
    }


}
