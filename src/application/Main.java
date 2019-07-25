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
			
			Agenda newAgenda1 = new Agenda(null, "Thiago", "3758-7784", "thiago@gmail.com");
			agendaDao.insert(newAgenda1);
			System.out.println("Inserted! New id: " + newAgenda1.getId());
			
			Agenda newAgenda2 = new Agenda(null, "Ellís", "3455-8126", "ellis@gmail.com");
			agendaDao.insert(newAgenda2);
			System.out.println("Inserted! New id: " + newAgenda2.getId());
			
			
			Agenda findName = agendaDao.findByName("Ana Beatriz");
			System.out.println(findName);
			
			Agenda findId = agendaDao.findById(2);
			System.out.println(findId);
		}
		finally {
			DB.closeConnection();
		}
	}
}
