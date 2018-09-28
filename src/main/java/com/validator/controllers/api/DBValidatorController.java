package com.validator.controllers.api;

import com.validator.application.domain.QRCodeInfo;
import com.validator.application.usecase.MysqlAliDBOpUsecase;
import com.validator.application.usecase.MysqlAzureDBOpUsecase;
import com.validator.application.usecase.PostgresqlAliDBOpUsecase;
import com.validator.application.usecase.PostgresqlAzureDBOpUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBValidatorController {
  @Autowired
  //  QRCodeInfoRepository qrCodeInfoRepository;
  MysqlAliDBOpUsecase mysqlAliDBOpUsecase;

  @Autowired PostgresqlAliDBOpUsecase postgresqlAliDBOpUsecase;

  @Autowired PostgresqlAzureDBOpUsecase postgresqlAzureDBOpUsecase;

  @Autowired MysqlAzureDBOpUsecase mysqlAzureDBOpUsecase;

  @GetMapping(path = "/mysql-ali/all")
  public @ResponseBody Iterable<QRCodeInfo> getAllUsersFromMysqlAliDB() {
    // This returns a JSON or XML with the users
    //          return qrCodeInfoRepository.findAll();
    return mysqlAliDBOpUsecase.findAll();
  }

  @GetMapping(path = "/mysql-ali/search")
  public @ResponseBody QRCodeInfo findByIdFromMysqlAliDB(
      @RequestParam(value = "id") String auto_navi_id) {
    // This returns a JSON or XML with the users
    //          return qrCodeInfoRepository.findById(auto_navi_id);
    return mysqlAliDBOpUsecase.searchById(auto_navi_id);
  }

  @GetMapping(path = "/postgresql-ali/all")
  public @ResponseBody Iterable<QRCodeInfo> getAllUsersFromPostgresqlAliDB() {
    // This returns a JSON or XML with the users
    return postgresqlAliDBOpUsecase.findAll();
  }

  @GetMapping(path = "/postgresql-ali/search")
  public @ResponseBody QRCodeInfo findByIdFromPostgresqlAliDB(
      @RequestParam(value = "id") String auto_navi_id) {
    // This returns a JSON or XML with the users
    return postgresqlAliDBOpUsecase.searchById(auto_navi_id);
  }

  @GetMapping(path = "/mysql-azure/all")
  public @ResponseBody Iterable<QRCodeInfo> getAllUsersFromMysqlAzureDB() {
    // This returns a JSON or XML with the users
    return mysqlAzureDBOpUsecase.findAll();
  }

  @GetMapping(path = "/mysql-azure/search")
  public @ResponseBody QRCodeInfo findByIdFromMysqlAzureDB(
      @RequestParam(value = "id") String auto_navi_id) {
    // This returns a JSON or XML with the users
    return mysqlAzureDBOpUsecase.searchById(auto_navi_id);
  }

  @GetMapping(path = "/postgresql-azure/all")
  public @ResponseBody Iterable<QRCodeInfo> getAllUsersFromPostgresqlAzureDB() {
    // This returns a JSON or XML with the users
    return postgresqlAzureDBOpUsecase.findAll();
  }

  @GetMapping(path = "/postgresql-azure/search")
  public @ResponseBody QRCodeInfo findByIdFromPostgresqlAzureDB(
      @RequestParam(value = "id") String auto_navi_id) {
    // This returns a JSON or XML with the users
    return postgresqlAzureDBOpUsecase.searchById(auto_navi_id);
  }
}
