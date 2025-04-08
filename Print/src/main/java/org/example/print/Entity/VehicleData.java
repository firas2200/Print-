package org.example.print.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;


@Entity



@Table(name = "VehiclesData", schema = "A02")
public class VehicleData {

    @Id
    @Column(name = "Vehicle_ID")
    private Short vehicleId;

    @Column(name = "Vehicle_Registration_No")
    private String vehicleRegistrationNo;

    @Column(name = "Combined_VIN")
    private String combinedVin;

    @Column(name = "Marque_Description")
    private String marqueDescription;

    @Column(name = "Model_Range_Description")
    private String modelRangeDescription;

    @Column(name = "Model_Series")
    private String modelSeries;

    @Column(name = "Model_Variant_Description")
    private String modelVariantDescription;

    @Column(name = "Model_Year")
    private Integer modelYear;

    @Column(name = "Date_of_Manufacture")
    private LocalDate dateOfManufacture;

    @Column(name = "Body_Style_Description")
    private String bodyStyleDescription;

    @Column(name = "Colour")
    private String colour;

    @Column(name = "Combined_Engine_Capacity")
    private String combinedEngineCapacity;

    @Column(name = "Combined_Forward_Gears")
    private String combinedForwardGears;

    @Column(name = "Combined_Transmission")
    private String combinedTransmission;

    @Column(name = "Combined_Engine_Model_Code")
    private String combinedEngineModelCode;

    @Column(name = "Euro_Status")
    private String euroStatus;

    @Column(name = "Fuel_Type")
    private String fuelType;

    @Column(name = "Maximum_Power_bhp")
    private Double maximumPowerBhp;

    @Column(name = "Maximum_Power_kw")
    private Double maximumPowerKw;

    @Column(name = "Number_of_Cylinders")
    private Integer numberOfCylinders;

    @Column(name = "Number_Of_Doors")
    private Integer numberOfDoors;

    @Column(name = "Seats")
    private Integer seats;

    @Column(name = "Drive_Axle")
    private String driveAxle;

    @Column(name = "Drive_Type")
    private String driveType;

    @Column(name = "Vat")
    private String vat;

    @Column(name = "Status")
    private String status;

    @Column(name = "Model_Year2")
    private String  modelYear2;

    @Column(name = "Paint_Colore_code")
    private String paintColoreCode;

    @Column(name = "Engine_Details")
    private String engineDetails;

    @Column(name = "Model_Series2")
    private String modelSeries2;

    @Column(name = "Seller_Name")
    private String sellerName;

    @Column(name = "Purchase_Date")
    private LocalDate purchaseDate;

    @Column(name = "Payment_Date")
    private LocalDate paymentDate;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Scrap_Date")
    private String scrapDate;

    @Column(name = "arrived_Date")
    private LocalDate arrivedDate;

    @Column(name = "Inspection")
    private String inspection;

    @Column(name = "COD")
    private String cod;

    @Column(name = "ADDID")
    private String addid;

