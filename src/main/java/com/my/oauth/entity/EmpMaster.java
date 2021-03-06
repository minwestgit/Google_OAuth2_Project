package com.my.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="emp_master")
public class EmpMaster extends BaseTimeEntity
{
  @Id
  private String email;
  
  @Column(nullable = false, name = "domain_id")
  private String domainId;
  
  @Column(nullable = false, name = "company_cd")
  private String companyCd;
  
  @Column(nullable = false, name = "emp_nm")
  private String empNm;
  
  @Column(name = "emp_eng_nm")
  private String empEngNm;
  
  @Column(name = "emp_no")
  private String empNo;
  
  @Column(nullable = true, name = "pos_cd")
  private String posCd;
  
  @Column(nullable = true, name = "dept_cd")
  private String deptCd;
  
  @Column(nullable = true, name = "emp_status_cd")
  private String empStatusCd;
  
  @Column(name = "enter_dt")
  private String enterDt;
  
  @Column(name = "quit_dt")
  private String quitDt;
  
  @Column(name = "hidden_yn")
  private String hiddenYn;
  
  @Column(name = "job_tel_no")
  private String jobTelNo;
  
  @Column(name = "mobile_tel_no")
  private String mobileTelNo;
  
  @Column(name = "manual_mng_yn")
  private String manualMngYn;
  
  @Column(name = "update_usr")
  private String updateUsr;
  
  @Column(name = "create_usr")
  private String createUsr;
  
  // @@ 1:N 관계에서 EMP는 N이므로 ManyToOne 사용
  @ManyToOne
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumns({
    @JoinColumn(name = "domain_id",   referencedColumnName = "domain_id",   insertable = false, updatable = false),
    @JoinColumn(name = "company_cd",  referencedColumnName = "company_cd",  insertable = false, updatable = false),
    @JoinColumn(name = "dept_cd",     referencedColumnName = "dept_cd",     insertable = false, updatable = false)
  })
  private DeptMaster deptMaster;
  
  // @@ 1:N 관계에서 EMP는 N이므로 ManyToOne 사용
  @ManyToOne
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumns({
    @JoinColumn(name = "domain_id",   referencedColumnName = "domain_id",   insertable = false, updatable = false),
    @JoinColumn(name = "company_cd",  referencedColumnName = "company_cd",  insertable = false, updatable = false)
  })
  private CompanyMaster companyMaster;

  // @@ 1:1 관계
  @OneToOne
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
  private EmpConfig empConfig;
  
  @Builder
  public EmpMaster(String email, String domainId, String companyCd, String empNm, String empEngNm, String empNo, String posCd, String deptCd, 
      String empStatusCd, String enterDt, String quitDt, String hiddenYn, String jobTelNo, String mobileTelNo, String manualMngYn, String updateUsr, String createUsr) 
  {
      this.email        = email;
      this.domainId     = domainId;
      this.companyCd    = companyCd;
      this.empNm        = empNm;
      this.empEngNm     = empEngNm;
      this.empNo        = empNo;
      this.posCd        = posCd;
      this.deptCd       = deptCd;
      this.empStatusCd  = empStatusCd;
      this.enterDt      = enterDt;
      this.quitDt       = quitDt;
      this.hiddenYn     = hiddenYn;
      this.jobTelNo     = jobTelNo;
      this.mobileTelNo  = mobileTelNo;
      this.manualMngYn  = manualMngYn;
      this.updateUsr    = updateUsr;
      this.createUsr    = createUsr;
  }
}
