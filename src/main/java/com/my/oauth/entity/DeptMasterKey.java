package com.my.oauth.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class DeptMasterKey implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String domainId;
  private String companyCd;
  private String deptCd;
}