    @Column(name = "ModificatorID")
    private String modificatorId;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "MecanicalID")
    private String mecanicalId;

    @Column(name = "VerificatorID")
    private String verificatorId;

    @Column(name = "CreationDate")
    private LocalDate creationDate;

    @Column(name = "\"ALL\"")  // Using quotes to handle reserved word
    private String allValue;

    @Column(name = "EN")
    private String en;

    @Column(name = "BS")
    private String bs;

    @Column(name = "TR")
    private String tr;

    @Column(name = "LPG")
    private String lpg;

    @Column(name = "BodyStyleDetails")
    private String bodyStyleDetails;

    @Column(name = "SlidingDoor")
    private String slidingDoor;

    @Column(name = "k_Type")
    private String kType;

    @Column(name = "DismantlingOrder")
    private String dismantlingOrder;

    @Column(name = "VehicleLink")
    private String vehicleLink;

    // Getters and setters

    public Short getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Short vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleRegistrationNo() {
        return vehicleRegistrationNo;
    }

    public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
        this.vehicleRegistrationNo = vehicleRegistrationNo;
    }

    public String getCombinedVin() {
        return combinedVin;
    }

    public void setCombinedVin(String combinedVin) {
        this.combinedVin = combinedVin;
    }

    public String getMarqueDescription() {
        return marqueDescription;
    }

    public void setMarqueDescription(String marqueDescription) {
        this.marqueDescription = marqueDescription;
    }

    public String getModelRangeDescription() {
        return modelRangeDescription;
    }

    public void setModelRangeDescription(String modelRangeDescription) {
        this.modelRangeDescription = modelRangeDescription;
    }

    public String getModelSeries() {
        return modelSeries;
    }

    public void setModelSeries(String modelSeries) {
        this.modelSeries = modelSeries;
    }

    public String getModelVariantDescription() {
        return modelVariantDescription;
    }

    public void setModelVariantDescription(String modelVariantDescription) {
        this.modelVariantDescription = modelVariantDescription;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public LocalDate getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(LocalDate dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getBodyStyleDescription() {
        return bodyStyleDescription;
    }

    public void setBodyStyleDescription(String bodyStyleDescription) {
        this.bodyStyleDescription = bodyStyleDescription;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getCombinedEngineCapacity() {
        return combinedEngineCapacity;
    }

    public void setCombinedEngineCapacity(String combinedEngineCapacity) {
        this.combinedEngineCapacity = combinedEngineCapacity;
    }

    public String getCombinedForwardGears() {
        return combinedForwardGears;
    }

    public void setCombinedForwardGears(String combinedForwardGears) {
        this.combinedForwardGears = combinedForwardGears;
    }

    public String getCombinedTransmission() {
        return combinedTransmission;
    }

    public void setCombinedTransmission(String combinedTransmission) {
        this.combinedTransmission = combinedTransmission;
    }

    public String getCombinedEngineModelCode() {
        return combinedEngineModelCode;
    }

    public void setCombinedEngineModelCode(String combinedEngineModelCode) {
        this.combinedEngineModelCode = combinedEngineModelCode;
    }

    public String getEuroStatus() {
        return euroStatus;
    }

    public void setEuroStatus(String euroStatus) {
        this.euroStatus = euroStatus;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getMaximumPowerBhp() {
        return maximumPowerBhp;
    }

    public void setMaximumPowerBhp(Double maximumPowerBhp) {
        this.maximumPowerBhp = maximumPowerBhp;
    }

    public Double getMaximumPowerKw() {
        return maximumPowerKw;
    }

    public void setMaximumPowerKw(Double maximumPowerKw) {
        this.maximumPowerKw = maximumPowerKw;
    }

    public Integer getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public void setNumberOfCylinders(Integer numberOfCylinders) {
        this.numberOfCylinders = numberOfCylinders;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getDriveAxle() {
        return driveAxle;
    }

    public void setDriveAxle(String driveAxle) {
        this.driveAxle = driveAxle;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String  getModelYear2() {
        return modelYear2;
    }

    public void setModelYear2(String  modelYear2) {
        this.modelYear2 = modelYear2;
    }

    public String getPaintColoreCode() {
        return paintColoreCode;
    }

    public void setPaintColoreCode(String paintColoreCode) {
        this.paintColoreCode = paintColoreCode;
    }

    public String getEngineDetails() {
        return engineDetails;
    }

    public void setEngineDetails(String engineDetails) {
        this.engineDetails = engineDetails;
    }

    public String getModelSeries2() {
        return modelSeries2;
    }

    public void setModelSeries2(String modelSeries2) {
        this.modelSeries2 = modelSeries2;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getScrapDate() {
        return scrapDate;
    }

    public void setScrapDate(String scrapDate) {
        this.scrapDate = scrapDate;
    }

    public LocalDate getArrivedDate() {
        return arrivedDate;
    }

    public void setArrivedDate(LocalDate arrivedDate) {
        this.arrivedDate = arrivedDate;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getAddid() {
        return addid;
    }

    public void setAddid(String addid) {
        this.addid = addid;
    }

    public String getModificatorId() {
        return modificatorId;
    }

    public void setModificatorId(String modificatorId) {
        this.modificatorId = modificatorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMecanicalId() {
        return mecanicalId;
    }

    public void setMecanicalId(String mecanicalId) {
        this.mecanicalId = mecanicalId;
    }

    public String getVerificatorId() {
        return verificatorId;
    }

    public void setVerificatorId(String verificatorId) {
        this.verificatorId = verificatorId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getAllValue() {
        return allValue;
    }

    public void setAllValue(String allValue) {
        this.allValue = allValue;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    public String getLpg() {
        return lpg;
    }

    public void setLpg(String lpg) {
        this.lpg = lpg;
    }

    public String getBodyStyleDetails() {
        return bodyStyleDetails;
    }

    public void setBodyStyleDetails(String bodyStyleDetails) {
        this.bodyStyleDetails = bodyStyleDetails;
    }

    public String getSlidingDoor() {
        return slidingDoor;
    }

    public void setSlidingDoor(String slidingDoor) {
        this.slidingDoor = slidingDoor;
    }

    public String getKType() {
        return kType;
    }

    public void setKType(String kType) {
        this.kType = kType;
    }

    public String getDismantlingOrder() {
        return dismantlingOrder;
    }

    public void setDismantlingOrder(String dismantlingOrder) {
        this.dismantlingOrder = dismantlingOrder;
    }

    public String getVehicleLink() {
        return vehicleLink;
    }

    public void setVehicleLink(String vehicleLink) {
        this.vehicleLink = vehicleLink;
    }
}
