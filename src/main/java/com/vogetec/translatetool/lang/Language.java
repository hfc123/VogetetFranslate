package com.vogetec.translatetool.lang;

public class Language {
  private String code;
  private String androidCode;
  private boolean supported;
  private String name;

  public Language(String code, String androidCode, boolean supported, String name) {
      this.code = code;
      this.androidCode = androidCode;
      this.supported = supported;
      this.name = name;
  }

  public String getCode() {
      return code;
  }

  public void setCode(String code) {
      this.code = code;
  }

  public String getAndroidCode() {
      return androidCode;
  }

  public void setAndroidCode(String androidCode) {
      this.androidCode = androidCode;
  }

  public boolean isSupported() {
      return supported;
  }

  public void setSupported(boolean supported) {
      this.supported = supported;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  @Override
  public String toString() {
      return "Language{" +
              "code='" + code + '\'' +
              ", androidCode='" + androidCode + '\'' +
              ", supported=" + supported +
              ", name='" + name + '\'' +
              '}';
  }
}
