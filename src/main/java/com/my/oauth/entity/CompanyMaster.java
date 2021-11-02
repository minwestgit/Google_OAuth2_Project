package com.my.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="company_master")
@IdClass(CompanyMasterKey.class)
public class CompanyMaster extends BaseTimeEntity
{
  @Id
  @Column(nullable = false, name = "domain_id")
  private String domainId;
  
  @Id
  @Column(nullable = false, name = "company_cd")
  private String companyCd;
  
  @Column(name = "company_nm")
  private String companyNm;
  
  @Column(name = "company_eng_nm")
  private String companyEngNm;
  
  @Column(nullable = false, name = "language_cd")
  private String languageCd;
  
  @Column(nullable = false, name = "use_yn")
  private String useYn;
  
  @Column(name = "update_usr")
  private String updateUsr;

  @Column(name = "create_usr")
  private String createUsr;
  
  @Builder
  public CompanyMaster(String domain_id, String company_cd, String company_nm, String company_eng_nm, String language_cd, String use_yn, String update_usr, String create_usr) 
  {
      this.domainId       = domain_id;
      this.companyCd      = company_cd;
      this.companyNm      = company_nm;
      this.companyEngNm   = company_eng_nm;
      this.languageCd     = language_cd;
      this.useYn          = use_yn;
      this.updateUsr      = update_usr;
      this.createUsr      = create_usr;
  }
}
