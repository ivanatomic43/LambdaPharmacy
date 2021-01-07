package com.example.pharmacybackend.dto;

import com.example.pharmacybackend.enumerations.MedicineMode;
import com.example.pharmacybackend.model.Medicine;

public class MedicineDTO {

	private Long id;
	private String medicineCode;
	private String medType;
	private String name;
	private String shape;
	private String producer;
	private String structure;
	private MedicineMode mode;
	private String note;
	private String contraindications;
	private String dailyDose;

	private Long pharmacyID;
	private String pharmacyName;
	private String modee;
	private String status;

	public MedicineDTO() {
	}

	public MedicineDTO(Medicine m) {
		this(m.getId(), m.getMedicine_code(), m.getMed_type(), m.getName(), m.getShape(), m.getProducer(),
				m.getStructure(), m.getMode(), m.getNote());
	}

	public MedicineDTO(Long id, String medicineCode, String medicineType, String name, String shape, String producer,
			String structure, MedicineMode mode, String note) {
		super();
		this.id = id;
		this.medicineCode = medicineCode;
		this.medType = medicineType;
		this.name = name;
		this.shape = shape;
		this.producer = producer;
		this.structure = structure;
		this.mode = mode;
		this.note = note;

	}

	public MedicineDTO(Long id, String medicineCode, String medicineType, String name, String shape, String producer,
			String structure, String modee, String note, Long pharmacyID, String pharmacyName, String status,
			String contraindications, String dailyDose) {
		super();
		this.id = id;
		this.medicineCode = medicineCode;
		this.medType = medicineType;
		this.name = name;
		this.shape = shape;
		this.producer = producer;
		this.structure = structure;
		this.modee = modee;
		this.note = note;
		this.pharmacyID = pharmacyID;
		this.pharmacyName = pharmacyName;
		this.status = status;
		this.contraindications = contraindications;
		this.dailyDose = dailyDose;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedicineCode() {
		return medicineCode;
	}

	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	public String getMedType() {
		return medType;
	}

	public void setMedType(String medType) {
		this.medType = medType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public MedicineMode getMode() {
		return mode;
	}

	public void setMode(MedicineMode mode) {
		this.mode = mode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return Long return the pharmacyID
	 */
	public Long getPharmacyID() {
		return pharmacyID;
	}

	/**
	 * @param pharmacyID the pharmacyID to set
	 */
	public void setPharmacyID(Long pharmacyID) {
		this.pharmacyID = pharmacyID;
	}

	/**
	 * @return String return the pharmacyName
	 */
	public String getPharmacyName() {
		return pharmacyName;
	}

	/**
	 * @param pharmacyName the pharmacyName to set
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	/**
	 * @return String return the modee
	 */
	public String getModee() {
		return modee;
	}

	/**
	 * @param modee the modee to set
	 */
	public void setModee(String modee) {
		this.modee = modee;
	}

	/**
	 * @return String return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return String return the contraindications
	 */
	public String getContraindications() {
		return contraindications;
	}

	/**
	 * @param contraindications the contraindications to set
	 */
	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}

	/**
	 * @return int return the dailyDose
	 */
	public String getDailyDose() {
		return dailyDose;
	}

	/**
	 * @param dailyDose the dailyDose to set
	 */
	public void setDailyDose(String dailyDose) {
		this.dailyDose = dailyDose;
	}

}
