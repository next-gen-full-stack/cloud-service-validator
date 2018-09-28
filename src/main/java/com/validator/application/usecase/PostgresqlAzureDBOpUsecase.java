package com.validator.application.usecase;

import com.validator.application.domain.QRCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostgresqlAzureDBOpUsecase {

  @Autowired
  @Qualifier("postgresqlAzureJdbcTemplate")
  private JdbcTemplate postgresqlAzureJdbcTemplate;

  public QRCodeInfo searchById(String auto_navi_id) throws RuntimeException {
    String query =
        String.format("select * from qrcode_info where auto_navi_id = '%s' ", auto_navi_id);

    return (QRCodeInfo)
        postgresqlAzureJdbcTemplate.queryForObject(
            query, new BeanPropertyRowMapper(QRCodeInfo.class));
  }

  public Iterable<QRCodeInfo> findAll() throws RuntimeException {
    String query = "select * from qrcode_info";

    return (Iterable<QRCodeInfo>)
        postgresqlAzureJdbcTemplate.query(query, new BeanPropertyRowMapper(QRCodeInfo.class));
  }
}
