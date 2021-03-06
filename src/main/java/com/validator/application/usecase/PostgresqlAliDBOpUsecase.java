package com.validator.application.usecase;

import com.validator.application.domain.QRCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostgresqlAliDBOpUsecase {
  @Autowired
  @Qualifier("postgresqlAliJdbcTemplate")
  private JdbcTemplate postgresqlAliJdbcTemplate;

  public QRCodeInfo searchById(String auto_navi_id) throws RuntimeException {
    String query =
        String.format("select * from qrcode_info where auto_navi_id = '%s' ", auto_navi_id);

    return (QRCodeInfo)
        postgresqlAliJdbcTemplate.queryForObject(
            query, new BeanPropertyRowMapper(QRCodeInfo.class));
  }

  public Iterable<QRCodeInfo> findAll() throws RuntimeException {
    String query = "select * from qrcode_info";

    return (Iterable<QRCodeInfo>)
        postgresqlAliJdbcTemplate.query(query, new BeanPropertyRowMapper(QRCodeInfo.class));
  }
}
