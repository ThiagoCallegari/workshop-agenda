package application;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.AgendaDao;
import model.dao.DaoFactory;
import model.entities.Agenda;

public class Main {

	public static void main(String[] args) {
		
		try {
			Scanner sc = new Scanner(System.in);
			
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
			
			
			Agenda person = agendaDao.findById(1);
			person.setName("Martha");
			person.setNumber("3534-4553");
			person.setEmail("martha@gmail.com");
			agendaDao.update(person);
			System.out.println("Update completed");
			

			System.out.println("Enter id for delete test: ");
			int id = sc.nextInt();
			agendaDao.deleteById(id);
			System.out.println("Delete completed");
			
			
			List<Agenda> list = agendaDao.findAll();
			for (Agenda obj : list) {
				System.out.println(obj);
				
			sc.close();
			}
		}
		finally {
			DB.closeConnection();
		}
	}
}
