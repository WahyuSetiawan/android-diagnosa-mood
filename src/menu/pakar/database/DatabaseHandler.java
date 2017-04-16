package menu.pakar.database;

import java.util.ArrayList;
import java.util.List;

import menu.pakar.entity.Admin;
import menu.pakar.entity.Gangguan;
import menu.pakar.entity.Gejala;
import menu.pakar.entity.Rule;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	static final int DATABASE_VERSION = 2;
	static final String DATABASE_NAME = "diagnosamood";

	static final String TABLE_ADMIN = "table_admin";
	static final String KEY_ADMIN_ID = "idadmin";
	static final String KEY_ADMIN_PASS = "pass";

	static final String TABLE_GEJALA = "gejala";
	static final String KEY_GEJALA_ID = "idgejala";
	static final String KEY_GEJALA_NAMA = "nama";
	static final String KEY_GEJALA_PERTANYAAN = "pertanyaan";

	static final String TABLE_GANGGUAN = "gangguan";
	static final String KEY_GANGGUAN_ID = "idgangguan";
	static final String KEY_GANGGUAN_NAMA = "nama";
	static final String KEY_GANGGUAN_SARAN = "saran";
	static final String KEY_GANGGUAN_PENGERTIAN = "pengertian";

	static final String TABLE_RULE = "rule";
	static final String KEY_RULE_ID = "idrule";
	static final String KEY_RULE_TRUE = "if_rule";
	static final String KEY_RULE_FALSE = "if_false";
	static final String KEY_RULE_CURRENT = "current";

	static final String[] TABLESBUILD = {
			"create table " + TABLE_ADMIN + " ( " 
					+ KEY_ADMIN_ID	+ " varchar (6)  primary key, " 
					+ KEY_ADMIN_PASS + " varchar (6))",
			"create table " + TABLE_GANGGUAN + " ( " + KEY_GANGGUAN_ID
					+ " varchar (6)  primary key, " + KEY_GANGGUAN_NAMA
					+ " char(100), " + KEY_GANGGUAN_SARAN + " char(100), "+KEY_GANGGUAN_PENGERTIAN+ " char(100))",
			"create table " + TABLE_GEJALA + " ( " + KEY_GEJALA_ID
					+ " varchar (6)  primary key, " + KEY_GEJALA_NAMA
					+ " char (100), " + KEY_GEJALA_PERTANYAAN + " char (100))",
			"create table " + TABLE_RULE + " ( " + KEY_RULE_CURRENT
					+ " varchar (6) primary key, " + KEY_RULE_TRUE
					+ " varchar(6), " + KEY_RULE_FALSE + " varchar(6))" };

	Admin[] admins = { new Admin("admin", "admin") };
	Gejala[] gejalas = {
			new Gejala("GE-001", "Terjadi perubahan mood yang terus menerus",
					"Terjadi perubahan mood yang terus menerus ?"),
			new Gejala("GE-002",
					"Meningkatnya iritabilitas (mudah tersinggung) ",
					" Meningkatnya iritabilitas (mudah tersinggung) ?"),
			new Gejala(
					"GE-003",
					"Kesulitan berkonsentrasi dan berpikiran negatif tentang diri sendiri",
					"Kesulitan berkonsentrasi dan berpikiran negatif tentang diri sendiri ?"),
			new Gejala("GE-004",
					"Kurang percaya diri dan berpikir akan kematian",
					"Kurang percaya diri dan berpikir akan kematian?"),
			new Gejala("GE-005", "Mood menuru", "Mood menuru ?"),
			new Gejala("GE-006", "Kehilangan minat pada aktifitas sosial",
					"Kehilangan minat pada aktifitas sosial ?"),
			new Gejala("GE-007", "Perubahan kebiasaan tidur dan selera makan",
					"Perubahan kebiasaan tidur dan selera makan ?"),
			new Gejala("GE-008", "Perasaan terpuruk sepanjang waktu",
					"Perasaan terpuruk sepanjang waktu ?"),
			new Gejala("GE-009", "Terjadi pergantian mood yang cepat",
					"Terjadi pergantian mood yang cepat ?"),
			new Gejala("GE-010", "Perubahan mood yang kronis",
					"Perubahan mood yang kronis ?"),
			new Gejala("GE-011", "Kehilangan minat atau kesenangan",
					"Kehilangan minat atau kesenangan ?"), };
	Gangguan[] gangguans = {
			new Gangguan("GA-001", "Gangguan Depresi Mayor",
					"Lakukan terapi elektrokonvulsif (ECT)", "oeuaoeuaoeuaoeuaou"),
			new Gangguan("GA-002", "Gangguan Distimik",
					"Berikan obat-obatan anti depresan dan konsultasi ke psikiatri","idhuidhuidhui"),
			new Gangguan(
					"GA-003",
					"Gangguan Bipolar",
					"Berikan litium atau obat-obatan antikonvulsan untuk menstabilkan mood dan fototerapi","lrcl/rcl/cl/rcl/cr"),
			new Gangguan("GA-004", "Gangguan Siklotimik",
					"Lakukan terapi kognitif-behavioral dan terapi interpersonal","ns-nts-\tns-tns-"),

	};
	Rule[] rules ={ 
			new Rule("GE-001", "GE-002", "GE-002"),
			new Rule("GE-002", "GE-003", "GE-003"),
			new Rule("GE-003", "GE-004", "GE-004"),
			new Rule("GE-004", "GE-005", "GE-005"),
			new Rule("GE-005", "GE-006", "GE-008"),
			new Rule("GE-006", "GE-007", "GE-007"),
			new Rule("GE-007", "GA-001", "GA-001"),
			new Rule("GE-008", "GA-002", "GE-009"),
			new Rule("GE-009", "GA-003", "GE-011"),
			new Rule("GE-010", "GE-011", "GE-011"),
			new Rule("GE-011", "GA-004", "NULL"),
			new Rule("GA-001", "NULL", "NULL"),
			new Rule("GA-002", "NULL", "NULL"),
			new Rule("GA-003", "NULL", "NULL"),
			new Rule("GA-004", "NULL", "NULL"),

	};

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		for (String sql : TABLESBUILD) {
			db.execSQL(sql);
		}
		for (Admin admin : admins) {
			db.execSQL("insert into " + TABLE_ADMIN + " values ('"
					+ admin.getNama() + "','" + admin.getPass() + "')");
		}
		for (Gejala gejala : gejalas) {
			db.execSQL("insert into " + TABLE_GEJALA + " values ('"
					+ gejala.getId() + "','" + gejala.getNama() + "','"
					+ gejala.getPertanyaan() + "')");
		}
		for (Gangguan gangguan : gangguans) {
			db.execSQL("insert into " + TABLE_GANGGUAN + " values ('"
					+ gangguan.getId() + "','" 
					+ gangguan.getNama() + "','"
					+ gangguan.getSaran() + "','"
					+ gangguan.getPengertian() + "')");
		}
		for (Rule rule : rules) {
			String sql = "insert into " + TABLE_RULE + " values ( '"
					+ rule.getCurrent() + "', '" + rule.getTrueString() + "','"
					+ rule.getFalseString() + "')";
			db.execSQL(sql);
			Log.d(sql, sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists " + TABLE_ADMIN);
		db.execSQL("drop table if exists " + TABLE_GEJALA);
		db.execSQL("drop table if exists " + TABLE_GANGGUAN);
		db.execSQL("drop table if exists " + TABLE_RULE);

		onCreate(db);
	}

	/*
	 * 
	 * function untuk akses ke dalam table admin insertAdmin() getAdmin()
	 * getAllAdmin()
	 */
	public Admin getIDAdmin(String string) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query(TABLE_ADMIN, new String[] { KEY_ADMIN_ID,
				KEY_ADMIN_PASS }, KEY_ADMIN_ID + " = '" + string + "'", null,
				null, null, null);
		try {
			if (cursor.moveToFirst()) {
				return new Admin(cursor.getString(0), cursor.getString(1));
			}
			return null;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public void ubahAdmin(String pass) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("update " + TABLE_ADMIN + " set " + KEY_ADMIN_PASS + " = '"
				+ pass + "' where " + KEY_ADMIN_ID + " = 'admin'");

		db.close();
	}

	public Admin getAdmin(String id) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query(TABLE_ADMIN, new String[] { KEY_ADMIN_ID,
				KEY_ADMIN_PASS }, KEY_ADMIN_PASS + " = '" + id + "'", null,
				null, null, null);
		try {
			if (cursor.moveToFirst()) {
				return new Admin(cursor.getString(0), cursor.getString(1));
			}
			return null;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public List<Admin> getAllAdmin() {
		ArrayList<Admin> admins = new ArrayList<Admin>();

		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("Select * from " + TABLE_ADMIN, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					admins.add(new Admin(cursor.getString(0), cursor
							.getString(1)));
				} while (cursor.moveToNext());
			}
			return admins;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	/*
	 * 
	 * function untuk akses ke dalam table gejala insertGejala() getGejala()
	 * getAllGejala()
	 */

	public String getIDGejala(String Pertanyaan) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("select " + KEY_GEJALA_ID + " from "
				+ TABLE_GEJALA + " where " + KEY_GEJALA_PERTANYAAN + " = '"
				+ Pertanyaan + "'", null);
		try {
			if (cursor.moveToFirst()) {
				return cursor.getString(0);
			}
			return null;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public String getNewIDGejala() {
		SQLiteDatabase db = getReadableDatabase();

		String sqlString = "select substr(" + KEY_GEJALA_ID
				+ ",0,4), (max(substr(" + KEY_GEJALA_ID + ",4, 6)) + 1) from "
				+ TABLE_GEJALA;
		Cursor cursor = db.rawQuery(sqlString, null);
		try {
			if (cursor.moveToFirst()) {
				android.util.Log.d("", sqlString);
				String number = "000" + cursor.getString(1);
				return cursor.getString(0)
						+ new String(number).substring(number.length() - 3,
								number.length());
			}

			return "";
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public boolean insertGejala(Gejala gejala) {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.execSQL("insert into " + TABLE_GEJALA + " values ('"
					+ gejala.getId() + "','" + gejala.getNama() + "','"
					+ gejala.getPertanyaan() + "')");

			db.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public void delGejala(String id) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("delete from " + TABLE_GEJALA + " where " + KEY_GEJALA_ID
				+ " = '" + id + "'");
	}

	public void updateGejala(Gejala gejala) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("update " + TABLE_GEJALA + " set " + KEY_GEJALA_NAMA
				+ " = '" + gejala.getNama() + "'," + KEY_GEJALA_PERTANYAAN
				+ " = '" + gejala.getPertanyaan() + "' where " + KEY_GEJALA_ID
				+ " = '" + gejala.getId() + "'");

		db.close();
	}

	public Gejala getGejala(String id) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query(TABLE_GEJALA, new String[] { KEY_GEJALA_ID,
				KEY_GEJALA_NAMA, KEY_GEJALA_PERTANYAAN }, KEY_GEJALA_ID
				+ " = '" + id + "'", null, null, null, null);
		try {

			if (cursor.moveToFirst()) {
				return new Gejala(cursor.getString(0), cursor.getString(1),
						cursor.getString(2));
			}

			db.close();
			return null;
		} finally {
			cursor.close();
		}
	}

	public List<Gejala> getAllGejala() {
		ArrayList<Gejala> gejalas = new ArrayList<Gejala>();

		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("Select * from " + TABLE_GEJALA, null);

		try {
			if (cursor.moveToFirst()) {
				do {
					gejalas.add(new Gejala(cursor.getString(0), cursor
							.getString(1), cursor.getString(2)));
				} while (cursor.moveToNext());
			}
			return gejalas;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	/*
	 * function untuk akses ke dalam table gangguan insertGangguan()
	 * getGanggguan() getAllGangguan()
	 */

	public String getIDGangguan(String NamaGangguan) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("select " + KEY_GANGGUAN_ID + " from "
				+ TABLE_GANGGUAN + " where " + KEY_GANGGUAN_NAMA + " = '"
				+ NamaGangguan + "'", null);
		try {
			if (cursor.moveToFirst()) {
				return cursor.getString(0);
			}
			return null;
		} finally {
			cursor.close();
		}

	}

	public String getNewIDGangguan() {
		SQLiteDatabase db = getReadableDatabase();

		String sqlString = "select substr(" + KEY_GANGGUAN_ID
				+ ",0,4), (max(substr(" + KEY_GANGGUAN_ID
				+ ",4, 6)) + 1) from " + TABLE_GANGGUAN;
		Cursor cursor = db.rawQuery(sqlString, null);
		try {
			if (cursor.moveToFirst()) {
				android.util.Log.d("", sqlString);
				String number = "000" + cursor.getString(1);
				return cursor.getString(0)
						+ new String(number).substring(number.length() - 3,
								number.length());
			}

			return "";
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public boolean insertGangguan(Gangguan gangguan) {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.execSQL("insert into " + TABLE_GANGGUAN + " values ('"
					+ gangguan.getId() + "','" 
					+ gangguan.getNama() + "','"
					+ gangguan.getSaran() + "' , '"
					+ gangguan.getPengertian() + "')");

			db.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public void updateGangguan(Gangguan gangguan) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("update " + TABLE_GANGGUAN + " set " 
				+ KEY_GANGGUAN_NAMA	+ " = '" + gangguan.getNama() + "'," 
				+ KEY_GANGGUAN_SARAN + " = '" + gangguan.getSaran() + "'," 
				+ KEY_GANGGUAN_PENGERTIAN + " = '" + gangguan.getPengertian()
				+ "' where " + KEY_GANGGUAN_ID	+ " = '" + gangguan.getId() + "'");

		db.close();
	}

	public void delGangguan(String id) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("delete from " + TABLE_GANGGUAN + " where "
				+ KEY_GANGGUAN_ID + " = '" + id + "'");
	}

	public Gangguan getGangguan(String id) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query(TABLE_GANGGUAN, new String[] {
				KEY_GANGGUAN_ID, KEY_GANGGUAN_NAMA, KEY_GANGGUAN_SARAN, KEY_GANGGUAN_PENGERTIAN },
				KEY_GANGGUAN_ID + " = '" + id + "'", null, null, null, null);
		try {
			if (cursor.moveToFirst()) {

				return new Gangguan(cursor.getString(0), cursor.getString(1),
						cursor.getString(2), cursor.getString(3));
			}
			return null;
		} finally {
			cursor.close();
		}

	}

	public List<Gangguan> getAllGangguan() {
		ArrayList<Gangguan> gangguans = new ArrayList<Gangguan>();

		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * from " + TABLE_GANGGUAN, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					gangguans.add(new Gangguan(cursor.getString(0), cursor
							.getString(1), cursor.getString(2), cursor.getString(3)));
				} while (cursor.moveToNext());
			}
			return gangguans;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	/*
	 * 
	 * function untuk akses ke dalam table rule insertRule() getRule()
	 * getAllRule()
	 */

	public String getNewIDRule() {
		SQLiteDatabase db = getReadableDatabase();

		String sqlString = "select substr(" + KEY_RULE_ID
				+ ",0,4), (max(substr(" + KEY_RULE_ID + ",4, 6)) + 1) from "
				+ TABLE_RULE;
		Cursor cursor = db.rawQuery(sqlString, null);
		try {
			if (cursor.moveToFirst()) {
				android.util.Log.d("", sqlString);
				String number = "000" + cursor.getString(1);
				return cursor.getString(0)
						+ new String(number).substring(number.length() - 3,
								number.length());
			}

			return "";
		} finally {
			// TODO: handle exception-
			cursor.close();
		}

	}

	public void updateRuleKondisi(String ruleBaru, String ruleLama) {
		SQLiteDatabase db = getWritableDatabase();
		
		String sqlString = "update " + TABLE_RULE + " set " + KEY_RULE_CURRENT + " = '"
				+ ruleBaru + "' where " + KEY_RULE_CURRENT + " = '"
				+ ruleLama + "'";
try {
	db.execSQL(sqlString);
} catch (Exception e) {
	// TODO: handle exception
	Log.d(sqlString, sqlString);
}
		

		db.close();
	}

	public void updateRuleTrue(Rule Rule) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("update " + TABLE_RULE + " set " + KEY_RULE_TRUE + " = '"
				+ Rule.getTrueString() + "' where " + KEY_RULE_CURRENT + " = '"
				+ Rule.getCurrent() + "'");

		db.close();
	}

	public void updateRuleFalse(Rule Rule) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("update " + TABLE_RULE + " set " + KEY_RULE_FALSE + " = '"
				+ Rule.getFalseString() + "' where " + KEY_RULE_CURRENT
				+ " = '" + Rule.getCurrent() + "'");

		db.close();
	}

	public boolean insertRule(Rule Rule) {
		SQLiteDatabase db = getWritableDatabase();

		try {
			db.execSQL("insert into " + TABLE_RULE + " values ('"
					+ Rule.getCurrent() + "','" + Rule.getTrueString() + "','"
					+ Rule.getFalseString() + "')");
			db.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void delRule(String id) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("delete from " + TABLE_RULE + " where " + KEY_RULE_CURRENT
				+ " = '" + id + "'");
	}

	public Rule getRule(String id) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query(TABLE_RULE, new String[] { KEY_RULE_CURRENT,
				KEY_RULE_TRUE, KEY_RULE_FALSE}, KEY_RULE_CURRENT
				+ " = '" + id + "'", null, null, null, null);
		android.util.Log.d(id, String.valueOf(cursor.getCount()));
		try {
			if (cursor.moveToFirst()) {

				return new Rule(cursor.getString(0), cursor.getString(1),
						cursor.getString(2));
			}
			return null;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public List<Rule> getAllRule() {
		ArrayList<Rule> Rules = new ArrayList<Rule>();

		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("Select * from " + TABLE_RULE, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					Rules.add(new Rule(cursor.getString(0),
							cursor.getString(1), cursor.getString(2)));
				} while (cursor.moveToNext());
			}
			return Rules;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public String getRuleId(String current) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("select " + KEY_RULE_ID + " from "
				+ TABLE_RULE + " where " + KEY_RULE_CURRENT + " = '" + current
				+ "'", null);
		try {
			if (cursor.moveToFirst()) {
				return cursor.getString(0);
			}
			return null;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public String getIDFromTrue(String id) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery(
				"select " + KEY_RULE_ID + " from " + TABLE_RULE + " where "
						+ KEY_RULE_CURRENT + " = '" + id + "'", null);
		try {
			if (cursor.moveToFirst()) {
				return cursor.getString(0);
			}
			return null;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}

	public String getIDFromFalse(String id) {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery(
				"select " + KEY_RULE_ID + " from " + TABLE_RULE + " where "
						+ KEY_RULE_CURRENT + " = '" + id + "'", null);
		try {
			if (cursor.moveToFirst()) {
				return cursor.getString(0);
			}
			return null;
		} finally {
			// TODO: handle exception
			cursor.close();
		}

	}
}
