package com.my.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name= "dept_master")
@IdClass(DeptMasterKey.class)
@Entity
public class DeptMaster extends BaseTimeEntity {
	
    @Id
    private String domainId;
    
    @Id
    private String companyCd;
    
    @Id
    private String deptCd;
   
    @Column(nullable = false)
    private String deptNm;
    
    @Column
    private String deptEngNm;
    
    @Column(nullable = false)
    private String parentDeptCd;
    
    @Column
    private int deptOrd;
    
    @Column
    private String updateUsr;
    
    @Column
    private String createUsr;

    @Builder
    public DeptMaster(String domainId, String companyCd, String deptCd, String deptNm, String deptEngNm, String parentDeptCd, int deptOrd, String updateUsr, String createUsr) {
        this.domainId = domainId;
        this.companyCd = companyCd;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
        this.deptEngNm = deptEngNm;
        this.parentDeptCd = parentDeptCd;
        this.deptOrd = deptOrd;
        this.updateUsr = updateUsr;
        this.createUsr = createUsr;
    }
  
}