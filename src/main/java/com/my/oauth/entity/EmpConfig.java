package com.my.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "emp_config")
public class EmpConfig extends BaseTimeEntity
{
  @Id
  private String email;
  
  @Column(nullable = false, name = "domain_id")
  private String domainId;
  
  @Column(nullable = false, name = "company_cd")
  private String companyCd;
  
  @Column(name = "language_cd")
  private String languageCd;
  
  @Column(name = "update_usr")
  private String updateUsr;
  
  @Column(name = "create_usr")
  private String createUsr;
  
  @Builder
  public EmpConfig(String email, String domain_id, String company_cd, String language_cd, String update_usr, String create_usr) 
  {
      this.email        = email;
      this.domainId     = domain_id;
      this.companyCd    = company_cd;
      this.languageCd   = language_cd;
      this.updateUsr    = update_usr;
      this.createUsr    = create_usr;
  }
}
