package com.xiaba2.core;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author goddie
 * UUID作为主键的实体基类
 */
@MappedSuperclass
public abstract class BaseUUIDEntity extends BaseEntity<UUID> {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;


	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}
}
