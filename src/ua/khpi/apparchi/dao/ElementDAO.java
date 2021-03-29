package ua.khpi.apparchi.dao;

import java.util.ArrayList;
import java.util.List;

import ua.khpi.apparchi.dao.api.IElementDAO;
import ua.khpi.apparchi.entity.api.IGenericEntity;

public class ElementDAO implements IElementDAO {
	private List<IGenericEntity> objects;

	public ElementDAO() {
		this.objects = new ArrayList<>();
	}

	@Override
	public void create(IGenericEntity obj) {
		this.objects.add(obj);
	}

	@Override
	public IGenericEntity read(String id) {
		throw new IllegalAccessError();
	}

	@Override
	public List<IGenericEntity> readAll() {
		return this.objects;
	}

	@Override
	public void update(String id, IGenericEntity obj) {
		throw new IllegalAccessError();
	}

	@Override
	public void delete(String id) {
		throw new IllegalAccessError();
	}

}
