package com.github.sgeorgiev24.sisosocialnetwork.data.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntity {
  private String id;

  @Id
  @GeneratedValue(generator = "uuid-string")
  @GenericGenerator(
          name = "uuid-string",
          strategy = "org.hibernate.id.UUIDGenerator"
  )
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
