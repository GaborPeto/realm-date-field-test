package com.thinkabstract.realmdatefieldtest;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import java.util.List;

public class RealmOperator {

  private final RealmConfiguration configuration;

  public RealmOperator(Context context) {
    this.configuration = buildConfiguration(context);
  }

  public void save(ClassWithDate instance) {
    Realm realm = getRealm();
    realm.beginTransaction();
    realm.copyToRealm(instance);
    realm.commitTransaction();
    realm.close();
  }

  public ClassWithDate load() {
    ClassWithDate classWithDate;
    Realm realm = getRealm();
    List<ClassWithDate> result = realm.copyFromRealm(realm.allObjects(ClassWithDate.class));
    classWithDate = result.get(0);
    realm.close();
    return classWithDate;
  }

  private Realm getRealm() {
    return Realm.getInstance(configuration);
  }

  private RealmConfiguration buildConfiguration(Context context) {
    return new RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded().build();
  }
}
