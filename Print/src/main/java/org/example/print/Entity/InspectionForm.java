package org.example.print.Entity;


import jakarta.persistence.*;

@Entity


@Table(name = "InspectionForm", schema = "A02")
public class InspectionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Vehicle_ID")
    private Short  vehicleId;

    @Column(name = "MakeModel")
    private String makeModel;

    @Column(name = "ElectricSeatsFDS")
    private String  electricSeatsFDS;

    @Column(name = "ElectricSeatsFPS")
    private String  electricSeatsFPS;

    @Column(name = "PrintVNonkey")
    private String  printVNonkey;

    @Column(name = "Start")
    private String  start;

    @Column(name = "Drive")
    private String  drive;

    @Column(name = "LightOnTheDash")
    private String  lightOnTheDash;

    @Column(name = "RemoveRubishFromTheVehicle")
    private String  removeRubishFromTheVehicle;

    @Column(name = "WipeDashboardAndInteriorIfNeeded")
    private String  wipeDashboardAndInteriorIfNeeded;

    @Column(name = "EnsureTheWindScreenIsclearOfAnyMarkingsLabels")
    private String  ensureTheWindScreenIsclearOfAnyMarkingsLabels;

    @Column(name = "CleanWashEngineBay")
    private String  cleanWashEngineBay;

    @Column(name = "CleanWashTheOutside")
    private String  cleanWashTheOutside;

    @Column(name = "Cover3LastLettersofVehicleRegistrationPlate")
    private String  cover3LastLettersofVehicleRegistrationPlate;

    @Column(name = "IdentifyandHighlightVehicleDamage")
    private String  identifyandHighlightVehicleDamage;

    @Column(name = "IdentifyandHighlightTyresAlloysDamage")
    private String  identifyandHighlightTyresAlloysDamage;

    @Column(name = "PaintCode")
    private String paintCode;

    @Column(name = "VIN")
    private String vin;

    @Column(name = "AllTheWayRoundExterior")
    private String  allTheWayRoundExterior;

    @Column(name = "Roof")
    private String  roof;

    @Column(name = "EngineBay")
    private String  engineBay;

    @Column(name = "CompleteSeatsWithAirbagIndications")
    private String  completeSeatsWithAirbagIndications;

    @Column(name = "CompleteDashboard")
    private String  completeDashboard;

    @Column(name = "SpeedometerAndFuelGauge")
    private String  speedometerAndFuelGauge;

    @Column(name = "EngineRunning")
    private String  engineRunning;

    @Column(name = "TestAllGears")
    private String  testAllGears;

    @Column(name = "StickQRVNSheetOnTheCarSign")
    private String  stickQRVNSheetOnTheCarSign;

    @Column(name = "LockingWheelNutRemovalIfLockingKeyExist")
    private String  lockingWheelNutRemovalIfLockingKeyExist;

    @Column(name = "RemoveSeatBoltsFDS")
    private String  removeSeatBoltsFDS;

    @Column(name = "RemoveSeatBoltsFPS")
    private String  removeSeatBoltsFPS;

    @Column(name = "OpenAndLockBoot")
    private String  openAndLockBoot;

    @Column(name = "LowerWindowTo20")
    private String  lowerWindowTo20;

    @Column(name = "SunRoof")
    private String  sunRoof;

    @Column(name = "ParkingSensors")
    private String  parkingSensors;

    @Column(name = "ReverseSensors")
    private String  reverseSensors;

    @Column(name = "TowBar")
    private String  towBar;

    @Column(name = "FloorMats")
    private String  floorMats;

    @Column(name = "AlloysWheels")
    private String  alloysWheels;

    @Column(name = "ElectricwindowsFront")
    private String  electricWindowsFront;

    @Column(name = "ElectricWindowsRear")
    private String  electricWindowsRear;

    @Column(name = "CurtainAirBags")
    private String  curtainAirBags;

    @Column(name = "SeatsAirBags")
    private String  seatsAirBags;

    @Column(name = "SpareWheel")
    private String  spareWheel;

    @Column(name = "SillCover2Pieces")
    private String  sillCover2Pieces;

    @Column(name = "FogLightsSurrounds")
    private String  fogLightsSurrounds;

    @Column(name = "QuarterGlassFront")
    private String  quarterGlassFront;

    @Column(name = "QuarterPanelFront")
    private String  quarterPanelFront;

    @Column(name = "QuarterglassRear")
    private String  quarterglassRear;

    @Column(name = "QuarterPanelRear")
    private String  quarterPanelRear;

    @Column(name = "WheelArchTrimsFront")
    private String  wheelArchTrimsFront;

    @Column(name = "WheelArchTrimsRear")
    private String  wheelArchTrimsRear;

    @Column(name = "RoofRails")
    private String  roofRails;

    @Column(name = "RoofRack")
    private String  roofRack;

    @Column(name = "RemovablePassengerAirbag")
    private String  removablePassengerAirbag;

    @Column(name = "ExhaustTwin")
    private String  exhaustTwin;

    @Column(name = "LowerTailgate")
    private String  lowerTailgate;

    @Column(name = "Size_Tyre")
    private String sizeTyre;

    public InspectionForm() {
    }

    // Getters and setters for all fields


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Short vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public String getElectricSeatsFDS() {
        return electricSeatsFDS;
    }

    public void setElectricSeatsFDS(String electricSeatsFDS) {
        this.electricSeatsFDS = electricSeatsFDS;
    }

    public String getElectricSeatsFPS() {
        return electricSeatsFPS;
    }

    public void setElectricSeatsFPS(String electricSeatsFPS) {
        this.electricSeatsFPS = electricSeatsFPS;
    }

    public String getPrintVNonkey() {
        return printVNonkey;
    }

    public void setPrintVNonkey(String printVNonkey) {
        this.printVNonkey = printVNonkey;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getLightOnTheDash() {
        return lightOnTheDash;
    }

    public void setLightOnTheDash(String lightOnTheDash) {
        this.lightOnTheDash = lightOnTheDash;
    }

    public String getRemoveRubishFromTheVehicle() {
        return removeRubishFromTheVehicle;
    }

    public void setRemoveRubishFromTheVehicle(String removeRubishFromTheVehicle) {
        this.removeRubishFromTheVehicle = removeRubishFromTheVehicle;
    }

    public String getWipeDashboardAndInteriorIfNeeded() {
        return wipeDashboardAndInteriorIfNeeded;
    }

    public void setWipeDashboardAndInteriorIfNeeded(String wipeDashboardAndInteriorIfNeeded) {
        this.wipeDashboardAndInteriorIfNeeded = wipeDashboardAndInteriorIfNeeded;
    }

    public String getEnsureTheWindScreenIsclearOfAnyMarkingsLabels() {
        return ensureTheWindScreenIsclearOfAnyMarkingsLabels;
    }

    public void setEnsureTheWindScreenIsclearOfAnyMarkingsLabels(String ensureTheWindScreenIsclearOfAnyMarkingsLabels) {
        this.ensureTheWindScreenIsclearOfAnyMarkingsLabels = ensureTheWindScreenIsclearOfAnyMarkingsLabels;
    }

    public String getCleanWashEngineBay() {
        return cleanWashEngineBay;
    }

    public void setCleanWashEngineBay(String cleanWashEngineBay) {
        this.cleanWashEngineBay = cleanWashEngineBay;
    }

    public String getCleanWashTheOutside() {
        return cleanWashTheOutside;
    }

    public void setCleanWashTheOutside(String cleanWashTheOutside) {
        this.cleanWashTheOutside = cleanWashTheOutside;
    }

    public String getCover3LastLettersofVehicleRegistrationPlate() {
        return cover3LastLettersofVehicleRegistrationPlate;
    }

    public void setCover3LastLettersofVehicleRegistrationPlate(String cover3LastLettersofVehicleRegistrationPlate) {
        this.cover3LastLettersofVehicleRegistrationPlate = cover3LastLettersofVehicleRegistrationPlate;
    }

    public String getIdentifyandHighlightVehicleDamage() {
        return identifyandHighlightVehicleDamage;
    }

    public void setIdentifyandHighlightVehicleDamage(String identifyandHighlightVehicleDamage) {
        this.identifyandHighlightVehicleDamage = identifyandHighlightVehicleDamage;
    }

    public String getIdentifyandHighlightTyresAlloysDamage() {
        return identifyandHighlightTyresAlloysDamage;
    }

    public void setIdentifyandHighlightTyresAlloysDamage(String identifyandHighlightTyresAlloysDamage) {
        this.identifyandHighlightTyresAlloysDamage = identifyandHighlightTyresAlloysDamage;
    }

    public String getPaintCode() {
        return paintCode;
    }

    public void setPaintCode(String paintCode) {
        this.paintCode = paintCode;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getAllTheWayRoundExterior() {
        return allTheWayRoundExterior;
    }

    public void setAllTheWayRoundExterior(String allTheWayRoundExterior) {
        this.allTheWayRoundExterior = allTheWayRoundExterior;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public String getEngineBay() {
        return engineBay;
    }

    public void setEngineBay(String engineBay) {
        this.engineBay = engineBay;
    }

    public String getCompleteSeatsWithAirbagIndications() {
        return completeSeatsWithAirbagIndications;
    }

    public void setCompleteSeatsWithAirbagIndications(String completeSeatsWithAirbagIndications) {
        this.completeSeatsWithAirbagIndications = completeSeatsWithAirbagIndications;
    }

    public String getCompleteDashboard() {
        return completeDashboard;
    }

    public void setCompleteDashboard(String completeDashboard) {
        this.completeDashboard = completeDashboard;
    }

    public String getSpeedometerAndFuelGauge() {
        return speedometerAndFuelGauge;
    }

    public void setSpeedometerAndFuelGauge(String speedometerAndFuelGauge) {
        this.speedometerAndFuelGauge = speedometerAndFuelGauge;
    }

    public String getEngineRunning() {
        return engineRunning;
    }

    public void setEngineRunning(String engineRunning) {
        this.engineRunning = engineRunning;
    }

    public String getTestAllGears() {
        return testAllGears;
    }

    public void setTestAllGears(String testAllGears) {
        this.testAllGears = testAllGears;
    }

    public String getStickQRVNSheetOnTheCarSign() {
        return stickQRVNSheetOnTheCarSign;
    }

    public void setStickQRVNSheetOnTheCarSign(String stickQRVNSheetOnTheCarSign) {
        this.stickQRVNSheetOnTheCarSign = stickQRVNSheetOnTheCarSign;
    }

    public String getLockingWheelNutRemovalIfLockingKeyExist() {
        return lockingWheelNutRemovalIfLockingKeyExist;
    }

    public void setLockingWheelNutRemovalIfLockingKeyExist(String lockingWheelNutRemovalIfLockingKeyExist) {
        this.lockingWheelNutRemovalIfLockingKeyExist = lockingWheelNutRemovalIfLockingKeyExist;
    }

    public String getRemoveSeatBoltsFDS() {
        return removeSeatBoltsFDS;
    }

    public void setRemoveSeatBoltsFDS(String removeSeatBoltsFDS) {
        this.removeSeatBoltsFDS = removeSeatBoltsFDS;
    }

    public String getRemoveSeatBoltsFPS() {
        return removeSeatBoltsFPS;
    }

    public void setRemoveSeatBoltsFPS(String removeSeatBoltsFPS) {
        this.removeSeatBoltsFPS = removeSeatBoltsFPS;
    }

    public String getOpenAndLockBoot() {
        return openAndLockBoot;
    }

    public void setOpenAndLockBoot(String openAndLockBoot) {
        this.openAndLockBoot = openAndLockBoot;
    }

    public String getLowerWindowTo20() {
        return lowerWindowTo20;
    }

    public void setLowerWindowTo20(String lowerWindowTo20) {
        this.lowerWindowTo20 = lowerWindowTo20;
    }

    public String getSunRoof() {
        return sunRoof;
    }

    public void setSunRoof(String sunRoof) {
        this.sunRoof = sunRoof;
    }

    public String getParkingSensors() {
        return parkingSensors;
    }

    public void setParkingSensors(String parkingSensors) {
        this.parkingSensors = parkingSensors;
    }

    public String getReverseSensors() {
        return reverseSensors;
    }

    public void setReverseSensors(String reverseSensors) {
        this.reverseSensors = reverseSensors;
    }

    public String getTowBar() {
        return towBar;
    }

    public void setTowBar(String towBar) {
        this.towBar = towBar;
    }

    public String getFloorMats() {
        return floorMats;
    }

    public void setFloorMats(String floorMats) {
        this.floorMats = floorMats;
    }

    public String getAlloysWheels() {
        return alloysWheels;
    }

    public void setAlloysWheels(String alloysWheels) {
        this.alloysWheels = alloysWheels;
    }

    public String getElectricWindowsFront() {
        return electricWindowsFront;
    }

    public void setElectricWindowsFront(String electricWindowsFront) {
        this.electricWindowsFront = electricWindowsFront;
    }

    public String getElectricWindowsRear() {
        return electricWindowsRear;
    }

    public void setElectricWindowsRear(String electricWindowsRear) {
        this.electricWindowsRear = electricWindowsRear;
    }

    public String getCurtainAirBags() {
        return curtainAirBags;
    }

    public void setCurtainAirBags(String curtainAirBags) {
        this.curtainAirBags = curtainAirBags;
    }

    public String getSeatsAirBags() {
        return seatsAirBags;
    }

    public void setSeatsAirBags(String seatsAirBags) {
        this.seatsAirBags = seatsAirBags;
    }

    public String getSpareWheel() {
        return spareWheel;
    }

    public void setSpareWheel(String spareWheel) {
        this.spareWheel = spareWheel;
    }

    public String getSillCover2Pieces() {
        return sillCover2Pieces;
    }

    public void setSillCover2Pieces(String sillCover2Pieces) {
        this.sillCover2Pieces = sillCover2Pieces;
    }

    public String getFogLightsSurrounds() {
        return fogLightsSurrounds;
    }

    public void setFogLightsSurrounds(String fogLightsSurrounds) {
        this.fogLightsSurrounds = fogLightsSurrounds;
    }

    public String getQuarterGlassFront() {
        return quarterGlassFront;
    }

    public void setQuarterGlassFront(String quarterGlassFront) {
        this.quarterGlassFront = quarterGlassFront;
    }

    public String getQuarterPanelFront() {
        return quarterPanelFront;
    }

    public void setQuarterPanelFront(String quarterPanelFront) {
        this.quarterPanelFront = quarterPanelFront;
    }

    public String getQuarterglassRear() {
        return quarterglassRear;
    }

    public void setQuarterglassRear(String quarterglassRear) {
        this.quarterglassRear = quarterglassRear;
    }

    public String getQuarterPanelRear() {
        return quarterPanelRear;
    }

    public void setQuarterPanelRear(String quarterPanelRear) {
        this.quarterPanelRear = quarterPanelRear;
    }

    public String getWheelArchTrimsFront() {
        return wheelArchTrimsFront;
    }

    public void setWheelArchTrimsFront(String wheelArchTrimsFront) {
        this.wheelArchTrimsFront = wheelArchTrimsFront;
    }

    public String getWheelArchTrimsRear() {
        return wheelArchTrimsRear;
    }

    public void setWheelArchTrimsRear(String wheelArchTrimsRear) {
        this.wheelArchTrimsRear = wheelArchTrimsRear;
    }

    public String getRoofRails() {
        return roofRails;
    }

    public void setRoofRails(String roofRails) {
        this.roofRails = roofRails;
    }

    public String getRoofRack() {
        return roofRack;
    }

    public void setRoofRack(String roofRack) {
        this.roofRack = roofRack;
    }

    public String getRemovablePassengerAirbag() {
        return removablePassengerAirbag;
    }

    public void setRemovablePassengerAirbag(String removablePassengerAirbag) {
        this.removablePassengerAirbag = removablePassengerAirbag;
    }

    public String getExhaustTwin() {
        return exhaustTwin;
    }

    public void setExhaustTwin(String exhaustTwin) {
        this.exhaustTwin = exhaustTwin;
    }

    public String getLowerTailgate() {
        return lowerTailgate;
    }

    public void setLowerTailgate(String lowerTailgate) {
        this.lowerTailgate = lowerTailgate;
    }

    public String getSizeTyre() {
        return sizeTyre;
    }

    public void setSizeTyre(String sizeTyre) {
        this.sizeTyre = sizeTyre;
    }
}
