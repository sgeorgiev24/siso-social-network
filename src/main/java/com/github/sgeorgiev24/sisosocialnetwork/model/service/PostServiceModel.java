package com.github.sgeorgiev24.sisosocialnetwork.model.service;

import com.github.sgeorgiev24.sisosocialnetwork.data.entity.Post;
import com.github.sgeorgiev24.sisosocialnetwork.data.entity.User;
import com.github.sgeorgiev24.sisosocialnetwork.mapping.IHaveCustomMappings;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class PostServiceModel implements IHaveCustomMappings {
  private String content;
  private LocalDateTime dateCreated;
  private String creator;

  public PostServiceModel() {
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

  @Override
  public void configureMappings(ModelMapper mapper) {
    mapper.createTypeMap(Post.class, PostServiceModel.class)
            .addMapping(
                    entity -> entity.getCreator().getUsername(),
                    (dto, value) -> dto.setCreator((String) value)
            );
  }
}
