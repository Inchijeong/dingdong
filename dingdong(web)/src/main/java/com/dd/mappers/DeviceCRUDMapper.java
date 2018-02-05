package com.dd.mappers;

public interface DeviceCRUDMapper<E,K> {
	public void create(E vo);
	public E read(K key);
	public void update(E vo);
	public void delete(String noti_token);
}