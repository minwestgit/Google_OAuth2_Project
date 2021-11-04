package com.my.oauth.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmpAuthorityKey implements Serializable
{ 
  private static final long serialVersionUID = 1L;
  private String email;
  private String authorityName;
}
