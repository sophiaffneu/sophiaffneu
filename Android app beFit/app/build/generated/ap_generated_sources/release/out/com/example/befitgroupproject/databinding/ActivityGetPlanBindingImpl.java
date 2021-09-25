package com.example.befitgroupproject.databinding;
import com.example.befitgroupproject.R;
import com.example.befitgroupproject.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityGetPlanBindingImpl extends ActivityGetPlanBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayout2, 1);
        sViewsWithIds.put(R.id.textView, 2);
        sViewsWithIds.put(R.id.textView9, 3);
        sViewsWithIds.put(R.id.textView4, 4);
        sViewsWithIds.put(R.id.textView2, 5);
        sViewsWithIds.put(R.id.textView5, 6);
        sViewsWithIds.put(R.id.textView6, 7);
        sViewsWithIds.put(R.id.constraintLayout, 8);
        sViewsWithIds.put(R.id.textView7, 9);
        sViewsWithIds.put(R.id.textView11, 10);
        sViewsWithIds.put(R.id.textView13, 11);
        sViewsWithIds.put(R.id.radioGroup2, 12);
        sViewsWithIds.put(R.id.radioButton4, 13);
        sViewsWithIds.put(R.id.radioButton5, 14);
        sViewsWithIds.put(R.id.radioButton6, 15);
        sViewsWithIds.put(R.id.radioGroup1, 16);
        sViewsWithIds.put(R.id.radioButton7, 17);
        sViewsWithIds.put(R.id.radioButton8, 18);
        sViewsWithIds.put(R.id.radioButton9, 19);
        sViewsWithIds.put(R.id.imageButton, 20);
        sViewsWithIds.put(R.id.guideline6, 21);
        sViewsWithIds.put(R.id.textView8, 22);
        sViewsWithIds.put(R.id.button16, 23);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityGetPlanBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private ActivityGetPlanBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[23]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[8]
            , (androidx.constraintlayout.widget.Guideline) bindings[21]
            , (android.widget.ImageButton) bindings[20]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.RadioButton) bindings[13]
            , (android.widget.RadioButton) bindings[14]
            , (android.widget.RadioButton) bindings[15]
            , (android.widget.RadioButton) bindings[17]
            , (android.widget.RadioButton) bindings[18]
            , (android.widget.RadioButton) bindings[19]
            , (android.widget.RadioGroup) bindings[16]
            , (android.widget.RadioGroup) bindings[12]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.data == variableId) {
            setData((com.example.befitgroupproject.MyViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setData(@Nullable com.example.befitgroupproject.MyViewModel Data) {
        this.mData = Data;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): data
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}