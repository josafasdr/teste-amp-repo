package br.uesb.sif.constraints;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.alfresco.repo.dictionary.constraint.ListOfValuesConstraint;

public class ListOfValuesQueryContraint extends ListOfValuesConstraint {

	private List<String> allowedLabels;

	public void setAllowedValues(List allowedValues) {
	}

	public void setCaseSensitive(boolean caseSensitive) {
	}

	public void initialize() {
		super.setCaseSensitive(false);
		this.loadDB();
	}

	public List<String> getAllowedLabels() {
		return this.allowedLabels;
	}

	public void setAllowedLabels(List<String> allowedLabels) {
		this.allowedLabels = allowedLabels;
	}

	public List<SelectItem> getSelectItemList() {
		List<SelectItem> result = new ArrayList<SelectItem>(this
				.getAllowedValues().size());
		for (int i = 0; i < this.getAllowedValues().size(); i++) {
			result.add(new SelectItem((Object) this.getAllowedValues().get(i),
					this.allowedLabels.get(i)));
		}
		return result;
	}

	public String getDisplayLabel(String constraintAllowableValue) {
		if (!super.getAllowedValues().contains(constraintAllowableValue)) {
			return null;
		}

		String message = null;
		List result = this.getSelectItemList();
		for (int i = 0; i < result.size(); i++) {
			if (((SelectItem) result.get(i)).getValue().equals(constraintAllowableValue)) {
				message = ((SelectItem) result.get(i)).getLabel();
			}
		}

		return message == null ? constraintAllowableValue : message;
	}

	protected void loadDB() {

		String driverName = "org.postgresql.Driver";
		String serverName = "localhost:5432";
		String mydatabase = "teste";
		String username = "postgres";
		String password = "user1pg";

		List<String> av = new ArrayList<String>();
		List<String> al = new ArrayList<String>();

		try {
			Connection connection = null;
			Class.forName(driverName);
			String url = "jdbc:postgresql://" + serverName + "/" + mydatabase;
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select codigo, nome from pessoa");
			while (rs.next()) {
				av.add(rs.getString("codigo"));
				al.add(rs.getString("nome"));
			}
		} catch (Exception e) {
		}

		super.setAllowedValues(av);
		this.setAllowedLabels(al);
	}

}
