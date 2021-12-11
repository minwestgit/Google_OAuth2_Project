package com.my.oauth.entity;

import java.io.Serializable;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.oauth2.sdk.util.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SessionUser implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private String email;
  private String domainId;
  private String companyCd;
  private String companyNm;
  private String companyEngNm;
  private String companyLangCdNm;
  private String empNm;
  private String empEngNm;
  private String empLangCdNm;
  private String empNo;
  private String posCd;
  private String deptCd;
  private String empStatusCd;
  private String enterDt;
  private String quitDt;
  private String hiddenYn;
  private String jobTelNo;
  private String mobileTelNo;
  private String deptNm;
  private String deptEngNm;
  private String deptLangCdNm;
  private String companyLangCd;
  private String manualMngYn;
  private String languageCd;

  public SessionUser() {}
  
  public SessionUser(Optional<EmpMaster> empMaster)
  {
    if (empMaster.isPresent())
    {
      this.email        = empMaster.get().getEmail();
      this.domainId     = empMaster.get().getDomainId();
      this.companyCd    = empMaster.get().getCompanyCd();
      this.empNm        = empMaster.get().getEmpNm();
      this.empEngNm     = empMaster.get().getEmpEngNm();
      this.empNo        = empMaster.get().getEmpNo();
      this.posCd        = empMaster.get().getPosCd();
      this.deptCd       = empMaster.get().getDeptCd();
      this.empStatusCd  = empMaster.get().getEmpStatusCd();
      this.enterDt      = empMaster.get().getEnterDt();
      this.quitDt       = empMaster.get().getQuitDt();
      this.hiddenYn     = empMaster.get().getHiddenYn();
      this.jobTelNo     = empMaster.get().getJobTelNo();
      this.mobileTelNo  = empMaster.get().getMobileTelNo();
      this.manualMngYn  = empMaster.get().getManualMngYn();
      
      if (empMaster.get().getCompanyMaster() != null)
      {
        this.companyNm     = empMaster.get().getCompanyMaster().getCompanyNm();
        this.companyEngNm  = empMaster.get().getCompanyMaster().getCompanyEngNm();
        this.companyLangCd = StringUtils.isBlank(empMaster.get().getCompanyMaster().getLanguageCd()) ? C.KOR : empMaster.get().getCompanyMaster().getLanguageCd();
      }
      else
      {
        this.companyNm     = "";
        this.companyEngNm  = "";
        this.companyLangCd = "ko-KR";
      }
      
      if (empMaster.get().getDeptMaster() != null)
      {
        this.deptNm     = empMaster.get().getDeptMaster().getDeptNm();
        this.deptEngNm  = empMaster.get().getDeptMaster().getDeptEngNm();
      }
      else 
      {
        this.deptNm     = "";
        this.deptEngNm  = "";
      }
      
      if (empMaster.get().getEmpConfig() != null)
      {
        this.languageCd = StringUtils.isBlank(empMaster.get().getEmpConfig().getLanguageCd()) ? this.companyLangCd : empMaster.get().getEmpConfig().getLanguageCd();
      }
      else
      {
        this.languageCd = this.companyLangCd;
      }
      
      if ("ko-KR".equals(this.languageCd))
      {
        this.companyLangCdNm = this.companyNm;
        this.deptLangCdNm = this.deptNm;
        this.empLangCdNm = this.empNm;
      }
      else if ("en-US".equals(this.languageCd))
      {
        this.companyLangCdNm = !StringUtils.isBlank(this.companyEngNm) ? this.companyEngNm : this.companyNm;
        this.deptLangCdNm = !StringUtils.isBlank(this.deptEngNm) ? this.deptEngNm : this.deptNm;
        this.empLangCdNm = !StringUtils.isBlank(this.empEngNm) ? this.empEngNm : this.empNm;
      }
      else
      {
        this.companyLangCdNm = this.companyNm;
        this.deptLangCdNm = this.deptNm;
        this.empLangCdNm = this.empNm;
      }
    }
  }
}