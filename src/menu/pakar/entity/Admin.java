package menu.pakar.entity;

public class Admin {
	String nama;
	String pass;

	public Admin(String nama, String pass) {
		super();
		this.nama = nama;
		this.pass = pass;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
