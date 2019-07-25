package model.dao;

import java.util.List;

import model.entities.Agenda;

public interface AgendaDao {

	void insert(Agenda obj);
	void update(Agenda obj);
	void deleteByName(String name);
	Agenda findByName(String name);
	List<Agenda> findAll();
}
