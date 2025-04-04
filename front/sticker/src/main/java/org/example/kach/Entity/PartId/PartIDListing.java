package org.example.kach.Entity.PartId;


import jakarta.persistence.*;

@Entity
@Table(name = "PartIDListing", schema = "A01") // Define table & schema
public class PartIDListing {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Part_ID",length = 50)
    private String partId;
    @Column(name = "Category_ID",length = 50)
    private String categoryId;
    @Column(name = "Pricing_List")
    private String pricingList;

    @Column(name = "Dismantling_List")
    private String dismantlingList;

    @Column(name = "Tak_Pic")
    private String takPic;

    @Column(name = "TecDoc_List")
    private String tecDocList;

    @Column(name = "Tec_Doc_Title")
    private String tecDocTitle;

    @Column(name = "Comp")
    private String comp;

    @Column(name = "P_P")
    private String pP;

    @Column(name = "Highland")
    private String highland;

    @Column(name = "Global_Shipping")
    private String globalShipping;

    @Column(name = "Description")
    private String description;

    @Column(name = "Filter")
    private String filter;

    @Column(name = "Note_For_Pricing")
    private String noteForPricing;

    @Column(name = "Note_For_Dismantling")
    private String noteForDismantling;

    @Column(name = "Note_For_testing")
    private String noteForTesting;

    @Column(name = "Note_For_Pictures")
    private String noteForPictures;

    @Column(name = "Note_Add_Confirm")
    private String noteAddConfirm;

    @Column(name = "Note_Prep")
    private String notePrep;

    @Column(name = "Note_For_Listing")
    private String noteForListing;

    @Column(name = "Note_For_Messages")
    private String noteForMessages;

    @Column(name = "How_TO_Test_It")
    private String howToTestIt;

    @Column(name = "Note_to_eBay")
    private String noteToEbay;

    @Column(name = "status_vehicle_Parts")
    private String statusVehicleParts;

    @Column(name = "InspectionFormFilter")
    private String inspectionFormFilter;

    @Column(name = "Category_Name")
    private String categoryName;

    @Column(name = "History", columnDefinition = "TEXT")
    private String history;

    @Column(name = "Note_For_ItemToDispatch")
    private String noteForItemToDispatch;

    @Column(name = "TitleCompatibility")
    private String titleCompatibility;

    @Column(name = "Secondary_Category_ID")
    private Long secondaryCategoryId;

    @Column(name = "Secondary_Category_Name")
    private String secondaryCategoryName;

    // Default Constructor
    public PartIDListing() {}

    // Getters and Setters
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPricingList() {
        return pricingList;
    }

    public void setPricingList(String pricingList) {
        this.pricingList = pricingList;
    }

    public String getDismantlingList() {
        return dismantlingList;
    }

    public void setDismantlingList(String dismantlingList) {
        this.dismantlingList = dismantlingList;
    }

    public String getTakPic() {
        return takPic;
    }

    public void setTakPic(String takPic) {
        this.takPic = takPic;
    }

    public String getTecDocList() {
        return tecDocList;
    }

    public void setTecDocList(String tecDocList) {
        this.tecDocList = tecDocList;
    }

    public String getTecDocTitle() {
        return tecDocTitle;
    }

    public void setTecDocTitle(String tecDocTitle) {
        this.tecDocTitle = tecDocTitle;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getpP() {
        return pP;
    }

    public void setpP(String pP) {
        this.pP = pP;
    }

    public String getHighland() {
        return highland;
    }

    public void setHighland(String highland) {
        this.highland = highland;
    }

    public String getGlobalShipping() {
        return globalShipping;
    }

    public void setGlobalShipping(String globalShipping) {
        this.globalShipping = globalShipping;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getNoteForPricing() {
        return noteForPricing;
    }

    public void setNoteForPricing(String noteForPricing) {
        this.noteForPricing = noteForPricing;
    }

    public String getNoteForDismantling() {
        return noteForDismantling;
    }

    public void setNoteForDismantling(String noteForDismantling) {
        this.noteForDismantling = noteForDismantling;
    }

    public String getNoteForTesting() {
        return noteForTesting;
    }

    public void setNoteForTesting(String noteForTesting) {
        this.noteForTesting = noteForTesting;
    }

    public String getNoteForPictures() {
        return noteForPictures;
    }

    public void setNoteForPictures(String noteForPictures) {
        this.noteForPictures = noteForPictures;
    }

    public String getNoteAddConfirm() {
        return noteAddConfirm;
    }

    public void setNoteAddConfirm(String noteAddConfirm) {
        this.noteAddConfirm = noteAddConfirm;
    }

    public String getNotePrep() {
        return notePrep;
    }

    public void setNotePrep(String notePrep) {
        this.notePrep = notePrep;
    }

    public String getNoteForListing() {
        return noteForListing;
    }

    public void setNoteForListing(String noteForListing) {
        this.noteForListing = noteForListing;
    }

    public String getNoteForMessages() {
        return noteForMessages;
    }

    public void setNoteForMessages(String noteForMessages) {
        this.noteForMessages = noteForMessages;
    }

    public String getHowToTestIt() {
        return howToTestIt;
    }

    public void setHowToTestIt(String howToTestIt) {
        this.howToTestIt = howToTestIt;
    }

    public String getNoteToEbay() {
        return noteToEbay;
    }

    public void setNoteToEbay(String noteToEbay) {
        this.noteToEbay = noteToEbay;
    }

    public String getStatusVehicleParts() {
        return statusVehicleParts;
    }

    public void setStatusVehicleParts(String statusVehicleParts) {
        this.statusVehicleParts = statusVehicleParts;
    }

    public String getInspectionFormFilter() {
        return inspectionFormFilter;
    }

    public void setInspectionFormFilter(String inspectionFormFilter) {
        this.inspectionFormFilter = inspectionFormFilter;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getNoteForItemToDispatch() {
        return noteForItemToDispatch;
    }

    public void setNoteForItemToDispatch(String noteForItemToDispatch) {
        this.noteForItemToDispatch = noteForItemToDispatch;
    }

    public String getTitleCompatibility() {
        return titleCompatibility;
    }

    public void setTitleCompatibility(String titleCompatibility) {
        this.titleCompatibility = titleCompatibility;
    }

    public Long getSecondaryCategoryId() {
        return secondaryCategoryId;
    }

    public void setSecondaryCategoryId(Long secondaryCategoryId) {
        this.secondaryCategoryId = secondaryCategoryId;
    }

    public String getSecondaryCategoryName() {
        return secondaryCategoryName;
    }

    public void setSecondaryCategoryName(String secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }
}
