package com.example.pharmacybackend.model;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.MedicineMode;
import com.example.pharmacybackend.enumerations.MedicineStatus;

@Entity
@Table(name = "MEDICINE")
public class Medicine {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "medicine_code", nullable = false)
	private String medicine_code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String med_type;

	@Column(nullable = false)
	private String shape;

	@Column(nullable = false)
	private String structure;

	@Column(nullable = false)
	private String producer;

	@Enumerated(EnumType.STRING)
	private MedicineMode mode;

	@Column(nullable = false)
	private String contraindications;

	@Column(nullable = false)
	private String dailyDose;

	@Enumerated(EnumType.STRING)
	private MedicineStatus defaultStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	private Pricelist pricelist;

	// @Column
	// private List<String> substituteMedicines = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedicine_code() {
		return medicine_code;
	}

	public void setMedicine_code(String medicine_code) {
		this.medicine_code = medicine_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
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

	@Column
	private String note;

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

	/**
	 * @return MedicineStatus return the defaultStatus
	 */
	public MedicineStatus getDefaultStatus() {
		return defaultStatus;
	}

	/**
	 * @param defaultStatus the defaultStatus to set
	 */
	public void setDefaultStatus(MedicineStatus defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

}
