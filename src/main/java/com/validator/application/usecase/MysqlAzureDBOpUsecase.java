package com.validator.application.usecase;

import com.validator.application.domain.QRCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MysqlAzureDBOpUsecase {
  @Autowired
  @Qualifier("mysqlAzureJdbcTemplate")
  private JdbcTemplate mysqlAzureJdbcTemplate;

  public QRCodeInfo searchById(String auto_navi_id) {
    String query =
        String.format("select * from qrcode_info where auto_navi_id = '%s' ", auto_navi_id);

    return (QRCodeInfo)
        mysqlAzureJdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(QRCodeInfo.class));
  }

  public Iterable<QRCodeInfo> findAll() {
    String query = "select * from qrcode_info";

    return (Iterable<QRCodeInfo>)
        mysqlAzureJdbcTemplate.query(query, new BeanPropertyRowMapper(QRCodeInfo.class));
  }
}
