package menu.pakar.entity;

public class Gangguan {
	String id;
	String nama;
	String saran;
	String pengertian;
	
	public String getPengertian() {
		return pengertian;
	}

	public void setPengertian(String pengertan) {
		this.pengertian = pengertan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getSaran() {
		return saran;
	}

	public void setSaran(String saran) {
		this.saran = saran;
	}

	public Gangguan(String id, String nama, String saran, String pengertian) {
		super();
		this.id = id;
		this.nama = nama;
		this.saran = saran;
		this.pengertian = pengertian;
	}

}
