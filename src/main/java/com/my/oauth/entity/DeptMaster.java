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
    private String domain_id;
    
    @Id
    private String company_cd;
    
    @Id
    private String dept_cd;
   
    @Column(nullable = false)
    private String dept_nm;
    
    @Column
    private String dept_eng_nm;
    
    @Column(nullable = false)
    private String parent_dept_cd;
    
    @Column
    private int dept_ord;
    
    @Column
    private String update_usr;
    
    @Column
    private String create_usr;

    @Builder
    public DeptMaster(String domain_id, String company_cd, String dept_cd, String dept_nm, String dept_eng_nm, String parent_dept_cd, int dept_ord, String update_usr, String create_usr) {
        this.domain_id = domain_id;
        this.company_cd = company_cd;
        this.dept_cd = dept_cd;
        this.dept_nm = dept_nm;
        this.dept_eng_nm = dept_eng_nm;
        this.dept_cd = dept_cd;
        this.parent_dept_cd = parent_dept_cd;
        this.dept_ord = dept_ord;
        this.update_usr = update_usr;
        this.create_usr = create_usr;
    }
  
}