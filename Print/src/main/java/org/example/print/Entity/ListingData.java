package org.example.print.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity

@Table(name = "ListingData", schema = "C01")
public class ListingData {

    @Column(name = "Vehicle_ID")
    private Short vehicleId;

    @Id
    @Column(name = "Part_ID")
    private String partId;

    @Column(name = "ItemCode")
    private String itemCode;

    @Column(name = "PartNumber")
    private String partNumber;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "ShippingCompany")
    private String shippingCompany;

    @Column(name = "P_P")
    private String  pp;

    @Column(name = "ListingPrice")
    private Double listingPrice;

    @Column(name = "Profit")
    private Double profit;

    @Column(name = "Sku")
    private String sku;

    @Column(name = "ListingNote")
    private String listingNote;

    @Column(name = "ListingTitle")
    private String listingTitle;

    @Column(name = "ConditionNote")
    private String conditionNote;

    @Column(name = "HtmlCode")
    private String htmlCode;

    @Column(name = "DataEntryDate")
    private LocalDate dataEntryDate;

    @Column(name = "DataEntryTime")
    private LocalTime dataEntryTime;

    @Column(name = "ImportDataDateTime")
    private LocalDateTime importDataDateTime;

    @Column(name = "BestOfferEnabled")
    private Boolean bestOfferEnabled;

    @Column(name = "DefaultDeliveryCompany")
    private String defaultDeliveryCompany;

    @Column(name = "DefaultPnPOption")
    private String defaultPnPOption;

    @Column(name = "History")
    private String history;

    @Column(name = "Listing_Status")
    private String listingStatus;

    @Column(name = "BestOfferAutoAcceptPrice")
    private Double bestOfferAutoAcceptPrice;

    @Column(name = "MinimumBestOfferPrice")
    private Double minimumBestOfferPrice;

    @Column(name = "Note_of_listing_From_images")
    private String noteOfListingFromImages;

    @Column(name = "Secondary_Category_ID")
    private String secondaryCategoryId;

    @Column(name = "Secondary_Category_Name")
    private String secondaryCategoryName;

    @Column(name = "DefaultCategoryID")
    private String defaultCategoryId;

    @Column(name = "DefaultCategoryName")
    private String defaultCategoryName;

    @Column(name = "DefaultSecondaryCategoryID")
    private String defaultSecondaryCategoryId;

    @Column(name = "DefaultSecondaryCategoryName")
    private String defaultSecondaryCategoryName;

    @Column(name = "CategoryID")
    private String categoryId;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "DescriptionTitle")
    private String descriptionTitle;

    // Getters and setters

    public Short getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(Short vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPartId() {
        return partId;
    }
    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getPartNumber() {
        return partNumber;
    }
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }
    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String  getPp() {
        return pp;
    }
    public void setPp(String  pp) {
        this.pp = pp;
    }

    public Double getListingPrice() {
        return listingPrice;
    }
    public void setListingPrice(Double listingPrice) {
        this.listingPrice = listingPrice;
    }

    public Double getProfit() {
        return profit;
    }
    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getListingNote() {
        return listingNote;
    }
    public void setListingNote(String listingNote) {
        this.listingNote = listingNote;
    }

    public String getListingTitle() {
        return listingTitle;
    }
    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public String getConditionNote() {
        return conditionNote;
    }
    public void setConditionNote(String conditionNote) {
        this.conditionNote = conditionNote;
    }

    public String getHtmlCode() {
        return htmlCode;
    }
    public void setHtmlCode(String htmlCode) {
        this.htmlCode = htmlCode;
    }

    public LocalDate getDataEntryDate() {
        return dataEntryDate;
    }
    public void setDataEntryDate(LocalDate dataEntryDate) {
        this.dataEntryDate = dataEntryDate;
    }

    public LocalTime getDataEntryTime() {
        return dataEntryTime;
    }
    public void setDataEntryTime(LocalTime dataEntryTime) {
        this.dataEntryTime = dataEntryTime;
    }

    public LocalDateTime getImportDataDateTime() {
        return importDataDateTime;
    }
    public void setImportDataDateTime(LocalDateTime importDataDateTime) {
        this.importDataDateTime = importDataDateTime;
    }

    public Boolean getBestOfferEnabled() {
        return bestOfferEnabled;
    }
    public void setBestOfferEnabled(Boolean bestOfferEnabled) {
        this.bestOfferEnabled = bestOfferEnabled;
    }

    public String getDefaultDeliveryCompany() {
        return defaultDeliveryCompany;
    }
    public void setDefaultDeliveryCompany(String defaultDeliveryCompany) {
        this.defaultDeliveryCompany = defaultDeliveryCompany;
    }

    public String getDefaultPnPOption() {
        return defaultPnPOption;
    }
    public void setDefaultPnPOption(String defaultPnPOption) {
        this.defaultPnPOption = defaultPnPOption;
    }

    public String getHistory() {
        return history;
    }
    public void setHistory(String history) {
        this.history = history;
    }

    public String getListingStatus() {
        return listingStatus;
    }
    public void setListingStatus(String listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Double getBestOfferAutoAcceptPrice() {
        return bestOfferAutoAcceptPrice;
    }
    public void setBestOfferAutoAcceptPrice(Double bestOfferAutoAcceptPrice) {
        this.bestOfferAutoAcceptPrice = bestOfferAutoAcceptPrice;
    }

    public Double getMinimumBestOfferPrice() {
        return minimumBestOfferPrice;
    }
    public void setMinimumBestOfferPrice(Double minimumBestOfferPrice) {
        this.minimumBestOfferPrice = minimumBestOfferPrice;
    }

    public String getNoteOfListingFromImages() {
        return noteOfListingFromImages;
    }
    public void setNoteOfListingFromImages(String noteOfListingFromImages) {
        this.noteOfListingFromImages = noteOfListingFromImages;
    }

    public String getSecondaryCategoryId() {
        return secondaryCategoryId;
    }
    public void setSecondaryCategoryId(String secondaryCategoryId) {
        this.secondaryCategoryId = secondaryCategoryId;
    }

    public String getSecondaryCategoryName() {
        return secondaryCategoryName;
    }
    public void setSecondaryCategoryName(String secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }

    public String getDefaultCategoryId() {
        return defaultCategoryId;
    }
    public void setDefaultCategoryId(String defaultCategoryId) {
        this.defaultCategoryId = defaultCategoryId;
    }

    public String getDefaultCategoryName() {
        return defaultCategoryName;
    }
    public void setDefaultCategoryName(String defaultCategoryName) {
        this.defaultCategoryName = defaultCategoryName;
    }

    public String getDefaultSecondaryCategoryId() {
        return defaultSecondaryCategoryId;
    }
    public void setDefaultSecondaryCategoryId(String defaultSecondaryCategoryId) {
        this.defaultSecondaryCategoryId = defaultSecondaryCategoryId;
    }

    public String getDefaultSecondaryCategoryName() {
        return defaultSecondaryCategoryName;
    }
    public void setDefaultSecondaryCategoryName(String defaultSecondaryCategoryName) {
        this.defaultSecondaryCategoryName = defaultSecondaryCategoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescriptionTitle() {
        return descriptionTitle;
    }
    public void setDescriptionTitle(String descriptionTitle) {
        this.descriptionTitle = descriptionTitle;
    }
}
