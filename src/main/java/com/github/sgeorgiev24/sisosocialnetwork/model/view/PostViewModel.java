package com.github.sgeorgiev24.sisosocialnetwork.model.view;

import java.time.LocalDateTime;

public class PostViewModel {
  private String content;
  private LocalDateTime dateCreated;
  private String creator;

  public PostViewModel() {
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }
}
