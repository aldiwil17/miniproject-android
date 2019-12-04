package com.tokopedia.testproject.problems.news.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.tokopedia.testproject.UtilKt;

public class VerticalRecyclerView extends RecyclerView {
    public VerticalRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public VerticalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        addItemDecoration(new VerticalSpaceItemDecoration((int) UtilKt.dpToPx(8f, getContext())));
    }
}
