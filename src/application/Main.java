package application;

import java.sql.Connection;

import db.DB;
import model.dao.AgendaDao;
import model.dao.DaoFactory;
import model.entities.Agenda;

public class Main {

	public static void main(String[] args) {
		
		try {
			Connection conn = DB.getConnection();
		
			AgendaDao agendaDao = DaoFactory.createAgendaDao();
			
			Agenda newAgenda = new Agenda(null, "Ana Beatriz", "3785-8426", "anabeatriz@gmail.com");
			agendaDao.insert(newAgenda);
			System.out.println("Inserted! New id: " + newAgenda.getId());
		}
		finally {
			DB.closeConnection();
		}
	}
}
