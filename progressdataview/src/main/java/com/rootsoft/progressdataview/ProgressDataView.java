package com.rootsoft.progressdataview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * A container view to change between different states: Loading, Content, Empty and Error.
 *
 * Created by Tomas Verhelst.
 */
public class ProgressDataView extends FrameLayout {

    //Constants
    public static final String TAG = ProgressDataView.class.getSimpleName();

    //Attributes
    private boolean inflateCalled;

    @LayoutRes
    private int resourceLoading;

    @LayoutRes
    private int resourceError;

    @LayoutRes
    private int resourceEmpty;

    //Views
    private View contentView;
    private View errorView;
    private View emptyView;
    private View loadingView;

    //Constructors
    public ProgressDataView(final Context context) {
        this(context, null);
    }

    public ProgressDataView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressDataView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressDataView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressDataView, defStyleAttr, defStyleRes);

        resourceLoading = typedArray.getResourceId(R.styleable.ProgressDataView_loadingLayout, R.layout.layout_pdv_default_loading);
        resourceError = typedArray.getResourceId(R.styleable.ProgressDataView_errorLayout, R.layout.layout_pdv_default_error);
        resourceEmpty = typedArray.getResourceId(R.styleable.ProgressDataView_emptyLayout, R.layout.layout_pdv_default_empty);

        typedArray.recycle();

        //Init view
        loadingView = LayoutInflater.from(getContext()).inflate(resourceLoading, this, false);
        errorView = LayoutInflater.from(getContext()).inflate(resourceError, this, false);
        emptyView = LayoutInflater.from(getContext()).inflate(resourceEmpty, this, false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (inflateCalled) {
            return;
        }
        inflateCalled = true;

        RuntimeAssert.assertTrue(getChildCount() == 1);
        contentView = getChildAt(0);

        if (isInEditMode()) {
            final View root = LayoutInflater.from(getContext()).inflate(resourceError, this, false);
            root.setAlpha(0.1f);
            addView(root);
        }
    }

    //Methods
    public void showContent() {
        errorView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);

        if (contentView.getParent() == null) {
            addView(contentView);
        }
        contentView.setVisibility(View.VISIBLE);
    }

    public void showError() {
        contentView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);

        if (errorView.getParent() == null) {
            addView(errorView);
        }
        errorView.setVisibility(View.VISIBLE);
    }

    public void showEmpty() {
        errorView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);

        if (emptyView.getParent() == null) {
            addView(emptyView);
        }
        emptyView.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        errorView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

        if (loadingView.getParent() == null) {
            addView(loadingView);
        }
        loadingView.setVisibility(View.VISIBLE);
    }

    public View getContentView() {
        return contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    public View getErrorView() {
        return errorView;
    }

    public void setErrorView(View errorView) {
        this.errorView = errorView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public View getLoadingView() {
        return loadingView;
    }

    public void setLoadingView(View loadingView) {
        this.loadingView = loadingView;
    }

}

