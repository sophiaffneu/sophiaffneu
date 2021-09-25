package com.example.befitgroupproject.databinding;
import com.example.befitgroupproject.R;
import com.example.befitgroupproject.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityFitnessTestDetailBindingImpl extends ActivityFitnessTestDetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageButtonRunInfo, 1);
        sViewsWithIds.put(R.id.imageViewRun, 2);
        sViewsWithIds.put(R.id.editTextTextRun, 3);
        sViewsWithIds.put(R.id.textViewRun, 4);
        sViewsWithIds.put(R.id.imageButtonPushup, 5);
        sViewsWithIds.put(R.id.imageViewPushup, 6);
        sViewsWithIds.put(R.id.editTextTextPersonName2, 7);
        sViewsWithIds.put(R.id.textViewPushup, 8);
        sViewsWithIds.put(R.id.cardView2, 9);
        sViewsWithIds.put(R.id.editTextBMI2, 10);
        sViewsWithIds.put(R.id.imageButtonBMIinfo, 11);
        sViewsWithIds.put(R.id.imageViewCaculate, 12);
        sViewsWithIds.put(R.id.editTextBMI, 13);
        sViewsWithIds.put(R.id.textView2Height, 14);
        sViewsWithIds.put(R.id.buttonPhysicalProfile, 15);
        sViewsWithIds.put(R.id.linearLayout, 16);
        sViewsWithIds.put(R.id.imageButton7, 17);
        sViewsWithIds.put(R.id.imageButtonHeart2, 18);
        sViewsWithIds.put(R.id.imageButtonLaunch2, 19);
        sViewsWithIds.put(R.id.imageButtonBadge2, 20);
        sViewsWithIds.put(R.id.imageButtonSetting2, 21);
        sViewsWithIds.put(R.id.buttonPlan, 22);
        sViewsWithIds.put(R.id.guideline2, 23);
        sViewsWithIds.put(R.id.guideline3, 24);
        sViewsWithIds.put(R.id.guideline4, 25);
        sViewsWithIds.put(R.id.cardView3, 26);
        sViewsWithIds.put(R.id.imageButtonCore, 27);
        sViewsWithIds.put(R.id.imageViewCore, 28);
        sViewsWithIds.put(R.id.editTextCore, 29);
        sViewsWithIds.put(R.id.textViewCore, 30);
        sViewsWithIds.put(R.id.guideline5, 31);
        sViewsWithIds.put(R.id.guideline7, 32);
        sViewsWithIds.put(R.id.textViewDetail, 33);
        sViewsWithIds.put(R.id.imageButtonBack, 34);
        sViewsWithIds.put(R.id.textView3, 35);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityFitnessTestDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }
    private ActivityFitnessTestDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[15]
            , (android.widget.Button) bindings[22]
            , (androidx.cardview.widget.CardView) bindings[9]
            , (androidx.cardview.widget.CardView) bindings[26]
            , (android.widget.EditText) bindings[13]
            , (android.widget.EditText) bindings[10]
            , (android.widget.EditText) bindings[29]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[3]
            , (androidx.constraintlayout.widget.Guideline) bindings[23]
            , (androidx.constraintlayout.widget.Guideline) bindings[24]
            , (androidx.constraintlayout.widget.Guideline) bindings[25]
            , (androidx.constraintlayout.widget.Guideline) bindings[31]
            , (androidx.constraintlayout.widget.Guideline) bindings[32]
            , (android.widget.ImageButton) bindings[17]
            , (android.widget.ImageButton) bindings[11]
            , (android.widget.ImageButton) bindings[34]
            , (android.widget.ImageButton) bindings[20]
            , (android.widget.ImageButton) bindings[27]
            , (android.widget.ImageButton) bindings[18]
            , (android.widget.ImageButton) bindings[19]
            , (android.widget.ImageButton) bindings[5]
            , (android.widget.ImageButton) bindings[1]
            , (android.widget.ImageButton) bindings[21]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[28]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[35]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[4]
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