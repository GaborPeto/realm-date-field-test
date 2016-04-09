package com.thinkabstract.realmdatefieldtest;

import android.support.test.rule.ActivityTestRule;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RealmOperatorTest {

  private static final Date DATE = new Date();

  @Rule public ActivityTestRule<MainActivity> activityRule =
      new ActivityTestRule(MainActivity.class);

  private RealmConfiguration configuration;
  private RealmOperator operator;

  @Before public void setUp() throws Exception {
    configuration = buildConfiguration();
    operator = new RealmOperator(activityRule.getActivity());
  }

  @After public void tearDown() throws Exception {
    deleteRealmObjects();
  }

  @Test public void testDateField() throws Exception {
    ClassWithDate instance = new ClassWithDate(DATE);

    operator.save(instance);

    ClassWithDate retrievedInstance = operator.load();

    assertEquals(instance.getDate().getTime(), retrievedInstance.getDate().getTime());
  }

  private void deleteRealmObjects() {
    Realm realm = getRealm();
    realm.beginTransaction();
    realm.deleteAll();
    realm.commitTransaction();
    realm.close();
  }

  private Realm getRealm() {
    return Realm.getInstance(configuration);
  }

  private RealmConfiguration buildConfiguration() {
    return new RealmConfiguration.Builder(activityRule.getActivity()).deleteRealmIfMigrationNeeded()
        .build();
  }
}