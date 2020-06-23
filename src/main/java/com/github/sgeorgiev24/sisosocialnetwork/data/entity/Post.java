package com.github.sgeorgiev24.sisosocialnetwork.data.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
  private String content;
  private LocalDateTime dateCreated;
  private User creator;

  public Post() {
  }

  @Column(name = "content", nullable = false, length = 2000)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Column(name = "date_created", nullable = false)
  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  @ManyToOne
  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }
}
