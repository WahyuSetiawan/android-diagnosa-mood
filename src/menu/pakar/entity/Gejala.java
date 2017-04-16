package menu.pakar.entity;

public class Gejala {
	String id;
	String nama;
	String pertanyaan;

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

	public String getPertanyaan() {
		return pertanyaan;
	}

	public void setPertanyaan(String pertanyaan) {
		this.pertanyaan = pertanyaan;
	}

	public Gejala(String id, String nama, String pertanyaan) {
		super();
		this.id = id;
		this.nama = nama;
		this.pertanyaan = pertanyaan;
	}

}
