package com.validator.application.domain;

import org.springframework.stereotype.Component;

@Component
public class QRCodeInfo {
  //    @Id
  private String auto_navi_id;
  private String name;
  private String image_url;

  public String getAuto_navi_id() {
    return auto_navi_id;
  }

  public void setAuto_navi_id(String auto_navi_id) {
    this.auto_navi_id = auto_navi_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }
}
