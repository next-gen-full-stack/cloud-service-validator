package com.validator.controllers.api;

import com.validator.application.domain.QRCodeInfo;
import com.validator.application.usecase.MysqlAliDBOpUsecase;
import com.validator.application.usecase.MysqlAzureDBOpUsecase;
import com.validator.application.usecase.PostgresqlAliDBOpUsecase;
import com.validator.application.usecase.PostgresqlAzureDBOpUsecase;
import com.validator.beans.DBValidationResult;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @Autowired DBValidationResult mysqlAliDBValidationResult;

  @Autowired DBValidationResult postgresqlAliDBValidationResult;

  @Autowired DBValidationResult mysqlAzureDBValidationResult;

  @Autowired DBValidationResult postgresqlAzureDBValidationResult;

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

  @RequestMapping("/api/v1/ping/azure/mysql")
  public DBValidationResult mysqlAzureDBValidation(HttpServletRequest request) {
    try {
      mysqlAzureDBValidationResult.setResponseTime(0l);
      LocalDateTime startTime = LocalDateTime.now();
      mysqlAzureDBOpUsecase.findAll();
      LocalDateTime endTime = LocalDateTime.now();
      // count seconds of the query
      Duration duration = Duration.between(startTime, endTime);
      mysqlAzureDBValidationResult.setResponseTime(duration.toMillis());
    } catch (Exception e) {
      mysqlAzureDBValidationResult.setAccessibility(false);
      mysqlAzureDBValidationResult.setScalability(false);
    }

    return this.mysqlAzureDBValidationResult;
  }

  @RequestMapping("/api/v1/ping/azure/postgresql")
  public DBValidationResult postgresqlAzureDBValidation(HttpServletRequest request) {

    try {
      postgresqlAzureDBValidationResult.setResponseTime(0l);
      LocalDateTime startTime = LocalDateTime.now();
      postgresqlAzureDBOpUsecase.findAll();
      LocalDateTime endTime = LocalDateTime.now();
      // count seconds of the query
      Duration duration = Duration.between(startTime, endTime);
      postgresqlAzureDBValidationResult.setResponseTime(duration.toMillis());
    } catch (Exception e) {
      postgresqlAzureDBValidationResult.setAccessibility(false);
      postgresqlAzureDBValidationResult.setScalability(false);
    }

    return this.postgresqlAzureDBValidationResult;
  }

  @RequestMapping("/api/v1/ping/ali/mysql")
  public DBValidationResult mysqlAliDBValidation(HttpServletRequest request) {
    try {
      mysqlAliDBValidationResult.setResponseTime(0l);
      LocalDateTime startTime = LocalDateTime.now();
      mysqlAliDBOpUsecase.findAll();
      LocalDateTime endTime = LocalDateTime.now();
      // count seconds of the query
      Duration duration = Duration.between(startTime, endTime);
      mysqlAliDBValidationResult.setResponseTime(duration.toMillis());
    } catch (Exception e) {
      mysqlAliDBValidationResult.setAccessibility(false);
      mysqlAliDBValidationResult.setScalability(false);
    }
    return this.mysqlAliDBValidationResult;
  }

  @RequestMapping("/api/v1/ping/ali/postgresql")
  public DBValidationResult postgresqlAliDBValidation(HttpServletRequest request) {
    try {
      postgresqlAliDBValidationResult.setResponseTime(0l);
      LocalDateTime startTime = LocalDateTime.now();
      postgresqlAliDBOpUsecase.findAll();
      LocalDateTime endTime = LocalDateTime.now();
      // count seconds of the query
      Duration duration = Duration.between(startTime, endTime);
      postgresqlAliDBValidationResult.setResponseTime(duration.toMillis());
    } catch (Exception e) {
      postgresqlAliDBValidationResult.setAccessibility(false);
      postgresqlAliDBValidationResult.setScalability(false);
    }

    return this.postgresqlAliDBValidationResult;
  }
}
