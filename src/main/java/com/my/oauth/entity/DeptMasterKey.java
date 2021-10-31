package com.my.oauth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class DeptMasterKey implements Serializable {
	
    @Column
    private String domain_id;

    @Column
    private String company_cd;
    
    @Column
    private String dept_cd;
}