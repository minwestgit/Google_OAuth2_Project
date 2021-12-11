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
@Table(name= "emp_authority")
@IdClass(EmpAuthorityKey.class)
@Entity
public class EmpAuthority extends BaseTimeEntity
{
  @Id
  private String email;
  
  @Id
  @Column(name = "authority_name")
  private String authorityName;
  
  @Column(nullable = false, name = "domain_id")
  private String domainId;
  
  @Column(nullable = false, name = "company_cd")
  private String companyCd;
  
  @Column(name = "update_usr")
  private String updateUsr;
  
  @Column(name = "create_usr")
  private String createUsr;
  
  @Builder
  public EmpAuthority(String email, String authorityName, String domainId, String companyCd, String updateUsr, String createUsr) 
  {
      this.email          = email;
      this.authorityName  = authorityName;
      this.domainId       = domainId;
      this.companyCd      = companyCd;
      this.updateUsr      = updateUsr;
      this.createUsr      = createUsr;
  }
}
