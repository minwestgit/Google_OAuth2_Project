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
  public EmpAuthority(String email, String authority_name, String domain_id, String company_cd, String update_usr, String create_usr) 
  {
      this.email          = email;
      this.authorityName  = authority_name;
      this.domainId       = domain_id;
      this.companyCd      = company_cd;
      this.updateUsr      = update_usr;
      this.createUsr      = create_usr;
  }
}
