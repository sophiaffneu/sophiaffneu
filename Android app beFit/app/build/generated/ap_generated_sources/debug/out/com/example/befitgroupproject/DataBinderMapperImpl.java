package com.example.befitgroupproject;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.befitgroupproject.databinding.ActivityFitnessTestDetailBindingImpl;
import com.example.befitgroupproject.databinding.ActivityFitnessTestHistoryBindingImpl;
import com.example.befitgroupproject.databinding.ActivityGetPlanBindingImpl;
import com.example.befitgroupproject.databinding.ActivityMainBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYFITNESSTESTDETAIL = 1;

  private static final int LAYOUT_ACTIVITYFITNESSTESTHISTORY = 2;

  private static final int LAYOUT_ACTIVITYGETPLAN = 3;

  private static final int LAYOUT_ACTIVITYMAIN = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.befitgroupproject.R.layout.activity_fitness_test_detail, LAYOUT_ACTIVITYFITNESSTESTDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.befitgroupproject.R.layout.activity_fitness_test_history, LAYOUT_ACTIVITYFITNESSTESTHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.befitgroupproject.R.layout.activity_get_plan, LAYOUT_ACTIVITYGETPLAN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.befitgroupproject.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYFITNESSTESTDETAIL: {
          if ("layout/activity_fitness_test_detail_0".equals(tag)) {
            return new ActivityFitnessTestDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_fitness_test_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYFITNESSTESTHISTORY: {
          if ("layout/activity_fitness_test_history_0".equals(tag)) {
            return new ActivityFitnessTestHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_fitness_test_history is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYGETPLAN: {
          if ("layout/activity_get_plan_0".equals(tag)) {
            return new ActivityGetPlanBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_get_plan is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "data");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/activity_fitness_test_detail_0", com.example.befitgroupproject.R.layout.activity_fitness_test_detail);
      sKeys.put("layout/activity_fitness_test_history_0", com.example.befitgroupproject.R.layout.activity_fitness_test_history);
      sKeys.put("layout/activity_get_plan_0", com.example.befitgroupproject.R.layout.activity_get_plan);
      sKeys.put("layout/activity_main_0", com.example.befitgroupproject.R.layout.activity_main);
    }
  }
}
