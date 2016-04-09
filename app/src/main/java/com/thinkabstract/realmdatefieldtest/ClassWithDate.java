package com.thinkabstract.realmdatefieldtest;

import io.realm.RealmObject;
import java.util.Date;

public class ClassWithDate extends RealmObject {

  private Date date;

  public ClassWithDate() {
  }

  public ClassWithDate(Date date) {
    this.date = date;
  }

  public Date getDate() {
    return date;
  }

  @Override public String toString() {
    return "ClassWithDate{" +
        "date=" + date +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ClassWithDate that = (ClassWithDate) o;

    return date != null ? date.equals(that.date) : that.date == null;
  }

  @Override public int hashCode() {
    return date != null ? date.hashCode() : 0;
  }
}
