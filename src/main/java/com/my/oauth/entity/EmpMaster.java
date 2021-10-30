package com.my.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name= "emp_master")
@Entity
public class EmpMaster extends BaseTimeEntity {
	
    @Id
    private String email;
    
    @Column(nullable = false)
    private String domain_id;
    
    @Column(nullable = false)
    private String company_cd;
    
    @Column(nullable = false)
    private String emp_nm;
    
    @Column
    private String emp_eng_nm;
    
    @Column
    private String pos_cd;
    
    @Column
    private String dept_cd;
    
    @Column(nullable = false)
    private String emp_status_cd;
    
    @Column
    private String enter_dt;
    
    @Column
    private String quit_dt;
    
    @Column
    private String hidden_yn;
    
    @Column
    private String job_tel_no;
    
    @Column
    private String mobile_tel_no;
    
    @Column
    private String update_usr;
    
    @Column
    private String create_usr;

    @Builder
    public EmpMaster(String email, String domain_id, String company_cd, String emp_nm, String emp_eng_nm, String pos_cd, String dept_cd, 
    		String emp_status_cd, String enter_dt, String quit_dt, String hidden_yn, String job_tel_no, String mobile_tel_no, String update_usr, String create_usr) {
        this.email = email;
        this.domain_id = domain_id;
        this.company_cd = company_cd;
        this.emp_nm = emp_nm;
        this.emp_eng_nm = emp_eng_nm;
        this.pos_cd = pos_cd;
        this.dept_cd = dept_cd;
        this.emp_status_cd = emp_status_cd;
        this.enter_dt = enter_dt;
        this.quit_dt = quit_dt;
        this.hidden_yn = hidden_yn;
        this.job_tel_no = job_tel_no;
        this.mobile_tel_no = mobile_tel_no;
        this.update_usr = update_usr;
        this.create_usr = create_usr;
    }
    
}