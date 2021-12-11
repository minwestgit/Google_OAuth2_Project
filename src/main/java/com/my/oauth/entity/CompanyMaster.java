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
  public CompanyMaster(String domainId, String companyCd, String companyNm, String companyEngNm, String languageCd, String useYn, String updateUsr, String createUsr) 
  {
      this.domainId       = domainId;
      this.companyCd      = companyCd;
      this.companyNm      = companyNm;
      this.companyEngNm   = companyEngNm;
      this.languageCd     = languageCd;
      this.useYn          = useYn;
      this.updateUsr      = updateUsr;
      this.createUsr      = createUsr;
  }
}
